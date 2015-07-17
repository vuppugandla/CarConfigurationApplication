<%-- .jsp file which will be called from finalDetails to display the final details for the selected model and options--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="model.*"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
table, th, td {
	border: 1px solid black;
	text-align: left
}

th {
	background-color: #FF8C00;
}
</style>
<title>Final Car Configuration</title>
</head>
<body>
	<h3>Here is what you selected:</h3>
	<% Automobile auto = (Automobile) session.getAttribute("Auto"); %>
	<%
if(auto != null){
	for(int i=0; i<auto.getOptionSet().size();i++){
		String optionSet = auto.getOptSetName(i);
		String selection = request.getParameter(optionSet);
		auto.setOptionChoice(optionSet,selection);
	}
%>
	<table style="width: 60%">
		<tr>
			<td><%=auto.getMake()+auto.getModel()%></td>
			<td>Base Price</td>
			<td><%=auto.getBasePrice()%></td>
		</tr>
		<tr>
			<th style="width: 40%" style="background-color: yellow">Option</th>
			<th style="width: 40%" style="background-color: yellow">Selection</th>
			<th style="width: 20%" style="background-color: yellow">Extra
				Cost</th>
		</tr>
		<% for(int i=0; i<auto.getOptionSet().size(); i++){ %>
		<tr>
			<td><%=auto.getOptSetName(i)%></td>
			<td><%=auto.getOptionChoice(auto.getOptSetName(i))%></td>
			<td><%=auto.getOptionChoicePrice(auto.getOptSetName(i))%></td>
		</tr>
		<% } %>
		<tr>
			<td colspan="2"><span style="font-weight: bold">Total
					Price</span></td>
			<td><span style="font-weight: bold"><%=auto.getTotalPrice()%></span></td>
		</tr>
	</table>
	<%	
}
%>
</body>
</html>