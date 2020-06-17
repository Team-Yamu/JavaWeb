package com.wordbookpage.action.svc;

import com.wordbookpage.db.vo.WordbookBean;
import com.wordbookpage.action.controller.Action;
import com.wordbookpage.action.controller.ActionForward;
import com.wordbookpage.db.dao.WordbookDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class initWordbookListSetAction implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        var wordbookDAO = new WordbookDAO();
        var wordbookBean = new WordbookBean();
        try
        {
            HttpSession session = request.getSession();
            wordbookBean.setUser_id((String) session.getAttribute("id"));

            request.setAttribute("wordbookList",wordbookDAO.selectWordbookListBean(wordbookBean));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            wordbookDAO.con.close();
            ActionForward forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("./views/wordbooklist/wbList.jsp");
            return forward;
        }
    }
}
