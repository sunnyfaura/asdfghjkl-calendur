import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Properties;

public class Derby
{
    /* the default framework is embedded*/
    private String framework = "embedded";
    private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private String protocol = "jdbc:derby:";
    
    Connection conn = null;
	/* This ArrayList usage may cause a warning when compiling this class
	 * with a compiler for J2SE 5.0 or newer. We are not using generics
	 * because we want the source to support J2SE 1.4.2 environments. */
    ArrayList statements = new ArrayList(); // list of Statements, PreparedStatements
    PreparedStatement eventInsert = null;
    PreparedStatement eventUpdate = null;
    PreparedStatement taskInsert = null;
    PreparedStatement taskUpdate = null;
    Statement s = null;
    ResultSet rs = null;

    //ARGS
    //embedded - Use embedded database (DEFAULT)
    //derbyclient - Use client model to connect to a server
    public static void main(String[] args)
    {
        new Derby().go(args);
        System.out.println("SimpleApp finished");
    }

    /**
     * Starts the actual demo activities. This includes loading the correct
     * JDBC driver, creating a database by making a connection to Derby,
     * creating a table in the database, and inserting, updating and retrieving
     * some data. Some of the retrieved data is then verified (compared) against
     * the expected results. Finally, the table is deleted and, if the embedded
     * framework is used, the database is shut down.
     */
    void go(String[] args)
    {
        /* parse the arguments to determine which framework is desired*/
        parseArguments(args);

        System.out.println("Derby starting in " + framework + " mode");

        /* load the desired JDBC driver */
        loadDriver();

        /* We will be using Statement and PreparedStatement objects for
         * executing SQL. These objects, as well as Connections and ResultSets,
         * are resources that should be released explicitly after use, hence
         * the try-catch-finally pattern used below.
         * We are storing the Statement and Prepared statement object references
         * in an array list for convenience.
         */
        
        try
        {
            String dbName = "derbyDB"; // the name of the database

            /*
             * This connection specifies create=true in the connection URL to
             * cause the database to be created when connecting for the first
             * time. To remove the database, remove the directory derbyDB (the
             * same as the database name) and its contents.
             *
             * The directory derbyDB will be created under the directory that
             * the system property derby.system.home points to, or the current
             * directory (user.dir) if derby.system.home is not set.
             */
             conn = DriverManager.getConnection(protocol + dbName
                    + ";create=true");

            System.out.println("Connected to and created database " + dbName);

            // We want to control transactions manually. Autocommit is on by
            // default in JDBC.
            conn.setAutoCommit(false);

            /* Creating a statement object that we can use for running various
             * SQL statements commands against the database.*/
            s = conn.createStatement();
            statements.add(s);
            // We create a table...
            s.execute("CREATE TABLE event(name varchar(255), description varchar(255), date varchar(255), time varchar(255), isAllDay boolean, endTime varchar(255), repeating smallint)");
            System.out.println("Created table event");
            
            s.execute("CREATE TABLE task(name varchar(255), description varchar(255), date varchar(255), time varchar(255), " +
            		"status smallint, priority smallint)");
            System.out.println("Created table task");

            eventInsert = conn.prepareStatement("insert into event values (?, ?, ?, ?, ?, ?, ?)");
            statements.add(eventInsert);

            // Let's update some rows as well...
            eventUpdate = conn.prepareStatement(
                        "UPDATE event SET name=?, description=?, date=?, time=?, isAllDay=?, endTime=?, repeating=?  where name=?");
            statements.add(eventUpdate);
            
            taskInsert = conn.prepareStatement("insert into task values (?, ?, ?, ?, ?, ?)");
            statements.add(taskInsert);

            // Let's update some rows as well...
            taskUpdate = conn.prepareStatement(
                        "UPDATE task SET name=?, description=?, date=?, time=?, statys=?, priority=?  where name=?");
            statements.add(taskUpdate);

            


            /*
               We select the rows and verify the results.
             */
            rs = s.executeQuery(
                    "SELECT name, description, date, time, isAllDay, endTime, repeating FROM event ORDER BY date");

            /* 
             * Normally, it is best to use a pattern of
             *  while(rs.next()) {
             *    // do something with the result set
             *  }
             * to process all returned rows, but we are only expecting two rows
             * this time, and want the verification code to be easy to
             * comprehend, so we use a different pattern.
             */
            
            
            
            
			/*
               We commit the transaction. Any changes will be persisted to
               the database now.
             */
            conn.commit();
            System.out.println("Committed the transaction");

            /*
             * In embedded mode, an application should shut down the database.
             * If the application fails to shut down the database,
             * Derby will not perform a checkpoint when the JVM shuts down.
             * This means that it will take longer to boot (connect to) the
             * database the next time, because Derby needs to perform a recovery
             * operation.
             *
             * It is also possible to shut down the Derby system/engine, which
             * automatically shuts down all booted databases.
             *
             * Explicitly shutting down the database or the Derby engine with
             * the connection URL is preferred. This style of shutdown will
             * always throw an SQLException.
             *
             * Not shutting down when in a client environment, see method
             * Javadoc.
             */

            if (framework.equals("embedded"))
            {
                try
                {
                    // the shutdown=true attribute shuts down Derby
                    DriverManager.getConnection("jdbc:derby:;shutdown=true");

                    // To shut down a specific database only, but keep the
                    // engine running (for example for connecting to other
                    // databases), specify a database in the connection URL:
                    //DriverManager.getConnection("jdbc:derby:" + dbName + ";shutdown=true");
                }
                catch (SQLException se)
                {
                    if (( (se.getErrorCode() == 50000)
                            && ("XJ015".equals(se.getSQLState()) ))) {
                        // we got the expected exception
                        System.out.println("Derby shut down normally");
                        // Note that for single database shutdown, the expected
                        // SQL state is "08006", and the error code is 45000.
                    } else {
                        // if the error code or SQLState is different, we have
                        // an unexpected exception (shutdown failed)
                        System.err.println("Derby did not shut down normally");
                        printSQLException(se);
                    }
                }
            }
        }
        catch (SQLException sqle)
        {
            printSQLException(sqle);
        } finally {
            // release all open resources to avoid unnecessary memory usage

            handle();
            
        }
    }
    
    private void handle() {
    	try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
        } catch (SQLException sqle) {
            printSQLException(sqle);
        }

        // Statements and PreparedStatements
        int i = 0;
        while (!statements.isEmpty()) {
            // PreparedStatement extend Statement
            Statement st = (Statement)statements.remove(i);
            try {
                if (st != null) {
                    st.close();
                    st = null;
                }
            } catch (SQLException sqle) {
                printSQLException(sqle);
            }
        }

        //Connection
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException sqle) {
            printSQLException(sqle);
        }
    }
    
    public void addEvent(String name, String desc, String date, String time, boolean isAllDay, String endTime, int repeating) {
    	try {
    		eventInsert.setString(1, name);
            eventInsert.setString(2, desc);
            eventInsert.setString(3, date);
            eventInsert.setString(4, time);
            eventInsert.setBoolean(5, isAllDay);
            eventInsert.setString(6, endTime);
            eventInsert.setInt(7, repeating);
            eventInsert.executeUpdate();
    	} catch (SQLException e) {
    		printSQLException(e);
    	} finally {
    		handle();
    	}
    }
    
    public void updateEvent(String name, String desc, String date, String time, boolean isAllDay, String endTime, int repeating, String toReplace) {
    	try {
    		eventUpdate.setString(1, name);
            eventUpdate.setString(2, desc);
            eventUpdate.setString(3, date);
            eventUpdate.setString(4, time);
            eventUpdate.setBoolean(5, isAllDay);
            eventUpdate.setString(6, endTime);
            eventUpdate.setInt(7, repeating);
            eventUpdate.setString(8, toReplace);
    	} catch(SQLException e) {
    		printSQLException(e);
    	} finally {
    		handle();
    	}
    }
    
    public void addTask(String name, String desc, String date, String time, int status, int priority) {
    	try {
    		taskUpdate.setString(1, name);
            taskUpdate.setString(2, desc);
            taskUpdate.setString(3, date);
            taskUpdate.setString(4, time);
            taskUpdate.setInt(5, status);
            taskUpdate.setInt(6, priority);
    	} catch(SQLException e) {
    		printSQLException(e);
    	} finally {
    		handle();
    	}
    }
    
    public void updateTask(String name, String desc, String date, String time, int status, int priority, String toReplace) {
    	try {
    		taskUpdate.setString(1, name);
            taskUpdate.setString(2, desc);
            taskUpdate.setString(3, date);
            taskUpdate.setString(4, time);
            taskUpdate.setInt(5, status);
            taskUpdate.setInt(6, priority);
            taskUpdate.setString(7, toReplace);
    	} catch(SQLException e) {
    		printSQLException(e);
    	} finally {
    		handle();
    	}
    }

    //Loads the embedded driver
    private void loadDriver() {
        /*
         *  The JDBC driver is loaded by loading its class.
         *  If you are using JDBC 4.0 (Java SE 6) or newer, JDBC drivers may
         *  be automatically loaded, making this code optional.
         *
         *  In an embedded environment, this will also start up the Derby
         *  engine (though not any databases), since it is not already
         *  running.
         */
        try {
            Class.forName(driver).newInstance();
            System.out.println("Loaded the appropriate driver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("\nUnable to load the JDBC driver " + driver);
            System.err.println("Please check your CLASSPATH.");
            cnfe.printStackTrace(System.err);
        } catch (InstantiationException ie) {
            System.err.println(
                        "\nUnable to instantiate the JDBC driver " + driver);
            ie.printStackTrace(System.err);
        } catch (IllegalAccessException iae) {
            System.err.println(
                        "\nNot allowed to access the JDBC driver " + driver);
            iae.printStackTrace(System.err);
        }
    }

    /**
     * Reports a data verification failure to System.err with the given message.
     *
     * @param message A message describing what failed.
     */
    private void reportFailure(String message) {
        System.err.println("\nData verification failed:");
        System.err.println('\t' + message);
    }

    /**
     * Prints details of an SQLException chain to <code>System.err</code>.
     * Details included are SQL State, Error code, Exception message.
     *
     * @param e the SQLException from which to print details.
     */
    public static void printSQLException(SQLException e)
    {
        // Unwraps the entire exception chain to unveil the real cause of the
        // Exception.
        while (e != null)
        {
            System.err.println("\n----- SQLException -----");
            System.err.println("  SQL State:  " + e.getSQLState());
            System.err.println("  Error Code: " + e.getErrorCode());
            System.err.println("  Message:    " + e.getMessage());
            // for stack traces, refer to derby.log or uncomment this:
            //e.printStackTrace(System.err);
            e = e.getNextException();
        }
    }

    private void parseArguments(String[] args)
    {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("derbyclient"))
            {
                framework = "derbyclient";
                driver = "org.apache.derby.jdbc.ClientDriver";
                protocol = "jdbc:derby://localhost:1527/";
            }
        }
    }
}
