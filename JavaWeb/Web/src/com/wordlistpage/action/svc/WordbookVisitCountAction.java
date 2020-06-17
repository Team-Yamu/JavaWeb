package com.wordlistpage.action.svc;

import com.wordlistpage.action.controller.Action;
import com.wordlistpage.action.controller.ActionForward;
import com.wordlistpage.db.dao.WordListDAO;
import com.wordlistpage.db.vo.WordbookBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WordbookVisitCountAction implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        var wordbookBean = new WordbookBean();
        var wordListDAO = new WordListDAO();
        try
        {
            wordbookBean.setId(Integer.parseInt(request.getParameter("wbId")));
            wordListDAO.visitCount(wordbookBean);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            wordListDAO.con.close();
            return null;
        }
    }
}