
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import beans.Course;
import beans.Student;

public class CourseDAO {
    private static Connection conn=null;

    public static void initConnection() throws Exception{//建立连接
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DataBase=Educational", "sa", "yinhuiying123");
    }

    public static Course findclasses(String Cno) throws Exception{//查找课程
        initConnection();
        Statement state =null;
        ResultSet rs = null;
        Course course=new Course();
        try{
            String sql = "select * from Course where Cno = '"+Cno+"'";
            state = conn.createStatement();
            rs=state.executeQuery(sql);
            if(rs.next()){
                course.setCno(rs.getString("Cno"));
                course.setCname(rs.getString("Cname"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return course;
    }

    public static void closeConnection() throws Exception{//断开连接
        conn.close();
    }
}