package net.__test3.action.svc;

import net.__test3.action.controller.Action;
import net.__test3.action.controller.ActionForward;

import net.__test3.action.util.consoleCommand;
import net.__test3.action.util.jsonData;
import net.__test3.action.util.jsonParser;

import net.__test3.db.dao.testDao;
import net.__test3.db.vo.testBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class testAction implements Action
{
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        testDao dao = new testDao();
        testBean bean = new testBean();

        boolean dataInsertFlag = false;

        try
        {
            String word = (String) request.getParameter("word");

            bean.setName(word);
            //단어가 DB에 존재하는지 검사
            bean = dao.existWordData(bean);

            if(bean == null)
            {
                bean = new testBean();
                bean.setName(word);
                consoleCommand cmd = new consoleCommand();
                String command = cmd.inputCommand(" cd D:\\GitHubRepo\\YAMU2020\\JavaWeb\\Python Features\\nltk && d: && python main.py "+bean.getName());
                String result = cmd.execCommand(command);
                System.out.println(result);
                bean.setJson_data(result);
                //데이터 베이스에 단어 등록
                dataInsertFlag = dao.setInsertJsonData(bean);
            }

            try
            {
                jsonData jsonParse = new jsonData();
                jsonParse = jsonParser.parsedJson(bean,jsonParse);

                if(jsonParse!= null)
                {
                    request.setAttribute("testData",jsonParse.name);
                    request.setAttribute("testList",jsonParse.target_trans_word);
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            ActionForward forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("./views/test/test_result_view.jsp");

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