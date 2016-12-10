<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<link href="bootstrap3.3.4/css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="bootstrap3.3.4/js/bootstrap.min.js" type="text/javascript"></script>
<script src="layer/layer.js" type="text/javascript"></script>
<script type="text/javascript">
	var request = new XMLHttpRequest();
	function findAllBookInfos() {
		/* //1-以Post方式异步发送AJAX请求
		request.open("post", "servlet/BookListServlet", true);
		request.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		request.onreadystatechange = fillbooksTable;
		//2-发送中文参数
		var queryName = $("#bookName").val();
		if (queryName == null || queryName == '')
			request.send();
		else
			request.send("bookName=" + queryName) */

		//1-初始化URL(分页Servlet)
		var url = "servlet/BookListPagedServlet";
		//2-URL加入图书名称查询参数
		var queryName = $("#bookName").val();
		if (queryName != null && queryName != '')
			url = url + "?bookName=" + queryName;
		//3-获得查询页数
		var queryPage = $("#lblRequet").text();
		if (queryPage == null || queryPage == "" || queryPage == "0") {
			queryPage = 1;
			$("#lblRequet").text(queryPage);
		}
		//4-URL加入请求页的索引(index)
		if (url.indexOf("?") >= 0)
			url = url + "&";
		else
			url = url + "?";
		url = url + "pageInex=" + (queryPage - 1);

		//5-请求分页数据
		request.open("get", url, true);
		request.onreadystatechange = fillbooksTable;
		request.send();

	}

	function fillbooksTable() {
		if (request.readyState == 4 && request.status == 200) {
			//清空图书列表
			clearBooksTable();
			var table = document.getElementById("booksTable");
			var books = JSON.parse(request.responseText);
			for ( var index in books) {
				var newRow = table.insertRow(table.rows.length);
				var col1 = newRow.insertCell(0);
				var col2 = newRow.insertCell(1);
				var col3 = newRow.insertCell(2);
				col1.innerHTML = books[index].bookName;
				col2.innerHTML = books[index].publishDate;
				col3.innerHTML = "<a href='#' onclick='updateBook(\""
						+ books[index].bookId + "\");' >修改</a>"
						+ "<a href='#' onclick='deleteBook(\""
						+ books[index].bookId + "\");' >删除</a>";
			}
		}
	}

	function clearBooksTable() {
		var table = document.getElementById("booksTable");
		while (table.rows.length > 1)
			table.deleteRow(1);
	}

	var layerIndex = -1;
	function updateBook(bookId) {
		layerIndex = layer.open({
			type : 2,
			title : '修改图书',
			shadeClose : true, //点击遮罩关闭层
			area : [ '400px', '300px' ],
			content : 'bookEdit.html?bookId=' + bookId,
			end : function(e) {
				$("#btnQuery").click();
			}
		});
	}

	function closeBookEdit() {
		layer.close(layerIndex);
	}

	function deleteBook(bookId) {
		layer.confirm("你是否要删除图书", {
			btn : [ "确定", "取消" ]
		//按钮设置
		}, function() {
			var url = "servlet/BookDeleteServlet?bookId=" + bookId;
			request.open("get", url, true);
			request.onreadystatechange = deleteBookReturn;
			request.send();
		}, function() {
		});
	}

	function deleteBookReturn() {
		if (request.readyState == 4 && request.status == 200) {
			if (request.responseText.indexOf("correct") >= 0) {
				findAllBookInfos();
				layer.msg("删除成功!");
			} else
				layer.msg("删除失败!");
		}
	}
</script>
</head>
<body onload="findAllBookInfos();">
	<form class="form-inline">
		<div class="form-group">
			<label class="control-label">文件</label> <input type="text"
				class="form-control" id="bookName" />
		</div>
		<input type="button" id="btnQuery" class="form-control"
			onclick="findAllBookInfos();" value="查询" />
	</form>
	<table id="booksTable" class="table table-bordered table-hover">
		<caption>书信息列表</caption>
		<tr>
			<td>书名</td>
			<td>出版时间</td>
			<td>操作</td>
		</tr>
	</table>

	<form class="form-inline">
		<a onclick="preRow();">上一页</a> <a onclick="nextRow();">下一页</a> 总共<label
			id="lblTotal"></label>页 当前<label id="lblRequet"></label>页
	</form>

</body>