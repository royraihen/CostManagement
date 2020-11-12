package costmanagement.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * This class navigate to the specific action and router to the
 * client controller with the action that selected using reflection
 */
@WebServlet("/controller/*")
public class RouterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RouterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            response.setContentType("text/html");
            // TODO Auto-generated method stub
            String text = request.getRequestURI();

            // extracting the controller name
            String[] texts = text.split("/");

            // extracting controller and action
            String controller = texts[0];
            String action = texts[2];

            // building the full qualified name of the controller
            String temp = controller + "ClientController";
            String controllerClassName = il.ac.hit.costmanagement.Settings.CONTROLLERS_PACKAGE + "."
                    + temp.substring(0, 1).toUpperCase() + temp.substring(1);

            // instantiating the controller class and calling
            // the action method on the controller object
            Class clazz = Class.forName(controllerClassName);
            Method method = clazz.getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(clazz.newInstance(), request, response);

            // creating a RequestDispatcher object that points at the JSP document
            // which is view of our action

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/" + action + ".jsp");

            dispatcher.include(request,response);


        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException |
                InvocationTargetException | InstantiationException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();

        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
