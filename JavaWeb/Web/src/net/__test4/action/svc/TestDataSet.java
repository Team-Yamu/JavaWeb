package net.__test4.action.svc;

import net.__test4.action.controller.Action;
import net.__test4.action.controller.ActionForward;

import net.__test4.db.dao.TestDAO;

import net.__test4.db.vo.TestBean;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TestDataSet implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        var testDAO = new TestDAO();
        var testBean = new TestBean();

        boolean testFlg = false;
        try
        {
            testBean.setName(request.getParameter("name"));
            testBean.setAge(request.getParameter("age"));

            testDAO.insertTestData(testBean);

            response.setCharacterEncoding("utf-8");

            String jsonData = testDAO.selectTestData();
            response.getWriter().write(jsonData);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            response.getWriter().close();
            testDAO.con.close();
        }
        return null;
    }
}
