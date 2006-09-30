<%@ page
	import="com.joshdrummond.liverecordingreview.BootlegBean,java.util.List"%>
<%
            boolean showPage = false;
            String recId = request.getParameter("rec_id");
            if (recId == null)
            {
                response.sendRedirect("index.jsp");
            } else if (null != request.getParameter("submit"))
            {
                if (BootlegBean.addReview(recId, request
                        .getParameter("username"), Integer.parseInt(request
                        .getParameter("performance")), Integer.parseInt(request
                        .getParameter("recording")), request
                        .getParameter("comments")))
                {
                    response.sendRedirect("recording.jsp?rec_id=" + recId);
                } else
                {
                    showPage = true;
                }
            } else
            {
                showPage = true;
            }

            if (showPage)
            {
                List results = BootlegBean.getBootleg(recId);
                if (results.size() <= 0)
                {
                    response.sendRedirect("index.jsp");
                } else
                {
                    List recording = (List) results.get(0);
                    String catId = (String) recording.get(1);
                    String catDesc = (String) recording.get(2);
                    String recDesc = (String) recording.get(4);
                    String recSource = (String) recording.get(5);
                    String recInfo = (String) recording.get(6);
                    String recType = (recording.get(3)).equals("A") ? "Audio"
                            : "Video";
                    String recAvgPerf = BootlegBean
                            .formatDecimal((String) recording.get(7));
                    String recAvgRec = BootlegBean
                            .formatDecimal((String) recording.get(8));
                    String recTotRev = (String) recording.get(9);
%>
<%@ include file="header.inc"%>

<table width=95% border=0>
	<tr>
		<td align=left><b>=&gt; <a href="index.jsp">Garbage</a> =&gt;
		<a href="category.jsp?cat_id=<%=catId%>"><%=catDesc%></a> =&gt; <a
			href="recording.jsp?rec_id=<%=recId%>"><%=recDesc%></a> =&gt; Add
		Review</b></td>
	</tr>
	<tr>
		<td align=center>
		<table border=1 width=100% bgcolor=#FFD6E6>
			<tr bgcolor=#FFB5E7>
				<td width=40%><b><%=recDesc%><br>
				<%=recType%><br>
				<%=recSource%></b></td>
			</tr>
			<tr bgcolor=#FFD6E6>
				<td><pre><%=recInfo%></pre></td>
			</tr>
			<tr bgcolor=#FFB5E7>
				<td><b>Average Performance Rating:</b> <%=recAvgPerf%><br>
				<b>Average Recording Rating:</b> <%=recAvgRec%><br>
				<b>Total Reviews:</b> <%=recTotRev%><br>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<br>
<br>
<br>

<form method=post action="addReview.jsp">
<table width=95% border=0>
	<tr>
		<td align=center><input type=hidden name=rec_id
			value="<%=recId%>">
		<table border=1 width=70% bgcolor=#FFD6E6>
			<tr bgcolor=#FFB5E7>
				<td align=center colspan=3>
				<h2>Your Review</h2>
				</td>
			</tr>
			<tr>
				<td><b>UserName</b></td>
				<td colspan=2><input type=text size=30 name=username></input></td>
			</tr>
			<tr>
				<td width=20%><b>Rating</b>&nbsp;&nbsp;&nbsp;&nbsp;(<a
					href="faq.jsp"
					onClick="javascript:window.open('faq.jsp', 'Help', 'top=100,left=100,width=600,height=500,toolbar=1,menubar=1'); return false;">Help</a>)</td>
				<td width=30%>Performance &nbsp;&nbsp;<select name=performance>
					<%
					                    for (int o = 10; o > 0; o--)
					                    {
					%>
					<option value="<%=o%>"><%=o%></option>
					<%
					}
					%>

				</select></td>
				<td width=30%>Recording <select name=recording>
					<%
					                    for (int o = 10; o > 0; o--)
					                    {
					%>
					<option value="<%=o%>"><%=o%></option>
					<%
					}
					%>

				</select></td>
			</tr>
			<tr>
				<td><b>Comments</b><br>
				(optional)</td>
				<td colspan=2><textarea name=comments cols=60 rows=8></textarea></td>
			</tr>
			<tr>
				<td align=center colspan=3><input type=submit name=submit
					value=Submit></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>

<%@ include file="footer.inc"%>
<%
            }
            }
%>
