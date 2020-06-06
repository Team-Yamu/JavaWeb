package com.loginpage.action.svc;

import com.loginpage.action.controller.Action;
import com.loginpage.action.controller.ActionForward;
import com.loginpage.db.dao.UserDAO;
import com.loginpage.db.vo.UserBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoingProcessAction implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        var userdao = new UserDAO();
        var userBean = new UserBean();
        var forward = new ActionForward();
        int result;

        try
        {
            userBean.setId(request.getParameter("id"));
            userBean.setPassword(request.getParameter("password"));
            result = userdao.isUser(userBean);

            if (result == 1)
                System.out.println("로그인 성공");
            else if (result == 0)
                System.out.println("비밀번호 틀림");
            else if (result == -1)
                System.out.println("아이디가 없습니다");
        }
        catch (Exception ex)
        {
            System.out.println("LoingProcessAction 실패" + ex);
        }
        finally
        {
            System.out.println("로그인 된거야");

            forward.setRedirect(true);
            forward.setPath("./views/loginpage/loginpage.jsp");
            return forward;
        }
    }
}
