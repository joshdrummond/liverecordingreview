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
//import java.util.logging.Logger;
//import java.util.logging.Level;
//import java.util.logging.FileHandler;
//import java.util.logging.SimpleFormatter;


/*
create table bands (band_id int not null auto_increment primary key, description varchar(50) not null)
create table categories (category_id int not null auto_increment primary key, band_id int not null, description varchar(50) not null)
create table bootlegs (bootleg_id int not null auto_increment primary key, category_id int not null, type char(1) not null, description varchar(100) not null, source varchar(50) not null, info text not null, avg_perf_rating float not null, avg_rec_rating float not null, total_reviews int not null, date_created datetime not null)
create table reviews (review_id int not null auto_increment primary key, bootleg_id int not null, user_id varchar(50) not null, performance_rating int not null, recording_rating int not null, notes text not null, date_created datetime not null)

//bands (band_id int, description varchar(50))
//categories (cat_id int, band_id int, description varchar(50))
//bootlegs (boot_id int, cat_id int, date datetime, description varchar(100), type varchar(10), source varchar(50), info text)
//reviews (review_id int, user_id varchar(50), boot_id int, perf_score int, rec_score int, notes text)
//
//admin
//add bootleg
//add category
//add band
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
//category detail (list all bootlegs with avg scores)
//
//bootleg detail (list details and all reviews, submit new review)

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
//   private static String logfile = "";
//   private static Logger logger = null;


   static
   {
      try
      {
         Class.forName(db_driver);
//         logger = Logger.getLogger("com.joshdrummond.bootrating");
//         logger.setLevel(Level.ALL);
//         FileHandler handler = new FileHandler("bootleg-rating_log.txt");
//         handler.setFormatter(new SimpleFormatter());
//         logger.addHandler(handler);

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }


   public static boolean addBand(String band)
   {
      if (!bandExists(band))
      {
         return executeSQL("INSERT INTO bands (band_id, description) VALUES (null, '" + band + "')");
      }

      return false;
   }

   public static boolean bandExists(String band)
   {
      return ((selectSQL("SELECT band_id FROM bands where description = '" + band + "'")).size() > 0);
   }

   public static boolean addCategory(String category, String band_id)
   {
      if (!categoryExists(category, band_id))
      {
         return executeSQL("INSERT INTO categories (category_id, band_id, description) VALUES (null, " + band_id + ",'" + category + "')");
      }

      return false;
   }

   public static boolean categoryExists(String category, String band_id)
   {
      if (isValidInt(band_id))
      {
         return ((selectSQL("SELECT category_id FROM categories WHERE description = '" + category + "' AND band_id = " + band_id)).size() > 0);
      }
      else
      {
         return false;
      }
   }

   public static List getBands()
   {
      return selectSQL("SELECT ba.band_id, ba.description, b.count(*), r.count(*) FROM bands ba, categories c, bootlegs b, reviews r WHERE ba.band_id = c.band_id AND c.category_id = b.category_id AND b.bootleg_id = r.bootleg_id ORDER BY ba.description");
   }

   public static List getCategories(String band_id)
   {
      // return selectSQL("SELECT c.category_id, c.description, b.count(*), r.count(*) FROM categories c, bootlegs b, reviews r WHERE c.band_id = " + band_id + " AND b.category_id = c.category_id AND r.bootleg_id = b.bootleg_id ORDER BY c.description");
      return selectSQL("SELECT c.category_id, c.description FROM categories c WHERE c.band_id = " + band_id + " ORDER BY c.description");
   }

   public static List getCategory(String category_id)
   {
      if (isValidInt(category_id))
      {
         // return selectSQL("SELECT c.category_id, c.description, b.count(*), r.count(*) FROM categories c, bootlegs b, reviews r WHERE c.band_id = " + band_id + " AND b.category_id = c.category_id AND r.bootleg_id = b.bootleg_id ORDER BY c.description");
         return selectSQL("SELECT c.description FROM categories c WHERE c.category_id = " + category_id);
      }
      else
      {
         return new Vector(0);
      }
   }

   public static List getBootlegs(String category_id)
   {
      if (isValidInt(category_id))
      {
//      return selectSQL("SELECT b.bootleg_id, b.type, b.description, b.source, b.info, perf=(sum(r.performance_rating) / r.count(*)), rec=(sum(r.recording_rating) / r.count(*)), count=r.count(*) FROM bootlegs b, reviews r WHERE b.category_id = " +
//         category_id + " AND b.bootleg_id = r.bootleg_id ORDER BY b.description, b.type, b.source");
         return selectSQL("SELECT b.bootleg_id, b.type, b.description, b.source, b.avg_perf_rating, b.avg_rec_rating, b.total_reviews FROM bootlegs b WHERE b.category_id = " +
            category_id + " ORDER BY b.description, b.type, b.source");
      }
      else
      {
         return new Vector(0);
      }
   }

   public static List getBootleg(String bootleg_id)
   {
      if (isValidInt(bootleg_id))
      {
         return selectSQL("SELECT b.bootleg_id, b.category_id, c.description, b.type, b.description, b.source, b.info, b.avg_perf_rating, b.avg_rec_rating, b.total_reviews FROM bootlegs b, categories c WHERE b.bootleg_id = " +
            bootleg_id + " AND b.category_id = c.category_id");
      }
      else
      {
         return new Vector(0);
      }
   }

   public static List getReviews(String bootleg_id)
   {
      if (isValidInt(bootleg_id))
      {
         return selectSQL("SELECT review_id, user_id, performance_rating, recording_rating, notes, date_created FROM reviews WHERE bootleg_id = " + bootleg_id+ " ORDER BY date_created");
      }
      else
      {
         return new Vector(0);
      }
   }

   public static List getReview(String review_id)
   {
      if (isValidInt(review_id))
      {
         return selectSQL("SELECT review_id, bootleg_id, user_id, performance_rating, recording_rating, notes, date_created FROM reviews WHERE review_id = " + review_id);
      }
      else
      {
         return new Vector(0);
      }
   }

   public static boolean addBootleg(String category_id, String type, String description, String source, String info)
   {
      boolean okay = false;

      if (isValidInt(category_id) && !"".equals(description))
      {
         description = description.replaceAll("'", "''");
         source = source.replaceAll("'", "''");
         info = info.replaceAll("'", "''");
   //      if (!bootlegExists(category_id, type, date, description, source))
   //      {
         okay = executeSQL("INSERT INTO bootlegs (bootleg_id, category_id, type, description, source, info, avg_perf_rating, avg_rec_rating, total_reviews, date_created) " +
            "VALUES (null, " + category_id + ", '" + type + "', '" + description + "', '" + source + "', '" + info + "', 0.0, 0.0, 0, NOW())");
   //      }
      }

      return okay;
   }

   public static boolean modifyBootleg(String bootleg_id, String category_id, String type, String description, String source, String info)
   {
      if (isValidInt(bootleg_id) && isValidInt(category_id) && !"".equals(type) && !"".equals(description) && !"".equals(source) && !"".equals(info))
      {
         description = description.replaceAll("'", "''");
         source = source.replaceAll("'", "''");
         info = info.replaceAll("'", "''");
         return executeSQL("UPDATE bootlegs SET category_id = "+category_id+", type = '"+type+"', description = '"+description+
            "', source='"+source+"', info = '"+info+"' WHERE bootleg_id = "+bootleg_id);
      }
      else
      {
         return false;
      }
   }
   
   public static boolean addReview(String bootleg_id, String user_id, int performanceRating, int recordingRating, String notes)
   {
//      if (!bootlegExists(category_id, type, date, description, source))
//      {
      boolean okay = false;
      if (isValidInt(bootleg_id) && !"".equals(user_id) && !previouslyReviewed(user_id, bootleg_id))
      {
         user_id = user_id.replaceAll("'", "''");
         notes = notes.replaceAll("'", "''");
         okay = executeSQL("INSERT INTO reviews (review_id, bootleg_id, user_id, performance_rating, recording_rating, notes, date_created) " +
            "VALUES (null, " + bootleg_id + ", '" + user_id + "', " + performanceRating + ", " + recordingRating + ", '" + notes + "', NOW())");
         if (okay)
         {
            okay = recalculateBootlegScore(bootleg_id);
         }
      }

      return okay;
   }


   public static boolean previouslyReviewed(String user_id, String bootleg_id)
   {
      if (isValidInt(user_id) && isValidInt(bootleg_id))
      {
         return ((selectSQL("SELECT review_id FROM reviews WHERE bootleg_id = "+bootleg_id+
            " AND user_id = '"+user_id+"'")).size() > 0);
      }
      else
      {
         return false;
      }
   }


   public static boolean recalculateAllBootlegScores()
   {
      boolean allGood = true;
      List results = selectSQL("SELECT bootleg_id FROM bootlegs");
      for (Iterator iter = results.iterator(); iter.hasNext(); )
      {
         List row = (List)iter.next();
         allGood = allGood && recalculateBootlegScore((String)row.get(0));
      }
      return allGood;
   }


   public static boolean recalculateBootlegScore(String bootleg_id)
   {
      boolean okay = false;

      if (isValidInt(bootleg_id))
      {
         List results = selectSQL("SELECT count(*), sum(performance_rating), sum(recording_rating) " +
            "FROM reviews WHERE bootleg_id = "+bootleg_id);
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

            okay = executeSQL("UPDATE bootlegs SET avg_perf_rating = "+avgPerf + ", avg_rec_rating = "+avgRec+
               ", total_reviews = "+totalCount+" WHERE bootleg_id = "+bootleg_id);
         }
      }

      return okay;
   }

   /*
   public static boolean modifyReview(String review_id, String bootleg_id, String user_id, int performanceRating, int recordingRating, String notes)
   {
      return executeSQL("UPDATE reviews SET bootleg_id = "+bootleg_id+", user_id='"+user_id+"', performance_rating="+performanceRating+
         ", recording_rating="+recordingRating+", notes='"+notes+"' WHERE review_id = "+review_id);
   }
   
   public static boolean deleteReview(String review_id)
   {
      return executeSQL("DELETE FROM reviews WHERE review_id = "+review_id);
   }
   */

   public static List getTopReviewers(int iTopCount)
   {
      return selectSQL("SELECT user_id, count(*) FROM reviews GROUP BY user_id ORDER BY count(*) DESC", iTopCount);
   }

   public static List getTopReviewed(int iTopCount)
   {
      return selectSQL("SELECT bootleg_id, count(*) FROM reviews GROUP BY bootleg_id ORDER BY count(*) DESC", iTopCount);
   }

   public static List getTopPerformances(int iTopCount)
   {
      return selectSQL("SELECT bootleg_id, score=(sum(performance_rating) / count(*)), count=count(*) FROM reviews GROUP BY bootleg_id ORDER BY score DESC", iTopCount);
   }

   public static List getTopRecordings(int iTopCount)
   {
      return selectSQL("SELECT bootleg_id, score=(sum(recording_rating) / count(*)), count=count(*) FROM reviews GROUP BY bootleg_id ORDER BY score DESC", iTopCount);
   }

   public static List getTopOverall(int iTopCount)
   {
      return selectSQL("SELECT bootleg_id, score=(sum(recording_rating + performance_rating) / count(*)), count=count(*) FROM reviews GROUP BY bootleg_id ORDER BY score DESC", iTopCount);
   }

   public static List selectSQL(String strSQL)
   {
      return selectSQL(strSQL, -1);
   }
   
   
   public static List selectSQL(String strSQL, int maxRows)
   {
//      logger.fine("SQL: "+strSQL);
      System.out.println("SQL: "+strSQL);
      Connection connection = null;
      List results = new Vector();
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
               List row = new Vector();
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
//      logger.fine("SQL: "+strSQL);
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
