package com.news.action.svc;

import com.news.action.controller.Action;
import com.news.action.controller.ActionForward;
import com.news.action.util.ConsoleCommand;
import com.news.db.dao.NewsDAO;
import com.news.db.vo.NewsBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class NewsInsertAction implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        var newsDAO = new NewsDAO();
        var news = new NewsBean();
        var forward = new ActionForward();
        boolean resultValue = false;

        try
        {
            // getAttribute이 아닌 getParameter임
            String URL = (String) request.getParameter("URL");

            // TODO: 중복되는 URL이 있는지 체크해야합니다
            if (true)
            {
                System.out.println("URL 검색 시작함: " + URL);

                var cmd = new ConsoleCommand();
                // CMD 경로 셋팅: 띄어쓰기 주의
                // EC2 Conatiner 의 경로인 /bin/bin/summary 로 변경 바람.
                String command = cmd.inputCommand("cd C:\\Resources\\JavaWeb\\Python Features\\summary && C: && python main.py " + URL + " -all");
                // CMD 실행, 결과 저장
                String result = cmd.execCommand(command);
                // CMD 결과 출력
                System.out.println(result);
                // CMD 결과 VO에 저장
                news.setHash(URL);
                news.setJson_data(result);
                // DB insert
                resultValue = newsDAO.newsInsert(news);

                // TODO: 저장작업은 문제없이 동작합니다, primary key overlap 부분과 alert box 부분 예외처리
                // TODO: HTML 이미지 이쁘게 출력하는거
                // TODO: SQL 이쁘게 뽑아내는 쿼리 작성
                if (resultValue == true)
                {
                    System.out.println("뉴스 삽입 성공");
                    forward.setRedirect(true);
                    forward.setPath("/newsparsinglist.news");
                    return forward;
                }
                else
                {
                    System.out.println("뉴스 삽입 실패");

                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out = response.getWriter();
                    out.println("<script>");
                    out.println("alert('뉴스 삽입 실패. 관리자에게 문의해주세요.');");
                    out.println("location.href='/newsparsinglist.news';");
                    out.println("</script>");
                    out.close();
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println("NewsInsertAction 에러: " + ex);

        }
        return null;
    }
}
