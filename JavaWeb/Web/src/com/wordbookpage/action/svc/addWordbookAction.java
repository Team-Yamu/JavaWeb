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

        response.setCharacterEncoding("utf-8");

        boolean existFlg = false;
        try
        {
            HttpSession session = request.getSession();
            //현재 유저의 아이디정보
            wordbookBean.setUser_id((String) session.getAttribute("id"));

            wordbookBean.setName(request.getParameter("wordbookName"));
            wordbookBean.setInfo(request.getParameter("info"));

            //내 단어장에 같은 이름의 단어장이 존재하는지 확인
            existFlg = wordbookDAO.existWordbookName(wordbookBean);

            if(existFlg)
            {
                wordbookDAO.insertWordbook(wordbookBean);

                //현재 유저의 단어장 리스트를 json으로 반환
                String jsonData = wordbookDAO.selectWordbookListJson(wordbookBean);
                response.getWriter().write(jsonData);
            }
            else
            {
                throw new Exception();
            }
            //단어장 추가
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            response.getWriter().write("");
        }
        finally
        {
            response.getWriter().close();
            wordbookDAO.con.close();
        }
        return null;
    }
}
