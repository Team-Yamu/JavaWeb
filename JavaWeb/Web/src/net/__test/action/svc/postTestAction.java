package net.__test.action.svc;

import net.__test.action.controller.Action;
import net.__test.action.controller.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import java.io.*;

import net.__test.db.dao.testDao;
import net.__test.db.vo.testBean;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class postTestAction implements Action
{
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
    {
        InputStream in= null;
        BufferedReader reader= null;

        testDao dao = new testDao();
        testBean bean = new testBean();

        try
        {
            in = request.getInputStream();

            reader = new BufferedReader(new InputStreamReader(in,"utf-8"));

            JSONParser parser = new JSONParser();

            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            String name = (String) jsonObject.get("target");
            System.out.println(name);

            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.println("성공");
            out.close();

            // error
            bean.setName(name);
            dao.setInsertTest(bean);

            return null;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}