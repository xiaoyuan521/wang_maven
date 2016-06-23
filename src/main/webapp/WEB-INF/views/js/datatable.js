/**
 * usage:
 * user rowspan for nested datasource construct
 *
 *     function createDatatable(data, destroyFlag) {
 *      $("#p000TableDiv").datatable({
 *          destroy: destroyFlag,
 *
 *          // user radio button for selection
 *          sel: "radio",
 *          selKey: "id",
 *          selIds: "2",
 *
 *          // user checkbox for selection
 *          sel: "checkbox",
 *          selKey: "id",
 *          selIds: ["2", "3"],
 *
 *          data : data,
 *          columns : [ {
 *              name : "id",
 *              text : "用户Id"
 *          }, {
 *              name : "name",
 *              text : "用户姓名",
 *              fn: nameColumnHandler
 *          }, {
 *              name : "age",
 *              text : "AGE"
 *          }, {
 *              name : "memo",
 *              text : "MEMO",
 *              fn : memoColumnHandler
 *          },{
 *              name : "details.first",
 *              text : "FIRST",
 *              fn: function(value, rowValue, tdDom){
 *                  var subIndex = arguments[3];
 *                  tdDom.text(value + " - index:" + subIndex + " - id:" + rowValue.id);
 *                  return false;
 *               }
 *          },{
 *              name : "details.last",
 *              text : "LAST"
 *          }]
 *      })
 *  }
 *
 */

/**
 *
 * usage:
 * normal
 *
 * $("#table").datatable({
 *      destroy: true,
 *      height: 200;
 *
 *      sel: true,
 *      selKey: "id",
 *      selIds: ["1","2","3"],
 *
 *      columns: [{
 *          name: "id",
 *          text: "用户Id",
 *          headerClass: "headerAlignRight",
 *          headerFn: function(td, text){
 *              td.addClass("xxx");
 *          }
 *          fn: function(value, rowValue, tdDom){
 *              reutrn Dom|String|void
 *          }
 *      },{
 *          name: "age",
 *          text: "年龄",
 *          sortAble: false,
 *          fn: function(value, rowValue, tdDom){
 *              reutrn Dom|String|void
 *          }
 *      }],
 *      data: xxx,
 *      rowFn: function(rowValue, trDom){
 *      },
 *      drawFn: function(){
 *      },
 *      sort: {
 *          name: "xxx",
 *          scending: "asc"
 *      }
 * });
 *
 * structure:
 *
 * <div class="dtWrapper">
 *  <div class="dtHeadWrapper">
 *      <div class="dtHead">
 *          <table>
 *              <thead>
 *                  <tr>
 *                     <td>
 *                     </td>
 *                     ...
 *                 </tr>
 *            </thead>
 *            <tbody>
 *            </tbody>
 *          </table>
 *      </div>
 *      <div class="tdHeaderBlank">
 *      </div>
 *  </div>
 *  <div class="dtBodyWrapper">
 *      <div class="dtBody">
 *          <table>
 *              <thead>
 *              </thead>
 *              <tbody>
 *                  <tr>
 *                      <td>
 *                      </td>
 *                      ...
 *                  </tr>
 *              </tbody>
 *          </table>
 *      </div>
 * </div>
 *</div>
 *
 * memo:
 * 1. the width of the datatable is upto the parent element
 * 2. width = parent element's width - scrollBarWidth
 * 3. support window resize
 *
 * support interface:
 * 1. reinit
 * $("#xxx").datatable({xxx:xxx});
 * 2. destroy & reinit
 * $("#xxx").datatable({destroy: true});
 * 3. destroy
 * $("#xxx").datatable("destroy");
 * 4. get selecte checkbox ids
 * $("#xxx").datatable("getSelIds");
 *
 * about destroy param in init params
 * user [destroy: true] to destroy the previous datable dom & data- attributes and jquery data object
 *
 * about checkbox for selection
 * use
 * sel: true
 * selKey: "" // get the select id from datasource
 * selIds: [x,x,x] // init select
 *
 */


define([], function() {
    var scrollBarWidth = 17;
    var headerHeight = 40;
    var rowMousedownX = 0;
    var rowMousedownY = 0;

    var SEL_KEY = "dt_sel";
    var PARAM_KEY = "dt_param";
    var STATUS_KEY ="data-status";

    function extend() {
        $.fn.datatable = Datatable;
    }

    var Datatable = function(params) {
        var baseDom = this;

        // API interface support
        if(typeof params === "string"){
            var argArr = Array.prototype.slice.call(arguments);
            var callMethodName = argArr.shift();
            return API[callMethodName].apply(this, argArr);
        }

        // redraw support
        if(params.destroy === true){
            _destroy(baseDom);
        }

        // support checkbox or radio button for selection
        selectionSupport(baseDom, params);

        // init baseDom
        init(baseDom, params);
        // draw the datatable
        var height = params.height;
        var drawFn = params.drawFn;
        var dtWrapper = $("<div class='dtWrapper'></div>");
        var noDataMsg = $("<div class='noData'>該当データは存在しません。</div>");
        createHeader(params).appendTo(dtWrapper);
        createBody(params).appendTo(dtWrapper);
        noDataMsg.appendTo(dtWrapper.find(".dtBodyWrapper"));
        if (params.data && params.data.length != 0) {
            noDataMsg.hide();
        } else {
            noDataMsg.show();
        }
        dtWrapper.appendTo(baseDom);
        calcWidth(baseDom);
        if (height) {
            calcHeight(baseDom, height);
        }
        if (drawFn) {
            drawFn(baseDom);
        }
        // supportResize(baseDom);
        return this;
    };

    function createHeader(params) {
        var columns = params.columns;
        var headerDom = $("<div class='dtHeadWrapper'><div class='dtHead'></div></div>");
        var headerBlankDom = $("<div class='tdHeaderBlank'></div>");
        var tableDom = $("<table class='table table-hover bgcolor-normal-30'><thead></thead></table>");
        var trDom = $("<tr></tr>");
        for (var i = 0; i < columns.length; i++) {
            var column = columns[i];
            var titleText = column.text || "";
            var nameForClass = column.name.replace(".", "");
            var td = $("<td></td>").html(titleText).addClass("col-" + nameForClass);
             td = setHeaderTdStyle(column, td, params.sortFn);
            if (column.headerClass) {
                td.addClass(column.headerClass);
            }
            if (column.headerFn) {
                column.headerFn(td, titleText);
            }
            td.appendTo(trDom);
        }
        trDom.appendTo(tableDom.find("thead"));
        tableDom.appendTo(headerDom.find(".dtHead"));
        headerBlankDom.css("width", scrollBarWidth + "px").appendTo(headerDom);

        // init sort
        if (params.sort) {
            var sortInfo = params.sort[0];
            var sortColumn = sortInfo.name;
            var sortScending = sortInfo.scending;
            trDom.find(".col-" + sortColumn).removeClass("sort_asc").removeClass("sort_desc").addClass(
                    "sort_" + sortScending);
        }

        return headerDom;
    }

    function setHeaderTdStyle(columnSetting, td, sortFn) {

        // default setting config: false
        if (!columnSetting.sortAble) {
            columnSetting.sortAble = false;
        }

        // do not need header sort, return
        if (columnSetting.sortAble === false) {
            // nothing to do
            return td;
        }

        // need header sort
        // set click able style and bind the click event
        td.addClass("sort_both");
        td.addClass("sortAble");

        td.on("click", {
            "sortFn" : sortFn
        }, headerSortHandler);
        return td;
    }

    function headerSortHandler(e) {
        var sortFn = e.data.sortFn;
        var thisTd = $(this);
        if ((thisTd).hasClass("sort_asc")) {
            thisTd.removeClass("sort_asc");
            thisTd.addClass("sort_desc");
        } else if ((thisTd).hasClass("sort_desc")) {
            thisTd.removeClass("sort_desc");
            thisTd.addClass("sort_asc");
        } else {
            thisTd.addClass("sort_asc");
        }
        thisTd.siblings(".sortAble").removeClass("sort_asc").removeClass("sort_desc").addClass("sort_both");

        if (sortFn) {
            var sortColumn = /col-(.+?)\b/.exec(thisTd.attr("class"))[1];
            var sortScending = thisTd.hasClass("sort_asc") ? "asc" : "desc";
            sortFn([ {
                name : sortColumn,
                scending : sortScending
            } ]);
        }
    }

    function createBody(params) {
        var data = params.data;
        var rowFn = params.rowFn;
        var rowClickFn = params.rowClickFn;
        var columns = params.columns;
        var bodyDom = $("<div class='dtBodyWrapper'><div class='dtBody'></div></div>");
        var tableDom = $("<table class= 'table table-hover'><thead></thead></table>");
        var body = $("<tbody></tbody>");

        var rowspanColumns = $(columns).filter(function(_, item){
            return item.name.indexOf(".") != -1;
        });
        var rowspanDataKey = null;
        if(rowspanColumns.length > 0){
            rowspanDataKey = rowspanColumns[0].name.split(".")[0];
        }

        for (var i = 0; i < data.length; i++) {
            var rowValue = data[i];
            var rowspanDs = null;
            var rowspanCount = 1;
            if(rowspanDataKey){
                rowspanDs = rowValue[rowspanDataKey];
                rowspanCount = rowspanDs.length;
            }
            for(var k = 0;k < rowspanCount; k++){

                var trDom = $("<tr class=''></tr>");
                columns.forEach(function(column){
                    var paramCount = (k === 0) ? rowspanCount : 0;
                    var createBodyArgs = [rowValue, column, paramCount, k];
                    var tdDom = createBodyTd.apply(null, createBodyArgs);
                    if(tdDom){
                        tdDom.appendTo(trDom);
                    }
                });
                trDom.appendTo(body);

                // call row fn callback method
                rowFn && rowFn(rowValue, trDom);
            }
            if(rowClickFn){
                trDom.on("mousedown", function(e) {
                    rowMousedownX = e.pageX;
                    rowMousedownY = e.pageY;
                });

                trDom.on("click", "", {rowValue: rowValue, trDom: trDom}, function(e) {
                    // マウスが動く場合、クリック動作をしない
                    if(rowMousedownX != e.pageX || rowMousedownY != e.pageY){
                        return false;
                    }

                    rowClickFn(e.data.rowValue, e.data.trDom);
                });
            }
        }
        body.appendTo(tableDom);
        tableDom.appendTo(bodyDom.find(".dtBody"));
        return bodyDom;
    }

    /**
     * paramCount  0
     * sub row
     *
     * paramCount > 0
     * main row
     */
    function createBodyTd() {
        var rowValue = arguments[0];
        var column = arguments[1];
        var paramCount = arguments[2];
        var subRowIndex = arguments[3];

        if(paramCount === 0 && column.name.indexOf(".") === -1){
            // this is sub row , no draw for main columns > 0
            return null;
        }

        var rowspanNumber = 1;
        if(column.name.indexOf(".") === -1){
            rowspanNumber = paramCount;
        }

        var name = column.name || "";
        var value = getDataByKey(rowValue, name, subRowIndex);
        var cellFn = column.fn;
        var fnResult = null;
        var nameForClass = name.replace(".", "");
        var tdDom = $("<td class='nowrap wrapByCharacter'></td>").addClass("col-" + nameForClass);
        if(rowspanNumber > 1){
            tdDom.attr("rowspan", rowspanNumber);
        }
        if (cellFn) {
            var fnArgs = [value, rowValue, tdDom, subRowIndex];
            fnResult = cellFn.apply(null, fnArgs);
            if (fnResult === false) {
                // nothing to do
            } else if (typeof fnResult === "string") {
                tdDom.text(fnResult);
            } else if (typeof fnResult === "object") {
                tdDom.append(fnResult);
            } else {
                // nothing to do
            }
        } else {
            tdDom.text(value);
        }
        return tdDom;
    }

    function getDataByKey(rowValue, key, subRowIndex){
        if(key.indexOf(".") == -1){
            return rowValue[key];
        }else{
            var keyArr = key.split(".");
            return rowValue[keyArr[0]][subRowIndex][keyArr[1]];
        }
    }

    function selectionSupport(baseDom, params) {
        var sel = params.sel;
        if (!sel) {
            return;
        }
        if (sel === "checkbox") {
            cbxSupport(baseDom, params);
        } else if (sel === "radio") {
            radioSupport(baseDom, params);
        }else {
            console.error("[datatable] unsupport selection type !");
        }
    }

    function cbxSupport(baseDom, params) {
        var status = baseDom.attr(STATUS_KEY);
        if (!status) {
            var selectIds = params.selIds || [];
            baseDom.data(SEL_KEY, selectIds);
        }

        var nameSuffix = parseInt(Math.random() * 10000);
        params.columns.unshift({
            name: "sel",
            text: "",
            fn: function(value, rowValue, tdDom) {
                var selKey = params.selKey;
                value = rowValue[selKey];
                var selectIds = baseDom.data(SEL_KEY);
                tdDom.addClass("dt_cbx");
                var cbx = $("<input type='checkbox' value='' />");
                cbx.attr({
                    "value": value,
                    "name": "dt_cbx_" + nameSuffix
                });
                cbx.prop("checked", selectIds.indexOf(value) != -1);
                cbx.on("click", function() {
                    var cbxStatus = $(this).is(":checked");
                    var id = $(this).attr("value");
                    if (cbxStatus === true) {
                        addSel(id, baseDom);
                    } else {
                        removeSel(id, baseDom);
                    }
                });
                cbx.appendTo(tdDom);
                return false;
            }
        });
    }

    function radioSupport(baseDom, params) {
        var status = baseDom.attr(STATUS_KEY);
        if (!status) {
            var selectedId = params.selId || [];
            baseDom.data(SEL_KEY, selectedId);
        }

        var nameSuffix = parseInt(Math.random() * 10000);
        params.columns.unshift({
            name: "sel",
            text: "",
            fn: function(value, rowValue, tdDom) {
                var selKey = params.selKey;
                value = rowValue[selKey];
                var selectedId = baseDom.data(SEL_KEY);
                tdDom.addClass("dt_radio");
                var $radio = $("<input type='radio' name='dt_radio' value='' />");
                $radio.attr({
                    "value": value,
                    "name": "dt_radio_" + nameSuffix
                });
                $radio.prop("checked", (selectedId == value));
                $radio.on("click", function() {
                    var id = $(this).attr("value");
                    baseDom.data(SEL_KEY, id);
                });
                $radio.appendTo(tdDom);
                return false;
            }
        });
    }

    /**
     * add select ids
     * this is for internal call
     */
    function addSel(id, baseDom){
        var selIds = baseDom.data(SEL_KEY);
        if(!selIds){
            selIds = [];
            baseDom.data(SEL_KEY, selIds);
        }
        selIds.push(id);
    }

    /**
     * remove select ids
     * this is for internal call
     */
    function removeSel(id, baseDom){
        var selIds = baseDom.data(SEL_KEY);
        var index = selIds.indexOf(id);
        selIds.splice(index, 1);
    }

    function calcWidth(baseDom) {
        var headerWrapper = baseDom.find(".dtHeadWrapper");
        var bodyWrapper = baseDom.find(".dtBodyWrapper");
        var headTable = baseDom.find(".dtHead");
        var bodyTable = baseDom.find(".dtBody");

        var ieFlg = document.body.className.indexOf("IE") != -1;

        var allWidth = baseDom.width();
        var tableWidth = allWidth - scrollBarWidth;
        headerWrapper.css("width", allWidth + "px");
        bodyWrapper.css("width", allWidth + "px");
        headTable.css("width", tableWidth + "px");
        if (ieFlg) {
            bodyTable.css("width", (tableWidth - 1) + "px");
        } else {
            bodyTable.css("width", tableWidth + "px");
        }
    }

    function calcHeight(baseDom, height) {
        var tableHeight = height - headerHeight;
        var dtBodyWrapper = baseDom.find(".dtBodyWrapper");
        dtBodyWrapper.css("max-height", tableHeight + "px");
        dtBodyWrapper.css("height", tableHeight + "px");
    }

    function supportResize(baseDom){
        $(window).on("resize", function(){
            calcWidth(baseDom);
        });
    }

    function init(baseDom, params){
        baseDom.empty();
        baseDom.data(PARAM_KEY, params);
        baseDom.attr(STATUS_KEY, "init");
    }

    function _destroy(baseDom){
        baseDom.empty();
        baseDom.removeAttr(STATUS_KEY);
        baseDom.removeData(PARAM_KEY);
        baseDom.removeData(SEL_KEY);
    }

    var API = {
        /**
         * get selected ids
         */
        getSelIds: function() {
            var baseDom = this;
            var selIds = baseDom.data(SEL_KEY);
            if (typeof selIds === "object") {
                // check box, sort the selected ids
                selIds = selIds.sort(function(a, b) {
                    var inta = parseInt(a, 10);
                    var intb = parseInt(b, 10);
                    return inta > intb;
                });
            }else {
                // radio button
            }
            return selIds;
        },
        /**
         * destroy the datatable
         */
        destroy: function(){
            var baseDom = this;
            _destroy(baseDom);
        }
    };

    return {
        "extend" : extend
    };
});