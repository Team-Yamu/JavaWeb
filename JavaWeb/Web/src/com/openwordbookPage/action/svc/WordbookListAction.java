package com.openwordbookPage.action.svc;

import com.openwordbookPage.action.controller.Action;
import com.openwordbookPage.action.controller.ActionForward;
import com.openwordbookPage.db.dao.OpenWordbookDAO;
import com.openwordbookPage.db.vo.WordbookBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class WordbookListAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        var openWordbookDAO = new OpenWordbookDAO();
        var wordbookBean = new WordbookBean();

        try
        {
            String jsonData = openWordbookDAO.selectWordbookListJson(Integer.parseInt(request.getParameter("count")),request.getParameter("searchBook"));
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(jsonData);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            openWordbookDAO.con.close();
        }
        return null;
    }
}
