package com.loginpage.action.svc;

import com.loginpage.action.controller.Action;
import com.loginpage.action.controller.ActionForward;
import com.loginpage.db.dao.UserDAO;
import com.loginpage.db.vo.UserBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class UserJoinAction implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        var userDAO = new UserDAO();
        var user = new UserBean();
        var forward = new ActionForward();
        boolean result = false;

        try
        {
            user.setId(request.getParameter("id"));
            user.setPassword(request.getParameter("password"));
            user.setName(request.getParameter("name"));

            if(user.getId().equals("")||user.getPassword().equals("")||user.getName().equals(""))
            {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>");
                out.println("alert('모든 정보를 입력해 주세요');");
                out.println("location.href='"+request.getContextPath()+"/Insertpage.login';");
                out.println("</script>");
                out.close();
                return null;
            }

            result = userDAO.userInsert(user);

            if (result == true)
            {
                System.out.println("회원가입 성공");

                forward.setRedirect(true);
                forward.setPath("./login.login");
                return forward;
            }
            else if (result == false)
            {
                System.out.println("회원가입 실패");

                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>");
                out.println("alert('회원가입에 실패했습니다. Log를 참조해주세요.');");
                out.println("location.href='"+request.getContextPath()+"/Insertpage.login';");
                out.println("</script>");
                out.close();

                return null;
            }
        }
        catch (Exception ex)
        {
            System.out.println("UserJoinAction 실패: " + ex.getMessage());

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('중복되는 아이디가 존재합니다.');");
            out.println("location.href='"+request.getContextPath()+"/login.login';");
            out.println("</script>");
            out.close();
        }
        return null;
    }
}
