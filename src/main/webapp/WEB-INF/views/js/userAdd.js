define([ "common" ], function(common) {

    function init() {
        initFocus();
        initDate();
        initFormValidate();
        initListener();
    }
    ;
    /**
     * 焦点
     */
    function initFocus() {
        $("#p003UserNameTxt").first().focus();
    }

    /**
     * 日期
     */
    function initDate() {

        $('#p003DateTxt').datepicker('option', $.datepicker.regional['zh-CN']);

        $("#p003DateTxt").datepicker({
            changeMonth: true,
            changeYear: true,
            dateFormat: "yy/mm/dd"
        });
    }

    /**
     * 校验
     */
    function initFormValidate() {
        $("#p003UserAddForm").validate({
            rules: {
                username: {
                    required: true,
                    maxlength: 85
                },
                gender: {
                    required: true,
                    maxlength: 85
                },
                age: {
                    required: true,
                    digits: true,
                    maxlength: 85
                },
                score: {
                    required: true,
                    digits: true,
                    maxlength: 85
                },
                date: {
                    required: true,
                }
            },
            messages: {
                username: {
                    required: "必须入力姓名",
                    maxlength: "长度不能超过85字符"
                },
                gender: {
                    required: "必须入力性别",
                    maxlength: "长度不能超过85字符"
                },
                age: {
                    required: "必须入力年龄",
                    digits: "必须输入整数",
                    maxlength: "长度不能超过85字符"
                },
                score: {
                    required: "必须入力成绩",
                    digits: "必须输入整数",
                    maxlength: "长度不能超过85字符"
                },
                date: {
                    required: "必须入力日期"
                }
            }
        });
    }

    function initListener() {

        /**
         * 登录按钮
         */
        $("#p003UserAddBtn").on("click", function() {

            var params = {};
            params["username"] = $("#p003UserNameTxt").val();
            params["gender"] = $("input:radio[name='gender']:checked").val();
            params["age"] = $("#p003AgeTxt").val();
            params["score"] = $("#p003ScoreTxt").val();
            if (!$("#p003UserAddForm").valid()) {
                $("#p003UserAddForm").submit();
                return;
            }
            $.ajax({
                url: "/" + getContextPath() + "/userAddInit",
                type: 'POST',
                data: JSON.stringify(params),
                contentType: "application/json",
                dataType: "json",
                cache: false,
                success: function(data) {

                    if (data.code == "ok") {

                        $("#p003UserNameTxt").val("");
                        $("#p003GenderBoyRadio").prop("checked", "true");
                        $("#p003AgeTxt").val("");
                        $("#p003ScoreTxt").val("");
                        $("#p003DateTxt").val("");
                        var params = {};
                        params["username"] = $("#p003UserNameTxt").val();
                        params["gender"] = $("input:radio[name='gender']:checked").val();
                        params["age"] = $("#p003AgeTxt").val();
                        params["score"] = $("#p003ScoreTxt").val();
                        $.ajax({
                            url: "/" + getContextPath() + "/studentList",
                            type: 'POST',
                            data: JSON.stringify(params),
                            contentType: "application/json",
                            dataType: "json",
                            cache: false,
                            success: function(data) {

                                if (data.code == "ok") {
                                    var studentDtoList = data.result.studentDtoList;
                                    createTable(studentDtoList);
                                }
                            }
                        });
                    }
                }
            });
        });

        /**
         * 检索按钮
         */
        $("#p003StudentSearchBtn").on("click", function() {
            var params = {};
            params["username"] = $("#p003UserNameTxt").val();
            params["gender"] = $("input:radio[name='gender']:checked").val();
            params["age"] = $("#p003AgeTxt").val();
            params["score"] = $("#p003ScoreTxt").val();
            $.ajax({
                url: "/" + getContextPath() + "/studentList",
                type: 'POST',
                data: JSON.stringify(params),
                contentType: "application/json",
                dataType: "json",
                cache: false,
                success: function(data) {

                    if (data.code == "ok") {
                        var studentDtoList = data.result.studentDtoList;
                        createTable(studentDtoList);
                    }
                }
            });

        });

        /**
         * 清除按钮
         */
        $("#p003ClearBtn").on("click", function() {
            $("#p003UserNameTxt").val("");
            $("#p003GenderTxt").val("boy");
            $("#p003GenderBoyRadio").prop("checked", "true");
            $("#p003AgeTxt").val("");
            $("#p003ScoreTxt").val("");
            $("#p003DateTxt").val("");

        });

    }

    /**
     * 创建一览
     */
    function createTable(studentDtoList) {
        $("#p003StudentTable").datatable({
            data: studentDtoList,
            columns: [ {
                name: "username",
                text: "用户名"
            }, {
                name: "gender",
                text: "性别",
                fn: studentAgeHandler
            }, {
                name: "age",
                text: "年龄"
            }, {
                name: "score",
                text: "成绩"
            }, {
                name: "delete",
                text: "删除",
                fn: deleteStudent
            } ],
            rowClickFn: rowClickCallback
        });
    }

    /**
     * 性别显示为汉字
     */
    function studentAgeHandler(value, rowValue, tdDom) {
        if (value == "boy") {
            return "男";
        } else if (value == "girl") {
            return "女";
        }

    }

    /**
     * 行点击
     */
    function rowClickCallback(rowValue, trDom) {
        var studentId = rowValue.studentId;
        $.ajax({
            url: "/" + getContextPath() + "/studentEdit",
            type: "POST",
            data: JSON.stringify({
                "studentId": studentId
            }),
            contentType: "application/json",
            dataType: "json",
            cache: false,
            success: function(data) {
                var studentEditList = data.result.studentEditList;
                for (var i = 0; i < studentEditList.length; i++) {
                    $("#p004UserNameTxt").val(studentEditList[i].username);
                    console.log(studentEditList[i].gender)
                    if (studentEditList[i].gender != "boy" && studentEditList[i].gender != "girl") {
                        $("#p004Gender input:radio[name=gender][value='boy']").prop("checked", "true");
                    } else {
                        $("#p004Gender input:radio[name=gender][value='" + studentEditList[i].gender + "']").prop("checked", "true");
                    }
                    $("#p004AgeTxt").val(studentEditList[i].age);
                    $("#p004ScoreTxt").val(studentEditList[i].score);
                    $("#p004StudentIdTxt").val(studentEditList[i].studentId);

                }
                // 不适用for循环
                /* $("#p004UserNameTxt").val(studentEditList[0].username);
                $("#p004GenderTxt").val(studentEditList[0].gender);
                $("#p004AgeTxt").val(studentEditList[0].age);
                $("#p004ScoreTxt").val(studentEditList[0].score);*/

                // 后台使用Optional方法往前台传值，前台studentEditList1.username直接取到值
                // var studentEditList1 = data.result.studentEditList1;
                // $("#p004UserNameTxt").val(studentEditList1.username);
            }
        });

        $("#p004UserEditDiv").dialog({
            title: "学生信息编辑",
            height: 550,
            width: 500,
            modal: true,
            buttons: [ {
                text: "关闭",
                click: function() {
                    $(this).dialog("close");
                }
            }, {
                text: "更新",
                click: clickUpdateBtnHandler
            } ]
        });
    }

    /**
     * 更新
     */
    function clickUpdateBtnHandler() {
        /**
         * 使用$("#p004UserEditForm").serialize()发ajax
         */
        $.ajax({
            url: "/" + getContextPath() + "/studentUpdate",
            type: "POST",
            data: $("#p004UserEditForm").serialize(),
            success: function(data) {
                if (data.code == "ok") {
                    alert("更新成功");
                    $("#p004UserEditDiv").dialog("close");
                    var params = {};
                    params["username"] = $("#p003UserNameTxt").val();
                    params["gender"] = $("input:radio[name='gender']:checked").val();
                    params["age"] = $("#p003AgeTxt").val();
                    params["score"] = $("#p003ScoreTxt").val();
                    $.ajax({
                        url: "/" + getContextPath() + "/studentList",
                        type: 'POST',
                        data: JSON.stringify(params),
                        contentType: "application/json",
                        dataType: "json",
                        cache: false,
                        success: function(data) {

                            if (data.code == "ok") {
                                var studentDtoList = data.result.studentDtoList;
                                createTable(studentDtoList);
                            }
                        }
                    });
                }
            }
        });
    }

    /**
     * 删除
     */
    function deleteStudent(value, rowValue, tdDom) {
        var deleteBtn = $("<input type='button' id='deleteBtn' value='删除' />");
        var studentId = rowValue.studentId;
        deleteBtn.appendTo(tdDom);

        deleteBtn.off("click").on("click", function(e) {
            e.stopPropagation();
            $.ajax({
                url: "/" + getContextPath() + "/studentDel",
                type: "POST",
                data: JSON.stringify({
                    "studentId": studentId
                }),
                contentType: "application/json",
                dataType: "json",
                cache: false,
                success: function(data) {

                    var params = {};
                    params["username"] = $("#p003UserNameTxt").val();
                    params["gender"] = $("input:radio[name='gender']:checked").val();
                    params["age"] = $("#p003AgeTxt").val();
                    params["score"] = $("#p003ScoreTxt").val();
                    $.ajax({
                        url: "/" + getContextPath() + "/studentList",
                        type: 'POST',
                        data: JSON.stringify(params),
                        contentType: "application/json",
                        dataType: "json",
                        cache: false,
                        success: function(data) {

                            if (data.code == "ok") {
                                var studentDtoList = data.result.studentDtoList;
                                createTable(studentDtoList);
                                alert("删除成功 !!!");
                            }
                        }
                    });

                }
            });
        });

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