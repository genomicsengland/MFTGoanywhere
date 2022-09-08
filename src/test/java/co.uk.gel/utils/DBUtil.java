package co.uk.gel.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

    private static final Logger log = LoggerFactory.getLogger(DBUtil.class);

    static String DB_SERVER ="servername";
        static String DB_NAME = "dbname";
        static String DB_USERNAME = "username";
        static String DB_PASSWORD = "password";

        static Connection connection;

        public static Connection getConnection() throws SQLException, ClassNotFoundException {
            String url;
            try {

                url = "jdbc:postgresql://" + DB_SERVER + ":5432/" + DB_NAME;
                log.info("DB: connecting to " + url);
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
                //MISDebugger.println("MIS.....Connection : "+connection);
                return connection;
            } catch (Exception exp) {
                log.info("ERROR in getting Connection to " + DB_SERVER + " Database.\nException:" + exp);
                return null;
            }
        }

        public static void closeConnection() throws SQLException {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception exp) {
                log.info("ERROR in closing Connection to Database....\n" + exp);
            }
        }

        public static ResultSet executeQuery(String queryString) throws SQLException, ClassNotFoundException {
            try {
                if(connection == null) {
                    connection = getConnection();
                }

                //Debugger.println("Executing Query:\n" + queryString + "\nConnection " + connection);
                ResultSet rs = connection.prepareStatement(queryString,ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE).executeQuery();
                return rs;
            } catch (SQLException | ClassNotFoundException exp) {
                log.info("Exception executing query:" + exp);
                //closeConnection();
                return null;
            }
        }

    }