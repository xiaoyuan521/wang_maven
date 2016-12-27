require.config({
    paths: {}
});

require([ "welcome", "userAdd", "result", "ssq", "datatable", "killing", "killerAdd"],
        function(welcome, userAdd, result, ssq, datatable, killing,killerAdd ) {
    $(function() {
        welcome.init();
        userAdd.init();
        result.init();
        ssq.init();
        datatable.extend();
        killing.init();
    });
});