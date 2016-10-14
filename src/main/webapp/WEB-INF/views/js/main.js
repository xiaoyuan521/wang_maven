require.config({
    paths: {
    }
});

require(["welcome" ,"userAdd","result","ssq","datatable","killing"], function(welcome,userAdd,result,ssq,datatable,killing) {
    $(function() {
        welcome.init();
        userAdd.init();
        result.init();
        ssq.init();
        datatable.extend();
        killing.init();
    });
});