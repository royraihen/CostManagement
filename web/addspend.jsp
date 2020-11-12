<%--
  Created by IntelliJ IDEA.
  User: Maor
  Date: 01/08/2020
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>


<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
<link rel="stylesheet" href="css/style.css">
<head>
    <title>Title</title>
</head>
<body>
<main class="login-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-sm-5">
                <div class="card">
                    <div class="card-header">Add amount</div>
                    <div class="card-body">
                        <form  action="addspend" method="get">

                            <div class="form-group row">
                                <label for="amount" class="col-md-4 col-form-label text-md-left">Amount</label>
                                <div class="col-sm-8">
                                    <input type="number" id="amount" class="form-control" name="amount" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="amount" class="col-md-4 col-form-label text-md-left">Category</label>
                                <select name="category" class=" col-sm-6 form-control">
                                    <option value="">Select Option</option>
                                    <option value="Shopping">Shopping</option>
                                    <option value="Transport">Transport</option>
                                    <option value="Restaurant">Restaurant</option>
                                    <option value="Health">Health</option>
                                    <option value="Family">Family</option>
                                    <option value="Groceries">Groceries</option>
                                    <option value="Leisure">Leisure</option>
                                    <option value="Government">Government</option>
                                    <option value="Food">Food</option>
                                </select>
                            </div>
                            <div class="form-group row">
                                <label for="amount" class="col-md-4 col-form-label text-md-left">Permanent spend?</label>
                                <select name="permanentspend" class=" col-sm-6 form-control">
                                    <option value="">Select Option</option>
                                    <option value="true">Yes</option>
                                    <option value="false">No</option>
                                </select>
                            </div>
                            <div class="form-group row">
                                <label for="comment" class="col-md-4 col-form-label text-md-left">Comment</label>
                                <div class="col-sm-8">
                                    <input type="text" id="comment" class="form-control" name="comment" required autofocus>
                                </div>
                            </div>
                            <div class="col-sm-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    Add
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</main>
</body>
</html>

