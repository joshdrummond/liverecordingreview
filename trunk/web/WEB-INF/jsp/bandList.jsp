<%@ include file="include/header.jspf" %>

<table width="40%">
	<tr>
		<td align="left"><b>Home</b></td>
	</tr>
	<tr>
		<td>
		<table class="light" width="100%">
			<tr class="medium">
				<td width="40%"><b>Bands</b></td>
			</tr>
			<c:forEach var="band" items="${bands}">
				<tr class="light">
					<td width="40%">&nbsp;<a href="categoryList.htm?id=${band.id}">${band.description}</a></td>
				</tr>
			</c:forEach>
		</table>
		</td>
	</tr>
</table>

<%@ include file="include/footer.jspf" %>