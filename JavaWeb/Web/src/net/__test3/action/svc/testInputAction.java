package net.__test3.action.svc;

import net.__test3.action.controller.Action;
import net.__test3.action.controller.ActionForward;

import net.__test3.action.util.consoleCommand;
import net.__test3.action.util.jsonData;
import net.__test3.action.util.jsonParser;

import net.__test3.db.dao.testDao;
import net.__test3.db.vo.testBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class testInputAction implements Action
{
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        testDao dao = new testDao();
        testBean bean = new testBean();
        try
        {
            List wordList = dao.getSelectAllWord();

            request.setAttribute("wordList",wordList);

            ActionForward forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("./views/test/test_input_view2.jsp");

        return forward;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            dao.con.close();
        }
        finally
        {
            dao.con.close();
        }
        return null;
    }
}