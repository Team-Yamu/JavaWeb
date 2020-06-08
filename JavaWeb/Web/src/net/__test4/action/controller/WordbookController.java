package net.__test4.action.controller;

import net.__test4.action.svc.TestDataListSet;
import net.__test4.action.svc.TestDataSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "*.ts4")
public class WordbookController extends HttpServlet
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
            // 단어장리스트 뷰
            case "/testDataSet.ts4":
                action = (Action) new TestDataSet();
                break;
            case "/testDataListSet.ts4":
                action = (Action) new TestDataListSet();
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
