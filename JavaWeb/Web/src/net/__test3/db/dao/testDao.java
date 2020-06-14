package net.__test3.db.dao;

import net.__test3.db.vo.testBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class testDao
{
    public Connection con;
    PreparedStatement pstmt;
    ResultSet rs;

    public testDao()
    {
        try
        {
            Context init = new InitialContext();
            DataSource ds = (DataSource)init.lookup("java:comp/env/test");
            con = ds.getConnection();
        }
        catch (Exception ex)
        {
            System.out.println("DB연결 실패 : " +ex);
            return;
        }
    }

    // 단어 이름과 같은 데이터를 찾습니다
    public testBean existWordData(testBean bean) throws Exception
    {
        String sql = "select json_data from word2 where hash = sha2(?,256)";

        try
        {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,bean.getName());
            rs = pstmt.executeQuery();

            if(rs.next())
            {
                return bean;
            }
        }
        catch (SQLException ex)
        {
            System.out.println("existWordData Error : "+ex);
        }
        return null;
    }

    // 데이터 삽입
    public boolean setInsertJsonData(testBean bean) throws Exception
    {
        String sql = "insert into word2(hash,json_data) values(sha2(?,256),?)";

        try
        {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,bean.getName());
            pstmt.setString(2,bean.getJson_data());
            pstmt.executeUpdate();
            return true;
        }
        catch (SQLException ex)
        {
            System.out.println("setInsertJsonData 에러 : "+ex);
        }
        return false;
    }

    // DB JSON 파싱
    public List getSelectAllWord() throws Exception
    {
        String sql = "select json_extract(json_data, '$.target') as 'word' from word2;";
        List wordList = new ArrayList();
        try
        {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if(rs != null)
            {
                while(rs.next())
                {
                    wordList.add(rs.getString("word"));
                }
                return wordList;
            }
        }
        catch (SQLException ex)
        {
            System.out.println("isBoardWriter 에러 : "+ex);
        }
        return null;
    }
}
