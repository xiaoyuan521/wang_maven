define([ "common", "userAdd", "welcome" ], function(common, userAdd, welcome) {

    function init() {
        initParams();
        initListener();
    }
    ;
    function initParams() {
        $("#p004RefeshBtn").on("click", function() {
            window.location.reload();
        })
    }

    $("#p004BackLoginBtn").on("click", subMenuClickHandler);

    function subMenuClickHandler() {
        window.location.href = "/" + getContextPath() + "/welcome";
    }

    function initListener() {
        $("#p004DownloadImgBtn").on("click", function() {
            $.ajax({
                url: "/" + getContextPath() + "/download",
                type: "POST",
                data: JSON.stringify({
                    fileName: $("#p004FileNmme").val()
                }),
                    contentType: "application/json",
                    dataType: "json",
                    cache: false,
                    success: function(data) {

                        if(data.code == "ok"){
                            alert("文件下载成功，下载到C:/Users/wang_changyuan/upload");
                        }
                    }
            });
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