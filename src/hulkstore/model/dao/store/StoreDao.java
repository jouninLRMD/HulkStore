package hulkstore_.model.dao.store_;

import hulkstore_.model.dao.AbstractDao;
import hulkstore_.model.dao.ResourceManager;
import hulkstore_.model.dto.store_.StoreDto;
import hulkstore_.model.dto.store_.StorePk;
import java.sql.Connection;
import java.util.Collection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/** 
 * This class handles queries to the store_ table.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class StoreDao extends AbstractDao
{
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
    protected final String SQL_SELECT = "SELECT store_Id, store_Name, address, state FROM " + getTableName() + "";

    /** 
     * Finder methods will pass this value to the JDBC setMaxRows method.
     */
    protected int maxRows;

    /** 
     * SQL INSERT statement for this table.
     */
    protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( store_Id, store_Name, address, state ) VALUES ( ?, ?, ?, ? )";

    /** 
     * SQL UPDATE statement for this table.
     */
    protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET store_Id = ?, store_Name = ?, address = ?, state = ? WHERE store_Id = ?";
    
    /** 
     * SQL DELETE statement for this table
     */
    protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE store_Id = ?";

    /** 
     * Indexes of the columns in the store_ table.
     */
    protected static final int COLUMN_STORE_ID = 1;
    protected static final int COLUMN_STORE_NAME = 2;
    protected static final int COLUMN_ADDRESS = 3;
    protected static final int COLUMN_STATE = 4;

    /** 
     * Number of columns
     */
    protected static final int NUMBER_OF_COLUMNS = 4;

    /** 
     * Index of primary-key column store_Id
     */
    protected static final int PK_COLUMN_STORE_ID = 1;

    /** 
     * Inserts a new row in the store_ table.
     * @param store_Dto
     * @return StorePk
     * @throws hulkstore_.model.dao.store_.StoreDaoException
     */
    public StorePk insert(StoreDto store_Dto) throws StoreDaoException
    {
        // declare variables
        long t1 = System.currentTimeMillis();
        final boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            statement = connection.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS );
            int index = 1;
            statement.setInt( index++, store_Dto.getStoreId() );
            statement.setString( index++, store_Dto.getStoreName() );
            statement.setString( index++, store_Dto.getAddress() );
            statement.setShort( index++, store_Dto.getState() );
            System.out.println( "Executing " + SQL_INSERT + " with DTO: " + store_Dto );
            int rows = statement.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );

            // retrieve values from auto-increment columns
            resultSet = statement.getGeneratedKeys();
            if (resultSet != null && resultSet.next()) {
                store_Dto.setStoreId( resultSet.getInt( 1 ) );
            }

            reset(store_Dto);
            return store_Dto.createPk();
        
        } catch (Exception exception) { throw new StoreDaoException( "Exception: " + exception.getMessage(), exception ); 
        
        } finally {
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }

    /** 
     * Updates a single row in the store_ table.
     * @param store_Pk
     * @param store_Dto
     * @return boolean
     * @throws hulkstore_.model.dao.store_.StoreDaoException
     */
    public boolean update(StorePk store_Pk, StoreDto store_Dto) throws StoreDaoException
    {
        // declare variables
        long t1 = System.currentTimeMillis();
        final boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            System.out.println( "Executing " + SQL_UPDATE + " with DTO: " + store_Dto );
            statement = connection.prepareStatement( SQL_UPDATE );
            int index=1;
            statement.setInt( index++, store_Dto.getStoreId() );
            statement.setString( index++, store_Dto.getStoreName() );
            statement.setString( index++, store_Dto.getAddress() );
            statement.setShort( index++, store_Dto.getState() );
            statement.setInt( 5, store_Pk.getStoreId() );
            int rows = statement.executeUpdate();
            reset(store_Dto);
            long t2 = System.currentTimeMillis();
            System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
            
            return rows > 0;
            
        } catch (Exception exception) { throw new StoreDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }

    /** 
     * Deletes a single row in the store_ table.
     * 
     * @param store_Pk
     * @return boolean
     * @throws hulkstore_.model.dao.store_.StoreDaoException
     */
    public boolean delete(StorePk store_Pk) throws StoreDaoException
    {
        // declare variables
        long t1 = System.currentTimeMillis();
        final boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            System.out.println( "Executing " + SQL_DELETE + " with PK: " + store_Pk );
            statement = connection.prepareStatement( SQL_DELETE );
            statement.setInt( 1, store_Pk.getStoreId() );
            int rows = statement.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
            
            return rows > 0;
        
        } catch (Exception exception) { throw new StoreDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }

    }

    /** 
     * Returns the rows from the store_ table that matches the specified primary-key value.
     * 
     * @param store_Pk
     * @return StoreDto
     * @throws hulkstore_.model.dao.store_.StoreDaoException
     */
    public StoreDto findByPrimaryKey(StorePk store_Pk) throws StoreDaoException
    {
        return findByPrimaryKey( store_Pk.getStoreId() );
    }

    /** 
     * Returns all rows from the store_ table that match the criteria 'store_Id = :store_Id'.
     * 
     * @param store_Id
     * @return StoreDto
     * @throws hulkstore_.model.dao.store_.StoreDaoException
     */
    public StoreDto findByPrimaryKey(int store_Id) throws StoreDaoException
    {
        StoreDto ret[] = findByDynamicSelect( SQL_SELECT + " WHERE store_Id = ?", new Object[] {store_Id} );
        return ret.length==0 ? null : ret[0];
    }

    /** 
     * Returns all rows from the store_ table that match the criteria ''.
     * 
     * @return StoreDto[]
     * @throws hulkstore_.model.dao.store_.StoreDaoException
     */
    public StoreDto[] findAll() throws StoreDaoException
    {
        return findByDynamicSelect( SQL_SELECT + " ORDER BY state ASC, store_Id ASC", null );
    }

    /**
     * Empty Constructor.
     */
    public StoreDao() {}

    /**
     * Constructor.
     * 
     * @param userConn
     */
    public StoreDao(final java.sql.Connection userConn) { this.userConn = userConn; }

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
    public String getTableName() { return "bd_Todo1_.store_"; }

    /** 
     * Fetches a single row from the result set.
     * 
     * @param resultSet
     * @return StoreDto
     * @throws java.sql.SQLException
     */
    protected StoreDto fetchSingleResult(ResultSet resultSet) throws SQLException
    {
        if (resultSet.next()) {
            StoreDto store_Dto = new StoreDto();
            populateDto( store_Dto, resultSet);
            return store_Dto;
            
        } else { return null; }
    }

    /** 
     * Fetches multiple rows from the result set.
     * 
     * @param resultSet
     * @return StoreDto[]
     * @throws java.sql.SQLException
     */
    protected StoreDto[] fetchMultiResults(ResultSet resultSet) throws SQLException
    {
        Collection resultList = new ArrayList();
        while (resultSet.next()) {
            StoreDto store_Dto = new StoreDto();
            populateDto( store_Dto, resultSet);
            resultList.add( store_Dto );
        }

        StoreDto response[] = new StoreDto[ resultList.size() ];
        resultList.toArray( response );
        return response;
    }

    /** 
     * Populates a DTO with data from a ResultSet.
     * 
     * @param store_Dto
     * @param resultSet
     * @throws java.sql.SQLException
     */
    protected void populateDto(StoreDto store_Dto, ResultSet resultSet) throws SQLException
    {
        store_Dto.setStoreId( resultSet.getInt( COLUMN_STORE_ID ) );
        store_Dto.setStoreName( resultSet.getString( COLUMN_STORE_NAME ) );
        store_Dto.setAddress( resultSet.getString( COLUMN_ADDRESS ) );
        store_Dto.setState( resultSet.getShort( COLUMN_STATE ) );
    }

    /** 
     * Resets the modified attributes in the DTO.
     * 
     * @param store_Dto
     */
    protected void reset(StoreDto store_Dto) {}

    /** 
     * Returns all rows from the store_ table that match the specified arbitrary SQL statement.
     * 
     * @param sql
     * @param sqlParams
     * @return StoreDto[]
     * @throws hulkstore_.model.dao.store_.StoreDaoException
     */
    public StoreDto[] findByDynamicSelect(String sql, Object[] sqlParams) throws StoreDaoException
    {
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
            
        } catch (Exception exception) { throw new StoreDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(resultSet);
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }

    /** 
     * Returns all rows from the store_ table that match the specified arbitrary SQL statement.
     * 
     * @param sql
     * @param sqlParams
     * @return StoreDto[]
     * @throws hulkstore_.model.dao.store_.StoreDaoException
     */
    public StoreDto[] findByDynamicWhere(String sql, Object[] sqlParams) throws StoreDaoException
    {
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

            System.out.println( "Executing " + SQL );
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
            
        } catch (Exception exception) { throw new StoreDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(resultSet);
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }

    /**
     * Returns the next available id for the next store_.
     * 
     * @return String
     * @throws StoreDaoException 
     */
    public String findNextStoreId() throws StoreDaoException {
        // declare variables
        final boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();
            
            // construct the SQL statement
            final String SQL = "SELECT LPAD((SELECT COUNT(*) + 1 FROM " + getTableName() + "), 6, '0') AS nextStoredId";
            
            System.out.println( "Executing " + SQL);
            statement = connection.prepareStatement( SQL );

            resultSet = statement.executeQuery();
            resultSet.next();
            
            return resultSet.getString(1);
            
        } catch (Exception exception) { throw new StoreDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(resultSet);
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }

    /**
     * Returns all rows from the store_ view.
     * 
     * @return StoreDto[]
     * @throws StoreDaoException 
     */
    public StoreDto[] getViewStore() throws StoreDaoException {
        // declare variables
        final boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            // construct the SQL statement
            final String SQL = "SELECT * FROM bd_Todo1_.vi_store_";

            System.out.println( "Executing " + SQL );
            // prepare statement
            statement = connection.prepareStatement( SQL );
            statement.setMaxRows( maxRows );

            resultSet = statement.executeQuery();

            // fetch the results
            return fetchMultiResults(resultSet);
        
        } catch (Exception exception) { throw new StoreDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(resultSet);
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }
}