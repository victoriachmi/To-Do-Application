<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>


Login<br>

<c:if test="${not empty errorMessage}"><div style="color:red; font-weight: bold; margin: 30px 0px;">${errorMessge}</div></c:if>

<form name= 'login' action="/login" method= 'POST'>
	<table>
	<tr>
		<td> UserName: </td>
		<td><input type='text' name='username' value=''></td>
		</tr>
		<tr>
			<td> Password: </td>
			<td><input type = 'password' name='password' /></td>
			</tr>
			<tr>
				<td colspan= '2'> <input name="submit" type= "submit" value= "Submit" /></td>
				</tr>
	</table>

<input type="hidden" name="${_csrf.parameterName }" value="${ _csrf.token}"/>
</form>
<%@ include file="common/footer.jspf" %>
