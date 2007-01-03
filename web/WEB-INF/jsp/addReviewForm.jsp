<%@ include file="include/header.jspf" %>

<table width="95%">
	<tr>
		<td align="left"><b><a href="artistList.htm">Home</a> =&gt; <a
			href="categoryList.htm?id=${recording.category.artist.id}">${recording.category.artist.description}</a> =&gt; <a
			href="recordingList.htm?id=${recording.category.id}">${recording.category.description}</a>
		=&gt; <a href="reviewList.htm?id=${recording.id}">${fn:escapeXml(recording.description)}</a> =&gt; Add Review</b></td>
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

<form:form commandName="review">
<table width="95%">
	<tr>
		<td align="center">
		<form:hidden path="recording.id" />
		<table class="light" width="70%">
			<tr class="medium">
				<td align="center" colspan="3">
				<h2>Your Review</h2>
				</td>
			</tr>
			<tr>
				<td><b>UserName</b></td>
				<td colspan="2">
				<form:input path="reviewer" />&nbsp;&nbsp;
				<form:errors path="reviewer" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td width="20%"><b>Rating</b>&nbsp;&nbsp;&nbsp;&nbsp;(<a
					href="faq.htm"
					onClick="javascript:window.open('faq.htm', 'Help', 'top=100,left=100,width=600,height=500,toolbar=1,menubar=1'); return false;">Help</a>)</td>
				<td width="30%">Performance &nbsp;&nbsp;
				<form:select items="${ratings}" path="performanceRating"/><br/>
				<form:errors path="performanceRating" cssClass="error"/>
				</td>
				<td width="30%">Recording &nbsp;&nbsp;
				<form:select items="${ratings}" path="recordingRating"/><br/>
				<form:errors path="recordingRating" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td><b>Comments</b><br/>
				(optional)</td>
				<td colspan="2"><form:textarea path="notes" cols="60" rows="8"/></td>
			</tr>
			<tr>
				<td align="center" colspan="3"><input type="submit" name="submit"
					value="Submit"></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form:form>

<%@ include file="include/footer.jspf" %>