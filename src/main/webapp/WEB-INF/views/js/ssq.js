define([ "common", "userAdd", "welcome" ], function(common, userAdd, welcome) {

    function init() {
        initListener();
    };

    function getNumber() {

        var redBallListResult = new Array();
        var redBallList = new Array();
        while (redBallList.length < 6) {
            var number = 1 + Math.random() * (33);
            var numberResult = Math.floor(number) < 10 ? "0" + Math.floor(number) : Math.floor(number);
            var blueBall = 1 + Math.random() * (16);
            if (redBallList.indexOf(numberResult) == -1) {
                redBallList.push(numberResult);
            }
        }

        for (var i = 0; i < redBallList.length; i++) {
            var redBallItem = Math.floor(redBallList[i]);
            redBallListResult.push((redBallItem < 10 ? "0" + redBallItem : redBallItem) + " ");
        }

        redBallListResult.sort();

        $("#redBall").text(redBallListResult);
        var blueBallResult = Math.floor(blueBall);
        $("#blueBall").text(blueBallResult < 10 ? "0" + blueBallResult : blueBallResult);
    }

    function initListener() {
        $("#click").on("click", function() {
            getNumber();
        });
    }
    return {
        "init": init
    }
});