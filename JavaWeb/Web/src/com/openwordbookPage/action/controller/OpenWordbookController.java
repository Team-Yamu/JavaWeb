package com.openwordbookPage.action.controller;

import com.openwordbookPage.action.svc.WordbookListAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "*.ob")
public class OpenWordbookController extends HttpServlet
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
            case "/wordbookList.ob":
                action = (Action) new WordbookListAction();
                break;

            case "/openWordbook.ob":
                forward = new ActionForward(false, "./views/openWordbook/openWordbook.jsp");
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
