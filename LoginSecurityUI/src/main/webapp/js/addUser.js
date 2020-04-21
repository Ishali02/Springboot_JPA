var users = [];
var isChecked;

function addNewUser(){
    isChecked = $('#userCheckBox').is(':checked');

    $("#deleteUserbtn").hide();
    if(isChecked)
    {
        $("#editUser").hide();
        $("#newUser").show();

        clearUserData();
        clearUserMsg();
    }
    else{
        $("#newUser").hide();
        $("#editUser").show();
        clearUserMsg();
    }
}

function populateUser(){
    $.ajax({
        type : "GET",
        url : baseUrl+"/"+wscontext+"/user/getUser",
        contentType : "application/json",
        success : function(data)
        {
            users = data;
            $("#selUser").empty();
            $("#selUser").append('<option value=' + "" + '>' + "" + '</option>');
            for (var x = 0; x < users.length; x++) {
                $("#selUser").append($("<option/>", {
                    value : users[x].userId,
                    text : users[x].userName
                }
                ));
            }
            $("#userErrorMsgId").hide();
        },
        error: function()
        {
            $("#userErrorMsgId").html("Something went wrong! Try again.");
            $("#userErrorMsgId").show();
            setTimeout(function(){
                $("#userErrorMsgId").hide();
            }, 9000);
        }
    });
}

function validateUserName()
{
    var username = $("#Username").val();
    if(username != "")
    {
        for (var x = 0; x < users.length; x++) {
            if(users[x].userName == username)
            {
                $("#userSuccessMsgId").hide();
                $("#userErrorMsgId").html("Username already exists!");
                $("#userErrorMsgId").show();
                setTimeout(function(){
                    $("#userErrorMsgId").hide();
                }, 9000);
                return false;
            }
        }
        return true;
    }
    else
    {
        $("#userSuccessMsgId").hide();
        $("#userErrorMsgId").html("Username is required!");
        $("#userErrorMsgId").show();
        setTimeout(function(){
            $("#userErrorMsgId").hide();
        }, 9000);
        return false;
    }
}



function saveUserDetails()
{
    event.preventDefault();

    var flag = false;
    if($("#selUser option:selected").val() != "" || $("#Username").val() != "")
    {
        flag = true;
    }
    else
    {
        flag = false;
        $("#userSuccessMsgId").hide();
        $("#userErrorMsgId").html("Username is required!");
        $("#userErrorMsgId").show();
        setTimeout(function(){
            $("#userErrorMsgId").hide();
        }, 9000);
    }

    if(!isChecked && flag)
    {
        var data = {};
        data.userId = $("#selUser option:selected").val();
        data.userName = $("#selUser option:selected").text();
        data.userPwd = $("#pwd").val();
        $.ajax({
            type : "PUT",
            data : JSON.stringify(data),
            url : baseUrl+"/"+wscontext+"/user/updateUser",
            contentType : "application/json",
            success : function(data)
            {
                if(data == "Updated Successfully.")
                {
                    $("#userErrorMsgId").hide();
                    $("#userSuccessMsgId").html("User Updated Successfully!");
                    $("#userSuccessMsgId").show();
                    setTimeout(function(){
                        $("#userSuccessMsgId").hide();
                    }, 9000);
                    clearUserData();
                }
                else
                {
                    $("#userSuccessMsgId").hide();
                    $("#userErrorMsgId").html("Failed to Update User!");
                    $("#userErrorMsgId").show();
                    setTimeout(function(){
                        $("#userErrorMsgId").hide();
                    }, 9000);
                }
            },
            error: function()
            {
                $("#userErrorMsgId").html("Something went wrong! Try again.");
                $("#userSuccessMsgId").hide();
                $("#userErrorMsgId").show();
                setTimeout(function(){
                    $("#userErrorMsgId").hide();
                }, 9000);
            }
        });
    }
    else if(flag && validateUserName())
    {
        var data = {};
        data.userName = $("#Username").val();
        data.userPwd = $("#pwd").val();
        $.ajax({
            type : "POST",
            data : JSON.stringify(data),
            url : baseUrl+"/"+wscontext+"/user/addUser",
            contentType : "application/json",
            success : function(data)
            {
                if(data == "Success")
                {
                    $("#userErrorMsgId").hide();
                    $("#userSuccessMsgId").html("User Added Successfully!");
                    $("#userSuccessMsgId").show();
                    setTimeout(function(){
                        $("#userSuccessMsgId").hide();
                    }, 9000);
                    clearUserData();
                }
                else
                {
                    $("#userSuccessMsgId").hide();
                    $("#userErrorMsgId").html("Failed to Add User!");
                    $("#userErrorMsgId").show();
                    setTimeout(function(){
                        $("#userErrorMsgId").hide();
                    }, 9000);
                }
            },
            error: function()
            {
                $("#userErrorMsgId").html("Something went wrong! Try again.");
                $("#userSuccessMsgId").hide();
                $("#userErrorMsgId").show();
                setTimeout(function(){
                    $("#userErrorMsgId").hide();
                }, 9000);
            }
        });
    }
}

function selectUserChange()
{
    clearUserMsg();
    if($("#selUser option:selected").val() == "")
    {
        $("#deleteUserbtn").hide();
    }
    else
    {
        $("#deleteUserbtn").show();
    }
}

function deleteUser()
{
    var userId = $("#selUser option:selected").val();
    if(userId != "")
    {
        var data = {};
        data.userId = userId;
        $.ajax({
            type : "DELETE",
            data : JSON.stringify(data),
            url : baseUrl+"/"+wscontext+"/user/deleteUser",
            contentType : "application/json",
            success : function(data)
            {
                if(data == "Deleted Successfully.")
                {
                    $("#userErrorMsgId").hide();
                    $("#userSuccessMsgId").html("User Deleted Successfully!");
                    $("#userSuccessMsgId").show();
                    setTimeout(function(){
                        $("#userSuccessMsgId").hide();
                    }, 9000);
                    clearUserData();
                }
                else
                {
                    $("#userSuccessMsgId").hide();
                    $("#userErrorMsgId").html("Failed to Delete User!");
                    $("#userErrorMsgId").show();
                    setTimeout(function(){
                        $("#userErrorMsgId").hide();
                    }, 9000);
                }
            },
            error: function()
            {
                $("#userErrorMsgId").html("Something went wrong! Try again.");
                $("#userSuccessMsgId").hide();
                $("#userErrorMsgId").show();
                setTimeout(function(){
                    $("#userErrorMsgId").hide();
                }, 9000);
            }
        });
    }
}

function clearUserData()
{
    $("#Username").val("");
    $("#pwd").val("");
    $("#selUser").empty();
    $("#deleteUserbtn").hide();
    populateUser();
}

function clearUserMsg()
{
    $("#userSuccessMsgId").hide();
    $("#userErrorMsgId").hide();
}