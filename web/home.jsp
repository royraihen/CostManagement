<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.GregorianCalendar" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    LocalDate date = new java.sql.Date(Calendar.getInstance().getTimeInMillis()).toLocalDate();
    int month = date.getMonth().getValue();
    int year = date.getYear();
    Calendar monthStart = new GregorianCalendar(year, month, 1);
    int maxDaysInMonth = monthStart.getActualMaximum(Calendar.DAY_OF_MONTH);
    int dayOfMonth = date.getDayOfMonth();




    double totalForMonth = 0;
    double spendForMonth = 0;
    double incomeForMonth = 0;

%>

<%

    System.out.println("Arrive");

    System.out.println();

    spendForMonth = (double) request.getSession().getAttribute("spendForMonth");
    incomeForMonth = (double) request.getSession().getAttribute("incomeForMonth");
    totalForMonth = (double) request.getSession().getAttribute("totalAmountForMonth");

    System.out.println(spendForMonth);
    System.out.println(incomeForMonth);
    System.out.println(totalForMonth);

%>
<%--<html>

<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<!------ Include the above in your HEAD tag ---------->--%>



<!DOCTYPE html><html lang='en' class=''>
<head>



    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
    <link rel="stylesheet" href="css/style.css">
    <div>
        <nav class="navbar navbar-expand-lg navbar-light navbar-laravel">
            <div class="container">
                <a class="navbar-brand" href="home.jsp">Cost Management</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/graphs">Statistics</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/categories">Statistics per category</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="addincome.jsp">Add income</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="addspend.jsp">Add spend</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
                        </li>

                    </ul>

                </div>
            </div>
        </nav>

    <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css'><link rel='stylesheet prefetch' href='//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css'>
    <style class="cp-pen-styles">@import url(https://fonts.googleapis.com/css?family=Lato:400,300,700);
    @import url(https://fonts.googleapis.com/css?family=Lato:400,300,700);
    body {
        -webkit-text-size-adjust: 100%;
        font-family: 'Lato', sans-serif;
        text-rendering: optimizeLegibility;
        -webkit-font-smoothing: antialiased;
    }

    input {
        -webkit-appearance: none;
    }

    *, *:after, *:before {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
    }

    a {
        text-decoration: none;
    }

    .pen-description {
        margin: 60px 0;
        text-align: center;
    }
    .pen-description .summary {
        margin-bottom: 10px;
    }
    .pen-description .note {
        color: #555;
        font-size: .85rem;
    }

    html, body {
        min-height: 100%;
    }

    html {
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        font-size: initial;
    }

    /* boostrap overrides */
    a, a:hover, a:visited {
        text-decoration: none;
    }

    p {
        margin: 0;
    }

    label {
        display: initial;
    }

    /*******************/
    body {
        background-image: url('data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz4gPHN2ZyB2ZXJzaW9uPSIxLjEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+PGRlZnM+PGxpbmVhckdyYWRpZW50IGlkPSJncmFkIiBncmFkaWVudFVuaXRzPSJvYmplY3RCb3VuZGluZ0JveCIgeDE9IjAuMCIgeTE9IjEuMCIgeDI9IjEuMCIgeTI9IjAuMCI+PHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iIzIyNmE5NiIvPjxzdG9wIG9mZnNldD0iMTAwJSIgc3RvcC1jb2xvcj0iIzk5ZDVlOCIvPjwvbGluZWFyR3JhZGllbnQ+PC9kZWZzPjxyZWN0IHg9IjAiIHk9IjAiIHdpZHRoPSIxMDAlIiBoZWlnaHQ9IjEwMCUiIGZpbGw9InVybCgjZ3JhZCkiIC8+PC9zdmc+IA==');
        background-size: 100%;
        background-image: -webkit-gradient(linear, 0% 100%, 100% 0%, color-stop(0%, #226a96), color-stop(100%, #99d5e8));
        background-image: -webkit-linear-gradient(left bottom, #226a96 0%, #99d5e8 100%);
        background-image: linear-gradient(to right top, #226a96 0%, #99d5e8 100%);
        font-family: 'Lato';
        background-color: #eee;
        color: #555;
        min-width: 100%;
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        -webkit-perspective: 8000px;
        perspective: 8000px;
    }

    * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
    }

    #app {
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        -webkit-box-flex: 1;
        -ms-flex-positive: 1;
        flex-grow: 1;
    }

    ul {
        list-style: none;
        margin: auto;
        padding: 0;
    }

    .card-container {
        -webkit-transform-style: preserve-3d;
        transform-style: preserve-3d;
        -webkit-transition: -webkit-transform 1s;
        transition: -webkit-transform 1s;
        transition: transform 1s;
        transition: transform 1s, -webkit-transform 1s;
    }

    li {
        cursor: -webkit-grab;
        cursor: grab;
        position: relative;
        -webkit-transform-style: preserve-3d;
        transform-style: preserve-3d;
        float: left;
    }
    li[draggable=false] {
        cursor: default;
    }
    li[draggable=false] figure.front,
    li[draggable=false] figure.back {
        box-shadow: none;
    }
    li .card-container {
        pointer-events: none;
    }
    li input[type="text"],
    li input[type="number"] {
        border: 0 none;
        border-bottom: 1px solid #eee;
        pointer-events: initial;
        width: 100%;
        height: 40px;
        margin-bottom: 20px;
        outline: 0 none;
        font-size: 1rem;
        -webkit-transition: border-bottom 0.2s ease-in-out;
        transition: border-bottom 0.2s ease-in-out;
    }
    li input[type="text"]:focus,
    li input[type="number"]:focus {
        border-bottom: 1px solid #3a8bbb;
    }
    li.placeholder {
        background: rgba(255, 255, 255, 0.2);
        height: 494px;
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        width: 300px;
        border-radius: 5px;
    }

    .modal {
        overflow-y: auto;
    }
    .modal-dialog {
        width: 400px;
    }
    .modal .fund-container {
        border: 1px solid #ddd;
        border-radius: 3px;
        height: 40px;
        margin-left: 5px;
        margin-top: 20px;
        margin-bottom: 10px;
        padding: 10px 0;
        width: 125px;
        text-align: right;
    }
    .modal label {
        display: inline-block;
        width: 48%;
    }
    .modal .data {
        width: 50%;
        text-align: right;
        margin-left: 5px;
        display: inline-block;
    }
    .modal .smallprint {
        font-size: .7rem;
        text-align: right;
    }
    .modal .pre-money {
        opacity: .7;
    }

    h1, h2, h3, h4, h5 {
        margin: 0;
    }

    .modal-container {
        position: relative;
    }

    .modal-container .modal, .modal-container .modal-backdrop {
        position: absolute;
    }

    button {
        pointer-events: initial;
    }

    .card-title {
        text-align: center;
        margin-bottom: 20px;
        font-size: 1rem;
    }

    .back-arrow {
        pointer-events: initial;
        display: table;
        position: absolute;
        padding: 20px;
        margin: -20px;
        font-size: 1.2rem;
    }
    .back-arrow:hover .fa {
        color: #777777;
    }

    .edit-money {
        display: table;
        position: absolute;
        margin-top: 10px;
        opacity: .7;
    }
    .edit-money + input {
        padding-left: 15px;
    }

    .delete {
        color: #777;
        pointer-events: initial;
        text-align: center;
        font-size: .8rem;
        margin: 0 auto;
        margin-top: 10px;
        padding: 10px;
        display: table;
        -webkit-transition: color 0.2s ease-in-out;
        transition: color 0.2s ease-in-out;
    }
    .delete:hover {
        color: #e54b4b;
    }

    figure {
        -webkit-backface-visibility: hidden;
        backface-visibility: hidden;
    }
    figure.front {
        background: #fff;
        -webkit-transform: rotateY(0deg);
        transform: rotateY(0deg);
        overflow: hidden;
        pointer-events: intial;
        border-radius: 5px;
        padding: 20px;
        margin: 10px;
        width: 280px;
        -webkit-transition: all 0.2s ease-in-out;
        transition: all 0.2s ease-in-out;
        box-shadow: 0 10px 20px 0 rgba(13, 59, 86, 0.5);
    }
    figure.back {
        background-color: #fff;
        -webkit-transform: rotateY(180deg);
        transform: rotateY(180deg);
        -ms-flex-wrap: wrap;
        flex-wrap: wrap;
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        -webkit-box-orient: vertical;
        -webkit-box-direction: normal;
        -ms-flex-direction: column;
        flex-direction: column;
        position: absolute;
        left: 0;
        margin: 10px;
        margin-top: 0;
        padding: 20px;
        width: 330px;
        height: 100%;
        top: 0;
        border-radius: 5px;
        opacity: 0;
        -webkit-transition: all .3s ease-in-out;
        transition: all .3s ease-in-out;
    }
    figure .input-container {
        -webkit-box-flex: 1;
        -ms-flex-positive: 1;
        flex-grow: 1;
    }

    .flipped figure.back {
        opacity: 1;
    }

    .flipped {
        transform: rotateY(180deg);
        -webkit-transform: rotateY(180deg);
    }

    .goal__name {
        color: #777;
        font-size: 1.25rem;
        text-align: center;
        width: 50%;
        margin: 10px auto;
        text-transform: uppercase;
        letter-spacing: 3px;
    }
    .goal--progress {
        color: #222;
        font-size: 1rem;
        text-align: center;
        margin-top: 10px;
    }
    .goal--remain {
        color: #222;
    }
    .goal--amount {
        color: #777;
        font-size: .7rem;
        text-align: center;
        margin-bottom: 10px;
    }

    .goal--top {
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        -webkit-box-orient: vertical;
        -webkit-box-direction: normal;
        -ms-flex-direction: column;
        flex-direction: column;
        position: relative;
        height: 300px;
    }
    .goal--top__container {
        margin: auto;
        position: relative;
    }

    .edit {
        pointer-events: initial;
        padding: 20px;
        position: absolute;
        right: 0;
        margin: -20px;
        top: 0;
        margin-top: -13px;
    }
    .edit:hover .fa {
        color: #777777;
    }
    .edit .fa {
        font-size: 1.2rem;
    }

    .money:after {
        content: "";
    }

    .goal--progress:after{
        content: "$";
    }

    .goal--amount:after{
        content: "$";
    }



    .right {
        float: right;
        margin-left: 5px;
    }

    .percent {
        color: #777;
    }

    .descriptor {
        font-size: .8rem;
        margin-bottom: 10px;
    }

    .dropdown-wrapper {
        margin-top: 10px;
        pointer-events: initial;
        border: 1px solid #eee;
        border-radius: 5px;
        position: relative;
        cursor: pointer;
        width: 100%;
        -webkit-transition: border 0.2s ease-in-out;
        transition: border 0.2s ease-in-out;
    }
    .dropdown-wrapper:hover {
        border: 1px solid #d5d5d5;
    }
    .dropdown-wrapper:after {
        font-family: 'FontAwesome';
        content: "\f107";
        color: #3FB8AF;
        position: absolute;
        right: 10px;
        top: 7px;
    }
    .dropdown-wrapper.outline {
        border: 1px solid rgba(63, 184, 175, 0.5);
        box-shadow: 0 0 10px rgba(63, 184, 175, 0.2);
    }

    select {
        cursor: pointer;
        pointer-events: initial;
        -webkit-appearance: none;
        -moz-appearance: none;
        appearance: none;
        background: none;
        border: 0 none;
        font-size: .8em;
        padding: 10px;
        outline: 0;
        width: 100%;
        display: block;
    }

    .button {
        background: #3a8bbb;
        border: 0 none;
        color: #fff;
        margin-bottom: auto;
        cursor: pointer;
        border-radius: 5px;
        pointer-events: initial;
        height: 44px;
        text-transform: uppercase;
        text-align: center;
        font-size: .7rem;
        letter-spacing: 2px;
        padding: 15px;
        -webkit-transition: all 0.2s ease-in-out;
        transition: all 0.2s ease-in-out;
        width: 100%;
    }
    .button:hover {
        background: #347da8;
    }

    .fa {
        font-family: 'FontAwesome';
        display: block;
        text-align: center;
    }
    .fa.category {
        left: 0;
        right: 0;
        top: -40px;
        margin: auto;
        position: absolute;
    }
    .fa.fa-plane {
        color: #3FB8AF;
    }
    .fa.fa-car {
        color: #e54b4b;
    }
    .fa.fa-pencil-square-o, .fa.fa-angle-left {
        color: #aaa;
        -webkit-transition: color 0.2s ease-in-out;
        transition: color 0.2s ease-in-out;
    }

    /** svg **/
    .svg-container {
        display: inline-block;
        position: relative;
        width: 100%;
        height: 100%;
        vertical-align: middle;
        overflow: hidden;
    }

    .svg-content {
        display: inline-block;
        margin: 0 auto;
        margin-top: 20px;
        width: 100%;
    }

    .animated {
        -webkit-animation: dash 1s cubic-bezier(0.165, 0.84, 0.44, 1) forwards;
        animation: dash 1s cubic-bezier(0.165, 0.84, 0.44, 1) forwards;
    }

    .box {
        width: 80%;
        height: 80%;
        margin: 0 auto;
        margin-bottom: 30px;
        position: absolute;
        left: 0;
        right: 0;
    }

    svg {
        -webkit-transform: rotate(-90deg);
        transform: rotate(-90deg);
    }

    text {
        text-anchor: middle;
    }

    circle {
        stroke-linecap: round;
    }

    .shadow {
        opacity: 0.8;
    }

    @-webkit-keyframes dash {
        to {
            stroke-dashoffset: 0;
        }
    }

    @keyframes dash {
        to {
            stroke-dashoffset: 0;
        }
    }
    </style></head><body>


<div id="app"></div>
<script src='//production-assets.codepen.io/assets/common/stopExecutionOnTimeout-b2a7b3fe212eaa732349046d8416e00a9dec26eb7fd347590fbced3ab38af52e.js'></script><script src='https://fb.me/react-with-addons-15.0.0.min.js'></script><script src='https://npmcdn.com/react-dom@15.3.0/dist/react-dom.min.js'></script><script src='https://cdnjs.cloudflare.com/ajax/libs/react-bootstrap/0.29.5/react-bootstrap.min.js'></script><script src='https://npmcdn.com/react-dnd@2.1.4/dist/ReactDnD.js'></script><script src='https://npmcdn.com/react-dnd-html5-backend@2.1.2/dist/ReactDnDHTML5Backend.min.js'></script>
<script >//var ReactCSSTransitionGroup = React.addons.CSSTransitionGroup;
'use strict';

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var placeholder = document.createElement("li");
placeholder.className = "placeholder";
var Button = ReactBootstrap.Button;
var Modal = ReactBootstrap.Modal;
var OverlayTrigger = ReactBootstrap.OverlayTrigger;

var Pie = function (_React$Component) {
    _inherits(Pie, _React$Component);

    function Pie() {
        _classCallCheck(this, Pie);

        return _possibleConstructorReturn(this, _React$Component.apply(this, arguments));
    }

    Pie.prototype.render = function render() {
        var _props = this.props;
        var color = _props.color;
        var width = _props.width;
        var progress = _props.progress;
        var category = "spend";
        var cat = 0;

        var pi = 3.14159265359;
        var r = 400 / 2;
        var c = 2 * pi * r;
        var realProgress = c * progress;
        return React.createElement(
            "div",
            { className: "svg-container" },
            React.createElement(
                "div",
                {
                    className: "svg-content"
                },
                React.createElement(
                    "svg",
                    {
                        viewBox: "0 0 500 500",
                        preserveAspectRatio: "xMinYMin meet"
                    },
                    React.createElement(
                        "filter",
                        { id: "shadow" },
                        React.createElement("feGaussianBlur", { "in": "SourceGraphic", stdDeviation: "15" }),
                        React.createElement("feOffset", { dx: "-5", dy: "-4" })
                    ),
                    React.createElement("circle", {

                        className: "animated",
                        cx: "250",
                        cy: "250",
                        r: "200",
                        stroke: "",
                        fillOpacity: "0",
                        strokeWidth: width + 10,
                        strokeDasharray: [realProgress, c],
                        strokeDashoffset: c * progress,
                        categoryName: "spend"
                    }),
                    React.createElement("circle", {
                        filter: "url(#shadow)",
                        className: "animated shadow",
                        cx: "250",
                        cy: "250",
                        r: "200",
                        stroke: color,
                        fillOpacity: "0",
                        strokeWidth: width,
                        strokeDasharray: [realProgress, c],
                        strokeDashoffset: c * progress
                    }),
                    React.createElement("circle", {
                        className: "animated fill",
                        cx: "250",
                        cy: "250",
                        r: "200",
                        stroke: color,
                        fillOpacity: "0",
                        strokeWidth: width,
                        strokeDasharray: [realProgress, c],
                        strokeDashoffset: c * progress
                    }),
                    React.createElement("circle", {
                        cx: "250",
                        cy: "250",
                        r: "200",
                        stroke: color,
                        fillOpacity: "0",
                        strokeWidth: "5",
                        strokeDasharray: [c, c],
                        strokeOpacity: "0.1"
                    })
                )
            )
        );
    };

    return Pie;
}(React.Component);

var Application = function (_React$Component2) {
    _inherits(Application, _React$Component2);

    function Application() {
        _classCallCheck(this, Application);

        return _possibleConstructorReturn(this, _React$Component2.apply(this, arguments));
    }

    Application.prototype.render = function render() {
        var percent = this.props.percent;
        var colorChart = this.props.colorChart;
        return React.createElement(
            "div",
            null,
            React.createElement(
                "div",
                { className: "box" },
                React.createElement(Pie, {
                    color: colorChart,
                    width: 10,
                    progress: percent

                })
            )
        );
    };

    return Application;
}(React.Component);

var progressUpdate;
var goals = [{
    category: 'car',
    name: 'Spend',
    amount: <%=totalForMonth%>,
    progress: <%=spendForMonth%>,
    flipped: false,
    dragStatus: true,
    categoryName:  "spend"
}, {
    category: 'plane',
    name: 'Income',
    amount: <%=totalForMonth%>,
    progress: <%=incomeForMonth%>,
    flipped: false,
    dragStatus: true,

}];

var dragStatus = true;

var GoalCard = function (_React$Component3) {
    _inherits(GoalCard, _React$Component3);

    function GoalCard(props) {
        _classCallCheck(this, GoalCard);

        var _this3 = _possibleConstructorReturn(this, _React$Component3.call(this, props));

        _this3.state = { condition: false,
            progress: _this3.props.item.progress,
            input1: _this3.props.item.name,
            input2: _this3.props.item.amount,
            input3: _this3.props.item.progress,
            selectValue: _this3.props.item.category
        };
        return _this3;
    }

    GoalCard.prototype.addMoney = function addMoney() {
        var addFunds = prompt('Add funds for: ' + this.props.item.name, "0");
        this.props.item.progress += Number(addFunds);
        this.setState({
            item: this.props.item
        });
    };

    GoalCard.prototype.onSave = function onSave() {
        this.props.item.progress = progressUpdate;
        this.setState({
            item: this.props.item
        });
    };

    GoalCard.prototype.editGoal = function editGoal(index) {
        this.props.onFlip(index);
        this.setState({
            condition: !this.state.condition,
            input1: this.props.item.name,
            input2: this.props.item.amount,
            input3: this.props.item.progress,
            selectValue: this.props.item.category

        });
    };

    GoalCard.prototype.deleteGoal = function deleteGoal(index) {
        this.props.onDelete(index);
        this.setState({ condition: !this.state.condition,
            input1: this.props.item.name,
            input2: this.props.item.amount,
            input3: this.props.item.progress,
            selectValue: this.props.item.category,
            flipped: false,
            dragStatus: true
        });
    };

    GoalCard.prototype.cancelEdit = function cancelEdit(index) {
        this.props.onFlip(index);
        this.setState({ condition: !this.state.condition,
            input1: this.props.item.name,
            input2: this.props.item.amount,
            input3: this.props.item.progress,
            selectValue: this.props.item.category });
    };

    GoalCard.prototype.saveGoal = function saveGoal(index) {
        this.props.onFlip(index);
        this.setState({ condition: !this.state.condition });
        this.props.item.name = this.state.input1;
        this.props.item.amount = Number(this.state.input2);
        this.props.item.progress = Number(this.state.input3);
        this.props.item.category = this.state.selectValue;
    };


    GoalCard.prototype.handleChange = function handleChange(name, e) {
        var change = {};
        change[name] = e.target.value;
        this.setState(change);
    };

    GoalCard.prototype.handleDropdown = function handleDropdown(e) {
        this.setState({ selectValue: e.target.value });
    };

    GoalCard.prototype.render = function render() {
        var message;
        var strokeColor;
        var status;
        var remaining;
        var percentRemaining;
        if (this.props.item.progress / this.props.item.amount < 1) {
            strokeColor = "#01579B";
            status = "Remaining days to month";
            remaining = <%=maxDaysInMonth-dayOfMonth%> +"  days";

        }
        if(this.props.item.categoryName == "spend"){
            strokeColor = "#FF3D00";
        }
       /* } else {
            strokeColor = "#7dbf69";
            status = "Exceeded";
            remaining = Math.abs(this.props.item.amount - this.props.item.progress).toLocaleString();
            percentRemaining = "(" + Math.abs((this.props.item.amount - this.props.item.progress) / this.props.item.amount * 100).toFixed(0) + "%)";
        }*/

        return React.createElement(
            "div",
            { className: this.state.condition ? "flipped card-container" : "card-container" },
            React.createElement(
                "figure",
                { className: "front" },
                React.createElement(
                    "div",
                    { className: "goal--top" },
                    React.createElement(
                        "div",
                        { className: "goal__name" },
                        this.props.item.name
                    ),
                    React.createElement(Application, { percent: this.props.item.progress / this.props.item.amount, colorChart: strokeColor }),
                    React.createElement(
                        "div",
                        { className: "goal--top__container" },
                        React.createElement("i", { className: 'category fa fa-' + this.props.item.category, "aria-hidden": "true" }),
                        React.createElement(
                            "div",
                            { className: "goal--progress" },
                            React.createElement(
                                "span",
                                { className: "money" , id: "progress" },
                                this.props.item.progress.toLocaleString()
                            )
                        ),
                        React.createElement(
                            "div",
                            { className: "goal--amount" },
                            "of ",
                            React.createElement(
                                "span",
                                { className: "money" },
                                this.props.item.amount.toLocaleString()
                            )
                        )
                    )
                ),
                React.createElement(
                    "div",
                    { className: "goal--bottom" },
                    React.createElement(
                        "div",
                        { className: "descriptor" },
                        status,
                        React.createElement(
                            "span",
                            { className: "money right goal--remain" },
                            remaining
                        ),
                        React.createElement(
                            "span",
                            { className: "percent right" },
                            percentRemaining
                        )
                    ),
                    React.createElement(Example, {
                        name: this.props.item.name,
                        progress: this.props.item.progress,
                        amount: this.props.item.amount,
                        onSave: this.onSave.bind(this)

                    })
                )
            ),
            React.createElement(
                "figure",
                { className: "back" },
                React.createElement(
                    "a",
                    { href: "#", className: "back-arrow", onClick: this.cancelEdit.bind(this, this.props.item.index) },
                    React.createElement("i", { className: "fa fa-angle-left" })
                ),
                React.createElement(
                    "h3",
                    { className: "card-title" },
                    'Food'
                ),
                React.createElement(
                    "div",
                    { className: "input-container" },
                    React.createElement(
                        "label",
                        { name: "name", className: "descriptor" },
                        "Comment"
                    ),
                    React.createElement("input", { type: "text", placeholder: "Goal Name", value: this.state.input1, onChange: this.handleChange.bind(this, 'input1') }),
                    React.createElement(
                        "label",
                        { name: "amount", className: "descriptor" },
                        "Amount"
                    ),
                    React.createElement(
                        "span",
                        { className: "edit-money" },
                        "$"
                    ),


                    /*  React.createElement("input", { type: "number", placeholder: "Amount", value: this.state.input2, onChange: this.handleChange.bind(this, 'input2') }),
                      React.createElement(
                          "label",
                          { name: "progress", className: "descriptor" },
                          "Permanent Spend?"
                      ),*/
                    /* React.createElement(
                         "span",
                         { className: "edit-money" },
                         "$"
                     ),*/
                    " ",
                    React.createElement("input", {name: "input", type: "number", placeholder: "Progress", value: this.state.input3, onChange: this.handleChange.bind(this, 'input3') }),
                    "         ",

                    React.createElement(
                        "label",
                        { name: "permanentSpend", className: "descriptor" },
                        "Permanent Spend?"
                    ),
                    React.createElement(
                        "div",
                        { className: "dropdown-wrapper" },
                        React.createElement(
                            "select",
                            { value: this.state.selectValue,
                                onChange: this.handleDropdown.bind(this)
                            },
                            React.createElement(
                                "option",
                                { value: "yes" },
                                "Yes"
                            ),
                            React.createElement(
                                "option",
                                { value: "no" },
                                "No"
                            ),
                            /*  React.createElement(
                                  "option",
                                  { value: "" },
                                  "None"
                              )*/
                        )

                    )
                ),
                React.createElement("input", { type: "button", className: "button", value: "Save Changes", onClick: this.saveGoal.bind(this, this.props.item.index) }),
                /*  React.createElement(
                      "a",
                      { href: "#", className: "delete", onClick: this.deleteGoal.bind(this, this.props.item.index) },
                      "Delete Goal"
                  )*/
            )
        );
    };

    return GoalCard;
}(React.Component);

var Example = React.createClass({
    displayName: "Example",
    getInitialState: function getInitialState() {
        return { showModal: false,
            progress: this.props.progress,
            input1: "",
            sum: ""
        };
    },
    close: function close() {
        this.setState({ showModal: false });
    },
    submit: function submit(e) {
        if (e.key === 'Enter') {
            progressUpdate = this.state.sum;
            this.props.onSave();
            this.setState({
                showModal: false
            });
        }
    },
    save: function save() {
        progressUpdate = this.state.sum;
        this.props.onSave();
        this.setState({
            showModal: false
        });
    },
    open: function open() {
        this.setState({ showModal: true,
            sum: this.props.progress,
            input1: "" });
    },
    handleChange: function handleChange(name, e) {
        var change = {};
        change[name] = e.target.value;

        this.setState(change);
        this.setState({
            sum: Number(this.props.progress) + Number(change[name])

        });
    },
    render: function render() {

        return React.createElement(
            "div",
            null,
            React.createElement(
                Modal,
                { show: this.state.showModal, onHide: this.close },
                React.createElement(
                    Modal.Header,
                    { closeButton: true },
                    React.createElement(
                        Modal.Title,
                        null,
                        "Add Funds for ",
                        this.props.name
                    )
                ),
                React.createElement(
                    Modal.Body,
                    null,
                    React.createElement(
                        "label",
                        null,
                        "Current progress:"
                    ),
                    React.createElement(
                        "div",
                        { className: "data" },
                        " days",
                        this.props.progress.toLocaleString()
                    ),
                    React.createElement(
                        "label",
                        null,
                        "Add amount:"
                    ),
                    React.createElement(
                        "div",
                        { className: "data" },
                        React.createElement(
                            "span",
                            { className: "pre-money" },
                            " days"
                        ),
                        " ",
                        React.createElement("input", { type: "number", className: "fund-container", defaultValue: "", onKeyPress: this.submit.bind(this), onChange: this.handleChange.bind(this, 'input1'), autoFocus: true })
                    ),
                    React.createElement(
                        "p",
                        { className: "smallprint" },
                        " Total Progress: ",
                        React.createElement(
                            "span",
                            { className: "pre-money" },
                            " days"
                        ),
                        this.state.sum.toLocaleString()
                    )
                ),
                React.createElement(
                    Modal.Footer,
                    null,
                    React.createElement(
                        Button,
                        { onClick: this.close },
                        "Close"
                    ),
                    React.createElement(
                        Button,
                        { type: "button", className: "btn-primary", onClick: this.save.bind(this, this.props) },
                        "Update Progress"
                    )
                )
            )
        );
    }
});



var GoalList = React.createClass({
    displayName: "GoalList",

    getInitialState: function getInitialState() {
        return {
            data: this.props.data
        };
    },
    dragStart: function dragStart(e) {
        //this.refs['update'].updateGoal();
        this.dragged = e.currentTarget;
        e.dataTransfer.effectAllowed = 'move';
        e.dataTransfer.setData("text/html", e.currentTarget);
    },
    dragEnd: function dragEnd(e) {
        this.dragged.style.display = "block";
        placeholder.remove();
        // Update data
        var data = this.state.data;
        var from = Number(this.dragged.dataset.id);
        var to = Number(this.over.dataset.id);
        if (from < to) to--;
        if (this.nodePlacement == "after") to++;
        data.splice(to, 0, data.splice(from, 1)[0]);
        this.setState({
            data: data
        });
    },
    dragOver: function dragOver(e) {

        e.preventDefault();
        this.dragged.style.display = "none";
        if (e.target.className == "placeholder") return;
        this.over = e.target;
        // Inside the dragOver method
        var relY = e.pageY - this.over.offsetTop;
        var height = this.over.offsetHeight / 2;
        var relX = e.pageX - this.over.offsetLeft;
        var width = this.over.offsetWidth / 2;
        var parent = e.target.parentNode;

        if (relX >= width) {
            this.nodePlacement = "after";
            parent.insertBefore(placeholder, e.target.nextElementSibling);
        } else {
            this.nodePlacement = "before";
            parent.insertBefore(placeholder, e.target);
        }
        this.setState({
            data: this.props.data
        });
    },
    onFlip: function onFlip(i) {
        var data = this.state.data;
        data[i].flipped = !data[i].flipped;

        this.setState({
            data: data });

        if (data[i].flipped) {
            var data = this.props.data;
            //	data[i].dragStatus = false;
            dragStatus = false;
        } else {
            //	data[i].dragStatus= true;
            dragStatus = true;
        }
    },
    onDelete: function onDelete(i) {
        var data = this.props.data;
        var newData = this.state.data.slice(); //copy array
        newData.splice(i, 1); //remove element
        this.setState({ data: newData }); //update state
        this.props = newData;
        //var data = this.props.data;
        data[i].flipped = !data[i].flipped;
        if (data[i].flipped) {

            //	data[i].dragStatus = false;
            dragStatus = false;
        } else {
            //	data[i].dragStatus= true;
            dragStatus = true;
        }
        data = newData;
        //data.splice(i, 1);

        //	this.setState({ data: data});
    },
    render: function render() {

        return React.createElement(
            "ul",
            { className: "goal__list", onDragOver: this.dragOver },
            this.state.data.map(function (item, i) {

                return React.createElement(
                    "li",
                    { "data-id": i,
                        key: i
                        // draggable={goals[i].dragStatus}
                        , draggable: dragStatus,
                        onDragEnd: this.dragEnd.bind(this),
                        onDragStart: this.dragStart.bind(this)

                    },
                    React.createElement(GoalCard, { item: item,
                        onFlip: this.onFlip.bind(this, i),
                        cancelEdit: this.onFlip.bind(this, i),
                        saveGoal: this.onFlip.bind(this, i),
                        onDelete: this.onDelete.bind(this, i)

                    })
                );
            }, this)
        );
    }

});

ReactDOM.render(React.createElement(GoalList, { data: goals }), document.getElementById('app'));

//# sourceURL=pen.js
</script>
</div>
</body></html>


