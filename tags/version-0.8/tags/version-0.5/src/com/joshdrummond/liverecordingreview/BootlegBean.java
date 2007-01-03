package com.joshdrummond.liverecordingreview;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.*;


/*
create table artists (artist_id int(11) not null auto_increment primary key, description varchar(50) not null)
create table categories (category_id int(11) not null auto_increment primary key, artist_id int(11) not null, description varchar(50) not null)
create table recordings (recording_id int(11) not null auto_increment primary key, category_id int(11) not null, type char(1) not null, description varchar(100) not null, source varchar(50) not null, info text not null, avg_perf_rating float not null, avg_rec_rating float not null, total_reviews int(11) not null, date_created datetime not null)
create table reviews (review_id int(11) not null auto_increment primary key, recording_id int(11) not null, user_id varchar(50) not null, performance_rating int not null, recording_rating int not null, notes text not null, date_created datetime not null)
insert into artists values (null, 'Garbage')
insert into categories values (null, 1, '1995')
insert into categories values (null, 1, '1998-1999')
insert into categories values (null, 1, '2002')
insert into categories values (null, 1, '2005')
insert into artists values (null, 'Beatles')
insert into categories values (null, 2, '1961-1966')
insert into categories values (null, 2, '1967-1970')

//
//admin
//add recording
//add category
//add artist
//
//reports
//top overall
//top performances
//top recordings
//top reviewed
//top reviewers
//top each category
//
//list categories, #count (select c.description,
//
//category detail (list all recordings with avg scores)
//
//recording detail (list details and all reviews, submit new review)

*/

/**
 *
 */
public class BootlegBean
{
   private static NumberFormat formatter = new DecimalFormat("0.0");
   private static String db_url = "jdbc:mysql://localhost/recreview";
   private static String db_login = "recreview";
   private static String db_password = "recreview";
   private static String db_driver = "com.mysql.jdbc.Driver";


   static
   {
      try
      {
         Class.forName(db_driver);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }


   public static List<List<String>> getArtists()
   {
      return selectSQL("SELECT a.artist_id, a.description FROM artists a ORDER BY a.description");
   }

   public static List<List<String>> getArtist(int id)
   {
      return selectSQL("SELECT a.description FROM artists a WHERE a.artist_id="+id);
   }
   
   public static List<List<String>> getCategories(int artistId)
   {
      return selectSQL("SELECT c.category_id, c.description FROM categories c WHERE c.artist_id=" + artistId + " ORDER BY c.description");
   }

   
   public static List<List<String>> getCategory(int categoryId)
   {
     return selectSQL("SELECT c.description, a.artist_id, a.description FROM categories c, artists a WHERE c.category_id=" + categoryId + " AND c.artist_id=a.artist_id");
   }

   public static List<List<String>> getRecordings(int categoryId)
   {
         return selectSQL("SELECT r.recording_id, r.type, r.description, r.source, r.avg_perf_rating, r.avg_rec_rating, r.total_reviews FROM recordings r WHERE r.category_id=" +
            categoryId + " ORDER BY r.description, r.type, r.source");
   }

   public static List<List<String>> getRecording(int recordingId)
   {
         return selectSQL("SELECT r.recording_id, r.category_id, c.description, c.artist_id, a.description, r.type, r.description, r.source, r.info, r.avg_perf_rating, r.avg_rec_rating, r.total_reviews " +
                "FROM recordings r, categories c, artists a WHERE r.recording_id=" + recordingId + " AND r.category_id=c.category_id and c.artist_id=a.artist_id");
   }

   public static List<List<String>> getReviews(int recordingId)
   {
         return selectSQL("SELECT review_id, user_id, performance_rating, recording_rating, notes, date_created FROM reviews WHERE recording_id=" + recordingId+ " ORDER BY date_created");
   }

   public static List getReview(String review_id)
   {
      if (isValidInt(review_id))
      {
         return selectSQL("SELECT review_id, recording_id, user_id, performance_rating, recording_rating, notes, date_created FROM reviews WHERE review_id = " + review_id);
      }
      else
      {
         return new Vector(0);
      }
   }

   public static boolean addRecording(int category_id, char type, String description, String source, String info)
   {
      boolean okay = false;
         description = description.replaceAll("'", "''");
         source = source.replaceAll("'", "''");
         info = info.replaceAll("'", "''");
         okay = executeSQL("INSERT INTO recordings (recording_id, category_id, type, description, source, info, avg_perf_rating, avg_rec_rating, total_reviews, date_created) " +
            "VALUES (null, " + category_id + ", '" + type + "', '" + description + "', '" + source + "', '" + info + "', 0.0, 0.0, 0, NOW())");
      return okay;
   }
   
   public static boolean addReview(int recording_id, String user_id, int performanceRating, int recordingRating, String notes)
   {
      boolean okay = false;
      if ( !"".equals(user_id) && !previouslyReviewed(user_id, recording_id))
      {
         user_id = user_id.replaceAll("'", "''");
         notes = notes.replaceAll("'", "''");
         okay = executeSQL("INSERT INTO reviews (review_id, recording_id, user_id, performance_rating, recording_rating, notes, date_created) " +
            "VALUES (null, " + recording_id + ", '" + user_id + "', " + performanceRating + ", " + recordingRating + ", '" + notes + "', NOW())");
         if (okay)
         {
            okay = recalculateRecordingScore(recording_id);
         }
      }

      return okay;
   }


   public static boolean previouslyReviewed(String user_id, int recording_id)
   {
         return ((selectSQL("SELECT review_id FROM reviews WHERE recording_id = "+recording_id+
            " AND user_id = '"+user_id+"'")).size() > 0);
   }


   public static boolean recalculateAllRecordingScores()
   {
      boolean allGood = true;
      List results = selectSQL("SELECT recording_id FROM recordings");
      for (Iterator iter = results.iterator(); iter.hasNext(); )
      {
         List row = (List)iter.next();
         allGood = allGood && recalculateRecordingScore(Integer.parseInt((String)row.get(0)));
      }
      return allGood;
   }


   public static boolean recalculateRecordingScore(int recording_id)
   {
      boolean okay = false;

         List results = selectSQL("SELECT count(*), sum(performance_rating), sum(recording_rating) " +
            "FROM reviews WHERE recording_id = "+recording_id);
         if (results.size() > 0)
         {
            okay = true;
            List row = (List)results.get(0);
            float totalCount = Float.parseFloat((String)row.get(0));
            float totalPerf = 0;
            float totalRec = 0;
            float avgPerf = 0;
            float avgRec = 0;

            if (totalCount > 0)
            {
               totalPerf = Float.parseFloat((String)row.get(1));
               totalRec = Float.parseFloat((String)row.get(2));
               avgPerf = totalPerf / totalCount;
               avgRec = totalRec / totalCount;
            }

            okay = executeSQL("UPDATE recordings SET avg_perf_rating = "+avgPerf + ", avg_rec_rating = "+avgRec+
               ", total_reviews = "+totalCount+" WHERE recording_id = "+recording_id);
         }

      return okay;
   }

   public static List getTopReviewers(int iTopCount)
   {
      return selectSQL("SELECT user_id, count(*) FROM reviews GROUP BY user_id ORDER BY count(*) DESC", iTopCount);
   }

   public static List getTopReviewed(int iTopCount)
   {
      return selectSQL("SELECT recording_id, count(*) FROM reviews GROUP BY recording_id ORDER BY count(*) DESC", iTopCount);
   }

   public static List getTopPerformances(int iTopCount)
   {
      return selectSQL("SELECT recording_id, score=(sum(performance_rating) / count(*)), count=count(*) FROM reviews GROUP BY recording_id ORDER BY score DESC", iTopCount);
   }

   public static List getTopRecordings(int iTopCount)
   {
      return selectSQL("SELECT recording_id, score=(sum(recording_rating) / count(*)), count=count(*) FROM reviews GROUP BY recording_id ORDER BY score DESC", iTopCount);
   }

   public static List getTopOverall(int iTopCount)
   {
      return selectSQL("SELECT recording_id, score=(sum(recording_rating + performance_rating) / count(*)), count=count(*) FROM reviews GROUP BY recording_id ORDER BY score DESC", iTopCount);
   }

   public static List<List<String>> selectSQL(String strSQL)
   {
      return selectSQL(strSQL, -1);
   }
   
   
   public static List<List<String>> selectSQL(String strSQL, int maxRows)
   {
      System.out.println("SQL: "+strSQL);
      Connection connection = null;
      List<List<String>> results = new ArrayList<List<String>>();
      try
      {
         try
         {
            connection = DriverManager.getConnection(db_url, db_login, db_password);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(strSQL);
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();
            int iRows = 0;
            while (rs.next())
            {
               List<String> row = new ArrayList<String>();
               for (int col = 0; col < numColumns; col++)
               {
                  row.add(rs.getString(col + 1));
               }

               results.add(row);
               iRows++;
               if ((maxRows > 0) && (iRows >= maxRows))
               {
                  break;
               }
            }
         }
         finally
         {
            if (connection != null)
            {
               connection.close();
               connection = null;
            }
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

      return results;
   }


   public static boolean executeSQL(String strSQL)
   {
      System.out.println("SQL: "+strSQL);
      Connection connection = null;
      boolean okay = true;

      try
      {
         try
         {
            connection = DriverManager.getConnection(db_url, db_login, db_password);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(strSQL);
         }
         finally
         {
            if (connection != null)
            {
               connection.close();
               connection = null;
            }
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         okay = false;
      }

      return okay;
   }
   
   public static String getCurrentSQLDate()
   {
      StringBuffer strDate = new StringBuffer(19);
      GregorianCalendar cal = new GregorianCalendar();
      strDate.append(String.valueOf(cal.get(GregorianCalendar.YEAR)));
      strDate.append("-");
      strDate.append(String.valueOf(cal.get(GregorianCalendar.MONTH) + 1));
      strDate.append("-");
      strDate.append(String.valueOf(cal.get(GregorianCalendar.DAY_OF_MONTH)));
      strDate.append(" ");
      strDate.append(String.valueOf(cal.get(GregorianCalendar.HOUR_OF_DAY)));
      strDate.append(":");
      strDate.append(String.valueOf(cal.get(GregorianCalendar.MINUTE)));
      strDate.append(":");
      strDate.append(String.valueOf(cal.get(GregorianCalendar.SECOND)));

      return strDate.toString();
   }
   
   /**
    * Parses and converts a SQL formated datetime string to a display format
    * @param strSQLDate
    * @return
    */
   public static String getDisplayDateTime(String strSQLDate)
   {
      String strDisplayDate = "";

      try
      {
         SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         Date date = sqlFormat.parse(strSQLDate);
         SimpleDateFormat displayFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
         strDisplayDate = displayFormat.format(date);
      }
      catch (ParseException e)
      {
         e.printStackTrace(); //FIXME??
         strDisplayDate = "";
      }

      return strDisplayDate;
   }


   public static String formatDecimal(String decimal)
   {
      return formatter.format(Double.parseDouble(decimal));
   }

   public static boolean isValidInt(String id)
   {
      boolean valid = false;
      if (null != id)
      {
         if (!"".equals(id))
         {
            try
            {
               Integer.parseInt(id);
               valid = true;
            }
            catch (Exception e)
            {
               // ignore
            }
         }
      }
      return valid;
   }
}
