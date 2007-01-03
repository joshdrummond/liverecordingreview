<%--
    Copyright 2006, 2007 Josh Drummond

    This file is part of LiveRecordingReview.

    LiveRecordingReview is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    LiveRecordingReview is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
--%>
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
							<td><form:input path="description" cssClass="inputField" />&nbsp;&nbsp;
								<form:errors path="description" cssClass="error"/></td>
						</tr>
						<tr>
							<td>Type:</td>
							<td><form:select items="${typeCodes}" path="typeCode" cssClass="dropdown" />&nbsp;&nbsp;
								<form:errors path="typeCode" cssClass="error"/></td>
						</tr>
						<tr>
							<td>Source:</td>
							<td><form:input path="source" cssClass="inputField" />&nbsp;&nbsp;
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
