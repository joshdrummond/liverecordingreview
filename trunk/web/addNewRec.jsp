<%@ page
	import="com.joshdrummond.liverecordingreview.BootlegBean,java.util.List"%>
<%
            String strClientIP = request.getRemoteAddr();
            System.out.println(strClientIP);
            String status = "";
            boolean showMain = true;

            if (request.getParameter("cat_id") != null)
            {
                if (BootlegBean.addBootleg(request.getParameter("cat_id"),
                        request.getParameter("type"), request
                                .getParameter("desc"), request
                                .getParameter("source"), request
                                .getParameter("info")))
                {
                    status = "success!";
                } else
                {
                    status = "FAILED";
                }
            }

            if (showMain)
            {
%>
<%@ include file="/WEB-INF/jsp/include/header.jspf"%>
<%=status%>
<br/>
<br/>
<table bgcolor=#FFB5E7 width=70% border=1>
	<tr>
		<td align=left><b>Add Recording - Garbage</b></td>
	</tr>
	<tr>
		<td>
		<form action="addNewRec.jsp" method=post>
		<table border=0>
			<tr>
				<td>Category:</td>
				<td><select name=cat_id>
					<%
					                List<List<String>> cats = BootlegBean.getCategories(1); //fixme Garbage band_id
					                for (List row : cats)
					                {
					%>
					<option value="<%=row.get(0)%>"><%=row.get(1)%></option>
					<%
					}
					%>
				</select></td>
			</tr>
			<tr>
				<td>Type:</td>
				<td><select name=type>
					<option value="A">Audio</option>
					<option value="V">Video</option>
				</select></td>
			</tr>
			<tr>
				<td>Description:</td>
				<td><input type=text name=desc size=50 maxlength=100
					value='yyyy/mm/dd - Venue; City, State, Country'></td>
			</tr>
			<tr>
				<td>Source:</td>
				<td><input type=text name=source size=50 maxlength=50
					value='aud/fm/tv/sbd mic/recorder/generation here'></td>
			</tr>
			<tr>
				<td>Info:</td>
				<td><textarea name=info rows=8 cols=50>Put all other info here</textarea></td>
			</tr>
			<tr>
				<td colspan=2 align=center><input type=submit name=submit
					value=Submit></td>
			</tr>
		</table>
		</form>
		</td>
	</tr>
</table>

<h4><a href="index.jsp">Back to Index</a></h4>
<%@ include file="/WEB-INF/jsp/include/footer.jspf"%>

<%
}
%>
