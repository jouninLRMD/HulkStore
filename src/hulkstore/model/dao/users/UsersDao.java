package hulkstore_.model.dao.users;

import hulkstore_.model.dao.AbstractDao;
import hulkstore_.model.dao.ResourceManager;
import hulkstore_.model.dto.users.UsersDto;
import hulkstore_.model.dto.users.UsersPk;
import java.sql.Connection;
import java.util.Collection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 * This class handles queries to the user table.
 * 
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class UsersDao extends AbstractDao {
    
    private static final Logger LOG = Logger.getLogger(UsersDao.class.getName());
    
    /** 
     * The factory class for this DAO has two versions of the create() method - one that
     * takes no arguments and one that takes a Connection argument. If the Connection version
     * is chosen then the connection will be store_d in this attribute and will be used by all
     * calls to this DAO, otherwise a new Connection will be allocated for each operation.
     */
    protected java.sql.Connection userConn;

    /** 
     * All finder methods in this class use this SELECT constant to build their queries.
     */
    protected final String SQL_SELECT = "SELECT userId, user_Name, userPass, identification, realName, surname, userProfile, state FROM " + getTableName() + "";

    /** 
     * Finder methods will pass this value to the JDBC setMaxRows method.
     */
    protected int maxRows;

    /** 
     * SQL INSERT statement for this table.
     */
    protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( userId, user_Name, userPass, identification, realName, surname, userProfile, state ) VALUES ( ?, ?, MD5(?), ?, ?, ?, ?, ? )";

    /** 
     * SQL UPDATE statement for this table.
     */
    protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET userId = ?, user_Name = ?, userPass = ?, identification = ?, realName = ?, surname = ?, userProfile = ?, state = ? WHERE userId = ?";

    /** 
     * SQL DELETE statement for this table
     */
    protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE userId = ?";
    
    /** 
     * Indexes of the columns in the user table.
     */
    protected static final int COLUMN_USER_ID = 1;
    protected static final int COLUMN_USER_NAME = 2;
    protected static final int COLUMN_USER_PASS = 3;
    protected static final int COLUMN_IDENTIFICATION = 4;
    protected static final int COLUMN_REAL_NAME = 5;
    protected static final int COLUMN_SURNAME = 6;
    protected static final int COLUMN_USER_PROFILE = 7;
    protected static final int COLUMN_STATE = 8;

    /** 
     * Number of columns.
     */
    protected static final int NUMBER_OF_COLUMNS = 8;

    /** 
     * Index of primary-key column userId
     */
    protected static final int PK_COLUMN_USER_ID = 1;

    /** 
     * Inserts a new row in the users table.
     * 
     * @param usersDto
     * @return UsersPk
     * @throws hulkstore_.model.dao.users.UsersDaoException
     */
    public UsersPk insert(UsersDto usersDto) throws UsersDaoException {
        long t1 = System.currentTimeMillis();
        // declare variables
        final boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            statement = connection.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS );
            int index = 1;
            statement.setInt( index++, usersDto.getUserId() );
            statement.setString( index++, usersDto.getUserName() );
            statement.setString( index++, usersDto.getUserPass() );
            statement.setString( index++, usersDto.getIdentification() );
            statement.setString( index++, usersDto.getRealName() );
            statement.setString( index++, usersDto.getSurname() );
            statement.setShort( index++, usersDto.getUserProfile() );
            statement.setShort( index++, usersDto.getState() );
            System.out.println( "Executing " + SQL_INSERT + " with DTO: " + usersDto );
            int rows = statement.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );

            // retrieve values from auto-increment columns
            resultSet = statement.getGeneratedKeys();
            if (resultSet != null && resultSet.next()) {
                    usersDto.setUserId( resultSet.getInt( 1 ) );
            }

            reset(usersDto);
            return usersDto.createPk();
            
        } catch (Exception exception) { throw new UsersDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }

    /** 
     * Updates a single row in the users table.
     * 
     * @param usersPk
     * @param usersDto
     * @return boolean
     * @throws hulkstore_.model.dao.users.UsersDaoException
     */
    public boolean update(UsersPk usersPk, UsersDto usersDto) throws UsersDaoException {
        // declare variables
        long t1 = System.currentTimeMillis();
        final boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            System.out.println( "Executing " + SQL_UPDATE + " with DTO: " + usersDto );
            statement = connection.prepareStatement( SQL_UPDATE );
            int index=1;
            statement.setInt( index++, usersDto.getUserId() );
            statement.setString( index++, usersDto.getUserName() );
            statement.setString( index++, usersDto.getUserPass() );
            statement.setString( index++, usersDto.getIdentification() );
            statement.setString( index++, usersDto.getRealName() );
            statement.setString( index++, usersDto.getSurname() );
            statement.setShort( index++, usersDto.getUserProfile() );
            statement.setShort( index++, usersDto.getState() );
            statement.setInt( 9, usersPk.getUserId() );
            int rows = statement.executeUpdate();
            reset(usersDto);
            long t2 = System.currentTimeMillis();
            System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
            
            return rows > 0;
            
        } catch (Exception exception) { throw new UsersDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }

    /** 
     * Deletes a single row in the users table.
     * 
     * @param usersPk
     * @return boolean
     * @throws hulkstore_.model.dao.users.UsersDaoException
     */
    public boolean delete(UsersPk usersPk) throws UsersDaoException {
        // declare variables
        long t1 = System.currentTimeMillis();
        final boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            System.out.println( "Executing " + SQL_DELETE + " with PK: " + usersPk );
            statement = connection.prepareStatement( SQL_DELETE );
            statement.setInt( 1, usersPk.getUserId() );
            int rows = statement.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
            
            return rows > 0;
        
        } catch (Exception exception) { throw new UsersDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
   }
    
    /** 
     * Returns the rows from the users table that matches the specified primary-key value.
     * 
     * @param usersPk
     * @return UsersDto
     * @throws hulkstore_.model.dao.users.UsersDaoException
     */
    public UsersDto findByPrimaryKey(UsersPk usersPk) throws UsersDaoException {
        return findByPrimaryKey( usersPk.getUserId() );
    }

    /** 
     * Returns all rows from the users table that match the criteria 'userId = :userId'.
     * 
     * @param userId
     * @return UsersDto
     * @throws hulkstore_.model.dao.users.UsersDaoException
     */
    public UsersDto findByPrimaryKey(int userId) throws UsersDaoException {
        UsersDto ret[] = findByDynamicSelect( SQL_SELECT + " WHERE userId = ?", new Object[] {userId} );
        return ret.length==0 ? null : ret[0];
    }

    /** 
     * Returns all rows from the users table that match the criteria ''.
     * 
     * @return UsersDto[]
     * @throws hulkstore_.model.dao.users.UsersDaoException
     */
    public UsersDto[] findAll() throws UsersDaoException {
        return findByDynamicSelect( SQL_SELECT + " ORDER BY userId", null );
    }

    /** 
     * Returns all rows from the users table that match the criteria 'userId = :userId'.
     * 
     * @param userId
     * @return UsersDto[]
     * @throws hulkstore_.model.dao.users.UsersDaoException
     */
    public UsersDto[] findWhereUserIdEquals(int userId) throws UsersDaoException {
        return findByDynamicSelect( SQL_SELECT + " WHERE userId = ? ORDER BY userId", new Object[] {userId} );
    }

    /**
     * Empty Constructor.
     */
    public UsersDao() {}

    /**
     * Constructor.
     * 
     * @param userConn
     */
    public UsersDao(final java.sql.Connection userConn) { this.userConn = userConn; }

    /** 
     * Sets the value of maxRows.
     * 
     * @param maxRows
     */
    public void setMaxRows(int maxRows) { this.maxRows = maxRows; }

    /** 
     * Gets the value of maxRows.
     * 
     * @return int
     */
    public int getMaxRows() { return maxRows; }

    /**
     * Returns the name of the table.
     * 
     * @return String
     */
    public String getTableName() { return "bd_Todo1_.users"; }

    /** 
     * Fetches a single row from the result set.
     * 
     * @param resultSet
     * @return UsersDto[]
     * @throws java.sql.SQLException
     */
    protected UsersDto fetchSingleResult(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            UsersDto usersDto = new UsersDto();
            populateDto( usersDto, resultSet);
            return usersDto;

        } else { return null; }		
    }

    /** 
     * Fetches multiple rows from the result set.
     * 
     * @param resultSet
     * @return 
     * @throws java.sql.SQLException
     */
    protected UsersDto[] fetchMultiResults(ResultSet resultSet) throws SQLException {
        Collection resultList = new ArrayList();
        while (resultSet.next()) {
            UsersDto usersDto = new UsersDto();
            populateDto( usersDto, resultSet);
            resultList.add( usersDto );
        }

        UsersDto ret[] = new UsersDto[ resultList.size() ];
        resultList.toArray( ret );
        return ret;
    }

    /** 
     * Populates a DTO with data from a ResultSet.
     * 
     * @param usersDto
     * @param resultSet
     * @throws java.sql.SQLException
     */
    protected void populateDto(UsersDto usersDto, ResultSet resultSet) throws SQLException {
        usersDto.setUserId( resultSet.getInt( COLUMN_USER_ID ) );
        usersDto.setUserName( resultSet.getString( COLUMN_USER_NAME ) );
        usersDto.setUserPass( resultSet.getString( COLUMN_USER_PASS ) );
        usersDto.setIdentification( resultSet.getString( COLUMN_IDENTIFICATION ) );
        usersDto.setRealName( resultSet.getString( COLUMN_REAL_NAME ) );
        usersDto.setSurname( resultSet.getString( COLUMN_SURNAME ) );
        usersDto.setUserProfile( resultSet.getShort( COLUMN_USER_PROFILE ) );
        usersDto.setState( resultSet.getShort( COLUMN_STATE ) );
    }

    /** 
     * Resets the modified attributes in the DTO.
     * 
     * @param usersDto
     */
    protected void reset(UsersDto usersDto) {}

    /** 
     * Returns all rows from the users table that match the specified arbitrary SQL statement.
     * 
     * @param sql
     * @param sqlParams
     * @return UsersDto[]
     * @throws hulkstore_.model.dao.users.UsersDaoException
     */
    public UsersDto[] findByDynamicSelect(String sql, Object[] sqlParams) throws UsersDaoException {
        // declare variables
        final boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            System.out.println( "Executing " + sql );
            // prepare statement
            statement = connection.prepareStatement( sql );
            statement.setMaxRows( maxRows );

            // bind parameters
            for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
                statement.setObject( i+1, sqlParams[i] );
            }

            resultSet = statement.executeQuery();

            // fetch the results
            return fetchMultiResults(resultSet);
            
        } catch (Exception exception) { throw new UsersDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(resultSet);
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }

    /** 
     * Returns all rows from the users table that match the specified arbitrary SQL statement.
     * 
     * @param sql
     * @param sqlParams
     * @return UsersDto[]
     * @throws hulkstore_.model.dao.users.UsersDaoException
     */
    public UsersDto[] findByDynamicWhere(String sql, Object[] sqlParams) throws UsersDaoException {
        // declare variables
        final boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            // construct the SQL statement
            final String SQL = SQL_SELECT + " WHERE " + sql;
            
            LOG.debug("Executing " + SQL);
            // prepare statement
            statement = connection.prepareStatement( SQL );
            statement.setMaxRows( maxRows );

            // bind parameters
            for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
                statement.setObject( i+1, sqlParams[i] );
            }

            resultSet = statement.executeQuery();

            // fetch the results
            return fetchMultiResults(resultSet);
        
        } catch (Exception exception) { throw new UsersDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(resultSet);
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }
    
    /**
     * Validate a user's credentials.
     * 
     * @param user_Name
     * @param userPass
     * @return UsersDto
     * @throws UsersDaoException 
     */
    public UsersDto validateUser(String user_Name, String userPass) throws UsersDaoException {
        LOG.info("Validating user...");
        UsersDto rsp[] = findByDynamicWhere( "user_Name = ? and userPass = MD5(?) and state = 1 ORDER BY userPass", new Object[] { user_Name, userPass } );          
        
        if (rsp.length != 0) {
            LOG.info("Valid user");
            return rsp[0]; 
            
        } else {
            LOG.info("Invalid user");
            return null;
        }
    }

    /**
     * Returns the next available id for the next user.
     * 
     * @return String
     * @throws UsersDaoException 
     */
    public String findNextUserId() throws UsersDaoException {
        // declare variables
        final boolean isConnSupplied = (userConn != null);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            conn = isConnSupplied ? userConn : ResourceManager.getConnection();
            
            // construct the SQL statement
            final String SQL = "SELECT LPAD((SELECT COUNT(*) + 1 FROM " + getTableName() + "), 6, '0') AS nextUserId";
            
            System.out.println( "Executing " + SQL);
            stmt = conn.prepareStatement( SQL );

            rs = stmt.executeQuery();
            rs.next();
            
            return rs.getString(1);
        
        } catch (Exception exception) { throw new UsersDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(rs);
            ResourceManager.close(stmt);
            if (!isConnSupplied) { ResourceManager.close(conn); }
        }
    }
}