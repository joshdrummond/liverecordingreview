<%@ include file="include/header.jspf"%>

<table width="95%">
	<tr>
		<td align="left"><b><a href="artistList.htm">Home</a> =&gt; <a
			href="categoryList.htm?id=${category.artist.id}">${category.artist.description}</a>
		=&gt; <a href="recordingList.htm?id=${category.id}">${category.description}</a> =&gt; Add Recording</b></td>
	</tr>
	<tr>
		<td>
		<table class="medium" width="70%">
			<tr>
				<td align="left"><b>Add Recording</b></td>
			</tr>
			<tr>
				<td><form:form commandName="recording">
					<table>
						<form:hidden path="category.id" />
						<tr>
							<td>Description:</td>
							<td><form:input path="description" />&nbsp;&nbsp;
								<form:errors path="description" cssClass="error"/></td>
						</tr>
						<tr>
							<td>Type:</td>
							<td><form:select items="${typeCodes}" path="typeCode" /></td>
						</tr>
						<tr>
							<td>Source:</td>
							<td><form:input path="source" />&nbsp;&nbsp;
								<form:errors path="source" cssClass="error"/></td>
						</tr>
						<tr>
							<td>Info:</td>
							<td><form:textarea path="info" rows="8" cols="50" />&nbsp;&nbsp;
								<form:errors path="info" cssClass="error"/></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="submit"
								name="submit" value="Submit"></td>
						</tr>
					</table>
				</form:form></td>
			</tr>
		</table>
		</td>
	</tr>
</table>

<%@ include file="include/footer.jspf"%>
