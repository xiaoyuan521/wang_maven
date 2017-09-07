require.config({
    paths: {}
});

require([ "welcome", "userAdd", "result", "ssq", "datatable", "killing", "killerAdd", "forum" ], function(welcome, userAdd, result, ssq, datatable, killing, killerAdd, forum) {
    $(function() {
        welcome.init();
        userAdd.init();
        result.init();
        ssq.init();
        datatable.extend();
        killing.init();
        forum.init();
    });
});