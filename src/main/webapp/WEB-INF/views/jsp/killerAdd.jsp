<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/" var="baseUrl" />
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${baseUrl}js/jquery.validate.js" type="text/JavaScript"></script>
<script src="${baseUrl}js/jquery.validate.min.js" type="text/JavaScript"></script>
<link type="text/css" href="${baseUrl}css/common.css" rel="stylesheet" />
<title>玩家信息录入</title>
<script>
    /**
     * 取路径
     */
    function getContextPath() {
        var fullPath = window.location.pathname;
        var contextPath = fullPath.split("/")[1];
        return contextPath;
    }
    //上传身份证
    var uploader = new plupload.Uploader({
        runtimes: 'flash',
        browse_button: 'pickfiles',
        container: 'container',
        max_file_size: '100kb',
        url: "/" + getContextPath() + "/upload",
        resize: {
            width: 100,
            height: 100,
            quality: 90
        },
        flash_swf_url: '${baseUrl}js/plupload.flash.swf',
        filters: [ {
            title: "Image files",
            extensions: "jpg,png,gif"
        } ]
    });
    uploader.init();

    uploader.bind('Error', function(up, err) {
        if (err.code == "-600") {
            alert('照片文件大小应小于100K！');
        }
    });
    //会在文件上传过程中不断触发，可以用此事件来显示上传进度
    uploader.bind('UploadProgress', function(up, files) {

    });

    document.getElementById('uploadImg').onclick = function() {
        uploader.start(); //调用实例对象的start()方法开始上传文件，当然你也可以在其他地方调用该方法
    };

    uploader.bind('FilesAdded', function(uploader, files) {
        for (var i = 0, len = files.length; i < len; i++) {
            var file_name = files[i].name; //文件名
            //构造html来更新UI
            $("input[id='upload0']").parents(".uploader").find(".filename").val(file_name);

        }
    });

    uploader.bind('FileUploaded', function(up, file, info) {
        if (file.status == "5") {
            //上传成功，返回文件名称.
            var fileSrc = info.response;
            $("#img0").attr("src", "${baseUrl}upload/" + fileSrc);
            $("#headPortraitName").val(fileSrc);
            $("input[id='upload0']").parents(".uploader").find(".filename").val(fileSrc);
            alert('恭喜：上传头像成功！');
        }
    });
</script>




</head>
<body>
  <div style="padding-top: 30px;">玩家信息录入</div>
  <div style="display: inline-block;">
    <form id="p006KillerAddForm">
      <table id="p006KillerAddTable" style="padding-top: 10px;">
        <tr>
          <th>玩家姓名</th>
          <td>
            <input id="p006PlayerNameTxt" name=playerName type="text" />
          </td>
        </tr>
        <tr>
          <th>玩家性别</th>
          <td>
            <input type="radio" name="gender" value="boy" checked="checked" id="p006GenderBoyRadio" />
            <label>男</label>
            <input type="radio" name="gender" value="girl" id="p006GenderGirlRadio" />
            <label>女</label>
          </td>
        </tr>
        <tr>
          <th>玩家年龄</th>
          <td>
            <input id="p006AgeTxt" name="age" type="text" />
          </td>
        </tr>
        <tr>
          <td>
            <button type="button" id="p006AddBtn">录入</button>
          </td>
        </tr>
      </table>
    </form>
  </div>

  <div class="uploadImageLeft">
    <table style="z: index:-9999; border-bottom: 2px solid #fff;">
      <tr>
        <td width="192" align="center">
          <img id="img0" src="" />
        </td>
      </tr>
      <tr>
        <td align="center" class="uploader" style="width: 500px;">
          <input type="hidden" id="headPortraitName" />
          <input type="text" class="filename img_edit_input" readonly />
          <div id="container" type="button" class="img_edit_input2">
            <span>浏览...</span>
          </div>
          <div id="uploadImg" type="button" class="img_edit_input2">
            <span>上传</span>
          </div>
          <input type="file" id="upload0" style="display: none;">
        </td>
      </tr>
      <tr>
        <td align="center">
          <font id="font0" style="color: gray">*图片格式为.jpg,.gif,.png</font>
        </td>
      </tr>

    </table>
  </div>

</body>
</html>