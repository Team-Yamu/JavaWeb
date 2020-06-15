package com.openwordbookPage.db.dao;

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

public class OpenWordbookDAO
{
    public Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    // 기본생성자: 커넥션 풀로 DB 연결
    public OpenWordbookDAO()
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

    public String selectWordbookListJson(int count,String searchBook)
    {
        String sql ="select id,name,info from Wordbook where info LIKE ? or name LIKE ? order by id desc LIMIT ?,?;";
        try
        {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,"%"+searchBook+"%");
            pstmt.setString(2,"%"+searchBook+"%");
            pstmt.setInt(3,count*10);
            pstmt.setInt(4,10);
            rs = pstmt.executeQuery();

            JSONArray jsonArray = new JSONArray();

            while(rs.next())
            {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id",rs.getInt("id"));
                jsonObject.put("name",rs.getString("name"));
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
}
