package com.news.db.dao;

import com.news.db.vo.NewsBean;
import com.news.db.vo.NewsParserBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO
{
    private Connection con;
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
            if (con != null)
                con.close();
        }
        catch (Exception ex)
        {
            System.out.println("DB종료 실패: " + ex);
        }
    }

    // 뉴스 추가
    public boolean newsInsert(NewsBean news)
    {
        int result;
        try
        {
            String sql = "insert into News values(sha2(?, 256), ?, ?)";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, news.getHash());
            pstmt.setString(2, news.getJson_data());
            pstmt.setInt(3, news.getVisit_count());

            result = pstmt.executeUpdate();

            if (result == 0)
                return false;
            return true;
        }
        catch (Exception ex)
        {
            System.out.println("newsInsert에러: " + ex);
            return false;
        }
        finally
        {
            this.dbClose();
        }
    }

    // 뉴스 목록 보기
    public List newsList()
    {
        List newsList = new ArrayList();

        try
        {
            // 쿼리 저장
            String sql = "select * from News";
            // 쿼리 셋팅
            pstmt = con.prepareStatement(sql);
            // 쿼리 실행
            rs = pstmt.executeQuery();

            while (rs.next())
            {
                var news = new NewsBean();

                news.setHash(rs.getString("hash"));
                news.setJson_data(rs.getString("json_data"));
                news.setVisit_count(rs.getInt("visit_count"));
                newsList.add(news);
            }
            return newsList;
        }
        catch (Exception ex)
        {
            System.out.println("newsList에러: " + ex);
        }
        finally
        {
            this.dbClose();
        }
        return null;
    }

    // JSON 파싱으로 가공된 데이터
    public List newsJSONresult()
    {
        List newsList = new ArrayList();
        try
        {
            // String sql = "select json_extract(json_data, '$.title') as title, json_extract(json_data, '$.top_image.base64_top_img') as image from News;";
            // String sql = "select json_data ->> '$.title' as title, json_data ->> '$.top_image.base64_top_img' as image from News";
            String sql =
                    "select " +
                            "json_data ->> '$.title' as title, " +
                            "json_data ->> '$.keyword' as keyword, " +
                            "json_data ->> '$.summary' as summary, " +
                            "json_data ->> '$.text' as text, " +
                            "json_data ->> '$.top_image.base64_top_img' as image " +
                            "from News";

            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next())
            {
                var news = new NewsParserBean();

                news.setTitle(rs.getString("title"));
                news.setKeyword(rs.getString("keyword"));
                news.setSummary(rs.getString("summary"));
                news.setText(rs.getString("text"));
                news.setImage(rs.getString("image"));
                newsList.add(news);
            }
            return newsList;
        }
        catch (Exception ex)
        {
            System.out.println("newsJSONresult 에러: " + ex);
        }
        finally
        {
            this.dbClose();
        }
        return null;
    }
}
