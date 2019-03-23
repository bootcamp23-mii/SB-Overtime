/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {
    $("#nav_home").on("click", function () {
        $("#loadthis").load("content.jsp");
    });
    $("#nav_review").on("click", function () {
        $("#loadthis").load("approvalManager.jsp");
    });
    $("#nav_all").on("click", function () {
        $("#loadthis").load("content.jsp");
    });
    $("#nav_calender").on("click", function () {
        $("#loadthis").load("calender.jsp");
    });
    $("#nav_add").on("click", function () {
        $("#loadthis").load("request.jsp");
    });
    $("#nav_profile").on("click", function () {
        $("#loadthis").load("profile.jsp");
    });
    $("#nav_history").on("click", function () {
        $("#loadthis").load("history.jsp");
    });
    $("#nav_status").on("click", function () {
        $("#loadthis").load("status.jsp");
    });
    $("#nav_add_emp").on("click", function () {
        $("#loadthis").load("adminCreateUser.jsp");
    });
    $("#nav_user_access").on("click", function () {
        $("#loadthis").load("adminUserAccess.jsp");
    });
});

