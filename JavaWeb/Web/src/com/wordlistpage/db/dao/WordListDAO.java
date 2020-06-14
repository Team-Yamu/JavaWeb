package com.wordlistpage.db.dao;

import com.wordlistpage.db.vo.WordBean;
import com.wordlistpage.db.vo.WordbookBean;
import net.__test3.db.vo.testBean;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordListDAO
{
    public Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    // 기본생성자: 커넥션 풀로 DB 연결
    public WordListDAO()
    {
        try
        {
            // context의 name과 일치하는 DB를 찾습니다
            String dbName = "WordBookWeb";
            Context init = new InitialContext();
            DataSource ds = (DataSource) init.lookup("java:comp/env/" + dbName);
            con = ds.getConnection();
        }
        catch (Exception ex)
        {
            System.out.println("DB연결 실패: " + ex);
            return;
        }
    }

    // 커넥션 풀 종료
    public void dbClose()
    {
        try
        {
            if (rs != null)
                rs.close();
            if (pstmt != null)
                pstmt.close();
        }
        catch (Exception ex)
        {
            System.out.println("DB종료 실패: " + ex);
        }
    }

    // 단어리스트 가져오기(해당 단어장의 단어와 뜻만)
    public List searchWordList(WordbookBean bean)
    {
        List wordList = new ArrayList();
        try
        {
            // 쿼리 저장
            String sql = "SELECT word " +
                        "FROM Wordbook," +
                        "JSON_TABLE(concat('[',wordList,']'),'$[*]' COLUMNS(word varchar(30) PATH '$')) AS temp " +
                        "where id = ?;";
            // 쿼리 셋팅
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,bean.getId());
            // 쿼리 실행
            rs = pstmt.executeQuery();

            while (rs.next())
            {
                var wordbookBean = new WordBean();

                wordbookBean.setWordName(rs.getString("word"));
                wordList.add(wordbookBean);
            }
            return wordList;
        }
        catch (Exception ex)
        {
            System.out.println("searchWordList 에러: " + ex);
        }
        finally
        {
            this.dbClose();
        }
        return null;
    }

    //단어리스트를 가져옴 (뜻과 단어만)
    public List getWordList(List wordBeanList)
    {
        String sql = "SELECT word_mean " +
                "FROM Word, " +
                "JSON_TABLE(json_data,'$.target_trans[*]' COLUMNS(word_mean varchar(20) PATH '$')) AS temp " +
                "where hash = sha2(?,256);";
        List wordMeanList = null;
        try
        {
            pstmt = con.prepareStatement(sql);
            for(int i = 0; i<wordBeanList.size();i++)
            {
                pstmt.setString(1,((WordBean)wordBeanList.get(i)).getWordName());
                rs = pstmt.executeQuery();
                wordMeanList = new ArrayList();
                while(rs.next())
                {
                    wordMeanList.add(rs.getString("word_mean"));
                }
                ((WordBean)wordBeanList.get(i)).setWordMeanList(wordMeanList);
            }

            return wordBeanList;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            this.dbClose();
        }
        return null;
    }

    //해당 단어장을 가지고 있는 사람의 아이디와 해당단어장의 이름을 가져옴
    public WordbookBean selectNameUserId(WordbookBean bean)
    {
        String sql = "select user_id,name from Wordbook where id = ?;";
        try
        {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,bean.getId());
            rs = pstmt.executeQuery();

            if(rs.next())
            {
                bean.setUser_id(rs.getString("user_id"));
                bean.setName(rs.getString("name"));
                return bean;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            this.dbClose();
        }
        return null;
    }

    //검색할 때 비슷한 단어를 가져옴
    public String selectSimilarWordName(WordBean bean)
    {
        String sql = "select (json_data->>'$.target') as word from Word where (json_data->>'$.target') LIKE ? ORDER BY word asc;";
        JSONArray jsonArray = new JSONArray();
        try
        {
            if(bean.getWordName()!=null||bean.getWordName()=="")
            {
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1,bean.getWordName()+"%");
                rs = pstmt.executeQuery();

                while(rs.next())
                {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("wordName",rs.getString("word"));
                    jsonArray.add(jsonObject);
                }
            }
            return jsonArray.toString();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            this.dbClose();
        }
        return "no exist data";
    }

    //단어가 존재하는지 확인
    public boolean existWordData(WordBean bean)
    {
        String sql = "select json_data from Word where hash = sha2(?,256)";

        try
        {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,bean.getWordName());
            rs = pstmt.executeQuery();

            if(rs.next())
            {
                return true;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            this.dbClose();
        }
        return false;
    }
    //해당 단어의 해시로 json데이터 형태로 단어 생성
    public boolean insertWordJsonData(WordBean bean)
    {
        String sql = "insert into Word(hash,json_data) values(sha2(?,256),?)";

        try
        {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,bean.getWordName());
            pstmt.setString(2,bean.getJsonData());
            pstmt.executeUpdate();
            return true;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            this.dbClose();
        }
        return false;
    }

    //페이지를 확인후 존재하는 단어가 아니면 삭제
    public boolean existWordPage(WordBean bean)
    {
        String sql = "select (json_data->>'$.synsets.pages') as bool from Word where hash = sha2(?,256);";

        try
        {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,bean.getWordName());
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                System.out.println(rs.getString("bool"));
                if(rs.getString("bool").equals("0"))
                {
                    //page가 0인것은 삭제
                    sql = "delete from Word where hash = sha2(?,256);";
                    pstmt = con.prepareStatement(sql);
                    pstmt.setString(1,bean.getWordName());
                    pstmt.executeUpdate();
                    return false;
                }
                else
                {
                    return true;
                }
            }
            return false;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            this.dbClose();
        }
        return false;
    }

    //단어장에 단어 추가
    public boolean updateWordbook(WordbookBean wbBean,WordBean wBean)
    {
        String sql = "select wordList from Wordbook where id = ?;";
        try
        {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,wbBean.getId());
            rs = pstmt.executeQuery();

            if(rs.next())
            {
                wbBean.setWordList(rs.getString("wordList"));

                String sql2 = "update Wordbook set wordList = ? where id = ?;";
                pstmt = con.prepareStatement(sql2);
                if(wbBean.getWordList().equals(""))
                {
                    pstmt.setString(1,"\""+wBean.getWordName()+"\"");
                }
                else
                {
                    pstmt.setString(1,wbBean.getWordList()+",\""+wBean.getWordName()+"\"");
                }
                pstmt.setInt(2,wbBean.getId());
                pstmt.executeUpdate();
                return true;
            }
            return false;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            this.dbClose();
        }
        return false;
    }
    public String selectWordListJson(List wordBeanList)
    {
        String sql = "SELECT word_mean " +
                "FROM Word, " +
                "JSON_TABLE(json_data,'$.target_trans[*]' COLUMNS(word_mean varchar(20) PATH '$')) AS temp " +
                "where hash = sha2(?,256);";
        JSONArray jsonArray = new JSONArray();
        int count = 0;
        try
        {
            pstmt = con.prepareStatement(sql);
            for(int i = 0; i<wordBeanList.size();i++)
            {
                pstmt.setString(1,((WordBean)wordBeanList.get(i)).getWordName());
                rs = pstmt.executeQuery();
                String means = "";
                while(rs.next())
                {
                    if(!means.equals(""))
                    {
                        means+= ", "+rs.getString("word_mean");
                    }
                    else
                    {
                        means+= rs.getString("word_mean");
                    }
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("wordName",((WordBean)wordBeanList.get(i)).getWordName());
                jsonObject.put("wordMean",means);
                jsonArray.add(jsonObject);
                means = "";
            }

            return jsonArray.toString();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            this.dbClose();
        }
        return "";
    }
}
