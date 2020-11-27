package servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Upload extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         * step1,创建DiskFileItemFactory对象,
         * 该对象为解析器提供解析时的缺省的配置。
         */
        DiskFileItemFactory diff = new DiskFileItemFactory();
        /*
         * 创建解析器
         */
        ServletFileUpload sfu = new ServletFileUpload(diff);
        sfu.setHeaderEncoding("utf-8");
        /*
         * 使用解析器解析,解析器会将每一个表单域
         * (比如 uname,file)中的数据解析出来之后，放到
         * 对应的一个FileItem对象上。FileItem对象提供了
         * 相应的方法来获得请求参数值。
         */
        try {
            List<FileItem> items = sfu.parseRequest(request);
            for(int i=0;i<items.size();i++){
                FileItem item = items.get(i);
                if(item.isFormField()){
                    //这是一个普通的表单域
                    String paramName = item.getFieldName();
                    String paramValue = item.getString();
                    paramValue = new String(paramValue.getBytes("iso-8859-1"),"utf-8");
                    System.out.println("paramName:" + paramName + " paramValue:" + paramValue);
                }else{
                    //这是上传文件域
                    ServletContext sctx = getServletContext();
                    //依据逻辑路径获得实际部署时的物理路径
                    String path = sctx.getRealPath("./upload");
                    System.out.println("path:" + path);
                    //获得上传文件的名称
                    String filename = item.getName();
                    //item.getName方法在某些操作系统上，会返回路径加文件名。
                    filename = filename.substring(filename.lastIndexOf(File.separator) +1);
                    System.out.println("filename:" + filename);
                    File file = new File(path + File.separator + filename);
                    item.write(file);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("上传文件失败");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
