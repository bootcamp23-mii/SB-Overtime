/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {
    $("#nav_home").on("click", function () {
        $("#loadthis").load("fragments/content.html");
    });
    $("#nav_review").on("click", function () {
        $("#loadthis").load("fragments/approvalManager.html");
    });
    $("#nav_all").on("click", function () {
        $("#loadthis").load("fragments/content.html");
    });
    $("#nav_add").on("click", function () {
        $("#loadthis").load("fragments/request.html");
    });
    $("#nav_profile").on("click", function () {
        $("#loadthis").load("fragments/profile.html");
    });
    $("#nav_history").on("click", function () {
        $("#loadthis").load("fragments/history.html");
    });
    $("#nav_status").on("click", function () {
        $("#loadthis").load("fragments/status.html");
    });
    $("#nav_add_emp").on("click", function () {
        $("#loadthis").load("fragments/adminCreateUser.html");
    });
    $("#nav_user_access").on("click", function () {
        $("#loadthis").load("fragments/adminUserAccess.html");
    });
});

