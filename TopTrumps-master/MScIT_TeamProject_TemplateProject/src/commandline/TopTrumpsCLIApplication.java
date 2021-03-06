package commandline;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

    /**
     * This main method is called by TopTrumps.java when the user specifies that they want to run in
     * command line mode. The contents of args[0] is whether we should write game logs to a file.
     * @param args
     * @throws SQLException 
     */
    public static void main(String[] args) throws SQLException {
       // final DatabaseConnection connection = new DatabaseConnection();
        Scanner scanner = new Scanner(System.in);

        boolean writeGameLogsToFile = false; // Should we write game logs to file?
        if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection

        // State
        boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
        

        // Loop until the user wants to exit the game
        while (!userWantsToQuit) {

            // ----------------------------------------------------
            // Add your game logic here based on the requirements
            // ----------------------------------------------------
            Controller cont = new Controller(String.valueOf(writeGameLogsToFile));
            cont.play();
        }
        
        userWantsToQuit = true; // use this when the user wants to exit the game

    }


}