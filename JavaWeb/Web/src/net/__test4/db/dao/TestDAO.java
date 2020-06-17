package net.__test4.db.dao;

import net.__test4.db.vo.TestBean;
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
import java.util.logging.ConsoleHandler;

public class TestDAO
{
    public Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    // 기본생성자: 커넥션 풀로 DB 연결
    public TestDAO()
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
    //테스트 데이터 넣기
    public boolean insertTestData(TestBean bean)
    {
        try
        {
            String sql="insert into test values(?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,bean.getName());
            pstmt.setString(2,bean.getAge());
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
    //테스트 데이터 검색 (JSON)
    public String selectTestData()
    {
        try
        {
            String sql = "select * from test";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();


            JSONArray jsonArray = new JSONArray();

            while(rs.next())
            {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name",rs.getString("name"));
                jsonObject.put("age",rs.getString("age"));
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

    //테스트 데이터 검색 (Bean)
    public List selectTestDataBean()
    {
        try
        {
            String sql = "select * from test";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();


            List beanList = new ArrayList();

            while(rs.next())
            {
                TestBean testBean = new TestBean();
                testBean.setName(rs.getString("name"));
                testBean.setAge(rs.getString("age"));
                beanList.add(testBean);
            }
            return beanList;
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
}
