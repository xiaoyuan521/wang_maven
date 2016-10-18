/**
 *
 */
define([ "userAdd","ssq","killing","killRateSearch"], function(userAdd,ssq,killing,killRateSearch) {

    function init() {
        loadPage();
    }

    /**
     * ページ遷移メソッド
     */
    function loadPage(pagename) {

        var url = "/" + getContextPath() + "/" + pagename;
        $.ajax({
            url: url,
            dataType: "html"

        }).done(function(htmlContent) {
            $("#p002WelcomeDiv").html(htmlContent);
            if (pagename == "userAdd") {
                userAdd.init();
            }

            if (pagename == "ssq") {
                ssq.init();
            }

            if (pagename == "killing") {
                killing.init();
            }
            if (pagename == "killRateSearch") {
                killRateSearch.init();
            }
        })
    }

    function getContextPath() {
        var fullPath = window.location.pathname;
        var contextPath = fullPath.split("/")[1];
        return contextPath;
    }

    return {
        "loadPage": loadPage
    }
});