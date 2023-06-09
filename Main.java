// Laurence Garcia
// Sunjay Guttikonda
// CS 4350
// Lab 4

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
                    // Display Schedule
                    case "ds":
                        displaySchedule(connect, statement);
                        break;
                    // Delete Trip offering
                    case "dt":
                        System.out.print("Insert Trip Number to Delete: ");
                        int delTripNum = kb.nextInt();
                        deleteTrip(connect, statement, delTripNum);
                        break;
                    // Add Trip Offering
                    case "at":
                        System.out.print("Insert Trip Number: ");
                        int addTripNum = kb.nextInt();
                        kb.nextLine();
                        System.out.print("Insert Date of Trip: ");
                        String addTripDate = kb.nextLine();
                        System.out.print("Insert Start Time of Trip: ");
                        String addTripStart = kb.nextLine();
                        System.out.print("Insert Arrival Time of Trip: ");
                        String addTripArrival = kb.nextLine();
                        System.out.print("Insert Driver of Trip: ");
                        String addTripDriver = kb.nextLine();
                        System.out.print("Insert Bus: ");
                        int addTripBus = kb.nextInt();
                        addTrip(connect, statement, addTripNum, addTripDate, addTripStart, addTripArrival, addTripDriver, addTripBus);
                        break;
                    // Change Driver
                    case "cd":
                        System.out.print("Insert New Driver: ");
                        String changeDriver = kb.nextLine();
                        System.out.print("Insert Trip to Change: ");
                        int tripChangeDriver = kb.nextInt();
                        changeDriver(connect, statement, changeDriver, tripChangeDriver);
                        break;
                    // Change bus
                    case "cb":
                        System.out.print("Insert New Bus: ");
                        int changeBus = kb.nextInt();
                        System.out.print("Insert Trip to Change: ");
                        int tripChangeBus = kb.nextInt();
                        changeBus(connect, statement, changeBus, tripChangeBus);
                        break;
                    // Display Trip Stops
                    case "dts":
                        displayStops(connect, statement);
                        break;
                    // Display Weekly Scehdule for driver
                    case "dw":
                        displayDriver(connect, statement);
                        break;
                    // Add driver
                    case "ad":
                        System.out.print("Insert Name of Driver: ");
                        String name = kb.nextLine();
                        System.out.print("Insert Number of Driver: ");
                        String num = kb.nextLine();
                        addDriver(connect, statement, name, num);
                        break;
                    // Add bus
                    case "ab":
                        System.out.print("Insert BusID: ");
                        int addBusID = kb.nextInt();
                        kb.nextLine();
                        System.out.print("Insert Model: ");
                        String model = kb.nextLine();
                        System.out.print("Insert Year: ");
                        String year = kb.nextLine();
                        addBus(connect, statement, addBusID, model, year);
                        break;
                    // Delete bus
                    case "db":
                        System.out.print("Select Bus ID to delete: ");
                        int delBusID = kb.nextInt();
                        deleteBus(connect, statement, delBusID);
                        break;
                    // Insert actual trip info
                    case "it":
                        System.out.print("Insert Trip Number: ");
                        int actualTripNum = kb.nextInt();
                        kb.nextLine();
                        System.out.print("Insert Actual Start Time: ");
                        String actualStartTime = kb.nextLine();
                        System.out.print("Insert Actual Arrival Time: ");
                        String actualArrivalTime = kb.nextLine();
                        System.out.print("How many passengers entered: ");
                        int passengerEnter = kb.nextInt();
                        kb.nextLine();
                        System.out.print("How many passengers exited: ");
                        int passengerExit = kb.nextInt();
                        kb.nextLine();
                        insertData(connect, statement, actualTripNum, actualStartTime, actualArrivalTime, passengerEnter, passengerExit);
                        break;
                    // Display all commands
                    case "h":
                        break;
                    // Exit
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

        kb.close();
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

            System.out.printf("| %-11s | %-4s | %-4s | %-4s | %-4s | %-4s |\n","Trip Number", "Date", "Start Time", "Arrival Time", "Driver", "Bus ID");
            System.out.printf("--------------------------------%n");
            while (result.next())
            {
                System.out.printf("| %03d | %-10s | %-7s | %-7s | %-10s | %03d |\n", result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getInt(6));
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    // Deletes a trip offering
    private static void deleteTrip(Connection connect, Statement statement, int tripNum)
    {
        try
        {
            String update = String.format("DELETE FROM tripoffering WHERE (TripNumber = '%d')", tripNum);
            statement.executeUpdate(update);

            System.out.printf("Deleted Trip %s", tripNum);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    // Adds a trip offering
    private static void addTrip(Connection connect, Statement statement, int addTripNum, String addTripDate, String addTripStart, String addTripArrival, String addTripDriver, int addTripBus)
    {
        try
        {
            String update = String.format("INSERT INTO tripoffering(TripNumber, Date, ScheduledStartTime, ScheduledArrivalTime, DriverName, BusID) VALUES ('%d', '%s', '%s', '%s', '%s', '%d')", addTripNum, addTripDate, addTripStart, addTripArrival, addTripDriver, addTripBus);
            statement.executeUpdate(update);

            System.out.printf("Added Trip %s", addTripNum);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    // Changes driver from trip offering
    private static void changeDriver(Connection connect, Statement statement, String newDriver, int tripNum)
    {
        try
        {
            String update = String.format("UPDATE tripoffering SET DriverName = '%s' WHERE (TripNumber = '%d')", newDriver, tripNum);
            statement.executeUpdate(update);

            System.out.printf("Changed Driver to %s", newDriver);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    // Changes bus from trip offering
    private static void changeBus(Connection connect, Statement statement, int newBus, int tripNum)
    {
        try
        {
            String update = String.format("UPDATE tripoffering SET BusID = '%d' WHERE (TripNumber = '%d')", newBus, tripNum);
            statement.executeUpdate(update);

            System.out.printf("Changed Bus to %d", newBus);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    // Display stops
    private static void displayStops(Connection connect, Statement statement)
    {
        try
        {
            ResultSet result = statement.executeQuery("select * from tripstopinfo");

            System.out.printf("| %-11s | %-11s | %-12s | %-12s |\n","Trip Number", "Stop Number", "Sequence Num", "Driving Time");
            System.out.printf("--------------------------------%n");

            while (result.next())
            {
                System.out.printf("| %03d | %03d | %04d | %-5s |\n", result.getInt(1), result.getInt(2), result.getInt(3), result.getString(4));
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    // DIsplay weekly schedule of given driver
    private static void displayDriver(Connection connect, Statement statement)
    {
        try
        {
            ResultSet result = statement.executeQuery("select * from driver");

            System.out.printf("| %-11s | %-20s |\n","Driver Name", "Driver Phone Number");
            System.out.printf("--------------------------------%n");

            while (result.next())
            {
                System.out.printf("| %-11s | %-10s |\n", result.getString(1), result.getString(2));
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    // add driver
    private static void addDriver(Connection connect, Statement statement, String name, String num)
    {
        try
        {
            String update = String.format("INSERT INTO driver(DriverName, DriverTelephoneNumber) VALUES ('%s', '%s')", name, num);
            statement.executeUpdate(update);

            System.out.printf("Added %s\n", name);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    // Add bus
    private static void addBus(Connection connect, Statement statement, int busID, String model, String year)
    {
        try
        {
            String update = String.format("INSERT INTO bus(BusID, Model, Year) VALUES ('%d', '%s', '%s')", busID, model, year);
            statement.executeUpdate(update);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    // Delete bus
    private static void deleteBus(Connection connect, Statement statement, int busID)
    {
        try
        {
            String update = String.format("DELETE FROM bus WHERE (BusID = '%d')", busID);
            statement.executeUpdate(update);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    // Record data from trip
    private static void insertData(Connection connect, Statement statement, int tripNum, String actualStart, String actualArrival, int passengerIn, int passengerOut)
    {
        try
        {
            ResultSet resultOffering = statement.executeQuery(String.format("select * from tripoffering where TripNumber = '%d'", tripNum));
            String startTime, arrivalTime;
            
            if(resultOffering.next())
            {
                startTime = resultOffering.getString("ScheduledStartTime");
                arrivalTime = resultOffering.getString("ScheduledArrivalTime");
            }
            else
            {
                startTime = null;
                arrivalTime = null;
            }
            

            Statement statement2 = connect.createStatement();
            ResultSet resultStop = statement2.executeQuery(String.format("select * from tripstopinfo where TripNumber = '%d'", tripNum));

            int stopNum = 0;
            if (resultStop.next())
            {
                stopNum = resultStop.getInt("StopNumber");
            }

            String update = String.format("INSERT INTO actualtripstopinfo(TripNumber, ScheduledStartTime, StopNumber, ScheduledArrivalTime, ActualStartTime, ActualArrivalTime, NumOfPassengerIn, NumOfPassengerOut) VALUES ('%d', '%s', '%d', '%s', '%s', '%s', '%d', '%d')", 
                                            tripNum, startTime, stopNum, arrivalTime, actualStart, actualArrival, passengerIn, passengerOut);
            statement.executeUpdate(update);

            System.out.println("Inserted Trip Data");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}