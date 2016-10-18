define([ "common" ], function(common) {

    function init() {

        initStatus();
        initDate();
        initFormValidate();
        initListener();
    }
    ;

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

                var option = $("<option value=''></option>").text("所有人");
                var rateOption = $("<option value=''></option>").text("所有角色");
                option.appendTo($("#p005PlayerNameSelect"));
                rateOption.appendTo($("#p005RoleSelect"));
                if (data.code == "ok") {
                    for (var i = 0; i < data.result.informationDtoList.length; i++) {
                        option = $("<option></option>").text(data.result.informationDtoList[i].playerName).val(data.result.informationDtoList[i].id);
                        $("#p005PlayerNameSelect").append(option);
                    }
                    for (var j = 0; j < data.result.roleDtoList.length; j++) {
                        rateOption = $("<option></option>").text(data.result.roleDtoList[j].role).val(data.result.roleDtoList[j].id);
                        $("#p005RoleSelect").append(rateOption);
                    }

                    // 胜率一览
                    $("#p005PlayerRateSearch").text("");
                    $("#p005PlayerRateSearch").text("玩家胜率查询");
                    var informationRateList = data.result.informationRateList;
                    createRateTable(informationRateList);
                }
            }
        });

    }

    /**
     * 日期
     */
    function initDate() {
        $("#p005Date").datepicker("option", $.datepicker.regional['zh-CN']);
        $("#p005Date").datepicker({
            changeMonth: true,
            changeYear: true,
            dateFormat: "yy/mm/dd",
        });
        $("#p005Date").datepicker('setDate', new Date());
    }

    /**
     * 校验
     */
    function initFormValidate() {
        $("#p005KillRateSearchForm").validate({
            rules: {
                date: {
                    date: true
                }
            },
            messages: {
                date: {
                    date: "请输入合法日期"
                }
            }
        });
    }

    /**
     * 按钮
     */
    function initListener() {

        // 检索按钮押下
        $("#p005SearchBtn").on("click", function() {
            $("#p005PlayerRateSearch").text("");
            $("#p005PlayerRateSearch").text("玩家胜率查询");
            var inforMationParam = {};
            $.ajax({
                url: "/" + getContextPath() + "/gameCountSearch",
                type: "POST",
                data: JSON.stringify(inforMationParam),
                contentType: "application/json",
                dataType: "json",
                cache: false,
                success: function(data) {
                    if (data.code == "ok") {
                        var informationDtoList = data.result.informationDtoList;
                        createRateTable(informationDtoList);
                    }
                }
            });
        });

    }

    /**
     * 创建胜率一览
     */
    function createRateTable(informationDtoList) {
        $("#p005InformationTable").show();
        $("#p005InformationTable").datatable({
            data: informationDtoList,
            columns: [ {
                name: "inforName",
                text: "用户名"
            }, {
                name: "allGamesCount",
                text: "总局数"
            }, {
                name: "successCount",
                text: "胜局"
            }, {
                name: "rate",
                text: "胜率",
                fn: rateHandler
            } ]
        });
    }

    function rateHandler(value, rowValue, tdDom) {

        return value + "%";
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