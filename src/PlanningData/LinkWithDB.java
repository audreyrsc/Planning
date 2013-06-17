/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanningData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Audrey
 */
public class LinkWithDB {

    private Connection connect;
    
    /**
     * Established a new connection with the local database if the connection
     * does not already exist
     */
    public boolean connection(String username, char[] password) {
        if(connect==null) {
            try {
                Class.forName("org.postgresql.Driver");

                String url = "jdbc:postgresql://localhost:5432/PlanningDatas";
                connect = DriverManager.getConnection(url, username, new String(password));
                connect.setAutoCommit(true);

                // Connection with DB established //
               // System.out.println("Connection with DB is OK!");
                return true;
            }
            catch (ClassNotFoundException ex) {
                System.err.println(ex.getMessage());
                return false;
            }
            catch (SQLException e) {
                JOptionPane pane = new JOptionPane(e.getMessage(),JOptionPane.ERROR_MESSAGE);
                JDialog dialog = pane.createDialog("Error");
                dialog.setVisible(true);
                System.err.println(e.getMessage());
                return false;
            }
        }
        else return true;
    }
    
//    public void getTasks(Day firstDay, Day lastDay) {
//        try {
//            // On récupère les taches qui commencent en "day"
////            PreparedStatement getTasksStatement = connect.prepareStatement(
////                    "SELECT * FROM tasks WHERE date_beginning='"+day.getFullDate()+"'");
////            ResultSet tasksResult = getTasksStatement.executeQuery();
//            
//            Statement getTasksStatement = connect.createStatement();
//            ResultSet tasksResult = getTasksStatement.executeQuery(
//                    "SELECT * FROM tasks t "
//                    +" LEFT JOIN occurrences o "
//                    +" ON t.num_task=o.id_task "
//                    +" WHERE '"+firstDay.getFullDate()+"' BETWEEN t.date_beginning AND t.date_end");
//            
//            while(tasksResult.next()) {
//                System.out.println(tasksResult.getInt("num_task")+" "+tasksResult.getString("description"));
//            }
//            // On récupère les récurrences (appel de plusieurs methodes)
//            
//            connect.commit();
//        }
//        catch (SQLException e) {
//            System.err.println(e.getMessage());
//            try { connect.rollback(); }
//            catch (SQLException e1) { System.err.println(e1.getMessage()); }
//        }
////        catch (ParseException parse) {
////            System.err.println(parse.getMessage());
////        }
//    }
   
//    private void getDailyRec(Day day) {
//        try {
//            Statement dailyRecSt = connect.createStatement();
//            dailyRecSt.executeQuery(
//                    "SELECT * FROM tasks t "
//                    +" INNER JOIN occurences o "
//                    +" ON t.num_task=o.id_task"
//                    +" WHERE '"+day.getFullDate()+"' BETWEEN t.date_beginning AND t.date_end "
//                    +" AND t.recurrence='DAY'");
//        }
//        catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//        
//    }
    
    private void getWeeklyRec() {
        
    }
    
    private void getMonthlyRec() {
        
    }
    
    private void getYearlyRec() {
        
    }
    
    public void getValues() throws SQLException {
        PreparedStatement dailyTaskStatement = connect.prepareStatement("SELECT * FROM task_of_day");
        ResultSet dailyTaskResults = dailyTaskStatement.executeQuery();
        
        System.out.println("tada");
        while(dailyTaskResults.next()) {
            System.out.println(dailyTaskResults.getDate("date_of_day"));
            System.out.println(dailyTaskResults.getInt("num_task"));
        }
    }
    
    /**
     * Add data in the data base
     * @param date : the beginning's day of the event
     * @param message : the description of the event
     */
    public void addValue(String date, String timeBegin, String timeEnd, String message, String recurrence) {
        try {
            String sql = "INSERT INTO tasks (date_beginning, date_end, time_beginning, time_end, description, recurrence) VALUES ("//'?','?',?,?);";
                    + "'"+date+"',"
                    + "'"+date+"',"
                    + "'"+timeBegin+"',"
                    + "'"+timeEnd+"',"
                    + "?,"
                    + "'"+recurrence+"');";
            PreparedStatement addLine = connect.prepareStatement(sql);
//            addLine.setString(1, day.getFullDate());
//            addLine.setString(2, day.getFullDate());
//            addLine.setTime(1, timeBegin);
//            addLine.setTime(2, timeEnd);
            addLine.setString(1, message);
            addLine.executeUpdate();
        }
        catch(SQLException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
    
    public void addValue(String date, String message, String recurrence) {
        try {
            String sql = "INSERT INTO tasks (date_beginning, date_end, description, recurrence) VALUES ("//'?','?',?,?);";
                    + "'"+date+"',"
                    + "'"+date+"',"
                    + "?,"
                    + "'"+recurrence+"');";
            PreparedStatement addLine = connect.prepareStatement(sql);
//            addLine.setString(1, day.getFullDate());
//            addLine.setString(2, day.getFullDate());
            addLine.setString(1, message);
            addLine.executeUpdate();
        }
        catch(SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    /**
     * Return a result set with datas for the day in parameter
     * @param date
     * @return num_task & description
     */
    public ResultSet getDatasOfDay(Date date) {
        try{
            String sql = "SELECT num_task, time_beginning, time_end, description "
                    + " FROM tasks WHERE date_beginning=?"
                    + " ORDER BY time_beginning ASC;";
            
//            PreparedStatement select = connect.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            PreparedStatement select = connect.prepareStatement(sql);
            select.setDate(1, new java.sql.Date(date.getTime()));
            return select.executeQuery();
            
        }catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    /**
     * Return all tasks since the date in parameter
     * @param today : the date to begin
     * @return ResultSet with num_task / date_beginning / description
     */
    public ResultSet getAllDatas(Date today) {
        try{
            String sql = "SELECT num_task, date_beginning, description "
                    + " FROM tasks "
                    + " WHERE date_beginning>=?"
                    + " ORDER BY date_beginning ASC;";
            
            PreparedStatement select = connect.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            select.setDate(1, new java.sql.Date(today.getTime()));
        }catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    /**
     * Return true if there is at least one thing to do for the day in parameter
     * @param date
     * @return boolean
     */
    public boolean haveSmthingToDo(String date) {
        try{
            Statement count = connect.createStatement();
            ResultSet countSet = count.executeQuery("SELECT COUNT(*) FROM tasks WHERE date_beginning='"+date+"';");
            
            countSet.next();
            if(countSet.getInt(1)>0)
                return true;
            else return false;
            
        }catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return false;
    }
    
    /**
     * Delete the selected element in the DB
     * @param numTask : primary key in DB
     */
    public void deleteData(int numTask) {
        try {
            Statement delete = connect.createStatement();
            delete.executeUpdate("DELETE FROM tasks WHERE num_task="+numTask);
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
    }
    
    public ResultSet getTask(int numTask) {
        try {
            Statement select = connect.createStatement();
            select.executeQuery("SELECT * FROM tasks WHERE num_task="+numTask);
            return select.getResultSet();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
    
    private boolean existValue(Date day) {
        System.out.println("Text existValue");
        try {
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM one_day WHERE date_of_day = '16/11/2011';");
//            if(statement.executeQuery("SELECT * FROM one_day WHERE date_of_day = "+day).next())
            if(result.next())
                return true;
            else return false;
        }
        catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
}
