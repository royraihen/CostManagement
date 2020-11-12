<%--
  Created by IntelliJ IDEA.
  User: Maor
  Date: 01/08/2020
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    private double shopping = 0;
    private double transport = 0;
    private double restaurant = 0;
    private double health = 0;
    private  double family = 0;
    private double groceries = 0;
    private double leisure = 0;
    private double government = 0;
    private  double food = 0;
    private double totalAmount = 0;
%>


<%
   shopping = (double) request.getSession().getAttribute("shopping");
    transport = (double)request.getSession().getAttribute("transport");
    restaurant = (double)request.getSession().getAttribute("restaurant");
    health = (double) request.getSession().getAttribute("health");
    family = (double) request.getSession().getAttribute("family");
    groceries = (double)request.getSession().getAttribute("groceries");
    leisure = (double) request.getSession().getAttribute("leisure");
    government = (double) request.getSession().getAttribute("government");
    food = (double) request.getSession().getAttribute("food");

    double[] categories ={shopping,transport,restaurant,health,family,
                        groceries,leisure,government,food};

    for(double amount : categories){
        totalAmount = totalAmount + amount;
    }

%>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css">

    <script src="jquery-3.3.1.min.js"></script>
    <script src="circle-progress.min.js"></script>
</head>
<body>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<div class="container">

    <div class="row text-center">


        <div class="col-sm-4">
            <div class="progressbar">
                <div class="first circle" data-percent="<%=shopping%>">
                    <strong></strong>
                    <span style="color:#FA6800">Shopping</span>
                </div>
            </div>
        </div>

        <div class="col-sm-4">
            <div  class="progressbar">
                <div class="second circle" data-percent="<%=transport%>">
                    <strong></strong>
                    <span style="color:#FA6800">Transport</span>
                </div>
            </div>
        </div>

        <div class="col-sm-4">
            <div class="progressbar">
                <div class="second circle" data-percent="<%=restaurant%>">
                    <strong></strong>
                    <span style="color:#FA6800">Restaurant</span>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="progressbar">
                <div class="second circle" data-percent="<%=health%>">
                    <strong ></strong>
                    <span style="color:#FA6800">Health</span>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="progressbar">
                <div class="second circle" data-percent="<%=family%>">
                    <strong></strong>
                    <span style="color:#FA6800">Family</span>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="progressbar">
                <div class="second circle" data-percent="<%=groceries%>">
                    <strong></strong>
                    <span style="color:#FA6800">Groceries</span>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="progressbar">
                <div class="second circle" data-percent="<%=leisure%>">
                    <strong></strong>
                    <span style="color:#FA6800">Leisure</span>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="progressbar">
                <div class="second circle" data-percent="<%=government%>">
                    <strong></strong>
                    <span style="color:#FA6800">Government</span>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="progressbar">
                    <div class="second circle" data-percent="<%=food%>">
                    <strong></strong>
                    <span style="color:#FA6800">Food</span>
                </div>
            </div>
        </div>




    </div>
</div>
<script>


    $(document).ready(function ($) {
        function animateElements() {
            $('.progressbar').each(function () {
                var elementPos = $(this).offset().top;
                var topOfWindow = $(window).scrollTop();
                var percent = $(this).find('.circle').attr('data-percent');
                var animate = $(this).data('animate');
                var colors = ["#EA6B66", "#000" , "#fff"]
                if (elementPos < topOfWindow + $(window).height() - 30 && !animate) {
                    $(this).data('animate', true);
                    $(this).find('.circle').circleProgress({
                        // startAngle: -Math.PI / 2,
                        value: percent / <%=totalAmount%>,
                        size : 180,
                        thickness: 5,
                        fill: {
                            color: '#EA6B66' ,
                        }
                    }).on('circle-animation-progress', function (event, progress, stepValue) {
                        $(this).find('strong').text((stepValue*<%=totalAmount%>).toFixed(0) + "$");
                    }).stop();
                }
            });
        }

        animateElements();
        $(window).scroll(animateElements);
    });



</script>
<script src="https://rawgit.com/kottenator/jquery-circle-progress/1.2.2/dist/circle-progress.js"></script>
</body>
</html>
