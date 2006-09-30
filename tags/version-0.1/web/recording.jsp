<%@ page import="com.joshdrummond.liverecordingreview.BootlegBean, java.util.List"%>
<%
   String recId = request.getParameter("rec_id");
   if (recId == null)
   {
      response.sendRedirect("index.jsp");
   }
   else
   {
      List results = BootlegBean.getBootleg(recId);
      if (results.size() <= 0)
      {
         response.sendRedirect("index.jsp");
      }
      else
      {
         List recording = (List)results.get(0);
         String catId = (String)recording.get(1);
         String catDesc = (String)recording.get(2);
         String recDesc = (String)recording.get(4);
         String recSource = (String)recording.get(5);
         String recInfo = (String)recording.get(6);
         String recType = (recording.get(3)).equals("A") ? "Audio" : "Video";
         String recAvgPerf = BootlegBean.formatDecimal((String)recording.get(7));
         String recAvgRec = BootlegBean.formatDecimal((String)recording.get(8));
         String recTotRev = (String)recording.get(9);
%>
<%@ include file="header.inc" %>

<table width=95% border=0>
<tr><td align=left><b>=&gt; <a href="index.jsp">Garbage</a> =&gt; <a href="category.jsp?cat_id=<%=catId%>"><%=catDesc%></a> =&gt; <%=recDesc%></b>
<tr><td>
   <table border=1 width=100% bgcolor=#FFD6E6>
   <tr bgcolor=#FFB5E7>
   <td width=40%><b><%=recDesc%><br><%=recType%><br><%=recSource%></b></td>
   </tr>
   <tr bgcolor=#FFD6E6>
   <td><pre><%=recInfo%></pre></td>
   <!-- <td><pre><%=""%></pre></td> -->
   </tr>
   <tr bgcolor=#FFB5E7><td><b>Average Performance Rating:</b> <%=recAvgPerf%><br>
   <b>Average Recording Rating:</b> <%=recAvgRec%><br>
   <b>Total Reviews:</b> <%=recTotRev%><br>
   </td></tr>
   </table>
   </td>
</tr>
</table>
<br><br><br>
<table width=95% border=0>
<tr><td>
   <table border=1 width=100% bgcolor=#FFD6E6>
   <tr><td align=center colspan=2><h2>Fan Reviews</h2> (<a href="addReview.jsp?rec_id=<%=recId%>">Add your own</a>)</td></tr>
<%
         String c1 = "#FF8DA1";
         String c2 = "#FFA7B6";
   List reviews = BootlegBean.getReviews(recId);
   for (int iRev = 0; iRev < reviews.size(); iRev++)
   { //review_id, user_id, performance_rating, recording_rating, notes, date_created FR
      List row = (List)reviews.get(iRev);
%>
   <tr bgcolor=<%=(((iRev % 2) == 0) ? c1 : c2)%>>
   <td ><b><%=(String)row.get(1)%></b><br><%=BootlegBean.getDisplayDateTime((String)row.get(5))%></td>
   <td>Performance: <%=(String)row.get(2)%><br>Recording: <%=(String)row.get(3)%></td></tr>
   <tr bgcolor=<%=(((iRev % 2) == 0) ? c1 : c2)%>><td colspan=2><%=(String)row.get(4)%>&nbsp;</td></tr>
<%
   }
%>
   </table>
   </td>
</tr>
</table>

<%@ include file="footer.inc" %>
<%
      }
   }
%>
