define([ "common" ], function(common) {

    function init() {

        initStatus();
        initDate();
        initFormValidate();
        initListener();

    };

    /**
     * 下拉初期化
     */
    function initStatus() {

        var params = {};
        $.ajax({
            url: "/" + getContextPath() + "/killing/init",
            type: 'POST',
            data: JSON.stringify(params),
            contentType: "application/json",
            dataType: "json",
            cache: false,
            success: function(data) {

                if (data.code == "ok") {
                    for (var i = 0; i < data.result.informationDtoList.length; i++) {
                        var option = $("<option></option>").text(data.result.informationDtoList[i].playerName).val(data.result.informationDtoList[i].id);
                        $("#p004PlayerNameSelect").append(option);
                    }

                    for (var j = 0; j < data.result.roleDtoList.length; j++) {
                        var option = $("<option></option>").text(data.result.roleDtoList[j].role).val(data.result.roleDtoList[j].id);
                        $("#p004RoleSelect").append(option);
                    }
                    // 初期化一览
                    $("#p004PlayerInforInsertSearch").text("");
                    $("#p004PlayerInforInsertSearch").text("玩家信息录入查询");
                    var playerDtoList = data.result.playerDtoList;

                    createTable(playerDtoList);

                }
            }
        });
    }

    /**
     * 日期
     */
    function initDate() {
        $("#p004Date").datepicker("option", $.datepicker.regional['zh-CN']);
        $("#p004Date").datepicker({
            changeMonth: true,
            changeYear: true,
            dateFormat: "yy/mm/dd",

        });
        $("#p004Date").datepicker('setDate', new Date());

    }

    /**
     * 校验
     */
    function initFormValidate() {
        $("#p004KillingAddForm").validate({
            rules: {
                date: {
                    required: true,
                    date:true
                }
            },
            messages: {
                date: {
                    required: "必须入力日期",
                    date:"请输入合法日期"
                }
            }
        });
    }

    /**
     * 按钮
     */
    function initListener() {

        // 录入按钮按下
        $("#p004AddBtn").on("click", function() {
            $("#p004PlayerInforInsertSearch").text("");
            $("#p004PlayerInforInsertSearch").text("玩家信息录入查询");
            var params = {};
            params["inforId"] = $("#p004PlayerNameSelect").val();
            params["roleId"] = $("#p004RoleSelect").val();
            params["date"] = $("#p004Date").val();
            params["gameStatus"] = $("input:radio[name='gameStatus']:checked").val();

            if (!$("#p004KillingAddForm").valid()) {
                $("#p004KillingAddForm").submit();
                return;
            }
            $("#p004PlayerTable").empty();

            $.ajax({
                url: "/" + getContextPath() + "/playerAdd",
                type: 'POST',
                data: JSON.stringify(params),
                contentType: "application/json",
                dataType: "json",
                cache: false,
                success: function(data) {
                    if (data.code == "ok") {

                        var playerDtoList = data.result.playerDtoList;
                        /*
                         * 下面代码是自己画表格
                         *
                         var $table = $("<table style='border:1px solid;border-collapse:collapse'><tr style='border:1px solid'><th>用户名</th></tr></table>");
                         $.each(inforNameList, function(name, value) {
                             $table.append("<tr style='border:1px solid'><td>" + value + "</td></tr>");
                         });
                         $("#p004PlayerTable").append($table);*/

                        createTable(playerDtoList);

                    }
                }
            });

        });

    }
    /**
     * 创建登录信息一览
     */
    function createTable(playerDtoList) {
        $("#p004InformationTable").hide();
        $("#p004PlayerTable").show();
        $("#p004PlayerTable").datatable({
            data: playerDtoList,
            columns: [ {
                name: "inforName",
                text: "用户名"
            }, {
                name: "date",
                text: "日期"
            }, {
                name: "roleName",
                text: "角色"
            }, {
                name: "gamestatus",
                text: "输赢状态",
                fn: playerGameStatusHandler
            } ]
        });
    }

    // 胜率显示
    function playerGameStatusHandler(value, rowValue, tdDom) {
        if (value == "success") {
            return "赢";
        } else if (value == "fail") {
            return "输";
        } else {
            return "平";
        }
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