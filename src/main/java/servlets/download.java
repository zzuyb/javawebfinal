package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class  download extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 得到要下载的文件名
        String fileName = request.getParameter("filename");
        System.out.println("进入下载");
        System.out.println(fileName);
        fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");
       // 上传的文件都是保存在upload目录下的子目录当中
        String fileSaveRootPath = this.getServletContext().getRealPath("./upload");
        // 得到要下载的文件
        File file = new File(fileSaveRootPath + "\\" + fileName);
        // 如果文件不存在
        if (!file.exists()) {
            request.setAttribute("message", "您要下载的资源已被删除！！");
            request.getRequestDispatcher("/page/message.jsp").forward(request, response);
            return;
        }
       // 设置响应头，控制浏览器下载该文件

        // 设置文件MIME类型

        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        FileInputStream in = new FileInputStream(file);
        OutputStream out = response.getOutputStream();
        byte buffer[] = new byte[1024];
        int len = 0;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}