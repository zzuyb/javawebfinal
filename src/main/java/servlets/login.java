package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import DAO.UserDAO;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Admin;
import beans.Teacher;
import beans.Student;
import filter.SysFilter;

public class login extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userid = request.getParameter("userid");//获取账号
        String password = request.getParameter("password");//获取密码
        String user=request.getParameter("user");//判断用户身份，学生还是管理员
        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();
        if(user==null||password==null){//未输入密码
            System.out.println("登录失败！");
            out.print("<script>alert('登录失败！请输入账号和密码');"
                    + "window.location.href='index.jsp'</script>");
        }else{
            if(user.equals("student")){//学生登录时
                try {
                    Student student=UserDAO.findstudent(userid);
                    if(student.getPassword().equals(password)){
                        HttpSession session = request.getSession();
                        session.setAttribute("student",student);
                        System.out.println("登录成功！");
                        response.sendRedirect("stu_showscore.jsp");
                    }else{
                        System.out.println("登录失败！");
                        out.print("<script>alert('登录失败！请确认账号和密码');"
                                + "window.location.href='index.jsp'</script>");
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else{//管理员登录时
                try {
                    Admin admin=UserDAO.findadmin(userid);
                    if(admin.getPassword().trim().equals(password)){
                        HttpSession session = request.getSession();
                        session.setAttribute("admin",admin);
                        System.out.println("登录成功！");
                        response.sendRedirect("admin_menu.jsp");
                    }else{
                        System.out.println("登录失败！");
                        out.print("<script>alert('登录失败！请确认账号和密码');"
                                + "window.location.href='index.jsp'</script>");
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);

    }

}