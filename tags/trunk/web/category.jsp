<%@ page
	import="com.joshdrummond.liverecordingreview.BootlegBean,java.util.List"%>
<%
            String catId = request.getParameter("cat_id");
            if (catId == null)
            {
                response.sendRedirect("index.jsp");
            } else
            {
                List results = BootlegBean.getCategory(catId);
                if (results.size() <= 0)
                {
                    response.sendRedirect("index.jsp");
                } else
                {
                    String catDesc = (String) ((List) results.get(0)).get(0);
%>
<%@ include file="header.inc"%>

<table width=95% border=0>
	<tr>
		<td align=left><b>=&gt; <a href="index.jsp">Garbage</a> =&gt;
		<%=catDesc%></b></td>
	</tr>
	<tr>
		<td>
		<table border=1 width=100% bgcolor=#FFD6E6>
			<tr bgcolor=#FFB5E7>
				<td width=40%><b>Description</b></td>
				<td width=6%><b>Type</b></td>
				<td width=22%><b>Source</b></td>
				<td width=8%><b># Reviews</b></td>
				<td width=8%><b>Avg<br>
				Perform.</b></td>
				<td width=8%><b>Avg<br>
				Record.</b></td>
				<td width=8%><b>Sumbit<br>
				Review</b></td>
			</tr>
			<%
			                    List recordings = BootlegBean.getBootlegs(catId);
			                    for (int iRec = 0; iRec < recordings.size(); iRec++)
			                    {
			                        List row = (List) recordings.get(iRec);
			%>
			<tr bgcolor=#FFD6E6>
				<td>&nbsp;<a
					href="recording.jsp?rec_id=<%=(String)row.get(0)%>"><%=(String) row.get(2)%></a></td>
				<td>&nbsp;<%=((row.get(1)).equals("A") ? "Audio"
                                                : "Video")%></td>
				<td>&nbsp;<%=(String) row.get(3)%></td>
				<td align=right>&nbsp;<%=(String) row.get(6)%></td>
				<td align=right>&nbsp;<%=BootlegBean
                                        .formatDecimal((String) row.get(4))%></td>
				<td align=right>&nbsp;<%=BootlegBean
                                        .formatDecimal((String) row.get(5))%></td>
				<td>&nbsp;<a
					href="addReview.jsp?rec_id=<%=(String)row.get(0)%>">Add</a></td>
			</tr>
			<%
			}
			%>
			<tr bgcolor=#FFB5E7>
				<td colspan=7 align=center><b><a href="addNewRec.jsp">Add
				New Recording</a></b></td>
			</tr>
		</table>
		</td>
	</tr>
</table>

<%@ include file="footer.inc"%>
<%
            }
            }
%>
