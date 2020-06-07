package com.loginpage.action.controller;

import com.loginpage.action.svc.UserJoinAction;
import com.loginpage.action.svc.UserLoginAction;
import com.loginpage.action.svc.UserListAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "*.login")
public class LoginController extends HttpServlet
{
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String RequestURL = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = RequestURL.substring(contextPath.length());

        ActionForward forward = null;
        Action action = null;

        switch (command)
        {
            // 로그인 페이지로 이동
            case "/login.login":
                forward = new ActionForward(false, "./views/loginpage/loginForm.jsp");
                break;
            // 회원가입 페이지로 이동
            case "/Insertpage.login":
                forward = new ActionForward(false, "./views/loginpage/joinForm.jsp");
                break;
            // 회원가입 Model
            case "/Insertuser.login":
                action = new UserJoinAction();
                break;
            // 로그인 Model
            case "/loginProcess.login":
                action = new UserLoginAction();
                break;
            // 모든 유저의 정보를 출력
            case "/UserList.login":
                action = new UserListAction();
                break;
        }

        try
        {
            // 실행 action이 있다면
            if (action != null)
                forward = action.execute(request, response);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            // 이동 페이지가 있다면
            if (forward != null)
            {
                if (forward.isRedirect())
                {
                    // Redirect 이동: URL주소가 변경되며, request와 response가 공유되지 않습니다
                    response.sendRedirect(forward.getPath());
                }
                else
                {
                    // Forward 이동: URL주소가 유지되며, request와 response가 공유됨
                    var dispatchar = request.getRequestDispatcher(forward.getPath());
                    dispatchar.forward(request, response);
                }
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.doProcess(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.doProcess(request, response);
    }
}
