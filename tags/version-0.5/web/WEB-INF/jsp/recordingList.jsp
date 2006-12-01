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
					<td>&nbsp;<a href="reviewList.htm?id=${recording.id}">${recording.description}</a></td>
					<td>&nbsp;${recording.type}</td>
					<td>&nbsp;${recording.source}</td>
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