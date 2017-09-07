/**
 *
 */
/*$(document).ready(function(){
    init();
});*/

define([ "common", "userAdd", "ssq", "killing", "killRateSearch", "killerAdd","barGraph" ,"pieGraph","lineGraph", "forum"], function(common, userAdd, ssq, killing, killRateSearch,killerAdd,barGraph,pieGraph,lineGraph, forum) {

    function init() {
        initParams();
        refreshDateTime();
        initListener();
        initMenu();
    }
    ;
    function initParams() {
        // initMenu
        $("#userInformationMenu").show();
        $("#ssqMenu").show();
        $("#werewolfMenu").show();
        $("#forumMenu").show();
        // initsubMenu
        $("#userAddSubMenu").hide();
        $("#ssqSubMenu").hide();
        $("#killerSubMenu").hide();
        $("#killRateSearchSubMenu").hide();
        $("#barGraphSubMenu").hide();
        $("#pieGraphSubMenu").hide();
        $("#lineGraphSubMenu").hide();
        $("#forumSubMenu").hide();

        $("#userInformationMenu").on("click",function(){
            $("#userAddSubMenu").show();
            $("#ssqSubMenu").hide();
            $("#killerSubMenu").hide();
            $("#killRateSearchSubMenu").hide();
            $("#barGraphSubMenu").hide();
            $("#pieGraphSubMenu").hide();
            $("#lineGraphSubMenu").hide();
            $("#forumSubMenu").hide();
        });

        $("#ssqMenu").on("click",function(){
            $("#ssqSubMenu").show();
            $("#userAddSubMenu").hide();
            $("#killerSubMenu").hide();
            $("#killRateSearchSubMenu").hide();
            $("#barGraphSubMenu").hide();
            $("#pieGraphSubMenu").hide();
            $("#lineGraphSubMenu").hide();
            $("#forumSubMenu").hide();
        });

        $("#werewolfMenu").on("click",function(){
            $("#killerSubMenu").show();
            $("#killRateSearchSubMenu").show();
            $("#barGraphSubMenu").show();
            $("#pieGraphSubMenu").show();
            $("#lineGraphSubMenu").show();
            $("#ssqSubMenu").hide();
            $("#userAddSubMenu").hide();
            $("#forumSubMenu").hide();
        });

        $("#forumMenu").on("click",function(){
            $("#forumSubMenu").show();
            $("#userAddSubMenu").hide();
            $("#ssqSubMenu").hide();
            $("#killerSubMenu").hide();
            $("#killRateSearchSubMenu").hide();
            $("#barGraphSubMenu").hide();
            $("#pieGraphSubMenu").hide();
            $("#lineGraphSubMenu").hide();
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

        hour = today.getHours();
        hour = (hour < 10) ? "0" + hour : hour;
        min = today.getMinutes();
        min = (min < 10) ? "0" + min : min;
        sec = today.getSeconds();
        sec = (sec < 10) ? "0" + sec : sec;

        curentDateTime = year + "/" + mon + "/" + date + " (" + weekday + ")  " + hour + ":" + min + ":" + sec;
        document.getElementById("dateTimeShow").innerHTML = "系统时间：" + curentDateTime;
    }

    function initListener() {
        $("#p003BackLoginBtn").on("click", function() {
            window.location.href = "/" + getContextPath() + "/login";
        });
    }

    function initMenu() {
        $(".subMenu a").on("click", subMenuClickHandler);
    }

    function subMenuClickHandler(e) {
        e.preventDefault();

        var pageName = $(this).attr("data-pagename");
        common.loadPage(pageName);
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