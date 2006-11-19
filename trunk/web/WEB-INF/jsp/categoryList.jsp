<%@ include file="include/header.jspf" %>

<table width="40%">
	<tr>
		<td align="left"><b><a href="bandList.htm">Home</a> =&gt; ${band.description}</b></td>
	</tr>
	<tr>
		<td>
		<table class="light" width="100%">
			<tr class="medium">
				<td width="40%"><b>Categories</b></td>
			</tr>
			<c:forEach var="category" items="${categories}">
				<tr class="light">
					<td width=40%>&nbsp;<a
						href="recordingList.htm?id=${category.id}">${category.description}</a></td>
				</tr>
			</c:forEach>
		</table>
		</td>
	</tr>
</table>

<%@ include file="include/footer.jspf" %>