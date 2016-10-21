define([ "common" ], function(common) {

    function init() {

        initParams();
        initStatus();
        initDate();
        initFormValidate();
        initListener();
    }
    ;

    /**
     * 初期化一览
     */
    function initParams() {
        $("#p005RecordSearch").hide();
        $("#p005RoleTr").hide();
        $("#p005RoleDateTr").hide();
        $("#p005GameStatusTr").hide();
        $("#p005RecordSearchBtn").hide();
        $("#p005RateSearch").show();
        $("#p005InformationTable").show();
        $("#p005PlayerRateSearch").text("");
        $("#p005PlayerRateSearch").text("玩家胜率查询");
        $("#p005InformationTable").show();
        $("#p005PlayerTable").hide();

    }

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
                    // 游戏记录查询
                    var playerDtoList = data.result.playerDtoList;
                    createTable(playerDtoList);
                    $("#p005PlayerTable").hide();
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

        $("input:radio[name='functionCheck']").on("change", function() {
            if ($("input:radio[name='functionCheck']:checked").val() == "rate") {
                $("#p005RecordSearch").hide();
                $("#p005RoleTr").hide();
                $("#p005RoleDateTr").hide();
                $("#p005GameStatusTr").hide();
                $("#p005RecordSearchBtn").hide();
                $("#p005RateSearch").show();
                $("#p005RateSearchBtn").show();
                $("#p005InformationTable").show();
                $("#p005PlayerRateSearch").text("");
                $("#p005PlayerRateSearch").text("玩家胜率查询");
                $("#p005InformationTable").show();
                $("#p005PlayerTable").hide();
                $("#p005PlayerNameSelect").val("");

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
                            // 胜率一览
                            $("#p005PlayerRateSearch").text("");
                            $("#p005PlayerRateSearch").text("玩家胜率查询");
                            var informationRateList = data.result.informationRateList;
                            createRateTable(informationRateList);
                        }
                    }
                });

            } else {
                $("#p005PlayerNameSelect").val("");
                $("#p005RoleSelect").val("");
                $("#p005Date").val("");
                $("#p005AllRadio").prop("checked", true);
                $("#p005RecordSearch").show();
                $("#p005RoleTr").show();
                $("#p005RoleDateTr").show();
                $("#p005GameStatusTr").show();
                $("#p005RecordSearchBtn").show();
                $("#p005RateSearch").hide();
                $("#p005RateSearchBtn").hide();
                $("#p005InformationTable").hide();
                $("#p005PlayerRateSearch").text("");
                $("#p005PlayerRateSearch").text("游戏记录查询");
                $("#p005InformationTable").hide();
                $("#p005PlayerTable").show();

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

                            // 游戏记录查询
                            var playerDtoList = data.result.playerDtoList;
                            createTable(playerDtoList);
                        }
                    }
                });

            }
        });

        // 胜率检索按钮押下
        $("#p005RateSearchBtn").off("click").on("click", function() {
            $("#p005PlayerRateSearch").text("");
            $("#p005PlayerRateSearch").text("玩家胜率查询");
            var inforMationParam = {};
            inforMationParam["inforId"] = $("#p005PlayerNameSelect").val();
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
                        $("#p005PlayerTable").hide();
                    }
                }
            });

        });

        // 记录检索按钮押下
        $("#p005RecordSearchBtn").off("click").on("click", function() {
            $("#p005PlayerRateSearch").text("");
            $("#p005PlayerRateSearch").text("游戏记录查询");
            var inforMationParam = {};
            inforMationParam["inforId"] = $("#p005PlayerNameSelect").val();
            inforMationParam["roleId"] = $("#p005RoleSelect").val();
            inforMationParam["date"] = $("#p005Date").val();
            inforMationParam["gameStatus"] = $("input:radio[name='gameStatus']:checked").val();
            $.ajax({
                url: "/" + getContextPath() + "/playerRecordSearch",
                type: "POST",
                data: JSON.stringify(inforMationParam),
                contentType: "application/json",
                dataType: "json",
                cache: false,
                success: function(data) {
                    if (data.code == "ok") {
                        // 游戏记录查询
                        var playerListList = data.result.playerListList;
                        createTable(playerListList);
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
            }, {
                name: "allWerewolfCount",
                text: "狼人场数",
            }, {
                name: "werewolfRate",
                text: "狼人胜率",
                fn: rateHandler
            }, {
                name: "allProphetCount",
                text: "预言家场数",
            }, {
                name: "prophetRate",
                text: "预言家胜率",
                fn: rateHandler
            }, {
                name: "allWitchCount",
                text: "女巫场数",
            }, {
                name: "witchRate",
                text: "女巫胜率",
                fn: rateHandler
            }, {
                name: "allHunterCount",
                text: "猎人场数",
            }, {
                name: "hunterRate",
                text: "猎人胜率",
                fn: rateHandler
            }, {
                name: "allCivilianCount",
                text: "平民场数",
            }, {
                name: "civilianRate",
                text: "平民胜率",
                fn: rateHandler
            } ]
        });
    }

    /**
     * 创建登录信息一览
     */
    function createTable(playerDtoList) {
        $("#p004InformationTable").hide();
        $("#p005PlayerTable").show();
        $("#p005PlayerTable").datatable({
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