var menuList = [];
var menusByRole = [];

function populateRM_Menu()
{
    console.log("--------------in populateMenu");
    $.ajax({
        type : "GET",
        url : baseUrl+"/"+wscontext+"/menu/getMenu",
        contentType : "application/json",
        success : function(data)
        {
            menu = data;
            $("#RM_selMenu").empty();
            $("#RM_selMenu").append('<option value=' + "" + '>' + "" + '</option>');
            for (var x = 0; x < menu.length; x++) {
                $("#RM_selMenu").append($("<option/>", {
                    value : menu[x].menuId,
                    text : menu[x].menuName
                }
                ));
            }
            $("#roleMenuErrorMsgId").hide();
        },
        error: function()
        {
            $("#roleMenuSuccessMsgId").hide();
            $("#roleMenuErrorMsgId").html("Something went wrong! Try again.");
            $("#roleMenuErrorMsgId").show();
            setTimeout(function(){
                $("#roleMenuErrorMsgId").hide();
            }, 9000);
        }
    });
}

function populateRM_Role()
{
    $.ajax({
        type : "GET",
        url : baseUrl+"/"+wscontext+"/role/getRole",
        contentType : "application/json",
        success : function(data)
        {
            role = data;
            $("#RM_selRole").empty();
            $("#RM_selRole").append('<option value=' + "" + '>' + "" + '</option>');
            for (var x = 0; x < role.length; x++) {
                $("#RM_selRole").append($("<option/>", {
                    value : role[x].roleId,
                    text : role[x].roleName
                }
                ));
            }
            $("#roleMenuErrorMsgId").hide();
        },
        error: function()
        {
            $("#roleMenuSuccessMsgId").hide();
            $("#roleMenuErrorMsgId").html("Something went wrong! Try again.");
            $("#roleMenuErrorMsgId").show();
            setTimeout(function(){
                $("#roleMenuErrorMsgId").hide();
            }, 9000);
        }
    });
}

function clearRoleMenuData()
{
    $("#RM_selRole").empty();
    $("#RM_selMenu").empty();
    populateRM_Role();
    populateRM_Menu();
}

function clearRoleMenuMsg()
{
    $("#roleMenuSuccessMsgId").hide();
    $("#roleMenuErrorMsgId").hide();
}

function menuByRole()
{
    var roleSelected = $("#RM_selRole option:selected").val();
    console.log("---------roleSelected selected-----" + roleSelected);
    $("#RM_selMenu").val([]);
    $("#RM_selMenu").trigger("change");
    if(roleSelected != "")
    {
        $.ajax({
            type : "GET",
            url : baseUrl+"/"+wscontext+"/rolemenu/getMenus/"+roleSelected,
            contentType : "application/json",
            success : function(result)
            {
                menusByRole = result;
                menuList = [];
                $.each(result, function(i, item) {
                   // console.log(item.menuId);
                    menuList.push(item.menuId);
                });
                $("#RM_selMenu").val(menuList);
                $("#RM_selMenu").trigger("change");
                $("#roleMenuErrorMsgId").hide();
            },
            error : function()
            {
                $("#roleMenuErrorMsgId").html("Something went wrong! Try again.");
                $("#roleMenuErrorMsgId").show();
                setTimeout(function(){
                    $("#roleMenuErrorMsgId").hide();
                }, 9000);
            }
        });
    }
}

function saveRoleMenuDetails()
{
    event.preventDefault();
    console.log("--------------save details------------------");
    console.log("--------menus by role------------" + menuList);
    var data = {};
    var re = {};
    var arrayOfMenu=[];
    var flag = false;
    if($("#RM_selRole option:selected").val() != "" )
    {
        flag = true;
        arrayOfMenu = $("#RM_selMenu").val();
        console.log("-----array of role --------" + arrayOfMenu);
        var unique = $.grep(menuList, function(element) {
            return $.inArray(element, arrayOfMenu) == -1;
        });
        var result = unique;
        var role_id = $("#RM_selRole").val();
        console.log("-------role to be deleted------"+ result);
        console.log("-------role to be deleted------"+ result.length);
        if(result.length>=1)
        {
            for(var i=0; i<result.length;i++)
            {
                re.roleId = role_id;
                console.log("re roleId" + re.roleId);
                re.menuId = result[i];
                console.log("re menuId" + re.menuId);
                $.ajax({
                    async: false,
                    type : "DELETE",
                    data : JSON.stringify(re),
                    contentType : "application/json",
                    url : baseUrl+"/"+wscontext+"/rolemenu/deleteByRoleIdMenuId",
                    success : function(result)
                    {
                        if(result = "Success")
                        {
                            $("#roleMenuErrorMsgId").hide();
                            $("#roleMenuSuccessMsgId").html("Roles Updated Successfully!");
                            $("#roleMenuSuccessMsgId").show();
                            setTimeout(function(){
                                $("#roleMenuSuccessMsgId").hide();
                            }, 9000);
                        }
                        else
                        {
                            $("#roleMenuSuccessMsgId").hide();
                            $("#roleMenuErrorMsgId").html("Failed to Update Role Menu!");
                            $("#roleMenuErrorMsgId").show();
                            setTimeout(function(){
                                $("#roleMenuErrorMsgId").hide();
                            }, 9000);
                        }
                    },
                    error : function()
                    {
                        $("#roleMenuErrorMsgId").html("Something went wrong! Try again.");
                        $("#roleMenuSuccessMsgId").hide();
                        $("#roleMenuErrorMsgId").show();
                        setTimeout(function(){
                            $("#roleMenuErrorMsgId").hide();
                        }, 9000);
                    }

                });
            }
        }

        for(var i=0; i<arrayOfMenu.length;i++)
        {
            data.roleId = role_id;
            console.log("data roleId" + data.roleId);
            data.menuId = arrayOfMenu[i];
            console.log("data menuId" + data.menuId);
            $.ajax({
                async: false,
                type : "POST",
                data : JSON.stringify(data),
                contentType : "application/json",
                url : baseUrl+"/"+wscontext+"/rolemenu/addRoleMenu",
                success : function(result)
                {
                    if(result = "Success")
                    {
                        $("#roleMenuErrorMsgId").hide();
                        $("#roleMenuSuccessMsgId").html("Menus Updated Successfully!");
                        $("#roleMenuSuccessMsgId").show();
                        setTimeout(function(){
                            $("#roleMenuSuccessMsgId").hide();
                        }, 9000);
                    }
                    else
                    {
                        $("#roleMenuSuccessMsgId").hide();
                        $("#roleMenuErrorMsgId").html("Failed to Update User!");
                        $("#roleMenuErrorMsgId").show();
                        setTimeout(function(){
                            $("#roleMenuErrorMsgId").hide();
                        }, 9000);
                    }
                },

                error : function()
                {
                    $("#roleMenuErrorMsgId").html("Something went wrong! Try again.");
                    $("#roleMenuSuccessMsgId").hide();
                    $("#roleMenuErrorMsgId").show();
                    setTimeout(function(){
                        $("#roleMenuErrorMsgId").hide();
                    }, 9000);
                }
            });
        }
        clearRoleMenuData();
    }
    else
    {
        flag = false;
        $("#roleMenuErrorMsgId").html("User required!!");
        $("#roleMenuErrorMsgId").show();
        setTimeout(function(){
            $("#roleMenuErrorMsgId").hide();
        }, 9000);
    }

}

