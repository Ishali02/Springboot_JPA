var role = [];
var rolesByUser = [];

function addNewRole(){
    isChecked = $('#roleCheckBox').is(':checked');

    $("#deleteRolebtn").hide();
    if(isChecked)
    {
        $("#labelName").text("Delete RoleName:");
        $("#editRole").show();
        $("#newRole").show();
        $("#deleteRolebtn").show();

        $("#saveUserRolebtn").hide();
        $("#saveRolebtn").show();
        $("#unamediv").hide();
        clearRoleData();
        clearRoleMsg();
    }
    else{
        $("#labelName").text("RoleName:");
        $("#newRole").hide();
        $("#editRole").show();
        $("#unamediv").show();
        $("#saveRolebtn").hide();
        $("#saveUserRolebtn").show();
        clearRoleMsg();
    }
}
function addUserRoleDetails()
{
     isChecked = $('#roleCheckBox').is(':checked');
     if(isChecked)
     {
        addNewRoles();
     }
     else
     {
        saveRoleDetails();
     }
}
function populateRole(){
    $.ajax({
        type : "GET",
        url : baseUrl+"/"+wscontext+"/role/getRole",
        contentType : "application/json",
        success : function(data)
        {
            role = data;
            $("#selRole").empty();
            $("#selRole").append('<option value=' + "" + '>' + "" + '</option>');
            for (var x = 0; x < role.length; x++) {
                $("#selRole").append($("<option/>", {
                    value : role[x].roleId,
                    text : role[x].roleName
                }
                ));
            }
            $("#roleErrorMsgId").hide();
        },
        error: function()
        {
            $("#roleErrorMsgId").html("Something went wrong! Try again.");
            $("#roleErrorMsgId").show();
            setTimeout(function(){
                $("#roleErrorMsgId").hide();
            }, 9000);
        }
    });

}


function validateRoleName()
{
    var rolename = $("#Rolename").val();
    if(rolename != "")
    {
        for (var x = 0; x < role.length; x++) {
            if(role[x].roleName == rolename)
            {
                $("#roleSuccessMsgId").hide();
                $("#roleErrorMsgId").html("Rolename already exists!");
                $("#roleErrorMsgId").show();
                setTimeout(function(){
                    $("#roleErrorMsgId").hide();
                }, 9000);
                return false;
            }
        }
        return true;
    }
    else
    {
        $("#roleSuccessMsgId").hide();
        $("#roleErrorMsgId").html("Rolename is required!");
        $("#roleErrorMsgId").show();
        setTimeout(function(){
            $("#roleErrorMsgId").hide();
        }, 9000);
        return false;
    }
}

function clearRoleData()
{
    $("#Username").val("");
    $("#Rolename").val("");
    $("#selRole").empty();
    $("#selUser").empty();
    $("#deleteUserbtn").hide();
    populateRole();
    populateUser();
}

function clearRoleMsg()
{
    $("#roleSuccessMsgId").hide();
    $("#roleErrorMsgId").hide();
}

function saveRoleDetails()
{
    event.preventDefault();
    console.log("--------------save details------------------");
    console.log("--------roles by user------------" + rolesByUser);
    var data = {};
    var re = {};
    var arrayOfRole=[];
    var flag = false;
    if($("#selUser option:selected").val() != "" )
    {
        flag = true;
        arrayOfRole = $("#selRole").val();
        console.log("-----array of role --------" + arrayOfRole);
        var unique = $.grep(rolesByUser, function(element) {
            return $.inArray(element, arrayOfRole) == -1;
        });
        var result = unique;
        var user_id = $("#selUser").val();
        console.log("-------role to be deleted------"+ result);
        console.log("-------role to be deleted------"+ result.length);
        if(result.length>=1)
        {
            for(var i=0; i<result.length;i++)
            {
                re.userId = user_id;
                console.log("re userId" + re.userId);
                re.roleId = result[i];
                console.log("re roleId" + re.roleId);

                $.ajax({
                    async: false,
                    type : "DELETE",
                    data : JSON.stringify(re),
                    contentType : "application/json",
                    url : baseUrl+"/"+wscontext+"/userrole/deleteByUserIdRoleId",

                    success : function(result)
                    {
                        if(result = "Success")
                        {
                            $("#roleErrorMsgId").hide();
                            $("#roleSuccessMsgId").html("Roles Deleted Successfully!");
                            $("#roleSuccessMsgId").show();
                            setTimeout(function(){
                                $("#roleSuccessMsgId").hide();
                            }, 9000);
                        }
                        else
                        {
                            $("#roleSuccessMsgId").hide();
                            $("#roleErrorMsgId").html("Failed to delete role !!");
                            $("#roleErrorMsgId").show();
                            setTimeout(function(){
                                $("#roleErrorMsgId").hide();
                            }, 9000);
                        }
                    },

                    error : function()
                    {
                        $("#roleErrorMsgId").html("Something went wrong! Try again.");
                        $("#roleSuccessMsgId").hide();
                        $("#roleErrorMsgId").show();
                        setTimeout(function(){
                            $("#roleErrorMsgId").hide();
                        }, 9000);
                    }

                });
            }
        }

        for(var i=0; i<arrayOfRole.length;i++)
        {
            data.userId = user_id;
            console.log("data userId" + data.userId);
            data.roleId = arrayOfRole[i];
            console.log("data roleId" + data.roleId);
            $.ajax({
                async: false,
                type : "POST",
                data : JSON.stringify(data),
                contentType : "application/json",
                url : baseUrl+"/"+wscontext+"/userrole/addUserRole",

                success : function(result)
                {
                    if(result = "Success")
                    {
                        $("#roleErrorMsgId").hide();
                        $("#roleSuccessMsgId").html("Roles Updated Successfully!");
                        $("#roleSuccessMsgId").show();
                        setTimeout(function(){
                            $("#roleSuccessMsgId").hide();
                        }, 9000);
                    }
                    else
                    {
                        $("#roleSuccessMsgId").hide();
                        $("#roleErrorMsgId").html("Failed to Update Role!");
                        $("#roleErrorMsgId").show();
                        setTimeout(function(){
                            $("#roleErrorMsgId").hide();
                        }, 9000);
                    }
                },

                error : function()
                {
                    $("#roleErrorMsgId").html("Something went wrong! Try again.");
                    $("#roleSuccessMsgId").hide();
                    $("#roleErrorMsgId").show();
                    setTimeout(function(){
                        $("#roleErrorMsgId").hide();
                    }, 9000);
                }

            });
        }
        clearRoleData();
    }
    else
    {
        flag = false;
        $("#roleErrorMsgId").html("Role required!!");
        $("#roleErrorMsgId").show();
        setTimeout(function(){
            $("#roleErrorMsgId").hide();
        }, 9000);
    }

}


function selectUserOnChanged(){

     var userSelected = $("#selUser option:selected").val();
     console.log("---------user selected-----" + userSelected);
        if(userSelected != "")
        {
            $.ajax({
                type : "GET",
                url : baseUrl+"/"+wscontext+"/userrole/getRoles/"+userSelected,
                contentType : "application/json",
                success : function(result)
                {
                    rolesByUser = result;
                    console.log("----------rolebyUSer-----" + JSON.stringify(rolesByUser));

                    console.log("----------rolebyUSer-----" + rolesByUser);
                    $("#selRole").val(rolesByUser);

                    $("#selRole").trigger("change");
                },
                error : function()
                {
                    $("#roleErrorMsgId").html("Something went wrong! Try again.");
                    $("#roleErrorMsgId").show();
                    setTimeout(function(){
                        $("#roleErrorMsgId").hide();
                    }, 9000);
                }
            });
        }

}

function deleteRole()
{
    var roleId = $("#selRole option:selected").val();
    console.log(" roleId-----------" + roleId);
    if(roleId != "")
    {
        var data = {};
        data.roleId = roleId;
        $.ajax({
            type : "DELETE",
            data : JSON.stringify(data),
            url : baseUrl+"/"+wscontext+"/role/deleteRole",
            contentType : "application/json",
            success : function(data)
            {
                if(data == "Deleted Successfully.")
                {
                    $("#roleErrorMsgId").hide();
                    $("#roleSuccessMsgId").html("User Deleted Successfully!");
                    $("#roleSuccessMsgId").show();
                    setTimeout(function(){
                        $("#roleSuccessMsgId").hide();
                    }, 9000);
                    clearRoleData();
                }
                else
                {
                    $("#roleSuccessMsgId").hide();
                    $("#roleErrorMsgId").html("Failed to Delete User!");
                    $("#roleErrorMsgId").show();
                    setTimeout(function(){
                        $("#roleErrorMsgId").hide();
                    }, 9000);
                }
            },
            error: function()
            {
                $("#roleErrorMsgId").html("Something went wrong! Try again.");
                $("#roleSuccessMsgId").hide();
                $("#roleErrorMsgId").show();
                setTimeout(function(){
                    $("#roleErrorMsgId").hide();
                }, 9000);
            }
        });
    }
}

function addNewRoles()
{
    event.preventDefault();
    var role = {};
    role.roleName = $("#Rolename").val();
    $.ajax({
        type : "POST",
        data : JSON.stringify(role),
        url : baseUrl+"/"+wscontext+"/role/addRole",
        contentType : "application/json",
        success : function(data)
        {
            if(data == "Role created successfully.")
            {
                $("#roleErrorMsgId").hide();
                $("#roleSuccessMsgId").html("Roles createdSuccessfully!");
                $("#roleSuccessMsgId").show();
                setTimeout(function(){
                    $("#roleSuccessMsgId").hide();
                }, 9000);
                clearRoleData();

            }
            else
            {
                $("#roleSuccessMsgId").hide();
                $("#roleErrorMsgId").html("Failed to Create Role!");
                $("#roleErrorMsgId").show();
                setTimeout(function(){
                    $("#roleErrorMsgId").hide();
                }, 9000);
            }
        },
        error: function()
        {
            $("#roleErrorMsgId").html("Something went wrong! Try again.");
            $("#roleSuccessMsgId").hide();
            $("#roleErrorMsgId").show();
            setTimeout(function(){
                $("#roleErrorMsgId").hide();
            }, 9000);
        }
    });

}