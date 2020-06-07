package com.loginpage.db.dao;

import com.loginpage.db.vo.UserBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO
{
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    // 기본생성자: 커넥션 풀로 DB 연결
    public UserDAO()
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

    // 모든 User 목록 보기
    public List getAllUserList()
    {
        List userList = new ArrayList();

        try
        {
            // 쿼리 저장
            String sql = "select * from User";
            // 쿼리 셋팅
            pstmt = con.prepareStatement(sql);
            // 쿼리 실행
            rs = pstmt.executeQuery();

            while (rs.next())
            {
                var user = new UserBean();

                user.setId(rs.getString("id"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                userList.add(user);
            }
            return userList;
        }
        catch (Exception ex)
        {
            System.out.println("getAllUserList 에러: " + ex);
        }
        finally
        {
            this.dbClose();
        }
        return null;
    }

    // 회원 가입
    public boolean userInsert(UserBean user) throws Exception
    {
        int result;
        try
        {
            String sql = "insert into User values(?, ?, ?)";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());

            result = pstmt.executeUpdate();

            if (result == 0)
                return false;
            return true;
        }
        catch (java.sql.SQLIntegrityConstraintViolationException ex)
        {
            System.out.println("중복되는 id 존재: " + ex);
            throw new Exception("중복되는 아이디가 존재합니다");
        }
        catch (Exception ex)
        {
            System.out.println("userInsert 에러: " + ex);
        }
        finally
        {
            this.dbClose();
        }
        return false;
    }

    // 회원 정보 확인
    public int isUser(UserBean user)
    {
        int result = 1;
        try
        {
            String sql = "select password from User where id = ?";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getId());
            rs = pstmt.executeQuery();

            if (rs.next())
            {
                if (rs.getString("password").equals(user.getPassword()))
                    // 회원 정보 일치
                    result = 1;
                else
                    // 비밀 번호 틀림
                    result = 0;
            }
            else
                // 아이디 존재하지 않음
                result = -1;
        }
        catch (Exception ex)
        {
            System.out.println("isUser 에러: " + ex);
        }
        finally
        {
            this.dbClose();
        }
        return result;
    }
}
