require.config({
    paths: {
    }
});

require(["welcome" ,"userAdd","result","ssq"], function(welcome,userAdd,result,ssq) {
    $(function() {
        welcome.init();
        userAdd.init();
        result.init();
        ssq.init();
    });
});