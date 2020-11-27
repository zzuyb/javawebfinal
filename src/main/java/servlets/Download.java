package servlets;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class Download extends HttpServlet {

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response)

            throws ServletException, IOException {



        // 获取资源目录

        String uploadFilePath = this.getServletContext().getRealPath("./upload");

        // 存储要下载的文件名

        List<String> list = new ArrayList<>();

        // 递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到list集合中

        listfile(new File(uploadFilePath), list);// File既可以代表一个文件也可以代表一个目录



        // 将list集合发送到页面进行显示

        request.setAttribute("fileNameList", list);

        request.getRequestDispatcher("/downloadForm.jsp").forward(request, response);

    }



    public void listfile(File file, List<String> list) {

        // 如果file代表的不是一个文件，而是一个目录

        if (!file.isFile()) {

            // 列出该目录下的所有文件和目录

            File files[] = file.listFiles();

            // 遍历files[]数组

            for (File f : files) {

                // 递归

                listfile(f, list);

            }

        } else {

            String realName = file.getName();

            list.add(realName);

        }

    }



    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response)

            throws ServletException, IOException {

        doGet(request, response);

    }

}