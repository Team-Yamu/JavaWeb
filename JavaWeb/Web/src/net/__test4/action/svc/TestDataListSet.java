package net.__test4.action.svc;

import net.__test4.action.controller.Action;
import net.__test4.action.controller.ActionForward;

import net.__test4.db.dao.TestDAO;

import net.__test4.db.vo.TestBean;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TestDataListSet implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        var testDAO = new TestDAO();
        try
        {
            request.setAttribute("testList",testDAO.selectTestDataBean());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            testDAO.con.close();
            ActionForward forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("./views/test/testAjax.jsp");
            return forward;
        }
    }
}
