package library.wlt.com.openlibrary.utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtil
{
	private static int baseSpace = 4;
	
	public static String getFormatJson(String json)
	{
		try 
		{
			return formatJson(json, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String formatJson(String json, int space) throws Exception
	{
		String prefix1 = getPrefixBySpace(space);
		String prefix2 = null;
		StringBuilder builder = new StringBuilder();
		builder.append(prefix1).append("{").append("\n");
		JSONObject jsonObject = new JSONObject(json);
		JSONArray nameArray = jsonObject.names();
		if(null != nameArray)
		{
			for (int i = 0; i < nameArray.length(); i++) 
			{
				String name = nameArray.get(i).toString();
				Object obj = jsonObject.get(name);
				name = "\""+name+"\":";
				prefix2 = getPrefixBySpace(space+baseSpace);
				if(obj instanceof JSONObject)
				{
					builder.append(prefix2).append(name).append("\n");
					builder.append(formatJson(obj.toString(), (space+baseSpace+1))).append("\n");
				}
				else if(obj instanceof JSONArray)
				{
					JSONArray jsonArray = (JSONArray) obj;
					builder.append(prefix2).append(name).append("\n");
					builder.append(formatJsonArray(jsonArray, (space+baseSpace+1)));
				}
				else
				{
					builder.append(prefix2).append(name);
					builder.append(obj).append("\n");
				}
			}
		}
		else
		{
			builder.append("\n");
		}
		builder.append(prefix1).append("}");
		return builder.toString();
	}
	
	public static String formatJsonArray(JSONArray jArray, int space) throws Exception
	{
		if(null == jArray) return null;
		String prefix1 = getPrefixBySpace(space);
		String prefix2 = null;
		StringBuilder builder = new StringBuilder();
		builder.append(prefix1).append("[").append("\n");
		for (int i = 0; i < jArray.length(); i++) 
		{
			Object obj = jArray.get(i);
			if(obj instanceof JSONObject)
			{
				builder.append(formatJson(obj.toString(), (space + baseSpace +1))).append("\n");
			}
			else if(obj instanceof JSONArray)
			{
				JSONArray jsonArray = (JSONArray) obj;
				builder.append(formatJsonArray(jsonArray, space + baseSpace));
			}
			else
			{
				builder.append(getPrefixBySpace(space + baseSpace));
				if(obj instanceof String)
				{
					builder.append("\"").append(obj).append("\"");
				}
				else
				{
					builder.append(obj);
				}
				if(i != jArray.length()-1)
				{
					builder.append(",");
				}
				builder.append("\n");
			}
		}
		builder.append(prefix1).append("]").append("\n");
		return builder.toString();
	}
	
	public static String getPrefixBySpace(int spaceCount)
	{
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < spaceCount; i++) 
		{
			builder.append(" ");
		}
		return builder.toString();
	}
	
}
