package net.__test.db.dao;

import net.__test.db.vo.testBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class testDao
{
    Connection con;
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

    public boolean setInsertTest(testBean bean) throws Exception
    {
        String sql = "insert into word(name) values(?)";

        try
        {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,bean.getName());
            pstmt.executeUpdate();
            return true;
        }
        catch (SQLException ex)
        {
            System.out.println("setReadCountUpdate 에러 : "+ex);
            con.close();
        }
        return false;
    }

    public testBean getNameData(testBean bean) throws Exception
    {
        String board_sql = "select name from word where name=?";

        try
        {
            pstmt = con.prepareStatement(board_sql);
            pstmt.setString(1,bean.getName());
            rs = pstmt.executeQuery();

            if(rs.next())
            {
                bean.setName(rs.getString("name"));
                return bean;
            }

        }
        catch (SQLException ex)
        {
            System.out.println("isBoardWriter 에러 : "+ex);
        }
        return null;
    }
}
