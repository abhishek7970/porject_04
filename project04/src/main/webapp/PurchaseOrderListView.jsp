<%@page import="in.co.rays.model.PurchaseOrderModel"%>
<%@page import="in.co.rays.ctl.PurchaseOrderListCtl"%>
<%@page import="in.co.rays.bean.PurchaseOrderBean"%>
<%@page import="in.co.rays.model.CourseModel"%>
<%@page import="in.co.rays.ctl.CourseListCtl"%>
<%@page import="in.co.rays.model.StudentModel"%>
<%@page import="in.co.rays.ctl.StudentListCtl"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.bean.CourseBean"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />
<title>PurchaseOrder List</title>

<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js""></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>


</head>
<body>
	<jsp:useBean id="bean" class="in.co.rays.bean.PurchaseOrderBean"
		scope="request"></jsp:useBean>


	<form action="<%=ORSView.PURCHASEORDER_LIST_CTL%>" method="post">
		<%@include file="Header.jsp"%>

		<center>

			<div align="center">
				<h1>PurchaseOrder List</h1>
				<h3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>
			</div>

			<%
				List productList = (List) request.getAttribute("productList");
			%>

			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;

				List list = ServletUtility.getList(request);
				Iterator<PurchaseOrderBean> it = list.iterator();

				if (list.size() != 0) {
			%>

			<%-- <table width="100%" align="center">
				<tr>
					<td><label>Product:</label> <%=HTMLUtility.getList("product", String.valueOf(bean.getId()), productList)%>
						&nbsp; <input type="submit" name="operation"
						value="<%=PurchaseOrderListCtl.OP_SEARCH%>"> <input
						type="submit" name="operation"
						value="<%=PurchaseOrderListCtl.OP_RESET%>"></td>

				</tr>
			</table> --%>

			<br>

			<table border="1" width="100%" align="center" cellpadding=6px
				cellspacing=".2">
				<tr>
					<th><input type="checkbox" id="select_all" name="select"></th>
					<th>S No.</th>
					<th>TotalQuantity.</th>
					<th>Product.</th>
					<th>Orderdate.</th>
					<th>TotalCost.</th>
					<th>Edit</th>
				</tr>

				<%
					while (it.hasNext()) {
							bean = (PurchaseOrderBean) it.next();
				%>



				<tr align="center">
					<td><input type="checkbox" class="checkbox" name="ids"
						value="<%=bean.getId()%>">
					<td><%=index++%></td>
					<td><%=bean.getTotalQuantity()%></td>
					<td><%=bean.getProduct()%></td>
					<td><%=bean.getOrderdate()%></td>
					<td><%=bean.getTotalCost()%></td>
					<td><a href="<%=ORSView.PURCHASEORDER_CTL%>?id=<%=bean.getId()%>">edit</a></td>
				</tr>
				<%
					}
				%>
			</table>
			<table width="100%">
				<tr>
					<%
						if (pageNo == 1) {
					%>
					<td><input type="submit" name="operation" disabled="disabled"
						value="<%=PurchaseOrderListCtl.OP_PREVIOUS%>"> <%
 	} else {
 %>
					<td><input type="submit" name="operation"
						value="<%=PurchaseOrderListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>

					<td><input type="submit" name="operation"
						value="<%=PurchaseOrderListCtl.OP_DELETE%>"></td>
					<td><input type="submit" name="operation"
						value="<%=PurchaseOrderListCtl.OP_NEW%>"></td>

					<%
						PurchaseOrderModel model = new PurchaseOrderModel();
					%>
					<%
						if (list.size() < pageSize || model.nextPk() - 1 == bean.getId()) {
					%>
					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=PurchaseOrderListCtl.OP_NEXT%>"></td>
					<%
						} else {
					%>
					<td align="right"><input type="submit" name="operation"
						value="<%=PurchaseOrderListCtl.OP_NEXT%>"></td>
					<%
						}
					%>

				</tr>
			</table>
			<%
				}
				if (list.size() == 0) {
			%>
			<td align="center"><input type="submit" name="operation"
				value="<%=PurchaseOrderListCtl.OP_BACK%>"></td>
			<%
				}
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</center>

	<%@include file="Footer.jsp"%>
</body>
</html>
