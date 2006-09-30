<%@ page import="com.joshdrummond.liverecordingreview.BootlegBean,
                 java.util.List"%>
 <%@ include file="header.inc" %>

<table width=40% border=0>
<tr><td align=left><b>=&gt; Garbage</b></td></tr>
<tr><td>
   <table border=1 width=100% bgcolor=#FFD6E6>
   <tr bgcolor=#FFB5E7>
   <td width=40%><b>Categories</b></td>
<%--   <td width=30%><b># Bootlegs</b></td>
   <td width=30%><b># Reviews</b></td> --%>
   </tr>
<%
   List categories = BootlegBean.getCategories("1");
   for (int iCat = 0; iCat < categories.size(); iCat++)
   {
      List row = (List)categories.get(iCat);
      String strCatId = (String)row.get(0);
      String strCatDesc = (String)row.get(1);
%>
   <tr bgcolor=#FFD6E6>
   <td width=40%>&nbsp;<a href="category.jsp?cat_id=<%=strCatId%>"><%=strCatDesc%></a></td>
<%--   <td width=30%>&nbsp;<%=strNumBoots%></td>
   <td width=30%>&nbsp;<%=strNumReviews%></td> --%>
   </tr>
<%
   }
%>
   </table>
   </td>
</tr>
</table>

<%@ include file="footer.inc" %>
