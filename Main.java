// Laurence Garcia
// Sunjay 
// CS 4350
// Lab 4

// Project Details
    // Design and implement a database system of the Pomona Transit System with Database of choice and JDBC
    // 1. Display the schedule of all trips for a given StartLocationName and Destination Name, and Date. 
        // In addition to these attributes, the schedule includes: Scheduled StartTime, ScheduledArrivalTime , DriverID, and BusID.
    // 2. Edit the schedule i.e. edit the table of Trip Offering as follows:
        // -Delete a trip offering specified by Trip#, Date, and ScheduledStartTime;
        // -Add a set of trip offerings assuming the values of all attributes are given (the software asks if you have more trips to enter) ;
        // - Change the driver for a given Trip offering (i.e given TripNumber, Date, ScheduledStartTime);
        // - Change the bus for a given Trip offering
    // 3. Display the stops of a given trip ( i.e. the attributes of the table TripStopInfo).
    // 4. Display the weekly schedule of a given driver and date.
    // 5. Add a drive.
    // 6. Add a bus.
    // 7. Delete a bus.
    // 8. Record (insert) the actual data of a given trip offering specified by its key. The actual
    // data include the attributes of the table ActualTripStopInfo.

import java.sql.*;
import java.util.Scanner;

public class Main 
{
    public static void main (String[] args)
    {
        Scanner kb = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/pomonatransitsystem";
        String username = "root";
        String password = "";

        String input = "";

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection(url, username, password);
            Statement statement = connect.createStatement();

            do
            {
                System.out.println("---------------------");
                displayAllCommands();
                System.out.print("Select Option: ");
                input = kb.nextLine();

                switch(input)
                {
                    case "ds":
                        displaySchedule(connect, statement);
                        break;
                    case "dt":
                        break;
                    case "at":
                        break;
                    case "cd":
                        break;
                    case "cb":
                        break;
                    case "dts":
                        break;
                    case "dw":
                        break;
                    case "ad":
                        break;
                    case "ab":
                        break;
                    case "it":
                        break;
                    case "h":
                        break;
                    case "x":
                        break;
                }

            } while(input != "x");

            connect.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    private static void displayAllCommands()
    {
        System.out.println("ds:\tDisplay a Schedule");
        System.out.println("dt:\tDelete a Trip Offering");
        System.out.println("at:\tAdd a Trip Offering");
        System.out.println("cd:\tChange a Driver");
        System.out.println("cb:\tChange a Bus");
        System.out.println("dts:\tDisplay Trip Stops");
        System.out.println("dw:\tDisplay Weekly Schedule for Driver");
        System.out.println("ad:\tAdd a Driver");
        System.out.println("ab:\tAdd a Bus");
        System.out.println("db:\tDelete a Bus");
        System.out.println("it:\tInsert Actual Trip Info");
        System.out.println("h:\tDisplay all commands");
        System.out.println("x:\tExit program");
    }

    // Display Schedule
    private static void displaySchedule(Connection connect, Statement statement)
    {
        try 
        {
            ResultSet result = statement.executeQuery("select * from tripoffering");

            while (result.next())
            {
                System.out.println(result.getInt(1));
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    // Edit Schedule
    private static void editSchedule()
    {
        try
        {

        }
        catch(Exception e)
        {

        }
    }

    // Display stops
    private static void displayStops()
    {
        try
        {

        }
        catch(Exception e)
        {
            
        }
    }

    // DIsplay weekly schedule of given driver
    private static void displayDriver()
    {
        try
        {

        }
        catch(Exception e)
        {
            
        }
    }

    // add driver
    private static void addDriver()
    {
        try
        {

        }
        catch(Exception e)
        {
            
        }
    }

    // Add bus
    private static void addBus()
    {
        try
        {

        }
        catch(Exception e)
        {
            
        }
    }

    // Delete bus
    private static void deleteBus()
    {
        try
        {

        }
        catch(Exception e)
        {
            
        }
    }

    // Record data from trip
    private static void insertData()
    {
        try
        {

        }
        catch(Exception e)
        {
            
        }
    }
}