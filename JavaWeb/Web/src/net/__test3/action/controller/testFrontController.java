package net.__test3.action.controller;

import net.__test3.action.svc.testAction;
import net.__test3.action.svc.testInputAction;

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

        if(command.equals("/Test.ts3"))
        {
            action = (Action) new testAction();
            try
            {
                forward = action.execute(request,response);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(command.equals("/TestInput.ts3"))
        {
            action = (Action) new testInputAction();
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
