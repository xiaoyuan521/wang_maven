require.config({
    paths: {
    }
});

require(["welcome" ,"userAdd","result","ssq","datatable"], function(welcome,userAdd,result,ssq,datatable) {
    $(function() {
        welcome.init();
        userAdd.init();
        result.init();
        ssq.init();
        datatable.extend();
    });
});