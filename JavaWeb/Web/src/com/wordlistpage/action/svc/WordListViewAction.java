package com.wordlistpage.action.svc;

import com.wordlistpage.action.controller.Action;
import com.wordlistpage.action.controller.ActionForward;
import com.wordlistpage.db.dao.WordListDAO;
import com.wordlistpage.db.vo.WordbookBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class WordListViewAction implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        var wordListDAO = new WordListDAO();
        var wordbookBean = new WordbookBean();

        try
        {
            wordbookBean.setId(Integer.parseInt(request.getParameter("wbId")));
            wordbookBean.setName(request.getParameter("wbName"));
            HttpSession session = request.getSession();
            wordbookBean.setUser_id((String) session.getAttribute("id"));

            List wordBeanList = wordListDAO.searchWordList(wordbookBean);
            if(wordBeanList!=null)
            {
                request.setAttribute("wordList",wordListDAO.getWordList(wordBeanList));
                wordbookBean = wordListDAO.selectNameUserId(wordbookBean);
                request.setAttribute("wordbookName",wordbookBean.getName());
                request.setAttribute("user_id",wordbookBean.getUser_id());
                request.setAttribute("wbId",wordbookBean.getId());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            wordListDAO.con.close();
            ActionForward forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("./views/wordlist/wordList.jsp");
            return forward;
        }
    }
}
