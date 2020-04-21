var baseUrl = "http://localhost:8080";
var loggedInUser;
var wscontext = "Login_Security_WS";
var accessiblePages = null;

function checkForm(form)
{
    event.preventDefault();

    var loginObj = {};
    loginObj.userName = $("#uname").val();
    loginObj.password = $("#pwd").val();

     $.ajax({
        type : "POST",
        url : baseUrl+"/"+wscontext+"/login/loginAuth",
        data : JSON.stringify(loginObj),
        contentType : "application/json",
        success : function(data){
            if(data[0] == "UserNotFound/IncorrectPassword")
            {
                $("#loginErrorMsgId").html("Incorrect Username/password");
                $("#loginErrorMsgId").show();
            }
            else
            {
                accessiblePages = data;
                enablePages(accessiblePages);
                $("#maindiv").load("Home.html");
                $("#navbarId").show();
            }
        },

        error : function(){
            $("#loginErrorMsgId").html("Something went wrong");
            $("#loginErrorMsgId").show();
        }
     });



}

function enablePages(accessiblePages)
{
    var menuList = $("#menuListId li a");
    //console.log("---------------------menuList------------------------\n" + JSON.stringify(menuList));
    menuList.each(function(idx, menu) {
      //  console.log(JSON.stringify(menu));
        var id = $(menu).get(0).id;
        $("#".concat(id)).hide();
     //   console.log( "----------------id---------------------" + id);
    });
     accessiblePages.forEach(function(li, index)
    {
         $("#".concat(li)).show();
    });

}