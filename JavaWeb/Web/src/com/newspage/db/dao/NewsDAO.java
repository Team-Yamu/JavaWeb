package com.newspage.db.dao;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsDAO
{
    public Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    // 기본생성자: 커넥션 풀로 DB 연결
    public NewsDAO()
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
}
