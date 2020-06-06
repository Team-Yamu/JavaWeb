package com.loginpage.action.svc;

import com.loginpage.action.controller.Action;
import com.loginpage.action.controller.ActionForward;

import com.loginpage.db.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserListAction implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        var userdao = new UserDAO();
        try
        {
            List userList = userdao.getAllUserList();

            request.setAttribute("userList", userList);
        }
        catch (Exception ex)
        {
            System.out.println("UserListAction 에러: " + ex);
        }
        finally
        {
            // userdao.con.close();
            var forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("./views/loginpage/userInfo.jsp");

            return forward;
        }
    }
}
