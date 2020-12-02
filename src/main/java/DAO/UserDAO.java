package DAO;

import java.sql.*;

import beans.Admin;
import beans.Student;
import beans.Teacher;

public class UserDAO {
    private static Connection conn=null;

    public static void initConnection() throws Exception{//建立连接
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DataBase=Educational", "sa", "yinhuiying123");
    }

    public static Student findstudent(String Sno) throws Exception{//查找学生
        initConnection();
        Statement state =null;
        ResultSet rs = null;
        Student student=new Student();
        try{
            String sql = "select * from Student where Sno = '"+Sno+"'";
            state = conn.createStatement();
            rs=state.executeQuery(sql);
            if(rs.next()){
                student.setSno(rs.getString("Sno"));
                student.setPassword(rs.getString("Password"));
                student.setSname(rs.getString("Sname"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return student;
    }

    public static boolean addStudent(Student student) {//增加学生
        boolean flag=false;
        int i=0;
        try {
            initConnection();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Statement state =null;
        String sql= "insert into student (Sno,studentname,password) values('"+student.getSno()+"','"+student.getSname()+"','"+student.getPassword()+"')";
        try {
            try {
                state = conn.createStatement();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            i=state.executeUpdate(sql);
            if(i>0){
                flag=true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            closeConnection();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return flag;
    }


    public static Admin findadmin(String Dno) throws Exception{//查找管理员
        initConnection();
        Statement state =null;
        ResultSet rs = null;
        Admin admin =new Admin();
        try{
            String sql = "select * from admin where Dno = '"+Dno+"'";
            state = conn.createStatement();
            rs=state.executeQuery(sql);

            if(rs.next()){
                admin.setDno(rs.getString("Dno"));
                admin.setPassword(rs.getString("Password"));
               admin.setAname(rs.getString("Aname"));
               //System.out.println(admin.getAname());
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return admin;
    }

    public static Teacher findTeacher(String Tno) throws Exception{//教师
        initConnection();
        Statement state =null;
        ResultSet rs = null;
        Teacher teacher =new Teacher();
        try{
            String sql = "select * from Teacher where Tno = '"+Tno+"'";
            state = conn.createStatement();
            rs=state.executeQuery(sql);

            if(rs.next()){
                teacher.setCno(rs.getString("Cno"));
                teacher.setTno(rs.getString("Tno"));
                teacher.setPassword(rs.getString("Password"));
                teacher.setTname(rs.getString("Tname"));
                //System.out.println(admin.getAname());
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return teacher;
    }
    public static void closeConnection() throws Exception{//断开连接
        conn.close();
    }
}