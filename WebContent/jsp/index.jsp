<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>DBReader</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
			<script  src="https://code.jquery.com/jquery-3.3.1.js"  integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="  crossorigin="anonymous"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
			<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
			<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
			<style type="text/css">
				div[class *= "col-md"]{
					padding: 0px;
				}

				.dataBaseTitle, .tablesTitle, .rowHeader{
					text-align: center;
					vertical-align: middle;
					height: 50px;
					padding-top: 10px !important;
					font-weight: bold;
				}
				
				.dataBaseTitle{
					cursor: pointer;
					background: #000;
					color: #fff;
					
					transition: .6s ease; 
				}
				
				.dataBaseTitle:hover{
					background: #fff;
					color: #000;
					transition: .6s ease;
				}
				
				.tablesTitle{
					background: #00BFFF;
					color: #fff;
				}
				
				#tablesColumns{
					opacity: 0;
				}
				
				#dataTables{
					text-align: center;
					overflow: auto;
					height: 650px;
					padding: 5px
				}
				
				#listTable{
					padding: 0px;
					height: 600px;
					overflow: auto;
				}
				
				#listTable ul{
					list-style-type: none;
					margin: 0;
					padding: 0;
				}
				
				#listTable li{
					cursor: pointer;
					height: 30px;
					font-size: 12px;
					text-align: center;
					padding-top: 5px !important;
					color: #004085;
				}
				
				.odd{
					background: #cce5ff;
				}
				
				.pair{
					background: #fff;
				}
				
				.rowHeader{
					background: #000;
					color: #fff;
				}
				
				.rowOdd, .rowPair{
					height: 50px;
					font-size: 12px;
					border-bottom: 1px solid grey
				}
				
				.rowOdd{
					background: #e2e3e5;
				}
							
			</style>
			<script type="text/javascript">
				// Carga de Tablas Via AJAX
				var dbSelected = "";
				function loadTables(database){
					var params = {
						search: database,
						opcion: "dbAjax"
					};
					dbSelected = database;
					$("#listTable").empty();
					$.get("DbServlet", $.param(params), function(responseJson) {    
						var $ul = $("<ul>").appendTo($("#listTable")); 
						$.each(responseJson, function(index, item) { 
							var classStyle = "odd";
							if(index == 0 || index%2 == 0){
								classStyle = "pair"
							}
							$("<li class='"+classStyle+"' onclick='loadData(\""+item+"\")'>").text(item).appendTo($ul);      
						});
						$("#tablesColumns").animate({
							opacity: 1
						}, 600);
					});
				}
				
				function loadData(table){
					var params = {
						table: table,
						opcion: "dataAjax",
						database: dbSelected
					};
					$("#dataTables").empty();
					$.get("DbServlet", $.param(params), function(responseJson) {    
						console.log(responseJson);
						var table = $("<table align='center'>").appendTo($("#dataTables"));
						$.each(responseJson, function(index, item) { 
							var classStyle = "rowOdd";
							if(index == 0){
								classStyle = "rowHeader";
							}else if(index%2 == 0){
								classStyle = "rowPair";
							}
							var row = $("<tr class='"+classStyle+"'>").appendTo(table);
							$.each(item, function(index, cell){
								$("<td>"+cell+"</td>").appendTo(row);
							});
						});
					});
				}
				
			</script>
	</head>
	<body>
		<div class="col-md-12 row offset-md-1 offset-sm-0 dataBasesHeader">	
			<c:if test = "${databases != null}">
				<c:forEach items="${databases}" var="db">
					<div class="col-md-2 col-sm-6 dataBaseTitle" id="${db}" onclick="loadTables('${db}')">
						${db}
					</div>
				</c:forEach>
			</c:if>
		</div>
		<div class="col-md-10 offset-md-1 row d-md-flex" id="tablesColumns">
			<div class="col-md-2">
				<div class="col-md-12 tablesTitle">
					Tablas
				</div>
				<div class="col-md-12" id="listTable">
					
				</div>
			</div>
			<div class="col-md-10" id="dataTables">
			
			</div>
		</div>
	</body>
</html>