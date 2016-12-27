define([ "common" ], function(common) {

    function init() {
        initStatus();
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
                var chartGraphOption = $("<option value=''></option>").text("");
                if (data.code == "ok") {
                    // 饼图radio页面的姓名下拉框初期化
                    for (var i = 0; i < data.result.informationDtoList.length; i++) {
                        chartGraphOption = $("<option></option>").text(data.result.informationDtoList[i].playerName).val(data.result.informationDtoList[i].id);
                        $("#p005PlayerNameChartGraphSelect").append(chartGraphOption);
                    }
                    initParams();
                }
            }
        });
    }

    /**
     * 初期化页面
     */
    function initParams() {
        // 个人饼图检索初期化
        $("#p005PlayerRateSearch").text("");
        $("#p005PlayerRateSearch").text("个人战绩查询（饼图）");
        var informationParam = {};
        informationParam["inforId"] = $("#p005PlayerNameChartGraphSelect").val();
        $.ajax({
            url: "/" + getContextPath() + "/chartGraphSearch",
            type: "POST",
            data: JSON.stringify(informationParam),
            contentType: "application/json",
            dataType: "json",
            cache: false,
            success: function(data) {
                if (data.code == "ok") {
                    var chartGraphList = data.result.chartGraphList;
                    initChartGraph(chartGraphList);
                }
            }
        });
    }

    /**
     * 按钮
     */
    function initListener() {

        // 个人饼图检索按钮押下
        $("#p005chartGraphSearchBtn").off("click").on("click", function() {
            $("#p005PlayerRateSearch").text("");
            $("#p005PlayerRateSearch").text("个人战绩查询（饼图）");
            var informationParam = {};
            informationParam["inforId"] = $("#p005PlayerNameChartGraphSelect").val();
            $.ajax({
                url: "/" + getContextPath() + "/chartGraphSearch",
                type: "POST",
                data: JSON.stringify(informationParam),
                contentType: "application/json",
                dataType: "json",
                cache: false,
                success: function(data) {
                    if (data.code == "ok") {
                        var chartGraphList = data.result.chartGraphList;
                        initChartGraph(chartGraphList);
                    }
                }
            });

        });
    }

    // 初期化饼图
    function initChartGraph(chartGraphList) {

        var options = {
            series: {
                pie: {
                    show: !0
                }
            },
            grid: {
                hoverable: !0
            },
            tooltip: !0,
            tooltipOpts: {
                content: "%p.0%, %s",
                shifts: {
                    x: 20,
                    y: 0
                },
                defaultTheme: !1
            }
        };

        var e = [];
        var a = 0;
        for (var i = 0; i < chartGraphList.length; i++) {
            a += chartGraphList[i].npl;
        }
        if (a == 0) {
            $("#flot-pie-chart").hide();
            alert("此用户无数据");
        } else {
            $("#flot-pie-chart").show();
            var sColor = [ '#FF0000', '#800080', '#00FA9A', '#1E90FF', '#FAFAD2' ]
            $.each(chartGraphList, function(index, item) {
                e.push({
                    label: item.role,
                    data: item.npl,
                    color: sColor[index]
                });
            });
        }
        $.plot($("#flot-pie-chart"), e, options)
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