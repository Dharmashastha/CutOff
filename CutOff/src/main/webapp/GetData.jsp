<%@page import="cutoff.Connected"%>
<%@page import="java.util.Objects"%>
<%@page import="cutoff.CalculateCutOff"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GetData</title>
</head>
<body>
	<fieldset>
		<form action="calculate" method="post">
			<label for="rollno">Roll Number</label><br> <input type="text"
				id="rollno" name="rollno" placeholder="Enter Your Roll Number"
				required="required"><br> <br> <label for="name">Name</label><br>
			<input type="text" id="name" name="name"
				placeholder="Enter Your Name" required="required"><br>
			<br> <label for="maths">Maths</label><br> <input
				type="number" id="maths" name="maths"
				placeholder="Enter Your Maths Mark" max="100" required="required"><br>
			<br> <label for="chemistry">Chemistry</label><br> <input
				type="number" id="chemistry" name="chemistry"
				placeholder="Enter Your Chemistry Mark" max="100"
				required="required"><br> <br> <label for="physics">Physics</label><br>
			<input type="number" id="physics" name="physics"
				placeholder="Enter Your Physics Mark" max="100" required="required"><br>
			<br>
			<%
			String rollNumber = request.getParameter("rollno");
			String name = request.getParameter("name");
			String mathsStr = request.getParameter("maths");
			String chemistryStr = request.getParameter("chemistry");
			String physicsStr = request.getParameter("physics");
			int maths = 0;
			int chemistry = 0;
			int physics = 0;
			if (Objects.nonNull(mathsStr)) {
				maths = Integer.parseInt(mathsStr);
			}
			if (Objects.nonNull(chemistryStr)) {
				chemistry = Integer.parseInt(chemistryStr);
			}
			if (Objects.nonNull(physicsStr)) {
				physics = Integer.parseInt(physicsStr);
			}
			%>
			<input type="submit" id="submit" name="submit" value="Calculate">
			<input type="reset"> <br> <br> <label for="cutoff">Engineering
				CutOff</label><br> <input type="text" id="cutoff" name="cutoff"
				readonly="readonly"
				value=<%CalculateCutOff calculateutOff = (cutoff.CalculateCutOff) request.getServletContext().getAttribute("calculateCutOff");
double cutoff = 0.0;
if (Objects.nonNull(calculateutOff)) {
	cutoff = calculateutOff.engineeringCutOff(maths, chemistry, physics);
}%>
				<%out.print(cutoff);%>><br>
		</form>
	</fieldset>
	
	<fieldset>
			<form action="calculate" method="post">
				<label for="rollNumber"> Enter the Roll Number</label><br> <input type="text"
				id="rollNumber" name="rollNumber" placeholder="Enter Your Roll Number"
				required="required"><br> <br>
				
				<% String getRollNumber = request.getParameter("rollNumber");%>
				
				<input type="submit" id="getData" name="getData" value="CutOff">	
				
				<input type="text" id = "mark" name = "mark" readonly="readonly" value = 
				<%Connected connect = null;
				if(Objects.nonNull(calculateutOff))
				{
				 	connect = calculateutOff.connect();
				}
				double cutOffMark = 0.0;
				if(Objects.nonNull(connect))
				{
				 cutOffMark = connect.getCutOffData(getRollNumber);}%> <%out.print(cutOffMark);%>>
				
			</form>
	
	</fieldset>
	
	
</body>
</html>