<%@ page import="il.ac.hit.costmanagement.model.IUserDAO" %>
<%@ page import="il.ac.hit.costmanagement.model.CostManagementDAO" %>
<%@ page import="il.ac.hit.costmanagement.exception.CostManagementException" %>
<%@ page import="il.ac.hit.costmanagement.dm.User" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.IOException" %><%--
  Created by IntelliJ IDEA.
  User: Maor
  Date: 24/07/2020
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%!
 /*   private String userNameCookie = null;
    private String passwordCookie = null;
    boolean isLoginSucceed;
    IUserDAO dao = CostManagementDAO.getInstance();*/
%>

<%


   /* getCookies(request);

    System.out.println("user: " + userNameCookie );
    System.out.println("password: " + passwordCookie );

    if (userNameCookie != null && passwordCookie != null) {
        try {
           isLoginSucceed =  dao.userAuthentication(userNameCookie, passwordCookie);
           if(isLoginSucceed) {
               User user = dao.getCurrentUser(userNameCookie);
               //request.setAttribute("currentUser", user);
               request.setAttribute("currentUser",user);

               response.sendRedirect(request.getContextPath()+"/home.jsp");
             //  request.getRequestDispatcher("home.jsp").forward(request, response);
             //  response.sendRedirect("home.jsp");
              // response.sendRedirect("home.jsp");
              // session.setAttribute("currentUser",user);
             *//*  request.getRequestDispatcher("graphs.jsp").forward(request, response);
               response.sendRedirect("graphs.jsp");
               out.println("<script type='text/javascript'>");
               RequestDispatcher dispatcher = request.getRequestDispatcher("function.js");
               dispatcher.forward(request,response);
               response.sendRedirect("function.js");
               out.println("</script>");*//*

           }
        } catch (CostManagementException e) {
            e.printStackTrace();
        }
    }*/
%>



<%!

 /*   private void getCookies(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();

        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userName"))
                    userNameCookie = cookie.getValue();
                if (cookie.getName().equals("password")) {
                    passwordCookie = cookie.getValue();
                }
            }
        }

    }*/
%>


<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
<link rel="stylesheet" href="css/style.css">


<nav class="navbar navbar-expand-lg navbar-light navbar-laravel">
    <div class="container">
        <a class="navbar-brand" href="#">Cost Management</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="register.jsp">Register</a>
                </li>

            </ul>

        </div>
    </div>
</nav>


<!------ Include the above in your HEAD tag ---------->


<main class="register-form">
    <div class="container">

     <%--   <div class="row">--%>

            <aside class="col-sm-4">

                <div class="card">
                    <article class="card-body">
                        <h4 class="card-title text-center mb-4 mt-1">Sign in</h4>
                        <p class="text-success text-center">Welcome</p>
                        <hr>
                        <form method="get" action="login" >
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                    </div>
                                    <input name="email" class="form-control" placeholder="Email" type="email">
                                </div> <!-- input-group.// -->
                            </div> <!-- form-group// -->
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                                    </div>
                                    <input name="password" class="form-control" placeholder="******" type="password">
                                </div> <!-- input-group.// -->
                            </div> <!-- form-group// -->
                            <div class="form-group row">
                                <div class="col-md-6 ">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" name="remember"> Remember Me
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary btn-block" > Login  </button>
                            </div> <!-- form-group// -->
                        </form>
                    </article>
                </div> <!-- card.// -->

            </aside> <!-- col.// -->
       <%-- </div> <!-- row.// -->--%>

    </div>
</main>




<%--<main class="register-form">
<div class="container">

    <div class="row">

        <aside class="col-sm-4">

            <div class="card">
                <article class="card-body">
                    <h4 class="card-title text-center mb-4 mt-1">Sign in</h4>
                    <p class="text-success text-center">Welcome</p>
                    <hr>
                    <form method="get" action="login" >
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                </div>
                                <input name="email" class="form-control" placeholder="Email" type="email">
                            </div> <!-- input-group.// -->
                        </div> <!-- form-group// -->
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                                </div>
                                <input name="password" class="form-control" placeholder="******" type="password">
                            </div> <!-- input-group.// -->
                        </div> <!-- form-group// -->
                        <div class="form-group row">
                            <div class="col-md-6 offset-md-4">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="remember"> Remember Me
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block" > Login  </button>
                        </div> <!-- form-group// -->
                    </form>
                </article>
            </div> <!-- card.// -->

        </aside> <!-- col.// -->
    </div> <!-- row.// -->

</div>
</main>--%>








<%--  <div class="form-group row">
                              <div class="col-sm-5 offset-md-4">
                                 &lt;%&ndash; <div class="checkbox">
                                      <label>
                                          <input type="checkbox" name="remember"> Remember Me
                                      </label>
                                  </div>&ndash;%&gt;
                              </div>
                          </div>--%>
