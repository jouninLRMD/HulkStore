package hulkstore_.model.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

/**
 * This class handles the connection to the database.
 * 
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public final class ResourceManager {
    
    private static final Logger LOG = Logger.getLogger(ResourceManager.class.getName());
    private static final String JDBC_DRIVER   = "com.mysql.cj.jdbc.Driver";
    private static String JDBC_URL;
    private static String JDBC_USER;
    private static String JDBC_PASSWORD;
    private static Driver driver = null;
    
    /**
     * Get the connection data to the database.
     * 
     * @return String[]
     */
    public static String[] getDataConnection() {
        String[] data = null;
        
        LOG.debug("Getting connection data...");                        
        try {
            data = new String[3];
            FileReader fReader = new FileReader("connection.dat");
            BufferedReader bReader = new BufferedReader(fReader);
            String line;
            int number = 0;

            while((line = bReader.readLine()) != null) {
                data[number] = line.substring(line.indexOf("=") + 1, line.length());
                number++;
                if(number > 2) { break; }
            }
            LOG.debug("Connection data loaded");
         
        } catch (IOException exception) {            
            LOG.fatal("Error accessing connection.dat file = " + exception);
            JOptionPane.showMessageDialog(null, "Error al acceder al archivo connection.dat", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return data;
    }
    
    /**
     * Sets the database connection data.
     * 
     * @param host
     * @param user
     * @param pass 
     */
    public static void setDataConnection(String host, String user, String pass) {        
        LOG.info("Setting connection data...");
        try (PrintWriter pWritter = new PrintWriter("connection.dat")) {            
            pWritter.print("host=" + host +
                        "\nusuario=" + user +
                        "\npassword=" + pass +
                        "\n\nNo editar este archivo.");
            LOG.info("Connection data saved");
        
        } catch (FileNotFoundException exception) {
            LOG.fatal("Error accessing connection.dat file = " + exception);
            JOptionPane.showMessageDialog(null, "No se encontro el archivo connection.dat", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Test if the connection data succeeds in connecting to the database successfully.
     * 
     * @param host
     * @param user
     * @param pass
     * @return boolean
     */
    public static boolean testConnection(String host, String user, String pass) {        
        boolean ok = false;
        Driver dvr;
        
        LOG.info("validating connection parameters...");
        try {            
            Class jdbcDriverClass = Class.forName( JDBC_DRIVER );
            LOG.debug("Driver loaded = " + JDBC_DRIVER);
            dvr = (Driver) jdbcDriverClass.newInstance();
            DriverManager.registerDriver( dvr );
            LOG.info("Trying to connect...");
            close(DriverManager.getConnection(host, user, pass));
            ok = true;
            LOG.info("Correct configuration");
        
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException exception) {              
            LOG.error("Connection failed = " + exception);
            JOptionPane.showMessageDialog(null, "Falló test de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return ok;
    }
    
    /**
     * Establishes the connection to the database.
     * 
     * @return boolean
     * @throws SQLException 
     */
    public static boolean setConnection() throws SQLException {        
        boolean ok = false;
        
        LOG.info("Establishing connection...");
        String[] dataConection = getDataConnection();

        if (dataConection[0] != null) {
            
            JDBC_URL = dataConection[0];
            JDBC_USER = dataConection[1];
            JDBC_PASSWORD = dataConection[2];

            try {                
                close(getConnection());            
                ok = true;
                LOG.info("Established connection");
                
            } catch (SQLException exception) {                
                LOG.fatal("Connection failed = " + exception);
                JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);                
            }
        }
         
        return ok;  
    }

    /**
     * Gets the connection to the database.
     * 
     * @return Connection
     * @throws SQLException 
     */
    public static synchronized Connection getConnection() throws SQLException {        
        if (driver == null) {    
            LOG.debug("Initializing Driver...");
            try {                
                Class jdbcDriverClass = Class.forName( JDBC_DRIVER );
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver( driver );
                LOG.debug("Driver loaded = " + JDBC_DRIVER);
            
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException exception) {                
                LOG.fatal("Failed to initialise JDBC driver = " + JDBC_DRIVER + " / " + exception);
            }
        }

        return DriverManager.getConnection (JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    /**
     * Close the connection to the database.
     * 
     * @param conn 
     */
    public static void close(Connection conn) {        
        LOG.debug("Closing connection...");
        try { 
            if (conn != null) conn.close();
            LOG.debug("Connection closed");
        
        } catch (SQLException exception) {        
            LOG.fatal("Couldn't close connection = " + exception );
        }
    }
    
    /**
     * Close the statement.
     * 
     * @param stmt 
     */
    public static void close(PreparedStatement stmt) {        
        LOG.debug("Closing preparedStatement...");
        try {
            if (stmt != null) stmt.close(); 
            LOG.debug("preparedStatement closed");
        
        } catch (SQLException exception) {
            LOG.fatal("Couldn't close preparedStatement = " + exception );
        }
    }

    /**
     * Close the resultSet.
     * 
     * @param rs 
     */
    public static void close(ResultSet rs) {        
        LOG.debug("Closing resultSet...");
        try {
            if (rs != null) rs.close(); 
            LOG.debug("resultSet closed");
        
        } catch (SQLException exception) {
            LOG.fatal("Couldn't close resultSet = " + exception );
        }
    }
}