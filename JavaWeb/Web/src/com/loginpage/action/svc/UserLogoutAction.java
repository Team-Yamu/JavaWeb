package com.loginpage.action.svc;

import com.loginpage.action.controller.Action;
import com.loginpage.action.controller.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLogoutAction implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        var forward = new ActionForward();
        HttpSession session = request.getSession();

        try
        {
            // Session id값 삭제
            session.removeAttribute("id");

            System.out.println("로그아웃 성공");

            forward.setRedirect(true);
            forward.setPath("./login.login");
            return forward;
        }
        catch (Exception ex)
        {
            System.out.println("UserLogoutAction에러: " + ex);
        }
        return null;
    }
}
