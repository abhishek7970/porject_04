<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.ctl.PurchaseOrderCtl"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.ctl.CourseCtl"%>
<%@page import="in.co.rays.ctl.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<jsp:useBean id="bean" class="in.co.rays.bean.PurchaseOrderBean"
		scope="request"></jsp:useBean>

	<form action="<%=ORSView.PURCHASEORDER_CTL%>" method="post">

		<div align="center">

			<h1>
				<font color="navy">Add Purchase Order</font>
			</h1>
			<h3>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</h3>
			<h3>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</h3>
			<table>
				<tr>
					<th>Total Quantity :</th>
					<td><input type="text" name="totalQuantity"
						value="<%=bean.getTotalQuantity()%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("totalQuantity", request)%></font></td>

				</tr>
				<tr>
					<th>Product:</th>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("car", "car");
							map.put("bus", "bus");
							map.put("bike","bike");
						%> <%=HTMLUtility.getList("product", bean.getProduct(), map)%>
					</td>


					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("product", request)%></font></td>
				</tr>

				<th>Order Date:</th>
				<td><input type="text" name="orderdate"
					placeholder="Select order date"
					value="<%=DataUtility.getDateString(bean.getOrderdate())%>"></td>
				<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("orderdate", request)%></font></td>
				</tr>
				<tr>
					<th>TotalCost :</th>
					<td><input type="text" name="totalCost"
						value="<%=DataUtility.getStringData(bean.getTotalCost())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("totalCost", request)%></font></td>
                
                 </tr>
				
				<tr>
					<th></th>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=PurchaseOrderCtl.OP_UPDATE%>">
						<input type="submit" name="operation"
						value="<%=PurchaseOrderCtl.OP_CANCEL%>"> <%
 	} else {
 %>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=PurchaseOrderCtl.OP_SAVE%>"> <input
						type="submit" name="operation"
						value="<%=PurchaseOrderCtl.OP_RESET%>"> <%
 	}
 %>
				</tr>
			</table>
		</div>
	</form>
	<%@ include file="Footer.jsp"%>
</body>
</html>
