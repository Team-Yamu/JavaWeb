package com.wordlistpage.action.svc;

import com.wordlistpage.action.controller.Action;
import com.wordlistpage.action.controller.ActionForward;
import com.wordlistpage.db.dao.WordListDAO;
import com.wordlistpage.db.vo.WordBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchWordAction implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        var wordBean = new WordBean();
        var wordListDAO = new WordListDAO();

        try
        {
            wordBean.setWordName(request.getParameter("wordName").toLowerCase());
            String jsonData = wordListDAO.selectSimilarWordName(wordBean);
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(jsonData);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            response.getWriter().write("");
        }
        finally
        {
            response.getWriter().close();
            wordListDAO.con.close();
        }
        return null;
    }
}
