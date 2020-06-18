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
        newsBean.setUrl(request.getParameter("searchNews"));

        try {
            ConsoleCommand cmd = new ConsoleCommand();
            String command = "python ./bin/summary/main.py " + newsBean.getUrl() + " -all";
            String result = cmd.execCommand(command);
            newsBean.setJson_data(result);
            System.out.println(result);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //newsBean.con.close();
        }
        return null;
    }
}
