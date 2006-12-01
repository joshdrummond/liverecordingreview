<%@ include file="include/header.jspf" %>

<table width="95%">
	<tr>
		<td align="left"><b><a href="artistList.htm">Home</a> =&gt; <a
			href="categoryList.htm?id=${recording.category.artist.id}">${recording.category.artist.description}</a> =&gt; <a
			href="recordingList.htm?id=${recording.category.id}">${recording.category.description}</a>
		=&gt; ${recording.description}</b></td>
	</tr>
	<tr>
		<td>
		<table class="light" width="100%">
			<tr class="medium">
				<td width="40%"><b>${recording.description}<br/>
				${recording.type}<br/>
				${recording.source}</b></td>
			</tr>
			<tr class="light">
				<td><pre>${recording.info}</pre></td>
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
				<td><b>${review.reviewer}</b><br/>
				<fmt:parseDate value="${review.dateCreated}" var="dateCreated" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
				<fmt:formatDate value="${dateCreated}" type="both" pattern="MM/dd/yyyy HH:mm:ss"/></td>
				<td>Performance: ${review.performanceRating}<br/>
				Recording: ${review.recordingRating}</td>
				</tr>
				<tr class="${reviewrowstyle}">
					<td colspan="2">${review.notes}&nbsp;</td>
				</tr>
			</c:forEach>
		</table>
		</td>
	</tr>
</table>

<%@ include file="include/footer.jspf" %>