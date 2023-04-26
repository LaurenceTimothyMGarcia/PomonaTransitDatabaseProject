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

public class Main 
{
    public static void main (String[] args)
    {

    }

    private static void displayAllCommands(){
		System.out.println("ds:\tDisplay a Schedule");
        System.out.println("dt:\tDelete a Trip Offering");
        System.out.println("at:\tAdd a Trip Offering");
        System.out.println("cd:\tChange a Driver");
        System.out.println("cb:\tChange a Bus");
        System.out.println("ds:\tDisplay Trip Stops");
        System.out.println("dw:\tDisplay Weekly Schedule for Driver");
        System.out.println("ad:\tAdd a Driver");
        System.out.println("ab:\tAdd a Bus");
        System.out.println("db:\tDelete a Bus");
        System.out.println("it:\tInsert Actual Trip Info");
        System.out.println("h:\tDisplay all commands");
        System.out.println("x:\tExit program");
    }

    // Display Schedule
    public void displaySchedule()
    {

    }

    // Edit Schedule
    public void editSchedule()
    {

    }

    // Display stops
    public void displayStops()
    {

    }

    // DIsplay weekly schedule of given driver
    public void displayDriver()
    {

    }

    // add driver
    public void addDriver()
    {

    }

    // Add bus
    public void addBus()
    {

    }

    // Delete bus
    public void deleteBus()
    {

    }

    // Record data from trip
    public void insertData()
    {

    }
}