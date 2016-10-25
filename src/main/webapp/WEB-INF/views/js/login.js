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
        initParams();
        refreshDateTime();
        initListener();
    }
    ;

    function initParams() {
        $("#p001UsernameTxt").focus();

        $("#p001UsernameTxt").keydown(function() {
            $("#spname").hide();
        });
        $("#p001UserPasswordTxt").keydown(function() {
            $("#sppassword").hide();
        });
        $("#spname").on("click", function() {
            $("#p001UsernameTxt").focus();
        });
        $("#sppassword").on("click", function() {
            $("#p001UserPasswordTxt").focus();
        });

        $("#p001UsernameTxt").on("blur", function() {
            if ($("#p001UsernameTxt").val() == "") {
                $("#spname").show();
            }
        });

        $("#p001UserPasswordTxt").on("blur", function() {
            if ($("#p001UserPasswordTxt").val() == "") {
                $("#sppassword").show();
            }

        });
    }

    function refreshDateTime() {
        weeks = new Array("日", "一", "二", "三", "四", "五", "六");
        today = new Date();
        year = today.getFullYear();
        mon = today.getMonth() + 1;
        mon = (mon < 10) ? "0" + mon : mon;
        date = today.getDate();
        date = (date < 10) ? "0" + date : date;
        weekday = weeks[today.getDay()];
        curentDateTime = year + "年" + mon + "月" + date + "日 (周" + weekday + ")";
        document.getElementById("dateShow").innerHTML = "北京时间：" + curentDateTime;
    }

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

        $("#p002WelcomeBtn").off("click").on("click", function(e) {
            e.stopPropagation();
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