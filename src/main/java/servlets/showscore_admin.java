
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DAO.CourseDAO;
import DAO.ScoreDAO_admin;
import DAO.UserDAO;
import beans.Course;
import beans.Score;
import beans.Student;
import beans.Teacher;

public class showscore_admin extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=gb2312");
        String flag=request.getParameter("flag");//按学号还是课程号查找
        String id=request.getParameter("id");//获取的id值
        String func=request.getParameter("func");//从哪个页面传来的表单
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
//        System.out.println(flag);
//        System.out.println(id);
//        System.out.println(func);
        if(func.equals("show")||func.equals("update"))
        {
            if(flag.equals("stu")){//按学号查找成绩
                session.setAttribute("flag", flag);
                try {
                    Student student=UserDAO.findstudent(id);
                    if(student.getSno()!=null){//输入的学号存在
                        try {
                            ArrayList<Score> list=(ArrayList<Score>) ScoreDAO_admin.findscore1(id);
                            session.setAttribute("score", list);
                            if(func.equals("show"))//从admin_showscore.jsp传来的值
                                response.sendRedirect("admin_showscore.jsp");
                            else//从admin_updatescore.jsp传来的值
                                response.sendRedirect("admin_updatescore.jsp");
                        }catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }else{//学号不存在
                        if(func.equals("show")){
                            out.print("<script>alert('不存在该学生');"
                                    + "window.location.href='admin_showscore.jsp'</script>");
                        }else{
                            out.print("<script>alert('不存在该学生');"
                                    + "window.location.href='admin_updatescore.jsp'</script>");
                        }
                    }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }else{//按课程号查找成绩
                try {
                    Course course=CourseDAO.findclasses(id);
                    if(course.getCno()!=null){
                        ArrayList<Score> list;
                        try {
                            session.setAttribute("flag", flag);
                            list = (ArrayList<Score>) ScoreDAO_admin.findscore_admin(id);
                            session.setAttribute("score", list);
                            if(func.equals("show"))
                                response.sendRedirect("admin_showscore.jsp");
                            else
                                response.sendRedirect("admin_updatescore.jsp");
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }else{
                        if(func.equals("show")){
                            out.print("<script>alert('不存在该课程');"
                                    + "window.location.href='admin_showscore.jsp'</script>");
                        }else{
                            out.print("<script>alert('不存在该课程');"
                                    + "window.location.href='admin_updatescore.jsp'</script>");
                        }

                    }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
        else if(func.equals("stu_show")){
            Student student = (Student) session.getAttribute("student");
            String Sno = student.getSno();
            session.setAttribute("flag", flag);
            if (flag.equals("stu")) {//按学号查找成绩
                ArrayList<Score> list = null;
                try {
                    list = (ArrayList<Score>) ScoreDAO_admin.findscore1(Sno);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                session.setAttribute("score", list);
                response.sendRedirect("stu_showscore.jsp");

            } else {//按课程号查找成绩
                ArrayList<Score> list = null;
                try {
                    if ((ArrayList<Score>) ScoreDAO_admin.findscore_student(Sno, id) == null)
                        out.print("<script>alert('不存在该课程');"
                                + "window.location.href='stu_showscore.jsp'</script>");
                    else {
                        list = (ArrayList<Score>) ScoreDAO_admin.findscore_student(Sno, id);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                session.setAttribute("score", list);
                response.sendRedirect("stu_showscore.jsp");
            }
        }
        else if(func.equals("teacher_show")){
            Teacher teacher= (Teacher) session.getAttribute("teacher");
            String Tno=teacher.getTno();

            session.setAttribute("flag", flag);
            ArrayList<Score> list = null;
            try {
                list = (ArrayList<Score>) ScoreDAO_admin.findscore_teacher(Tno);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(list.size());
            session.setAttribute("score",list);
            response.sendRedirect("teacher_showscore.jsp");
        }

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request,response);
    }

}