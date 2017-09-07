define([ "common" ], function(common) {

    function init() {
        getBkInfo();
    }
    ;
    function getBkInfo() {

        var params={};
        $.ajax({
            url: "/" + getContextPath() + "/forumInit",
            type:"POST",
            data: JSON.stringify(params),
            contentType: "application/json",
            dataType: "json",
            cache: false,
            success: function(data) {
                if (data.code == "ok") {
                var $formHtml = $("#info");
                $formHtml.empty();
                var html = "";
                var BkImg="/"+getContextPath()+"/images/a2.jpg";
                var forumList =data.result.forumList;
                for(var i = 0; i<forumList.length;i++){
                    var forumItem = forumList[i];
                    html += "<div class='box'><table border='0' height='85' width='100%'><tr>";
                    html += "<input type='hidden' id='bk' value='"+ forumItem.bkid +"'/><input type='hidden' id='bz' value='"+ forumItem.bkid+"'/>";
                    html += "<td width='10%' style='text-align:center;'><a style='text-decoration:none'><img src='" + BkImg + "' width='73' height='73'></a></td>";
                    html += "<td width='35%' style='text-align:left; padding-left:10px;'><a href='javascript:void(0)' style='text-decoration:none;color: #666;' target='_self' >"+ forumItem.bkname +"</a></td>";
                    html += "<td width='20%' style='text-align:center;'>"+ forumItem.bkid +"</td>";
                    html += "<td width='20%' style='text-align:center; paddi ng-left:10px;'>";
                    html += "<p><span>┏ 主题：</span>"+ "ztmc" +"</p><p><span>┠ 作者：</span>"+forumItem.bkid +"</p><p><span>┗ 时间：</span>"+ forumItem.bkid+"</p></td>";
                    html += "<td width='15%' style='text-align:center;'>"+ "wangxiaoyuan" +"</td>";
                    html += " </tr></table></div>";
                }
                $formHtml.html(html);

                }
            }

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