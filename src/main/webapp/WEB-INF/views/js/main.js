require.config({
    paths: {}
});

require([ "welcome", "userAdd", "result", "ssq", "datatable", "killing", "killRateSearch", "killerAdd"  ],
        function(welcome, userAdd, result, ssq, datatable, killing, killRateSearch,killerAdd) {
    $(function() {
        welcome.init();
        userAdd.init();
        result.init();
        ssq.init();
        datatable.extend();
        killing.init();
        killRateSearch.init();
        killerAdd.init();
    });
});