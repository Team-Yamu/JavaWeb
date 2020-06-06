package com.loginpage.action.svc;

import com.loginpage.action.controller.Action;
import com.loginpage.action.controller.ActionForward;
import com.loginpage.db.dao.UserDAO;
import com.loginpage.db.vo.UserBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertUserAction implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        var userdao = new UserDAO();
        var userBean = new UserBean();
        var forward = new ActionForward();
        boolean result = false;

        try
        {
            userBean.setId(request.getParameter("id"));
            userBean.setPassword(request.getParameter("password"));
            userBean.setName(request.getParameter("name"));

            result = userdao.userInsert(userBean);

            if (result == false)
            {
                System.out.println("회원가입 실패");
                return null;
            }
            System.out.println("회원가입 성공");
        }
        catch (Exception ex)
        {
            System.out.println("InsertUserAction 실패" + ex);
        }
        finally
        {
            forward.setRedirect(true);
            forward.setPath("./views/loginpage/loginpage.jsp");
            return forward;
        }
    }
}
