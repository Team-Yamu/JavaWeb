package com.loginpage.action.controller;

import com.loginpage.action.svc.InsertUserAction;
import com.loginpage.action.svc.LoingProcessAction;
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

        // 회원가입한 모든 유저의 list를 출력합니다
        if (command.equals("/UserList.login"))
        {
            action = new UserListAction();
            try
            {
                forward = action.execute(request, response);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        // 회원가입을 위한 페이지로 이동합니다
        else if (command.equals("/Insertpage.login"))
        {
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("./views/loginpage/joinForm.jsp");
        }
        // DB에 회원가입한 데이터를 삽입합니다
        else if (command.equals("/Insertuser.login"))
        {
            action = new InsertUserAction();
            try
            {
                forward = action.execute(request, response);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        // 로그인 정보가 맞는지 확인합니다
        else if (command.equals("/loginProcess.login"))
        {
            action = new LoingProcessAction();
            try
            {
                forward = action.execute(request, response);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

        // 이동할 페이지가 있다면
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.doProcess(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.doProcess(request, response);
    }
}
