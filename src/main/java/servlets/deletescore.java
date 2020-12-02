package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DAO.ScoreDAO_admin;
import beans.Score;

public class deletescore extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=gb2312");
        String studentid=request.getParameter("studentid");//获取学号
        String courseid=request.getParameter("courseid");//获取课程号
        int i=0;
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        ArrayList<Score> list = (ArrayList<Score>) session.getAttribute("score");
        try {
            i= ScoreDAO_admin.deletescore(studentid,courseid);//进行删除操作
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        for(int j=0;j<list.size();j++){
            if(list.get(j)==null){
                list.remove(j);
            }
            if(list.get(j)!=null && list.get(j).getSno().equals(studentid)&&list.get(j).getCno().equals(courseid)){
                list.remove(j);
                break;
            }
        }
        if(i!=0){
            out.print("<script>alert('删除成功');"
                    + "window.location.href='admin_updatescore.jsp'</script>");
        }else{
            out.print("<script>alert('删除失败');"
                    + "window.location.href='admin_updatescore.jsp'</script>");
        }

        // response.sendRedirect("../admin_updatescore.jsp");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request,response);
    }

}