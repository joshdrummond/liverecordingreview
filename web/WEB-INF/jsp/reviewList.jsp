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
			href="categoryList.htm?id=${recording.category.artist.id}">${recording.category.artist.description}</a> =&gt; <a
			href="recordingList.htm?id=${recording.category.id}">${recording.category.description}</a>
		=&gt; ${fn:escapeXml(recording.description)}</b></td>
	</tr>
	<tr>
		<td>
		<table class="light" width="100%">
			<tr class="medium">
				<td width="40%"><b>${fn:escapeXml(recording.description)}<br/>
				${recording.type}<br/>
				${fn:escapeXml(recording.source)}</b></td>
			</tr>
			<tr class="light">
				<td><pre>${fn:escapeXml(recording.info)}</pre></td>
			</tr>
			<tr class="medium">
				<td><b>Average Performance Rating:</b>
				<fmt:formatNumber maxFractionDigits="1" minFractionDigits="1">${recording.avgPerformanceRating}</fmt:formatNumber><br/>
				<b>Average Recording Rating:</b> <fmt:formatNumber maxFractionDigits="1" minFractionDigits="1">${recording.avgRecordingRating}</fmt:formatNumber><br/>
				<b>Total Reviews:</b> ${recording.totalReviews}<br/>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<br/>
<br/>
<br/>
<table width="95%">
	<tr>
		<td>
		<table class="light" width="100%">
			<tr>
				<td align="center" colspan="2">
				<h2>Fan Reviews</h2>
				(<a href="addReview.htm?id=${recording.id}">Add your own</a>)</td>
			</tr>

			<c:forEach var="review" items="${reviews}" varStatus="lineInfo">
				<c:choose>
					<c:when test="${lineInfo.count % 2 == 0}">
						<c:set var="reviewrowstyle" value="review1" />
					</c:when>
					<c:otherwise>
						<c:set var="reviewrowstyle" value="review2" />
					</c:otherwise>
				</c:choose>
				<tr class="${reviewrowstyle}">
				<td><b>${fn:escapeXml(review.reviewer)}</b><br/>
			<%--	<fmt:parseDate value="${review.dateCreated}" var="dateCreated" type="both" pattern="yyyy-MM-dd HH:mm:ss"/> --%>
				<fmt:formatDate value="${review.dateCreated}" type="both" pattern="MM/dd/yyyy HH:mm:ss"/></td>
				<td>Performance: ${review.performanceRating}<br/>
				Recording: ${review.recordingRating}</td>
				</tr>
				<tr class="${reviewrowstyle}">
					<td colspan="2">${fn:escapeXml(review.notes)}&nbsp;</td>
				</tr>
			</c:forEach>
		</table>
		</td>
	</tr>
</table>

<%@ include file="include/footer.jspf" %>