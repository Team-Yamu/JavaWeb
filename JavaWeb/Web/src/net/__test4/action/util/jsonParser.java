package net.__test4.action.util;

import com.wordbookpage.db.vo.WordbookBean;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class jsonParser
{
    public static WordbookBean parsedJson(WordbookBean bean,String jsonData)
    {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;

        try
        {
            jsonObject = (JSONObject) parser.parse(jsonData);

            bean.setName((String) jsonObject.get("wordbookName"));
            bean.setInfo((String) jsonObject.get("info"));
            return bean;
        }
        catch (ParseException ex)
        {
            System.out.println("pasedJson 에러 : "+ex);
        }
        return null;
    }
}
