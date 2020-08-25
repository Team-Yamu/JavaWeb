package com.newspage.action.svc;

import com.newspage.action.controller.Action;
import com.newspage.action.controller.ActionForward;
import com.newspage.db.dao.NewsDAO;
import com.newspage.db.vo.NewsBean;
import com.wordlistpage.action.util.ConsoleCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchNewsAction implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        var newsBean = new NewsBean();
        var newsDAO = new NewsDAO();

        boolean insertFlg = false;

        newsBean.setUrl(request.getParameter("searchNews"));

        try {
            ConsoleCommand cmd = new ConsoleCommand();
            // String command = "python3 ./bin/summary/main.py " + newsBean.getUrl() + " -all";
            String command = cmd.inputCommand("cd C:\\Resources\\JavaWeb\\Python Features\\summary && python main.py " + newsBean.getUrl() + " -all");
            String result = cmd.execCommand(command);
            newsBean.setJson_data(result);

            insertFlg = newsDAO.newsInsert(newsBean);

            if(insertFlg)
            {
                String jsonData = newsDAO.newsJSONresult(newsBean);
                if(jsonData != null)
                {
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().write(jsonData);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            newsDAO.dbClose();
        }
        return null;
    }
}
