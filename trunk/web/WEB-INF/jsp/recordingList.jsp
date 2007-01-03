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

<table width="95%">
	<tr>
		<td align="left"><b><a href="artistList.htm">Home</a> =&gt; <a
			href="categoryList.htm?id=${category.artist.id}">${category.artist.description}</a> =&gt;
		${category.description}</b></td>
	</tr>
	<tr>
		<td>
		<table class="light" width="100%">
			<tr class="medium">
				<td width="40%"><b>Description</b></td>
				<td width="6%"><b>Type</b></td>
				<td width="22%"><b>Source</b></td>
				<td width="8%"><b># Reviews</b></td>
				<td width="8%"><b>Avg<br/>
				Perform.</b></td>
				<td width="8%"><b>Avg<br/>
				Record.</b></td>
				<td width="8%"><b>Sumbit<br/>
				Review</b></td>
			</tr>
			<c:forEach var="recording" items="${recordings}">
				<tr class="light">
					<td>&nbsp;<a href="reviewList.htm?id=${recording.id}">${fn:escapeXml(recording.description)}</a></td>
					<td>&nbsp;${recording.type}</td>
					<td>&nbsp;${fn:escapeXml(recording.source)}</td>
					<td align="right">&nbsp;${recording.totalReviews}</td>
					<td align="right">&nbsp;<fmt:formatNumber maxFractionDigits="1" minFractionDigits="1">${recording.avgPerformanceRating}</fmt:formatNumber></td>
					<td align=right>&nbsp;<fmt:formatNumber maxFractionDigits="1" minFractionDigits="1">${recording.avgRecordingRating}</fmt:formatNumber></td>
					<td>&nbsp;<a href="addReview.htm?id=${recording.id}">Add</a></td>
				</tr>
			</c:forEach>
			<tr class="medium">
				<td colspan="7"><b><a href="addRecording.htm?id=${category.id}">Add Recording</a></b></td>
			</tr>
		</table>
		</td>
	</tr>
</table>

<%@ include file="include/footer.jspf" %>