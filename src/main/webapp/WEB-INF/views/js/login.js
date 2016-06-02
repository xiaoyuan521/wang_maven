/**
 *
 */
/*$(document).ready(function(){
    init();
});*/

require.config({

});

require([ "login" ], function(login) {
    $(function() {
        login.init();
    });
});

define([], function() {

    function init() {
        initListener();
    }
    ;

    function initListener() {
        $("#p001LoginBtn").on("click", function() {
            var params = {};
            params["username"] = $("#p001UsernameTxt").val();
            params["password"] = $("#p001UserPasswordTxt").val();

            if ($("#p001UsernameTxt").val() == "" || $("#p001UserPasswordTxt").val() == "") {
                alert("用户名密码不能为空");
                return;
            }

            var url = "/" + getContextPath() + "/init";

            $.ajax({
                url: url,
                type: 'POST',
                data: JSON.stringify(params),
                contentType: "application/json",
                dataType: "json",
                cache: false,
                success: function(data) {

                    if ("ok" == data.code) {
                        window.location.href = "/" + getContextPath() + "/welcome";
                    } else {
                        window.location.href = "/" + getContextPath() + "/error";
                    }
                }
            })

        });

        $("#p002WelcomeBtn").on("click", function() {
            window.location.href = "/" + getContextPath() + "/welcome";
        });
    }

    function getContextPath() {
        var fullPath = window.location.pathname;
        var contextPath = fullPath.split("/")[1];
        return contextPath;
    }

    return {
        "init": init
    }
});