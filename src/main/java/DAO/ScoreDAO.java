package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import beans.Score;
import beans.Student;

public class ScoreDAO {
    private static Connection conn=null;

    public static void initConnection() throws Exception{//建立连接
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DataBase=Educational", "sa", "yinhuiying123");
    }

    public static ArrayList<Score> findscore1(String Sno) throws Exception{//按学号查找成绩
        initConnection();
        Statement state =null;
        ResultSet rs = null;
        ArrayList<Score> list=new ArrayList<Score>();
        try{
            String sql ="select Student.Sno,Cname,Grade,Tno,Course.Cno,Sname from Student,SCT,Course Where Student.Sno= '"+Sno+"'and Course.Cno=SCT.Cno and Student.Sno=SCT.Sno";
            state = conn.createStatement();
            rs=state.executeQuery(sql);
            while(rs.next()){
                Score score=new Score();
                score.setSname(rs.getString("Sname"));
                score.setSno(rs.getString("Sno"));
                score.setCno(rs.getString("Cno"));
                score.setCname(rs.getString("Cname"));
                score.setGrade(rs.getString("Grade"));
                score.setTno(rs.getString("Tno"));
                list.add(score);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return list;
    }

    public static ArrayList<Score> findscore2(String Cno) throws Exception{//按课程号查找成绩
        initConnection();
        Statement state =null;
        ResultSet rs = null;
        ArrayList<Score> list=new ArrayList<Score>();
        try{
            String sql = "select Student.Sno,Cname,Grade,Tno,Course.Cno,Sname from Student,SCT,Course Where Course.Cno= '"+Cno+"'and Student.Sno=SCT.Sno and Course.Cno=SCT.Cno";
            state = conn.createStatement();
            rs=state.executeQuery(sql);
            while(rs.next()){
                Score score=new Score();
                score.setSname(rs.getString("Sname"));
                score.setSno(rs.getString("Sno"));
                score.setCno(rs.getString("Cno"));
                score.setCname(rs.getString("Cname"));
                score.setGrade(rs.getString("Grade"));
                score.setTno(rs.getString("Tno"));
                list.add(score);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return list;
    }

    public static int addscore(String Sno,String Cno,String Tno,String Grade) throws Exception{//增加成绩
        initConnection();
        Statement state =null;
        int rs = 0;
        try{
            String sql = "insert into SCT(Sno,Cno,Tno,Grade) values('"+Sno+"','"+Cno+"','"+Tno+"','"+Grade+"')";
            state = conn.createStatement();
            rs=state.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return rs;
    }

    public static int updatescore(String Sno,String Cno,String Grade) throws Exception{//修改成绩
        initConnection();
        Statement state =null;
        int rs = 0;
        try{
            String sql = "update SCT set Grade='"+Grade+"' where Sno = '"+Sno+"' and Cno='"+Cno+"'";
            state = conn.createStatement();
            rs=state.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return rs;
    }

    public static int deletescore(String Sno,String Cno) throws Exception{//删除成绩
        initConnection();
        Statement state =null;
        int rs = 0;
        try{
            String sql = "delete from SCT where Sno = '"+Sno+"' and Cno='"+Cno+"'";
            state = conn.createStatement();
            rs=state.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return rs;
    }

    public static void closeConnection() throws Exception{//断开连接
        conn.close();
    }
}