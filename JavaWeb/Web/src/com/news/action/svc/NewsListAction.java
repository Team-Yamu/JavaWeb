package com.news.action.svc;

import com.news.action.controller.Action;
import com.news.action.controller.ActionForward;
import com.news.db.dao.NewsDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class NewsListAction implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        var newsDAO = new NewsDAO();
        var forward = new ActionForward();
        try
        {
            List newsList = newsDAO.newsList();

            request.setAttribute("newsList", newsList);

            forward.setRedirect(false);
            forward.setPath("./views/news/newsList.jsp");
            return forward;
        }
        catch (Exception ex)
        {
            System.out.println("NewsListAction 에러: " + ex);
        }
        return null;
    }
}
