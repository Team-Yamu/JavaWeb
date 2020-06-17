package com.news.action.controller;

import com.loginpage.action.svc.UserJoinAction;
import com.loginpage.action.svc.UserListAction;
import com.loginpage.action.svc.UserLoginAction;
import com.loginpage.action.svc.UserLogoutAction;
import com.news.action.svc.NewsInsertAction;
import com.news.action.svc.NewsListAction;
import com.news.action.svc.NewsParsingListAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "*.news")
public class NewsController extends HttpServlet
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
            // 뉴스의 목록을 출력합니다: 레거시 코드 입니다
            // case "/newslist.news":
            // action = new NewsListAction();
            // break;
            // 파싱된 뉴스의 목록을 출력합니다
            case "/newsparsinglist.news":
                action = new NewsParsingListAction();
                break;
            // URL로 뉴스를 추가합니다
            case "/newsinsert.news":
                action = new NewsInsertAction();
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
