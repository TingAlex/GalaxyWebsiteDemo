package servlet.Forum;

import utils.BlogRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteCommentServlet",urlPatterns = {"/deleteComm"})
public class DeleteCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String index=request.getParameter("index");
        String title=request.getParameter("title");
        String page=request.getParameter("page");
        int pag=Integer.parseInt(page);
        int inde=Integer.parseInt(index);
        List<String> list= BlogRepositoryImpl.getCommentsByTitle(title);
        String rightOne=list.get(inde);
        String writer=rightOne.split("\\|")[0];
        String comment=rightOne.split("\\|")[1];
        BlogRepositoryImpl.deleteComment(writer,title,comment);
    }
}
