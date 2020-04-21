$(document).ready(function() {
    $("#navbarId").hide();
    $("#loginErrorMsgId").hide();
    $('.form-control-select2').select2();

    $(".menuListItems").click(function(){
        $(".menuListItems").removeClass("active");
        $(".dropdownListItems").removeClass("active");
        $(this).addClass("active");
    });

    $(".subMenuListItems").click(function(){
        $(".menuListItems").removeClass("active");
        $(".dropdownListItems").removeClass("active");
        $(this).closest(".dropdownListItems").addClass("active");
    });

    $("#page2Id").click(function(){
        event.preventDefault();
        $("#maindiv").load("Page2.html");

    });

    $("#page11Id").click(function(){
           event.preventDefault();
           $("#maindiv").load("page11.html");

    });

    $("#page12Id").click(function(){
        event.preventDefault();
        $("#maindiv").load("Page12.html");

    });

    $("#page13Id").click(function(){
               event.preventDefault();
               $("#maindiv").load("Page13.html");

    });

    $("#homeId").click(function(){
        event.preventDefault();
        $("#maindiv").load("Home.html");

    });

    $("#addUserId").click(function(){
        event.preventDefault();
        $("#maindiv").load("AddUser.html");


    });

     $("#addRoleId").click(function(){
        event.preventDefault();
        $("#maindiv").load("AddRole.html");

    });

     $("#addRoleMenuId").click(function(){
        event.preventDefault();
        $("#maindiv").load("AddRoleMenu.html");

    });

});

