package net.__test.action.controller;

import net.__test.action.svc.postTestAction;
import net.__test.action.svc.testAction;
import net.__test.action.util.consoleCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class testFrontController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String RequestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = RequestURI.substring(contextPath.length());

        ActionForward forward = null;
        Action action = null;

        if(command.equals("/TestPost.ts"))
        {
            action = new testAction();
            try
            {
                forward = action.execute(request,response);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(command.equals("/Test.ts"))
        {
            action = new postTestAction();
            try
            {
                forward = action.execute(request,response);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        if(forward != null)
        {
            if(forward.isRedirect())
            {
                response.sendRedirect(forward.getPath());
            }
            else
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
                dispatcher.forward(request,response);
            }
        }
    }

    protected  void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        doProcess(request,response);
    }

    protected  void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        doProcess(request,response);
    }
}
