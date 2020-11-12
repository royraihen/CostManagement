package costmanagement.controller;


import il.ac.hit.costmanagement.exception.CostManagementException;
import il.ac.hit.costmanagement.rest.ActionsService;
import il.ac.hit.costmanagement.rest.HomeService;
import il.ac.hit.costmanagement.rest.UsersService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Calendar;

/**
 * This class extends Abstract controller.
 * A new request that come from the RouterServlet
 * is redirect to RESTful web service.
 */
public class ClientController extends AbstractController {

    private final static String ERROR_FROM_RESTFUL = "ERROR";
    private  LocalDate date = new java.sql.Date(Calendar.getInstance().getTimeInMillis()).toLocalDate();
    int month = date.getMonth().getValue();
    private  JSONObject categories = new JSONObject();
    private  JSONObject spend = new JSONObject();
    private  JSONObject income = new JSONObject();
    private  JSONObject total = new JSONObject();
    private  JSONObject graphs = new JSONObject();
    private Response  responseFromRestful;
    private String responseFromRestfulDeserialize;
    private JSONObject responseFromRestfulSerialize;

    private int userId;


    private double shopping = 0;
    private double transport = 0;
    private double restaurant = 0;
    private double health = 0;
    private double family = 0;
    private double groceries = 0;
    private double leisure = 0;
    private double government = 0;
    private double food = 0;

    private double totalAmountForMonth = 0;
    private double spendForMonth = 0;
    private  double incomeForMonth = 0;

    private double januarySpend = 0;
    private double februarySpend = 0;
    private double marchSpend = 0;
    private double aprilSpend = 0;
    private double maySpend = 0;
    private double juneSpend = 0;
    private double julySpend = 0;
    private double augustSpend = 0;
    private double septemberSpend = 0;
    private double octoberSpend = 0;
    private double novemberSpend = 0;
    private double decemberSpend = 0;


    private double januaryIncome = 0;
    private double februaryIncome = 0;
    private double marchIncome = 0;
    private  double aprilIncome = 0;
    private  double mayIncome = 0;
    private double juneIncome = 0;
    private double julyIncome = 0;
    private double augustIncome = 0;
    private  double septemberIncome = 0;
    private double octoberIncome = 0;
    private double novemberIncome = 0;
    private  double decemberIncome = 0;

    private double totalIncomeForMonth = 0;
    private double totalSpendForMonth = 0;
    private double totalAllCosts = 0;
    private  double incomePercent = 0;
    private  double spendPercent = 0;

    /**
     * Checks the authentication and query the income and spend from db.
     * @param request an object that represent the request
     * @param response and object that represent the response
     * @throws IOException
     * @throws ServletException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, CostManagementException {


        String email = request.getParameter("email");
        String password = request.getParameter("password");


        // Create a new JSON object to check the authentication
        // in the RESTful web service
        JSONObject userAuthentication = new JSONObject();
        userAuthentication.put("email",email);
        userAuthentication.put("password",password);

        // Creating a new authentication request and
        // getting the response from RESTful web service
        // as a Response object
        responseFromRestful = new UsersService().checkAuthentication(userAuthentication.toString());


        // Deserialize the response from RESTful
        responseFromRestfulDeserialize = (String) responseFromRestful.getEntity();

        //Serialize a new json object
        responseFromRestfulSerialize = new JSONObject(responseFromRestfulDeserialize);

        //Checking the response status
        if(responseFromRestfulSerialize.get("status").equals(ERROR_FROM_RESTFUL))
            response.sendRedirect(request.getContextPath()+"/err.jsp");


        addCookies(response,email,password);

        // Get the current user that login
        responseFromRestful = new UsersService().getUser(email);

        // Deserialize the response from RESTful
        responseFromRestfulDeserialize = (String) responseFromRestful.getEntity();

        //Serialize a new json object
        JSONObject currentUser = new JSONObject(responseFromRestfulDeserialize);

        //Checking the response status
        if(currentUser.get("status").equals(ERROR_FROM_RESTFUL))
            response.sendRedirect(request.getContextPath()+"/err.jsp");

        // Getting the cuurent user that login
        userId = currentUser.getInt("currentUser");


        request.getSession().setAttribute("currentUser", userId);

        spend.put("currentUser",userId);
        spend.put("month",month);


        // Getting all the spend costs for
        // the current month
        responseFromRestful  = new HomeService().getSpendForMonth(spend.toString());


        // Deserialize the response from RESTful
        responseFromRestfulDeserialize = (String)responseFromRestful.getEntity();

        //Serialize a new json object
        JSONObject totalSpendForMonth = new JSONObject(responseFromRestfulDeserialize);

        //Checking the response status
        if(totalSpendForMonth.get("status").equals(ERROR_FROM_RESTFUL))
            response.sendRedirect(request.getContextPath()+"/err.jsp");

        spendForMonth =  Double.parseDouble((String) totalSpendForMonth.get("spendForMonth"));
        request.getSession().setAttribute("spendForMonth",spendForMonth);

        income.put("currentUser",userId);
        income.put("month",month);


        // Getting all the income costs for
        // the current month
        responseFromRestful  = new HomeService().getIncomeForMonth(income.toString());

        // Deserialize the response from RESTful
        responseFromRestfulDeserialize = (String)responseFromRestful.getEntity();

        //Serialize a new json object
        JSONObject totalIncomeForMonth = new JSONObject(responseFromRestfulDeserialize);

        if(totalIncomeForMonth.get("status").equals(ERROR_FROM_RESTFUL))
            response.sendRedirect(request.getContextPath()+"/err.jsp");

        incomeForMonth = Double.parseDouble((String) totalIncomeForMonth.get("incomeForMonth"));
        request.getSession().setAttribute("incomeForMonth",incomeForMonth);

        total.put("currentUser",userId);
        total.put("month",month);

        // Getting all costs for the current month
        responseFromRestful = new HomeService().getTotalAmount(total.toString());

        // Deserialize the response from RESTful
        responseFromRestfulDeserialize = (String)responseFromRestful.getEntity();

        //Serialize a new json object
        JSONObject totalCostForMonth = new JSONObject(responseFromRestfulDeserialize);


        if(totalCostForMonth.get("status").equals(ERROR_FROM_RESTFUL))
            response.sendRedirect(request.getContextPath()+"/err.jsp");

        totalAmountForMonth = Double.parseDouble((String) totalCostForMonth.get("totalAmountForMonth"));
        request.getSession().setAttribute("totalAmountForMonth",totalAmountForMonth);


        request.getRequestDispatcher("home.jsp").forward(request, response);

    }


    /**
     * Register a new user
     * @param request an object that represent the request
     * @param response and object that represent the response
     * @throws IOException
     * @throws ServletException
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            // Create a new JSON object to create a new user
            // in the RESTful web service
            JSONObject registerNewUser = new JSONObject();
            registerNewUser.put("email", email);
            registerNewUser.put("password",password);

            // Creating a new register request and
            // getting the response from RESTful web service
            // as a Response object
            responseFromRestful = new UsersService().registerUser(registerNewUser.toString());

            // Deserialize the response from RESTful
            responseFromRestfulDeserialize = (String)responseFromRestful.getEntity();

            //Serialize a new json object
            JSONObject totalCostForMonth = new JSONObject(responseFromRestfulDeserialize);

            if(totalCostForMonth.get("status").equals(ERROR_FROM_RESTFUL))
                throw new CostManagementException(totalCostForMonth.getString("errorMessage"));

            boolean isRegisterSucceed = totalCostForMonth.getBoolean("isRegistrationSucceed");

            if (isRegisterSucceed) {
                PrintWriter writer = response.getWriter();
                writer.print("Registered Succeed");
            }
        }
        catch (CostManagementException e){
            request.setAttribute("error",e.getMessage());
            request.getRequestDispatcher("err.jsp").forward(request, response);
            e.printStackTrace();
        }

    }


    /**
     * Add a new spend to the currently month
     * @param request an object that represent the request
     * @param response and object that represent the response
     * @throws ServletException
     * @throws IOException
     */
    public void addspend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            userId = (int) request.getSession().getAttribute("currentUser");

            double amount = Double.parseDouble(request.getParameter("amount"));
            String category = request.getParameter("category");
            boolean permanentSpend = Boolean.parseBoolean(request.getParameter("permanentspend"));
            String comment = request.getParameter("comment");

            JSONObject newSpend = new JSONObject();
            newSpend.put("userId",userId);
            newSpend.put("amount",amount);
            newSpend.put("category",category);
            newSpend.put("permanentSpend",permanentSpend);
            newSpend.put("comment",comment);

            // Creating a new add spend request
            // getting the response from RESTful web service
            // as a Response object
            responseFromRestful = new ActionsService().addSpend(newSpend.toString());

            // Deserialize the response from RESTful
            responseFromRestfulDeserialize = (String)responseFromRestful.getEntity();

            //Serialize a new json object
            JSONObject addSpendCommitted = new JSONObject(responseFromRestfulDeserialize);


            if(addSpendCommitted.getString("status").equals(ERROR_FROM_RESTFUL)){
                throw new CostManagementException(addSpendCommitted.getString("errorMessage"));
            }

            request.getRequestDispatcher("/home.jsp").include(request, response);
        }
        catch (CostManagementException e){
            request.setAttribute("error",e.getMessage());
            request.getRequestDispatcher("err.jsp").forward(request, response);
            e.printStackTrace();
        }



    }

    /**
     * Add a new income to the currently month
     * @param request an object that represent the request
     * @param response and object that represent the response
     * @throws ServletException
     * @throws IOException
     */
    public void addincome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            userId = (int) request.getSession().getAttribute("currentUser");

            double amount = Double.parseDouble(request.getParameter("amount"));
            boolean permanentIncome = Boolean.parseBoolean(request.getParameter("permanentincome"));
            String comment = request.getParameter("comment");

            JSONObject newIncome = new JSONObject();
            newIncome.put("userId",userId);
            newIncome.put("amount",amount);
            newIncome.put("permanentIncome",permanentIncome);
            newIncome.put("comment",comment);

            // Creating a new add income request
            // getting the response from RESTful web service
            // as a Response object
            responseFromRestful = new ActionsService().addIncome(newIncome.toString());

            // Deserialize the response from RESTful
            responseFromRestfulDeserialize = (String)responseFromRestful.getEntity();

            //Serialize a new json object
            JSONObject addIncomeCommitted = new JSONObject(responseFromRestfulDeserialize);

            if(addIncomeCommitted.getString("status").equals(ERROR_FROM_RESTFUL)){
                throw new CostManagementException(addIncomeCommitted.getString("errorMessage"));
            }

            request.getRequestDispatcher("/home.jsp").include(request, response);

        }
        catch (CostManagementException e){
            request.setAttribute("error",e.getMessage());
            request.getRequestDispatcher("err.jsp").forward(request, response);
            e.printStackTrace();
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Getting all the categories that spend of the currently month
     * @param request an object that represent the request
     *  @param response and object that represent the response
     * @throws ServletException
     * @throws IOException
     */
    public void categories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        userId = (int) request.getSession().getAttribute("currentUser");

        try {

            // Creating a new request to get all spend categories
            // for current month.
            // getting the response from RESTful web service
            // as a Response object
            responseFromRestful = new HomeService().getAllCategories(userId);

            // Deserialize the response from RESTful
            responseFromRestfulDeserialize = (String)responseFromRestful.getEntity();

            //Serialize a new json object
            JSONObject addIncomeCommitted = new JSONObject(responseFromRestfulDeserialize);

            if(addIncomeCommitted.getString("status").equals(ERROR_FROM_RESTFUL)){
                throw new CostManagementException(addIncomeCommitted.getString("errorMessage"));
            }

            shopping = Double.parseDouble((String) addIncomeCommitted.get("Shopping"));
            transport = Double.parseDouble((String) addIncomeCommitted.get("Transport"));
            restaurant = Double.parseDouble((String) addIncomeCommitted.get("Restaurant"));
            health = Double.parseDouble((String) addIncomeCommitted.get("Health"));
            family = Double.parseDouble((String)  addIncomeCommitted.get("Family"));
            groceries = Double.parseDouble((String)  addIncomeCommitted.get("Groceries"));
            leisure = Double.parseDouble((String) addIncomeCommitted.get("Leisure"));
            government = Double.parseDouble((String) addIncomeCommitted.get("Government"));
            food = Double.parseDouble((String) addIncomeCommitted.get("Food"));

            request.getSession().setAttribute("shopping", shopping);
            request.getSession().setAttribute("transport", transport);
            request.getSession().setAttribute("restaurant", restaurant);
            request.getSession().setAttribute("health", health);
            request.getSession().setAttribute("family", family);
            request.getSession().setAttribute("groceries", groceries);
            request.getSession().setAttribute("leisure", leisure);
            request.getSession().setAttribute("government", government);
            request.getSession().setAttribute("food", food);

            request.getRequestDispatcher("/categories.jsp").include(request, response);

        }
        catch (CostManagementException e){
            request.setAttribute("error",e.getMessage());
            request.getRequestDispatcher("err.jsp").forward(request, response);
            e.printStackTrace();
        }

    }


    /**
     * Getting all the costs of the current year to make a graph of the year
     * and getting all the costs of the currently month
     * @param request an object that represent the request
     * @param response and object that represent the response
     * @throws ServletException
     * @throws IOException
     */
    public void graphs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            userId = (int) request.getSession().getAttribute("currentUser");

            graphs.put("currentUser",userId);
            graphs.put("month",month);



            responseFromRestful = new HomeService().getAllYearCosts(userId);

            // Deserialize the response from RESTful
            responseFromRestfulDeserialize = (String)responseFromRestful.getEntity();

            //Serialize a new json object
            JSONObject allYearCosts = new JSONObject(responseFromRestfulDeserialize);

            if(allYearCosts.getString("status").equals(ERROR_FROM_RESTFUL)){
                throw new CostManagementException(allYearCosts.getString("errorMessage"));
            }


            januarySpend =  Double.parseDouble((String) allYearCosts.get("januarySpend"));
            februarySpend = Double.parseDouble((String) allYearCosts.get("februarySpend"));
            marchSpend =  Double.parseDouble((String) allYearCosts.get("marchSpend"));
            aprilSpend =  Double.parseDouble((String) allYearCosts.get("aprilSpend"));
            maySpend =  Double.parseDouble((String) allYearCosts.get("maySpend"));
            juneSpend =  Double.parseDouble((String) allYearCosts.get("juneSpend"));
            julySpend =  Double.parseDouble((String) allYearCosts.get("julySpend"));
            augustSpend =  Double.parseDouble((String) allYearCosts.get("augustSpend"));
            septemberSpend =  Double.parseDouble((String) allYearCosts.get("septemberSpend"));
            octoberSpend =  Double.parseDouble((String) allYearCosts.get("octoberSpend"));
            novemberSpend =  Double.parseDouble((String) allYearCosts.get("novemberSpend"));
            decemberSpend =  Double.parseDouble((String) allYearCosts.get("decemberSpend"));

            totalSpendForMonth =  Double.parseDouble((String) allYearCosts.get("totalSpendForMonth"));

            januaryIncome =  Double.parseDouble((String) allYearCosts.get("januaryIncome"));
            februaryIncome =  Double.parseDouble((String) allYearCosts.get("februaryIncome"));
            marchIncome =  Double.parseDouble((String) allYearCosts.get("marchIncome"));
            aprilIncome =  Double.parseDouble((String) allYearCosts.get("aprilIncome"));
            mayIncome = Double.parseDouble((String) allYearCosts.get("mayIncome"));
            juneIncome =  Double.parseDouble((String) allYearCosts.get("juneIncome"));
            julyIncome =  Double.parseDouble((String) allYearCosts.get("julyIncome"));
            augustIncome =  Double.parseDouble((String) allYearCosts.get("augustIncome"));
            septemberIncome =  Double.parseDouble((String) allYearCosts.get("septemberIncome"));
            octoberIncome =  Double.parseDouble((String) allYearCosts.get("octoberIncome"));
            novemberIncome = Double.parseDouble((String) allYearCosts.get("novemberIncome"));
            decemberIncome =  Double.parseDouble((String) allYearCosts.get("decemberIncome"));

            totalIncomeForMonth =  Double.parseDouble((String) allYearCosts.get("totalIncomeForMonth"));

            totalAllCosts = totalIncomeForMonth + totalSpendForMonth;

            request.getSession().setAttribute("januarySpend",januarySpend);
            request.getSession().setAttribute("februarySpend",februarySpend);
            request.getSession().setAttribute("marchSpend",marchSpend);
            request.getSession().setAttribute("aprilSpend",aprilSpend);
            request.getSession().setAttribute("maySpend",maySpend);
            request.getSession().setAttribute("juneSpend",juneSpend);
            request.getSession().setAttribute("julySpend",julySpend);
            request.getSession().setAttribute("augustSpend",augustSpend);
            request.getSession().setAttribute("septemberSpend",septemberSpend);
            request.getSession().setAttribute("octoberSpend",octoberSpend);
            request.getSession().setAttribute("novemberSpend",novemberSpend);
            request.getSession().setAttribute("decemberSpend",decemberSpend);

            request.getSession().setAttribute("totalSpendForMonth",totalSpendForMonth);


            request.getSession().setAttribute("januaryIncome",januaryIncome);
            request.getSession().setAttribute("februaryIncome",februaryIncome);
            request.getSession().setAttribute("marchIncome",marchIncome);
            request.getSession().setAttribute("aprilIncome",aprilIncome);
            request.getSession().setAttribute("mayIncome",mayIncome);
            request.getSession().setAttribute("juneIncome",juneIncome);
            request.getSession().setAttribute("julyIncome",julyIncome);
            request.getSession().setAttribute("augustIncome",augustIncome);
            request.getSession().setAttribute("septemberIncome",septemberIncome);
            request.getSession().setAttribute("octoberIncome",octoberIncome);
            request.getSession().setAttribute("novemberIncome",novemberIncome);
            request.getSession().setAttribute("decemberIncome",decemberIncome);

            request.getSession().setAttribute("totalIncomeForMonth",totalIncomeForMonth);

            request.getSession().setAttribute("totalAllCosts",totalAllCosts);

            incomePercent = calculatePercentage(totalIncomeForMonth,totalAllCosts);
            spendPercent = calculatePercentage(totalSpendForMonth,totalAllCosts);

            incomePercent = Math.floor(incomePercent * 10) / 10;
            spendPercent = Math.floor(spendPercent * 10) / 10;

            request.getSession().setAttribute("incomePercent",incomePercent);
            request.getSession().setAttribute("spendPercent",spendPercent);

            request.getRequestDispatcher("/graphs.jsp").include(request, response);

        }

        catch (CostManagementException e){
            request.setAttribute("error",e.getMessage());
            request.getRequestDispatcher("err.jsp").forward(request, response);
            e.printStackTrace();
        }

    }

    /**
     * logout the current user and invalidate his session and erase the cookies
     * @param request an object that represent the request
     * @param response and object that represent the response
     * @throws ServletException
     * @throws IOException
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userId = (int) request.getSession().getAttribute("currentUser");

        request.getSession().invalidate();
        eraseCookies(request, response);
        request.getRequestDispatcher("/logout.jsp").include(request, response);

    }

    /**
     * This function erases the cookies from the current user
     * @param req an object that represent the request
     * @param resp and object that represent the response
     */
    private void eraseCookies(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
    }


    /**
     * This function add cookies 'userName' and 'password' for the user
     * for an auto login
     * @param response an object that represent the response
     * @param userName an object the represent the user name of the user
     * @param password an object the represent the password of the user
     */
    private void addCookies(HttpServletResponse response,String userName,String password) {

        javax.servlet.http.Cookie userNameCookie = new javax.servlet.http.Cookie("userName",userName);
        javax.servlet.http.Cookie passwordCookie = new Cookie("password", password);
        response.addCookie(userNameCookie);
        response.addCookie(passwordCookie);
    }


    /**
     * This function calculate the percentage of the income and spend for the current month
     * @param obtained the income object or spend object
     * @param total the total sum of income and spend
     * @return the percentage of the obtained object
     */
    public double calculatePercentage(double obtained, double total) {
        return obtained * 100 / total;
    }






}

