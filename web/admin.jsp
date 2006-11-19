<%@ page
	import="com.joshdrummond.liverecordingreview.BootlegBean,
                 java.util.List"%>
<%
   String strClientIP = request.getRemoteAddr();
   System.out.println(strClientIP);
   if (false) //(!strClientIP.equals("192.168.1.1"))
   {
      out.write("ACCESS DENIED");
   }
   else
   {
   String status = "";
   boolean showMain = true;
   if (request.getParameter("admin_type") != null)
   {
      String admintype = request.getParameter("admin_type");
      System.out.println("admintype="+admintype);
      if ("1".equals(admintype))
      {
         if (BootlegBean.addBootleg(request.getParameter("cat_id"),
            request.getParameter("type"), request.getParameter("desc"),
            request.getParameter("source"), request.getParameter("info")))
         {
            status="success!";
         }
         else
         {
            status = "FAILED";
         }
      }
      else if ("2".equals(admintype))
      {
         if (BootlegBean.recalculateBootlegScore(Integer.parseInt(request.getParameter("rec_id"))))
         {
            status="success!";
         }
         else
         {
            status = "FAILED";
         }
      }
      else if ("3".equals(admintype))
      {
         response.sendRedirect("admin.jsp?admin_type=5&rec_id="+request.getParameter("rec_id"));
      }
      else if ("4".equals(admintype))
      {
         if (BootlegBean.modifyBootleg(request.getParameter("rec_id"),
            request.getParameter("cat_id"), request.getParameter("type"),
            request.getParameter("desc"), request.getParameter("source"),
            request.getParameter("info")))
         {
            status="success!";
         }
         else
         {
            status = "FAILED";
         }
      }
      else if ("5".equals(admintype))
      {
         showMain = false;
         String recId = request.getParameter("rec_id");
         System.out.println("rec_id="+recId);
         if (recId == null)
         {
            response.sendRedirect("admin.jsp");
         }
         else
         {
            List recording = BootlegBean.getRecording(Integer.parseInt(recId));
            if (recording.size() != 1)
            {
               response.sendRedirect("admin.jsp");
            }
            else
            {
               recording = (List)recording.get(0);
               String catId = (String)recording.get(1);
               String recType = (String)recording.get(3);
               String recDesc = (String)recording.get(4);
               String recSource = (String)recording.get(5);
               String recInfo = (String)recording.get(6);
////// MODIFY RECORDING INFO PAGE
%>
<%@ include file="/WEB-INF/jsp/include/header.jspf"%>

<h3>Admin Page</h3>
<br/>
<br/>
<table bgcolor=#FFB5E7 width=70% border=1>
	<tr>
		<td align=left><b>Modify Recording - Garbage</b></td>
	</tr>
	<tr>
		<td>
		<form action="admin.jsp" method=post><input type=hidden
			name=admin_type value=4> <input type=hidden name=rec_id
			value=<%=recId%>>
		<table border=0>
			<tr>
				<td>Category:</td>
				<td><select name=cat_id>
					<%
					                                List cats = BootlegBean.getCategories(1); //fixme Garbage band_id
					                                for (int i = 0; i < cats.size(); i++)
					                                {
					                                    List row = (List) cats.get(i);
					                                    String cId = (String) row.get(0);
					                                    String cDesc = (String) row.get(1);
					                                    String selected = cId.equals(catId) ? "selected"
					                                            : "";
					%>
					<option value="<%=cId%>" selected="<%=selected%>"><%=cDesc%></option>
					<%
					}
					%>
				</select></td>
			</tr>
			<tr>
				<td>Type:</td>
				<td><select name=type>
					<option value="A" selected=<%="A".equals(recType) ? "selected" : ""%>>Audio</option>
					<option value="V" selected=<%="V".equals(recType) ? "selected" : ""%>>Video</option>
				</select></td>
			</tr>
			<tr>
				<td>Description:</td>
				<td><input type=text name=desc size=50 value="<%=recDesc%>"></td>
			</tr>
			<tr>
				<td>Source:</td>
				<td><input type=text name=source size=50 value="<%=recSource%>"></td>
			</tr>
			<tr>
				<td>Info:</td>
				<td><textarea name=info rows=8 cols=50><%=recInfo%></textarea></td>
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

<%@ include file="/WEB-INF/jsp/include/footer.jspf"%>

<%
                        }
                        }
                    }
                }

                if (showMain)
                {
                    ///////////// DEFAULT ADMIN PAGE
%>
<%@ include file="/WEB-INF/jsp/include/header.jspf"%>

<h3>Admin Page</h3>
<%=status%>
<br/>
<br/>
<table bgcolor=#FFB5E7 width=70% border=1>
	<tr>
		<td align=left><b>Add Recording - Garbage</b></td>
	</tr>
	<tr>
		<td>
		<form action="admin.jsp" method=post><input type=hidden
			name=admin_type value=1>
		<table border=0>
			<tr>
				<td>Category:</td>
				<td><select name=cat_id>
					<%
					                    List cats = BootlegBean.getCategories(1); //fixme Garbage band_id
					                    for (int i = 0; i < cats.size(); i++)
					                    {
					                        List row = (List) cats.get(i);
					%>
					<option value="<%=(String)row.get(0)%>"><%=(String) row.get(1)%></option>
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
				<td><input type=text name=desc size=50></td>
			</tr>
			<tr>
				<td>Source:</td>
				<td><input type=text name=source size=50></td>
			</tr>
			<tr>
				<td>Info:</td>
				<td><textarea name=info rows=8 cols=50></textarea></td>
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
<br/>
<br/>
<table bgcolor=#FFB5E7 width=70% border=1>
	<tr>
		<td align=left><b>Modify Recording - Garbage</b></td>
	</tr>
	<tr>
		<td>
		<form action="admin.jsp" method=post><input type=hidden
			name=admin_type value=3> RecordingId: <input type=text
			name=rec_id size=10>&nbsp;&nbsp;&nbsp;<input type=submit
			name=submit value=Submit></form>
		</td>
	</tr>
</table>
<br/>
<br/>
<table bgcolor=#FFB5E7 width=70% border=1>
	<tr>
		<td align=left><b>Recalculate Recording Score</b></td>
	</tr>
	<tr>
		<td>
		<form action="admin.jsp" method=post><input type=hidden
			name=admin_type value=2> RecordingId: <input type=text
			name=rec_id size=10>&nbsp;&nbsp;&nbsp;<input type=submit
			name=submit value=Submit></form>
		</td>
	</tr>
</table>

<%@ include file="/WEB-INF/jsp/include/footer.jspf"%>

<%
            }
            }
%>
