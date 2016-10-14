/**
 *
 */
/*$(document).ready(function(){
    init();
});*/

define([ "common", "userAdd","ssq", "killing"], function(common, userAdd,ssq,killing) {

    function init() {
        initListener();
        initMenu();
    }
    ;

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