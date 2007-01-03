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
    along with LiveRecordingReview; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
--%>
<%@ include file="include/header.jspf" %>

<table width="40%">
	<tr>
		<td align="left"><b>Home</b></td>
	</tr>
	<tr>
		<td>
		<table class="light" width="100%">
			<tr class="medium">
				<td width="40%"><b>Artists</b></td>
			</tr>
			<c:forEach var="artist" items="${artists}">
				<tr class="light">
					<td width="40%">&nbsp;<a href="categoryList.htm?id=${artist.id}">${artist.description}</a></td>
				</tr>
			</c:forEach>
		</table>
		</td>
	</tr>
</table>

<%@ include file="include/footer.jspf" %>