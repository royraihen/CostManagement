<%@ page import="il.ac.hit.costmanagement.dm.User" %>
<%@ page import="il.ac.hit.costmanagement.exception.CostManagementException" %>
<%@ page import="il.ac.hit.costmanagement.model.CostManagementDAO" %>
<%@ page import="il.ac.hit.costmanagement.model.IIncomingDAO" %>
<%@ page import="il.ac.hit.costmanagement.model.ISpendDAO" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.util.Calendar" %>
<%--
  Created by IntelliJ IDEA.
  User: Maor
  Date: 24/07/2020
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!

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

    double januaryIncome = 0;
    double februaryIncome = 0;
    double marchIncome = 0;
    double aprilIncome = 0;
    double mayIncome = 0;
    double juneIncome = 0;
    double julyIncome = 0;
    double augustIncome = 0;
    double septemberIncome = 0;
    double octoberIncome = 0;
    double novemberIncome = 0;
    double decemberIncome = 0;

    double totalIncomeForMonth = 0;
    double totalSpendForMonth = 0;
    double totalAllCosts = 0;
    double incomePercent = 0;
    double spendPercent = 0;

%>

<%

    januarySpend = (double)request.getSession().getAttribute("januarySpend");
    februarySpend = (double) request.getSession().getAttribute("februarySpend");
    marchSpend = (double)request.getSession().getAttribute("marchSpend");
    aprilSpend = (double) request.getSession().getAttribute("aprilSpend");
    maySpend = (double)request.getSession().getAttribute("maySpend");
    juneSpend = (double) request.getSession().getAttribute("juneSpend");
    julySpend = (double) request.getSession().getAttribute("julySpend");
    augustSpend = (double) request.getSession().getAttribute("augustSpend");
    septemberSpend = (double) request.getSession().getAttribute("septemberSpend");
    octoberSpend = (double) request.getSession().getAttribute("octoberSpend");
    novemberSpend = (double) request.getSession().getAttribute("novemberSpend");
    decemberSpend = (double) request.getSession().getAttribute("decemberSpend");

    totalSpendForMonth = (double) request.getSession().getAttribute("totalSpendForMonth");



    januaryIncome = (double)request.getSession().getAttribute("januaryIncome");
    februaryIncome = (double) request.getSession().getAttribute("februaryIncome");
    marchIncome = (double)request.getSession().getAttribute("marchIncome");
    aprilIncome = (double) request.getSession().getAttribute("aprilIncome");
    mayIncome = (double)request.getSession().getAttribute("mayIncome");
    juneIncome = (double) request.getSession().getAttribute("juneIncome");
    julyIncome = (double) request.getSession().getAttribute("julyIncome");
    augustIncome = (double) request.getSession().getAttribute("augustIncome");
    septemberIncome = (double) request.getSession().getAttribute("septemberIncome");
    octoberIncome = (double) request.getSession().getAttribute("octoberIncome");
    novemberIncome = (double) request.getSession().getAttribute("novemberIncome");
    decemberIncome = (double) request.getSession().getAttribute("decemberIncome");



    totalIncomeForMonth = (double) request.getSession().getAttribute("totalIncomeForMonth");


    totalAllCosts = (double) request.getSession().getAttribute("totalAllCosts");

     incomePercent = (double)request.getSession().getAttribute("incomePercent");
     spendPercent = (double)request.getSession().getAttribute("spendPercent");

%>

<html>
<head>

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['Month', 'Incoming', 'Spend'],
                ['January',  <%=januaryIncome%>,      <%=januarySpend%>],
                ['February',  <%=februaryIncome%>,      <%=februarySpend%>],
                ['March',  <%=marchIncome%>,       <%=marchSpend%>],
                ['April',  <%=aprilIncome%>,      <%=aprilSpend%>],
                ['May',  <%=mayIncome%>,       <%=maySpend%>],
                ['June',  <%=juneIncome%>,       <%=juneSpend%>],
                ['July',  <%=julyIncome%>,       <%=julySpend%>],
                ['August',  <%=augustIncome%>,       <%=augustSpend%>],
                ['September',  <%=septemberIncome%>,       <%=septemberSpend%>],
                ['October',  <%=octoberIncome%>,       <%=octoberSpend%>],
                ['November',  <%=novemberIncome%>,       <%=novemberSpend%>],
                ['December',  <%=decemberIncome%>,       <%=decemberSpend%>],
            ]);

            var options = {
                title: 'My year cost',
                curveType: 'function',
                legend: { position: 'bottom' },
                backgroundColor: 'transparent'
            };

            var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

            chart.draw(data, options);
        }
    </script>
    <link rel="stylesheet" href="css/style.css" />
</head>
<body>

<div id="curve_chart"  style="width: 1000px; height: 400px"></div>
</body>
</html>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <link
            href="https://fonts.googleapis.com/css?family=Montserrat&display=swap"
            rel="stylesheet"
    />
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"
    />
    <link rel="stylesheet" href="css/style.css" />
</head>
<body>
<!-- Container containing all the elements -->
<div class="circleBar">

    <!-- Refresh button -->
    <!-- Container for circular progress bar -->
    <div class="container ">
        <div class="row align-items-center">
            <div class="col-md-6">
                <div
                        class="spend"
                        data-value="<%=spendPercent/100%>"
                        data-size="160"
                        data-thickness="4"
                >
                    <strong></strong>
                </div>
            </div>
            <div class="col-md-6">
                <div
                        class="round"
                        data-value="<%=incomePercent/100%>"
                        data-size="160"
                        data-thickness="4"
                >
                    <strong></strong>
                </div>
            </div>
        </div>
    </div>
    <div class="webloper-sm">

    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-circle-progress/1.2.0/circle-progress.min.js"></script>
<script src="function.js"></script>
</body>
</html>
