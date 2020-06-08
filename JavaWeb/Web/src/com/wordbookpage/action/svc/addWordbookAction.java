package com.wordbookpage.action.svc;

import com.wordbookpage.action.controller.Action;
import com.wordbookpage.action.controller.ActionForward;
import com.wordbookpage.db.dao.WordbookDAO;
import com.wordbookpage.db.vo.WordbookBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class addWordbookAction implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        var wordbookDAO = new WordbookDAO();
        var wordbookBean = new WordbookBean();

        boolean testFlg = false;
        try
        {
            HttpSession session = request.getSession();
            //현재 유저의 아이디정보
            wordbookBean.setUser_id((String) session.getAttribute("id"));

            wordbookBean.setName(request.getParameter("wordbookName"));
            wordbookBean.setInfo(request.getParameter("info"));

            //단어장 추가
            wordbookDAO.insertWordbook(wordbookBean);

            //현재 유저의 단어장 리스트를 json으로 반환
            String jsonData = wordbookDAO.selectWordbookListJson(wordbookBean);
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(jsonData);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            response.getWriter().close();
            wordbookDAO.con.close();
        }
        return null;
    }
}
