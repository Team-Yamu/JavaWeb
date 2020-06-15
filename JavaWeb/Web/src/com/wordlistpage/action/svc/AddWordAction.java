package com.wordlistpage.action.svc;

import com.wordlistpage.action.controller.Action;
import com.wordlistpage.action.controller.ActionForward;
import com.wordlistpage.action.util.ConsoleCommand;
import com.wordlistpage.db.dao.WordListDAO;
import com.wordlistpage.db.vo.WordBean;
import com.wordlistpage.db.vo.WordbookBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AddWordAction  implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        var wordListDAO = new WordListDAO();
        var wordBean = new WordBean();
        var wordbookBean = new WordbookBean();

        boolean dataInsertFlag = true;
        boolean inWordbookFlg = true;

        try {

            wordBean.setWordName(request.getParameter("wordName").toLowerCase());
            wordbookBean.setId(Integer.parseInt(request.getParameter("wbId")));
            //단어가 DB에 존재하는지 검사
            if(!wordListDAO.existWordData(wordBean))
            {
                ConsoleCommand cmd = new ConsoleCommand();
                String command = cmd.inputCommand(" cd D:\\GitHubRepo\\YAMU2020\\JavaWeb\\Python Features\\nltk && d: && python main.py " + wordBean.getWordName()) +" -all";
                String result = cmd.execCommand(command);
                wordBean.setJsonData(result);
                //데이터 베이스에 단어 등록
                wordListDAO.insertWordJsonData(wordBean);
                //page가 0이면 틀린단어이므로 삭제 처리
                dataInsertFlag = wordListDAO.existWordPage(wordBean);
            }
            try{
                List WordBeanList = null;
                WordBeanList = wordListDAO.searchWordList(wordbookBean);

                if(dataInsertFlag)
                {
                    for(var item : WordBeanList)
                    {
                        if((((WordBean) item).getWordName()).equals(wordBean.getWordName()))
                        {
                            inWordbookFlg = false;
                            break;
                        }
                    }
                    if(inWordbookFlg)
                    {
                        wordListDAO.updateWordbook(wordbookBean,wordBean);
                    }
                }
                WordBeanList = wordListDAO.searchWordList(wordbookBean);
                String jsonData = wordListDAO.selectWordListJson(WordBeanList);
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(jsonData);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            wordListDAO.con.close();
        }
        return null;
    }
}
