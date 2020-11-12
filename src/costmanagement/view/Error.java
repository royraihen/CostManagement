package costmanagement.view;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "/error",urlPatterns = {"/error"})
public class Error extends HttpServlet {

    public final static String REGISTER_ERROR = "User name is already exists";
    public final static String LOGIN_ERROR = "Username or password is incorrect";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();

        String error = (String)request.getAttribute("error");

        switch (error){
            case REGISTER_ERROR:
                printRegisterError(out);
                break;
            case LOGIN_ERROR:
                printLoginError(out);
                break;
            default:
                printDefaultError(out);
        }
    }

    private void printDefaultError(PrintWriter out) {

        out.println("<script type=\"text/javascript\">");
        out.println("alert('Something went wrong!');");
        out.println("location='login.jsp';");
        out.println("</script>");

    }

    private void printLoginError(PrintWriter out) {

        out.println("<script type=\"text/javascript\">");
        out.println("alert('Username or password is incorrect!');");
        out.println("location='login.jsp';");
        out.println("</script>");

    }

    private void printRegisterError(PrintWriter out) {

        out.println("<script type=\"text/javascript\">");
        out.println("alert('User name is already exists!');");
        out.println("location='register.jsp';");
        out.println("</script>");

    }
}
