package net.__test3.action.util;

import net.__test3.db.vo.testBean;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Iterator;

public class jsonParser
{
    public static jsonData parsedJson(testBean bean, jsonData reJsonData)
    {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;

        try
        {
            jsonObject = (JSONObject) parser.parse(bean.getJson_data());

            reJsonData.name = (String) jsonObject.get("target");
            JSONArray target_trans = (JSONArray) jsonObject.get("target_trans");

            Iterator<String> iterator = target_trans.iterator();
            reJsonData.target_trans_word = new ArrayList();

            while (iterator.hasNext())
            {
                reJsonData.target_trans_word.add(iterator.next());
            }
            return reJsonData;
        }
        catch (ParseException ex)
        {
            System.out.println("pasedJson 에러 : "+ex);
        }
        return null;
    }
}
