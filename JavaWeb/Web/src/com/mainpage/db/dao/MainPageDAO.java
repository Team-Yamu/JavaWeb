package com.mainpage.db.dao;

import com.mainpage.db.vo.WordbookBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainPageDAO
{
    public Connection con;
    PreparedStatement pstmt;
    ResultSet rs;

    public MainPageDAO()
    {
        try
        {
            Context init = new InitialContext();
            DataSource ds = (DataSource)init.lookup("java:comp/env/WordBookWeb");
            con = ds.getConnection();
        }
        catch (Exception ex)
        {
            System.out.println("DB연결 실패 : " +ex);
            return;
        }
    }

    public List getWordbookBest8()
    {
        String sql = "select id,user_id,name,visit_count,info from Wordbook order by visit_count desc limit 8;";

        try
        {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            WordbookBean bean = null;
            List beanList = new ArrayList();

            while(rs.next())
            {
                bean = new WordbookBean();
                bean.setId(rs.getInt("id"));
                bean.setUser_id(rs.getString("user_id"));
                bean.setName(rs.getString("name"));
                bean.setVisit_count(rs.getInt("visit_count"));
                bean.setInfo(rs.getString("info"));
                beanList.add(bean);
            }
            return beanList;
        }
        catch (SQLException ex)
        {
            System.out.println("getWordbookBest4 Error : "+ex);
        }
        finally
        {
            if(rs!=null) try{rs.close();}catch (SQLException ex){}
            if(pstmt!=null) try{pstmt.close();}catch (SQLException ex){}
        }
        return null;
    }
}
