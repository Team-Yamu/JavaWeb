package com.loginpage.action.svc;

import com.loginpage.action.controller.Action;
import com.loginpage.action.controller.ActionForward;
import com.loginpage.db.dao.UserDAO;
import com.loginpage.db.vo.UserBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class UserLoginAction implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        var userDAO = new UserDAO();
        var user = new UserBean();
        var forward = new ActionForward();
        int result;

        HttpSession session = request.getSession();

        try
        {
            user.setId(request.getParameter("id"));
            user.setPassword(request.getParameter("password"));
            result = userDAO.isUser(user);

            if (result == 1)
            {
                System.out.println("로그인 성공");

                // Session 등록
                session.setAttribute("id", user.getId());

                forward.setRedirect(true);
                forward.setPath("/");
                return forward;
            }
            else if (result == 0)
            {
                System.out.println("비밀번호 틀림");

                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>");
                out.println("alert('비밀번호가 일치하지 않습니다.');");
                out.println("location.href='/login.login';");
                out.println("</script>");
                out.close();

                return null;
            }
            else if (result == -1)
            {
                System.out.println("아이디가 없습니다");

                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>");
                out.println("alert('아이디가 존재하지 않습니다.');");
                out.println("location.href='/login.login';");
                out.println("</script>");
                out.close();

                return null;
            }
        }
        catch (Exception ex)
        {
            System.out.println("UserLoginAction 실패: " + ex);
        }
        return null;
    }
}
