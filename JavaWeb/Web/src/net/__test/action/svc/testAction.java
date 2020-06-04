package net.__test.action.svc;

import net.__test.action.controller.Action;
import net.__test.action.controller.ActionForward;
import net.__test.action.util.consoleCommand;
import net.__test.db.dao.testDao;
import net.__test.db.vo.testBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class testAction implements Action
{
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
    {
        testDao dao = new testDao();
        testBean bean = new testBean();

        consoleCommand cmd = new consoleCommand();

        String word = "hi";
        String command = cmd.inputCommand(" cd D:\\GitHubRepo\\JsonNLTK && d: && python main.py "+word+" -post=http://localhost:8088/Test.ts");
        cmd.execCommand(command);

        bean.setName(word);

        try
        {
            bean = dao.getNameData(bean);
            request.setAttribute("testData",bean.getName());
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

