package hulkstore_.model.dao.unity_;

import hulkstore_.model.dao.AbstractDao;
import hulkstore_.model.dao.ResourceManager;
import hulkstore_.model.dto.unity_.UnityDto;
import hulkstore_.model.dto.unity_.UnityPk;
import java.sql.Connection;
import java.util.Collection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/** 
 * This class handles queries to the unity_ table.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class UnityDao extends AbstractDao
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
    protected final String SQL_SELECT = "SELECT unity_Id, unity_Description, state FROM " + getTableName() + "";

    /** 
     * Finder methods will pass this value to the JDBC setMaxRows method.
     */
    protected int maxRows;

    /** 
     * SQL INSERT statement for this table.
     */
    protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( unity_Id, unity_Description, state ) VALUES ( ?, ?, ? )";

    /** 
     * SQL UPDATE statement for this table.
     */
    protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET unity_Id = ?, unity_Description = ?, state = ? WHERE unity_Id = ?";
    
    /** 
     * SQL DELETE statement for this table
     */
    protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE unity_Id = ?";

    /** 
     * Indexes of the columns in the unity_ table.
     */
    protected static final int COLUMN_UNITY_ID = 1;
    protected static final int COLUMN_UNITY_DESCRIPTION = 2;
    protected static final int COLUMN_STATE = 3;

    /** 
     * Number of columns.
     */
    protected static final int NUMBER_OF_COLUMNS = 3;

    /** 
     * Index of primary-key column unity_Id.
     */
    protected static final int PK_COLUMN_UNITY_ID = 1;

    /** 
     * Inserts a new row in the unity_ table.
     * 
     * @param unity_Dto
     * @return UnityPk
     * @throws hulkstore_.model.dao.unity_.UnityDaoException 
     */
    public UnityPk insert(UnityDto unity_Dto) throws UnityDaoException
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
            statement.setInt( index++, unity_Dto.getUnityId() );
            statement.setString( index++, unity_Dto.getUnityDescription() );
            statement.setShort( index++, unity_Dto.getState() );
            System.out.println( "Executing " + SQL_INSERT + " with DTO: " + unity_Dto );
            int rows = statement.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );

            // retrieve values from auto-increment columns
            resultSet = statement.getGeneratedKeys();
            if (resultSet != null && resultSet.next()) {
                unity_Dto.setUnityId( resultSet.getInt( 1 ) );
            }

            reset(unity_Dto);
            return unity_Dto.createPk();
            
        } catch (Exception exception) { throw new UnityDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }

    /** 
     * Updates a single row in the unity_ table.
     * 
     * @param unity_Pk
     * @param unity_Dto
     * @return boolean
     * @throws hulkstore_.model.dao.unity_.UnityDaoException
     */
    public boolean update(UnityPk unity_Pk, UnityDto unity_Dto) throws UnityDaoException
    {
        // declare variables
        long t1 = System.currentTimeMillis();
        final boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            System.out.println( "Executing " + SQL_UPDATE + " with DTO: " + unity_Dto );
            statement = connection.prepareStatement( SQL_UPDATE );
            int index=1;
            statement.setInt( index++, unity_Dto.getUnityId() );
            statement.setString( index++, unity_Dto.getUnityDescription() );
            statement.setShort( index++, unity_Dto.getState() );
            statement.setInt( index++, unity_Pk.getUnityId() );
            int rows = statement.executeUpdate();
            reset(unity_Dto);
            long t2 = System.currentTimeMillis();
            System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
            
            return rows > 0;
        
        } catch (Exception exception) { throw new UnityDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }
    
    /** 
     * Deletes a single row in the unity_ table.
     * 
     * @param unity_Pk
     * @return boolean
     * @throws hulkstore_.model.dao.unity_.UnityDaoException
     */
    public boolean delete(UnityPk unity_Pk) throws UnityDaoException
    {
        // declare variables
        long t1 = System.currentTimeMillis();
        final boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            System.out.println( "Executing " + SQL_DELETE + " with PK: " + unity_Pk );
            statement = connection.prepareStatement( SQL_DELETE );
            statement.setInt( 1, unity_Pk.getUnityId() );
            int rows = statement.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
            
            return rows > 0;
        
        } catch (Exception exception) { throw new UnityDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
   }

    /** 
     * Returns the rows from the unity_ table that matches the specified primary-key value.
     * 
     * @param unity_Pk
     * @return UnityDto
     * @throws hulkstore_.model.dao.unity_.UnityDaoException
     */
    public UnityDto findByPrimaryKey(UnityPk unity_Pk) throws UnityDaoException
    {
        return findByPrimaryKey( unity_Pk.getUnityId() );
    }

    /** 
     * Returns all rows from the unity_ table that match the criteria 'unity_Id = :unity_Id'.
     * 
     * @param unity_Id
     * @return UnityDto
     * @throws hulkstore_.model.dao.unity_.UnityDaoException
     */
    public UnityDto findByPrimaryKey(int unity_Id) throws UnityDaoException
    {
        UnityDto ret[] = findByDynamicSelect( SQL_SELECT + " WHERE unity_Id = ?", new Object[] {unity_Id} );
        return ret.length==0 ? null : ret[0];
    }

    /** 
     * Returns all rows from the unity_ table that match the criteria ''.
     * 
     * @return UnityDto[]
     * @throws hulkstore_.model.dao.unity_.UnityDaoException
     */
    public UnityDto[] findAll() throws UnityDaoException
    {
        return findByDynamicSelect( SQL_SELECT + " ORDER BY unity_Id", null );
    }

    /** 
     * Returns all rows from the unity_ table that match the criteria 'state = :state'.
     * 
     * @param state
     * @return UnityDto[]
     * @throws hulkstore_.model.dao.unity_.UnityDaoException
     */
    public UnityDto[] findWhereStateEquals(short state) throws UnityDaoException
    {
        return findByDynamicSelect( SQL_SELECT + " WHERE state = ? ORDER BY state", new Object[] {state} );
    }

    /**
     * Empty Constructor.
     * 
     */
    public UnityDao() {}

    /**
     * Constructor.
     * 
     * @param userConn
     */
    public UnityDao(final java.sql.Connection userConn) { this.userConn = userConn; }

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
    public String getTableName() { return "bd_Todo1_.unity_"; }

    /** 
     * Fetches a single row from the result set.
     * 
     * @param resultSet
     * @return UnityDto
     * @throws java.sql.SQLException
     */
    protected UnityDto fetchSingleResult(ResultSet resultSet) throws SQLException
    {
        if (resultSet.next()) {
                UnityDto unity_Dto = new UnityDto();
                populateDto( unity_Dto, resultSet);
                return unity_Dto;
                
        } else { return null; }
    }

    /** 
     * Fetches multiple rows from the result set.
     * 
     * @param resultSet
     * @return UnityDto[]
     * @throws java.sql.SQLException
     */
    protected UnityDto[] fetchMultiResults(ResultSet resultSet) throws SQLException
    {
        Collection resultList = new ArrayList();
        while (resultSet.next()) 
        {
            UnityDto unity_Dto = new UnityDto();
            populateDto( unity_Dto, resultSet);
            resultList.add( unity_Dto );
        }

        UnityDto respose[] = new UnityDto[ resultList.size() ];
        resultList.toArray( respose );
        return respose;
    }

    /** 
     * Populates a DTO with data from a ResultSet.
     * 
     * @param unity_Dto
     * @param resultSet
     * @throws java.sql.SQLException
     */
    protected void populateDto(UnityDto unity_Dto, ResultSet resultSet) throws SQLException
    {
        unity_Dto.setUnityId( resultSet.getInt( COLUMN_UNITY_ID ) );
        unity_Dto.setUnityDescription( resultSet.getString( COLUMN_UNITY_DESCRIPTION ) );
        unity_Dto.setState( resultSet.getShort( COLUMN_STATE ) );
    }

    /** 
     * Resets the modified attributes in the DTO.
     * 
     * @param unity_Dto
     */
    protected void reset(UnityDto unity_Dto) {}

    /** 
     * Returns all rows from the unity_ table that match the specified arbitrary SQL statement.
     * 
     * @param sql
     * @param sqlParams
     * @return UnityDto[]
     * @throws hulkstore_.model.dao.unity_.UnityDaoException
     */
    public UnityDto[] findByDynamicSelect(String sql, Object[] sqlParams) throws UnityDaoException
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
            
        } catch (Exception exception) { throw new UnityDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(resultSet);
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }

    /** 
     * Returns all rows from the unity_ table that match the specified arbitrary SQL statement.
     * 
     * @param sql
     * @param sqlParams
     * @return UnityDto[]
     * @throws hulkstore_.model.dao.unity_.UnityDaoException
     */
    public UnityDto[] findByDynamicWhere(String sql, Object[] sqlParams) throws UnityDaoException
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
            
        } catch (Exception exception) { throw new UnityDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(resultSet);
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }

    /**
     * Returns the next available id for the next unity_.
     * 
     * @return String
     * @throws UnityDaoException 
     */
    public String findNextUnityId() throws UnityDaoException {
        // declare variables
        final boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();
            
            // construct the SQL statement
            final String SQL = "SELECT LPAD((SELECT COUNT(*) + 1 FROM " + getTableName() + "), 6, '0') AS nextUnityId";
            
            System.out.println( "Executing " + SQL);
            statement = connection.prepareStatement( SQL );

            resultSet = statement.executeQuery();
            resultSet.next();
            
            return resultSet.getString(1);
            
        } catch (Exception exception) { throw new UnityDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(resultSet);
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }
}