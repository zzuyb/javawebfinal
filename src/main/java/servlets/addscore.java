
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CourseDAO;
import DAO.ScoreDAO_admin;
import DAO.UserDAO;
import beans.Course;
import beans.Student;
import beans.Teacher;

public class addscore extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=gb2312");
        String studentid=request.getParameter("studentid");//获取学号
        String courseid=request.getParameter("courseid");//获取课程号
        String score=request.getParameter("score");//获取成绩
        String Tno=request.getParameter("Tno");//获取老师
        PrintWriter out = response.getWriter();
        try {
            Student student=UserDAO.findstudent(studentid);//查找学生
            Course course=CourseDAO.findclasses(courseid);//查找课程
            Teacher teacher=UserDAO.findTeacher(Tno);
            System.out.print(student.getSno());
            if(student.getSno()!=null){//存在该学生
                if(course.getCno()!=null&&teacher.getCno()!=null)
                {
                    if(teacher.getCno().trim().equals(courseid)){//存在该课程和存在该老师教授这门课程
                        try {
                            int i= ScoreDAO_admin.addscore(studentid,courseid,Tno,score);
                            if(i!=0){
                                out.print("<script>alert('添加成功');"
                                        + "window.location.href='admin_addscore.jsp'</script>");
                            }else{
                                out.print("<script>alert('添加失败');"
                                        + "window.location.href='admin_addscore.jsp'</script>");
                            }
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                    else {
                        out.print("<script>alert('该老师不教授此课程，添加失败');"
                                + "window.location.href='admin_addscore.jsp'</script>");
                    }
                }
                else{
                    out.print("<script>alert('课程或老师不存在，添加失败');"
                            + "window.location.href='admin_addscore.jsp'</script>");
                }
            }else{
                out.print("<script>alert('学生不存在，添加失败');"
                        + "window.location.href='admin_addscore.jsp'</script>");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request,response);
    }

}