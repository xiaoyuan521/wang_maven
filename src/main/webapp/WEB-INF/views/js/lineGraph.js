define([ "common" ], function(common) {

    function init() {
        initGraphData();
    }

    // 初期化图表数据
    function initGraphData() {
        var params = {};
        $.ajax({
            url: "/" + getContextPath() + "/killing/barGraphInit",
            type: 'POST',
            data: JSON.stringify(params),
            contentType: "application/json",
            dataType: "json",
            cache: false,
            success: function(data) {
                if (data.code == "ok") {
                    var index = 0;
                    var informationRateList = data.result.informationRateList;
                    var inforNameTmp = [];
                    var rateTmp = [];
                    $.each(informationRateList, function(index, item) {
                        var inforName = [ index + 1, item.inforName ];
                        var rate = [ index + 1, item.rate ];
                        rateTmp.push(rate);
                        inforNameTmp.push(inforName);

                    });
                    initLineGraphData(inforNameTmp, rateTmp);

                }
            }
        });
    }

    ;
    // 初期化折线图
    function initLineGraphData(inforNameTmp, rateTmp) {
        var e = {
            series: {
                lines: {
                    show: !0,
                    lineWidth: 2,
                    fill: !0,
                    fillColor: {
                        colors: [ {
                            opacity: 0
                        }, {
                            opacity: 0
                        } ]
                    }
                }
            },
            xaxis: {
                tickDecimals: 0,
                ticks: inforNameTmp
            },
            colors: [ "#1ab394" ],
            grid: {
                color: "#999999",
                hoverable: !0,
                clickable: !0,
                tickColor: "#D4D4D4",
                borderWidth: 0
            },
            legend: {
                show: !1
            }
        }
        var barData = [ {
            label: "胜率",
            data: rateTmp
        } ];
        $.plot($("#flot-line-chart"), barData, e);
        $("#flot-line-chart").UseTooltip();
    }
    var previousPoint = null, previousLabel = null;
    $.fn.UseTooltip = function() {
        $(this).bind("plothover", function(event, pos, item) {
            if (item) {
                if ((previousLabel != item.series.label) || (previousPoint != item.dataIndex)) {
                    previousPoint = item.dataIndex;
                    previousLabel = item.series.label;
                    $("#tooltip").remove();

                    var x = item.datapoint[0];
                    var y = item.datapoint[1];
                    var color = item.series.color;
                    showTooltip(item.pageX, item.pageY, color, "<strong>" + item.series.label + "</strong> : <strong>" + y + "</strong> ");
                }
            } else {
                $("#tooltip").remove();
                previousPoint = null;
            }
        });
    };
    function showTooltip(x, y, color, contents) {
        $('<div id="tooltip">' + contents + '%</div>').css({
            position: 'absolute',
            display: 'none',
            top: y - 40,
            left: x - 20,
            border: '2px solid ' + color,
            padding: '3px',
            'font-size': '9px',
            'border-radius': '5px',
            'background-color': '#fff',
            'font-family': 'Verdana, Arial, Helvetica, Tahoma, sans-serif',
            opacity: 0.9
        }).appendTo("body").fadeIn(200);
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