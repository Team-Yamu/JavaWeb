package net.__test2.action.svc;

import net.__test2.action.controller.Action;
import net.__test2.action.controller.ActionForward;
import net.__test2.action.util.consoleCommand;
import net.__test2.db.dao.testDao;
import net.__test2.db.vo.testBean;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class testAction implements Action
{
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ParseException
    {
        testDao dao = new testDao();
        testBean bean = new testBean();

        consoleCommand cmd = new consoleCommand();

        String word = (String) request.getParameter("word");

        String command = cmd.inputCommand(" cd D:\\GitHubRepo\\YAMU2020\\JavaWeb\\Python Features\\nltk && d: && python main.py "+word);
        BufferedReader resultBuffer = cmd.execCommand(command);

        try
        {
            JSONParser parser = new JSONParser();

            JSONObject jsonObject = (JSONObject) parser.parse(resultBuffer);
            String name = (String) jsonObject.get("target");

            JSONArray target_trans = (JSONArray) jsonObject.get("target_trans");
            Iterator<String> iterator = target_trans.iterator();
            List target_trans_word = new ArrayList();
            while (iterator.hasNext())
            {
                target_trans_word.add(iterator.next());
            }

            String mean = "";

            for(int i = 0; i<target_trans_word.size();i++)
            {
                mean += (String) target_trans_word.get(i);
                if(target_trans_word.size()-1 != i)
                {
                     mean += ",";
                }
            }

            bean.setName(name);
            bean.setMean(mean);

            dao.setInsertNameAndMean(bean);

            request.setAttribute("testData",name);
            request.setAttribute("testList",target_trans_word);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("./views/test/test_result_view.jsp");

        return forward;
    }
}

//package net.__test.action.svc;
//
//        import net.__test.action.controller.Action;
//        import net.__test.action.controller.ActionForward;
//        import net.__test.action.util.consoleCommand;
//
//        import javax.servlet.http.HttpServletRequest;
//        import javax.servlet.http.HttpServletResponse;
//        import java.io.IOException;
//        import java.sql.SQLException;
//
//public class testAction implements Action
//{
//    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
//    {
//        consoleCommand cmd = new consoleCommand();
//
//        String command = cmd.inputCommand(" cd D:\\GitHubRepo\\JsonNLTK && d: && dir");
//        String result = cmd.execCommand(command);
//
//        cmd = new consoleCommand();
//        String command2 = cmd.inputCommand(" dir");
//        String result2 = cmd.execCommand(command2);
//
//        System.out.println(result);
//        System.out.println(result2);
//
//        request.setAttribute("testData",result);
//
//        ActionForward forward = new ActionForward();
//        forward.setRedirect(false);
//        forward.setPath("/views/test/test_result_view.jsp");
//
//        return forward;
//    }
//}

