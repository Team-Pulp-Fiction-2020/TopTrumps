import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
 private int gamesPlayed = 0;

public DatabaseConnection() throws SQLException {
	
  //load the JDBC driver
  try {
   Class.forName("org.postgresql.Driver");

  } catch (ClassNotFoundException e) {
   System.out.println(" Could not find JBDC driver.");
   e.printStackTrace();
   return;
  }
  // try catch exception
  //the driver is loaded...
  System.out.println("Postgresql Driver found.");
  //proceed with a database connection
  Connection connection = null;
  // connect to the yacata.dcs.gla.ac.uk server, on port:5432
  Statement statement1 = null;
  String sql1 = "SELECT COUNT(GameID) AS Total_No_Games FROM Games;";
//  String sql2 =

  try {
   connection = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/",
    "m_19_2474073k", "2474073k");
  } catch (SQLException e) {
   System.out.println("Connection failed!");
   e.printStackTrace();
   return;
  }
  //try catch exception
  //connection to the database is done!

  if (connection != null) {
   try {
    System.out.println("Controlling your database!");
    statement1 = connection.createStatement();
    ResultSet resultGamesPlayed = statement1.executeQuery(sql1);
    while(resultGamesPlayed.next()) {
    	gamesPlayed = resultGamesPlayed.getInt(1);
    }
 
    //do not forget to close the connection to your database!

    connection.close();

   } catch (SQLException e) {
    e.printStackTrace();
   } // try catch exception
  } else {
   System.out.println("Failed to establish connection.");
  }
  //if-else
  
 }

public int getGamesPlayed() {
	return gamesPlayed;
}

}