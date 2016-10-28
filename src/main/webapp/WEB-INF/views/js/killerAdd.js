define([ "common" ], function(common) {

    function init() {
        initFocus();
        initFormValidate();
        initListener();

    }
    ;

    /**
     * 焦点
     */
    function initFocus() {
        $("#p006PlayerNameTxt").first().focus();
    }

    /**
     * 校验
     */
    function initFormValidate() {
        $("#p006KillerAddForm").validate({
            rules: {
                playerName: {
                    required: true,
                    maxlength: 85
                },
                gender: {
                    required: true,
                    maxlength: 85
                },
                age: {
                    required: true,
                    digits: true,
                    maxlength: 85
                }
            },
            messages: {
                playerName: {
                    required: "必须入力姓名",
                    maxlength: "长度不能超过85字符"
                },
                gender: {
                    required: "必须入力性别",
                    maxlength: "长度不能超过85字符"
                },
                age: {
                    required: "必须入力年龄",
                    digits: "必须输入整数",
                    maxlength: "长度不能超过85字符"
                }
            }
        });
    }

    /**
     * 按钮操作
     */
    function initListener() {
        /**
         * 登录按钮
         */
        $("#p006AddBtn").on("click", function() {

            var params = {};
            params["playerName"] = $("#p006PlayerNameTxt").val();
            params["gender"] = $("input:radio[name='gender']:checked").val();
            params["age"] = $("#p006AgeTxt").val();
            if (!$("#p006KillerAddForm").valid()) {
                $("#p006KillerAddForm").submit();
                return;
            }
            $.ajax({
                url: "/" + getContextPath() + "/killerAddInit",
                type: 'POST',
                data: JSON.stringify(params),
                contentType: "application/json",
                dataType: "json",
                cache: false,
                success: function(data) {
                    if (data.code == "ok") {
                        alert("保存成功");
                    }
                }
            });
        });
    }

    /**
     * 取路径
     */
    function getContextPath() {
        var fullPath = window.location.pathname;
        var contextPath = fullPath.split("/")[1];
        return contextPath;
    }

    return {
        "init": init
    }
});