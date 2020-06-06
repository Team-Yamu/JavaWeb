package com.mainpage.action.svc;

import com.mainpage.action.controller.Action;
import com.mainpage.action.controller.ActionForward;
import com.mainpage.db.dao.MainPageDAO;
import com.mainpage.db.vo.WordbookBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainPageAction implements Action
{
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        MainPageDAO dao = new MainPageDAO();

        try
        {
            List wordbookList = dao.getWordbookBest4();

            request.setAttribute("wordbookList",wordbookList);

            ActionForward forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("./main.jsp");

            return forward;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            dao.con.close();
        }
        finally
        {
            dao.con.close();
        }
        return null;
    }
}
