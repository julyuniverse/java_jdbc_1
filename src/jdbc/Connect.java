package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connect {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc"; // DB 경로: jdbc:mysql://서버주소:포드/DB이름
        String user = "root";
        String password = "1234";
        Connection conn = null; // DB 연결
        Statement stmt = null; // conn.createStatement() 메서드를 통해 Statement 객체를 만들고 DB query를 Statement 객체를 통해 날린다.
        ResultSet rs = null; // query 결과를 받을 객체

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from USER;");

            System.out.println("이름\t\t성\t나이");
            while (rs.next()) {
                System.out.print(rs.getString("first_name") + "\t");
                System.out.print(rs.getString("last_name") + "\t");
                System.out.println(rs.getInt("age"));
            }
        } catch (Exception e) {
            e.printStackTrace(); // e.printStackTrace(): 에러의 발생 근원지를 찾아서 단계별로 에러를 출력.
        } finally {
            // 연결되고 쿼리 해서 data를 가져오는 과정에서 메모리를 잡아먹기 때문에 쿼리가 끝나면 역순으로 해제한다.
            try {
                if (rs != null) rs.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                if (stmt != null) stmt.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
