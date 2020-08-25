package com.wordbookpage.db.dao;

import com.wordbookpage.db.vo.WordbookBean;
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

public class WordbookDAO
{
    public Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    // 기본생성자: 커넥션 풀로 DB 연결
    public WordbookDAO()
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

    // 내 단어장 이름 목록 보기
    public List selectWordbookListBean(WordbookBean bean)
    {
        List wordbookList = new ArrayList();
        try
        {
            // 쿼리 저장
            String sql = "select id,name,info from Wordbook where user_id = ?";
            // 쿼리 셋팅
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,bean.getUser_id());
            // 쿼리 실행
            rs = pstmt.executeQuery();

            while (rs.next())
            {
                var wordbookBean = new WordbookBean();

                wordbookBean.setId(rs.getInt("id"));
                wordbookBean.setName(rs.getString("name"));
                wordbookBean.setInfo(rs.getString("info"));
                wordbookList.add(wordbookBean);
            }
            return wordbookList;
        }
        catch (Exception ex)
        {
            System.out.println("selectWordbookListBean 에러: " + ex);
        }
        finally
        {
            this.dbClose();
        }
        return null;
    }

    public String selectWordbookListJson(WordbookBean bean)
    {
        try
        {
            String sql = "select id,name,info from Wordbook where user_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,bean.getUser_id());
            rs = pstmt.executeQuery();

            JSONArray jsonArray = new JSONArray();

            while(rs.next())
            {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id",rs.getInt("id"));
                jsonObject.put("wordbookName",rs.getString("name"));
                jsonObject.put("info",rs.getString("info"));
                jsonArray.add(jsonObject);
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

    //내 단어장에 이미 있는 이름인지 확인
    public boolean existWordbookName(WordbookBean bean) throws SQLException
    {
        try
        {
            // 쿼리 저장
            String sql = "select EXISTS (select * from Wordbook where user_id = ? and name = ?) as success;";
            // 쿼리 셋팅
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,bean.getUser_id());
            pstmt.setString(2,bean.getName());
            // 쿼리 실행
            rs = pstmt.executeQuery();

            if(rs.next())
            {
                if(rs.getInt("success") == 1)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            return false;
        }
        catch (Exception ex)
        {
            System.out.println("existWordbookName 에러: " + ex);
        }
        finally
        {
            this.dbClose();
        }
        return false;
    }

    //단어장 추가
    public boolean insertWordbook(WordbookBean bean) throws SQLException
    {
        int result = 0;
        try
        {
            String sql = "insert into Wordbook (user_id,name,info,wordList,visit_count) values(?,?,?,'',0)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,bean.getUser_id());
            pstmt.setString(2,bean.getName());
            pstmt.setString(3,bean.getInfo());

            result = pstmt.executeUpdate();

            if (result == 0)
                return false;
            return true;
        }
        catch (Exception ex)
        {
            System.out.println("insertWordbook 에러: " + ex);
        }
        finally
        {
            this.dbClose();
        }
        return false;
    }
}
