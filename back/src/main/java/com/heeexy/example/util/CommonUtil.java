package com.heeexy.example.util;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.config.exception.CommonJsonException;
import com.heeexy.example.dao.PolicyDao;
import com.heeexy.example.pojo.PolicyData;
import com.heeexy.example.util.constants.Constants;
import com.heeexy.example.util.constants.ErrorEnum;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

/**
 * @author: hxy
 * @description: 本后台接口系统常用的json工具类
 * @date: 2017/10/24 10:12
 */
public class CommonUtil {

	//各个集团首字母缩写
	private static final String[] CITY = new String[]{"HQ","SZ","BJ","CD","SH","WH","TJ","CQ","NJ"};
    private static final int CITYSTARTINDEX = 0;
	private static final int CITYENDINDEX = 8;
	//政策的类别集合
	private static final String[] CATEGORY = new String[]{"人力","财税","行业","新基建","安全环保","其他"};

	private static final int LEVELSTARTINDEX = 1;
	private static final int LEVELENDINDEX = 4;

	//12个月份
	private static final String[] MONTH = new String[]{"Jan","Feb","Mar","Apr","May","June","July","Aug","Sept","OCT","Nov","Dece"};
	private static final int MONTHNUM = 12;

	private static final String[] DEPTNAME = new String[]{"本部", "深圳", "北京", "成都", "上海","武汉", "天津", "重庆", "南京"};
	private static final int DEPTNUM = 9;
	private static final String LINECHART = "line";

	/**
	 * 返回一个info为空对象的成功消息的json
	 */
	public static JSONObject successJson() {
		return successJson(new JSONObject());
	}

	/**
	 * 返回一个返回码为100的json
	 */
	public static JSONObject successJson(Object info) {
		JSONObject resultJson = new JSONObject();
		resultJson.put("code", Constants.SUCCESS_CODE);
		resultJson.put("msg", Constants.SUCCESS_MSG);
		resultJson.put("info", info);
		return resultJson;
	}

	/**
	 * 返回错误信息JSON
	 */
	public static JSONObject errorJson(ErrorEnum errorEnum) {
		JSONObject resultJson = new JSONObject();
		resultJson.put("code", errorEnum.getErrorCode());
		resultJson.put("msg", errorEnum.getErrorMsg());
		resultJson.put("info", new JSONObject());
		return resultJson;
	}

	/**
	 * 查询分页结果后的封装工具方法
	 *
	 * @param requestJson 请求参数json,此json在之前调用fillPageParam 方法时,已经将pageRow放入
	 * @param list        查询分页对象list
	 * @param totalCount  查询出记录的总条数
	 */
	public static JSONObject successPage(final JSONObject requestJson, List<JSONObject> list, int totalCount) {
		int pageRow = requestJson.getIntValue("pageRow");
		int totalPage = getPageCounts(pageRow, totalCount);
		JSONObject result = successJson();
		JSONObject info = new JSONObject();
		info.put("list", list);
		info.put("totalCount", totalCount);
		info.put("totalPage", totalPage);
		result.put("info", info);
		return result;
	}

	/**
	 * 查询分页结果后的封装工具方法
	 *
	 * @param list 查询分页对象list
	 */
	public static JSONObject successPage(List<JSONObject> list) {
		JSONObject result = successJson();
		JSONObject info = new JSONObject();
		info.put("list", list);
		result.put("info", info);
		return result;
	}

	/**
	 * @description:
	 * @param:  * @param list
	 * @return: com.alibaba.fastjson.JSONObject
	 * @author Huangzhenqiang
	 * @date: 2020/11/18 11:53
	 */
	public static JSONObject successPage(JSONObject item) {
		JSONObject result = successJson();
		JSONObject info = new JSONObject();
		info.put("item", item);
		result.put("info", info);
		return result;
	}

	/**
	 * 获取总页数
	 *
	 * @param pageRow   每页行数
	 * @param itemCount 结果的总条数
	 */
	private static int getPageCounts(int pageRow, int itemCount) {
		if (itemCount == 0) {
			return 1;
		}
		return itemCount % pageRow > 0 ?
				itemCount / pageRow + 1 :
				itemCount / pageRow;
	}

	/**
	 * 将request参数值转为json
	 */
	public static JSONObject request2Json(HttpServletRequest request) {
		JSONObject requestJson = new JSONObject();
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String[] pv = request.getParameterValues(paramName);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < pv.length; i++) {
				if (pv[i].length() > 0) {
					if (i > 0) {
						sb.append(",");
					}
					sb.append(pv[i]);
				}
			}
			requestJson.put(paramName, sb.toString());
		}
		return requestJson;
	}

	/**
	 * 将request转JSON
	 * 并且验证非空字段
	 */
	public static JSONObject convert2JsonAndCheckRequiredColumns(HttpServletRequest request, String requiredColumns) {
		JSONObject jsonObject = request2Json(request);
		hasAllRequired(jsonObject, requiredColumns);
		return jsonObject;
	}

	/**
	 * 验证是否含有全部必填字段
	 *
	 * @param requiredColumns 必填的参数字段名称 逗号隔开 比如"userId,name,telephone"
	 */
	public static void hasAllRequired(final JSONObject jsonObject, String requiredColumns) {
		if (!StringTools.isNullOrEmpty(requiredColumns)) {
			//验证字段非空
			String[] columns = requiredColumns.split(",");
			String missCol = "";
			for (String column : columns) {
				Object val = jsonObject.get(column.trim());
				if (StringTools.isNullOrEmpty(val)) {
					missCol += column + "  ";
				}
			}
			if (!StringTools.isNullOrEmpty(missCol)) {
				jsonObject.clear();
				jsonObject.put("code", ErrorEnum.E_90003.getErrorCode());
				jsonObject.put("msg", "缺少必填参数:" + missCol.trim());
				jsonObject.put("info", new JSONObject());
				throw new CommonJsonException(jsonObject);
			}
		}
	}

	/**
	 * 在分页查询之前,为查询条件里加上分页参数
	 *
	 * @param paramObject    查询条件json
	 * @param defaultPageRow 默认的每页条数,即前端不传pageRow参数时的每页条数
	 */
	private static void fillPageParam(final JSONObject paramObject, int defaultPageRow) {
		int pageNum = paramObject.getIntValue("pageNum");
		pageNum = pageNum == 0 ? 1 : pageNum;
		int pageRow = paramObject.getIntValue("pageRow");
		pageRow = pageRow == 0 ? defaultPageRow : pageRow;
		paramObject.put("offSet", (pageNum - 1) * pageRow);
		paramObject.put("pageRow", pageRow);
		paramObject.put("pageNum", pageNum);
		//删除此参数,防止前端传了这个参数,pageHelper分页插件检测到之后,拦截导致SQL错误
		paramObject.remove("pageSize");
	}

	public static void processGetPolicyParam(final JSONObject paramObject)
	{
		//特殊处理：显示全部应用清单，即除本部外所有政策记录
		String values = paramObject.getString("dept");
		if( null == values || values.equals(""))
		{
			paramObject.remove("dept");
		}
		else if(values.equals("9"))
		{
			paramObject.remove("dept");
			paramObject.put("NoDept", "0");
		}
		processParamNull(paramObject,"id");
		processParamNull(paramObject,"policyLevel");
		processParamNull(paramObject,"minRevenue");
		processParamNull(paramObject,"maxRevenue");
		processParamNull(paramObject,"collectTime");
		processParamNull(paramObject,"policyLevel");
		processParamNull(paramObject,"category");
		processParamLike(paramObject,"policyTitle");
		processParamLike(paramObject,"schedule");
	}

	/**
	 * 分页查询之前的处理参数
	 * 没有传pageRow参数时,默认每页10条.
	 */
	public static void fillPageParam(final JSONObject paramObject) {
		fillPageParam(paramObject, 10);
	}

	/**
	 * @description: 判断参数是否为空，为空移除
	 * @param:  * @param paramObject
	 * @param key
	 * @return: void
	 * @author Huangzhenqiang
	 * @date: 2020/11/17 15:11
	 */
	public static void processParamNull(final JSONObject paramObject,String key)
	{
		String values = paramObject.getString(key);
		if( null == values || values.equals(""))
		{
			paramObject.remove(key);
		}
	}

	/**
	 * @description: 判断参数是否为空，为空移除，不为空进行模糊搜索
	 * @param:  * @param paramObject
	 * @param key
	 * @return: void
	 * @author Huangzhenqiang
	 * @date: 2020/11/17 15:11
	 */
	public static void processParamLike(final JSONObject paramObject,String key)
	{
		String values = paramObject.getString(key);
		if( null == values || values.equals(""))
		{
			paramObject.remove(key);
		}
		else
		{
			paramObject.put(key, "%"+values+"%");
		}
	}

	/**
	 * @description: 生成政策编号
	 * @param:  * @param dept
	 * @param policyDao
	 * @return: java.lang.String
	 * @author Huangzhenqiang
	 * @date: 2020/11/17 15:10
	 */
	public static String getPolicyId(int dept, PolicyDao policyDao) {

		String city = CITY[dept];

		//获取今天日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String now = sdf.format(new Date());

		int count = getPolicyCount(dept,policyDao);
		String countStr = String.format("%03d",count);

		String policyId = city+now+countStr;
		return policyId;
	}

	/**
	 * @description: 生成政策编号
	 * @param:  * @param dept
 	* @param count
 * @param policyDao
	 * @return: java.lang.String
	 * @author Huangzhenqiang
	 * @date: 2020/11/17 15:10
	 */
	public static String getPolicyId(int dept, int count, PolicyDao policyDao) {

		String city = CITY[dept];

		//获取今天日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String now = sdf.format(new Date());

		String countStr = String.format("%03d",count);

		String policyId = city+now+countStr;
		return policyId;
	}

	/**
	 * @description: 获取今天当前欢乐谷所发布的政策数量，从而获取编号
	 * @param:  * @param dept
	 * @param policyDao
	 * @return: int
	 * @author Huangzhenqiang
	 * @date: 2020/11/17 15:10
	 */
	public static int getPolicyCount(int dept, PolicyDao policyDao) {
		//获取今天日期
		SimpleDateFormat sdfCheck = new SimpleDateFormat("yyyy-MM-dd");
		String nowCheck = sdfCheck.format(new Date());

		int count = policyDao.countPolicyCityToday(dept,nowCheck) + 1;

		return count;
	}

	/**
	 * @description: 将后端查询到的数据封装成数组
	 * @param:  * @param jsonObject
	 * @return: int[]
	 * @author Huangzhenqiang
	 * @date: 2020/11/17 15:09
	 */
	public static BigDecimal[] getReleaseNumEachMonth(final JSONObject jsonObject)
	{
		BigDecimal[] res = new BigDecimal[MONTHNUM];
		for(int i = 0; i < MONTHNUM; i++)
		{
			res[i] = jsonObject.getBigDecimal(MONTH[i]);
		}
		return res;
	}

	/**
	 * @description: 将后端查询到的数据封装成折线图所需要的数据格式
	 * @param:  * @param null
	 * @return:
	 * @author Huangzhenqiang
	 * @date: 2020/11/17 15:02
	 */
	public static List<JSONObject> getLineChartMonthData(List<JSONObject> list,String stack)
	{
		List<JSONObject> result = new ArrayList<>();
		int index = 0;
		for(int i = 0; i < DEPTNUM ; i++)
		{
			JSONObject cur = new JSONObject();
			cur.put("name",DEPTNAME[i]);
			cur.put("type",LINECHART);
			cur.put("stack",stack);
			if(index < list.size() && list.get(index).getIntValue("dept") == i )
			{
				cur.put("data",CommonUtil.getReleaseNumEachMonth(list.get(index)));
				index++;
			}
			else
			{
				cur.put("data",new int[12]);
			}
			result.add(cur);
		}
		return result;
	}

	public static List<JSONObject> getLineChartYearData(JSONObject requestJson,List<JSONObject> list,String stack,String type)
	{
		List<JSONObject> result = new ArrayList<>();
		int startTime = requestJson.getIntValue("startTime");
		int endTime = requestJson.getIntValue("endTime");
		int subTime = endTime-startTime+1;
		BigDecimal[][] allRevenue = new BigDecimal[DEPTNUM][subTime];
		for(int i = 0; i < DEPTNUM ; i++)
		{
			JSONObject cur = new JSONObject();
			cur.put("name",DEPTNAME[i]);
			cur.put("type",LINECHART);
			cur.put("stack",stack);
			result.add(cur);
			for(int j = 0; j < subTime; j++)
			{
				allRevenue[i][j] = new BigDecimal(0);
			}
		}
		for(int i = 0; i < list.size(); i++)
		{
			JSONObject cur = list.get(i);
			int curDept = cur.getIntValue("dept");
			int curYear = cur.getIntValue("year");
			BigDecimal curType = cur.getBigDecimal(type);
			allRevenue[curDept][curYear-startTime] = curType;
		}
		for(int i = 0; i < DEPTNUM ; i++)
		{
			result.get(i).put("data",allRevenue[i]);
		}
		return result;
	}

	/** 
	 * @description: 对批量添加的政策记录的4个属性：dept level assist RevenueWay进行检测，查看该属性是否符合输入要求：
	 * @param:  * @param policy 
	 * @return: boolean 
	 * @author Huangzhenqiang
	 * @date: 2020/12/1 15:39
	 */
	public static boolean CheckedPolicyParams(PolicyData policy) {
		if(policy.getDept() == -1 || policy.getPolicyLevel() == -1 || policy.getAssist() == -1 || policy.getRevenueWay() == -1)
		{
			return false;
		}
		return true;
	}
}
