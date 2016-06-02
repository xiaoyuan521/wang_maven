define([ "common" ], function(common) {

    function init() {
        initListener();
    };
    function initListener() {
        $("#p003UserAddBtn").on("click", function() {
            var params = {};
            params["username"] = $("#p003UserNameTxt").val();
            params["gender"] = $("#p003GenderTxt").val();
            params["age"] = $("#p003AgeTxt").val();
            params["score"] = $("#p003ScoreTxt").val();
            $.ajax({
                url: "/" + getContextPath() + "/userAddInit",
                type: 'POST',
                data: JSON.stringify(params),
                contentType: "application/json",
                dataType: "json",
                cache: false,
                success: function(data) {

                    if (data.code == "ok") {

                        alert("登陆成功了,返回查询画面")
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