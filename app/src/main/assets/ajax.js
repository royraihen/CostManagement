$(function (){

    $('#registerbtn').on('click', function (){
        var $email = $('#email');
        var $password = $('#password');
        var user = {
            email: $email.val(),
            password: $password.val(),
        }
        $.ajax({
            url: 'http://10.0.2.2:8080/costmanagement/rest/users/register',
            type: 'POST',
            dataType: 'json',
            timeout: 30000,
            data: JSON.stringify(user),
            contentType: "application/json; charset=utf-8",
            success: function (response){
                 console.log("POST REG SUCCESS");
                 vm.makeToast("Registered Successfully!");
                 vm.goToHome();
            },
            error: function (){
                vm.makeToast("Register Failed!");
                console.log("POST REG FAILED");
                vm.goToRegister();
            }
        });
    })

    $('#logbtn').on('click', function (){
        console.log("alkosdjf");
        var $email = $('#login-email');
        var $password = $('#login-password');
        var user = {
            email: $email.val(),
            password: $password.val(),
        };
        $.ajax({
            url:'http://10.0.2.2:8080/costmanagement/rest/users/authentication',
            type: 'POST',
            data: JSON.stringify(user),
            dataType: 'json',
            timeout: 30000,
            contentType: "application/json; charset=utf-8",
            traditional: true,
            success: function (){
                 var end = '' + $email.val();
                 console.log("POST LOG SUCCESS");
                 $.ajax({
                        url: 'http://10.0.2.2:8080/costmanagement/rest/users/user/' + end,
                        type: 'GET',
                        timeout: 30000,
                        contentType: "application/json; charset=utf-8",
                        success: function(jsonID){
                            var test =  JSON.parse(jsonID);
                            id = test.currentUser;
                            vm.saveId(id);
                            vm.makeToast("Login Successfully!");
                            vm.goToHome();
                        },
                        error: function(){
                            vm.makeToast("Login Failed!");
                            console.log("FAILED GETTING ID");
                        }
                });
            },
            error: function (){
                vm.makeToast("Login Failed!");
                console.log("POST LOG FAILED");
            }
        });
    });


    $('#add-spend').on('click', function (){
        var id = vm.getId();
        var cbChecker = checkCB();
        var $amount = $('#amount-spent');
        var $comment = $('#comment-spend');
        var $category = $('#cat-spend');
        var data = {
            userId: id,
            amount: $amount.val(),
            category: $category.val(),
            permanentSpend: cbChecker,
            comment: $comment.val()
        }
        $.ajax({
            url: 'http://10.0.2.2:8080/costmanagement/rest/actions/addspend',
            type: 'POST',
            dataType: 'json',
            timeout: 30000,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            success: function (){
                 console.log("POST SPEND SUCCESS");
                 vm.makeToast("Spend Added!");
                 vm.goToHome();
            },
            error: function (){
                vm.makeToast("Failed Adding Spend!");
                console.log("POST SPEND FAILED");
            }
        });
    })


    $('#add-incoming').on('click', function (){
        var id = vm.getId();
        var cbChecker = checkCB();
        var $amount = $('#amount-incoming');
        var $comment = $('#comment-incoming');

        var data = {
            userId: id,
            amount: $amount.val(),
            permanentIncome: cbChecker,
            comment: $comment.val()
        }

        console.log(JSON.stringify(data));
        $.ajax({
            url: 'http://10.0.2.2:8080/costmanagement/rest/actions/addincome',
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(data),
            timeout: 30000,
            contentType: "application/json; charset=utf-8",
            success: function (){
                 console.log("POST INCOME SUCCESS");
                 vm.makeToast("Income Added!");
                 vm.goToHome();
            },
            error: function (){
                vm.makeToast("Failed Adding Income!");
                console.log("POST INCOME FAILED");

            }
        });
    })

    $('#show').on('click', function (){
        var $month = $('#month');
        var month_str = ''+$month.val();

        var monthlyIncome;
        var monthlySpend;
        var monthlyTotal;

        monthlyIncome = getIncome(month_str,monthlyIncome,monthlySpend,monthlyTotal);
    })

    function getSpend(month_str,monthlySpend, monthlyIncome, monthlyTotal){
        var temp;
        $.ajax({
                url: 'http://10.0.2.2:8080/costmanagement/rest/actions/spend/month/' + vm.getId()+ '/' + month_str,
                type: 'GET',
                timeout: 30000,
                contentType: "application/json; charset=utf-8",
                success: function(response){
                    console.log("GET SPEND SUCCESSFUL");
                    var parser =  JSON.parse(response);
                    monthlySpend = parser.spendByMonth;
                    monthlyTotal = monthlyIncome - monthlySpend;

                    document.getElementById("money").value = ''+monthlyTotal;

                },
                error: function(){
                    console.log("FAILED GETTING MONTH SPEND");
                }
        });
    }

    function getIncome(month_str,monthlyIncome,monthlySpend,monthlyTotal){
        var temp;
        $.ajax({
                url: 'http://10.0.2.2:8080/costmanagement/rest/actions/income/month/' + vm.getId()+ '/' + month_str,
                type: 'GET',
                timeout: 30000,
                contentType: "application/json; charset=utf-8",
                success: function(response){
                    console.log("GET INCOME SUCCESSFUL");
                    var parser =  JSON.parse(response);
                    monthlyIncome = parser.incomeByMonth;
                    monthlySpend = getSpend(month_str,monthlySpend, monthlyIncome, monthlyTotal);
                },
                error: function(){
                    console.log("FAILED GETTING MONTH INCOME");
                }
        });
    }
});