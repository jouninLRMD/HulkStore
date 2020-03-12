package hulkstore_.model.dao.inventory_;

import hulkstore_.model.dto.inventory_.KardexDto;
import hulkstore_.model.dto.inventory_.KardexPk;
import hulkstore_.model.dto.inventory_.KardexView;
import hulkstore_.model.dao.AbstractDao;
import hulkstore_.model.dao.ResourceManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/** 
 * This class handles queries to the inventory_ table.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public final class KardexDao extends AbstractDao
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
    protected final String SQL_SELECT = "SELECT product_Id, store_Id, quantity, unity_Value, total_Value, state FROM " + getTableName() + "";

    /** 
     * Finder methods will pass this value to the JDBC setMaxRows method.
     */
    protected int maxRows;

    /** 
     * SQL INSERT statement for this table.
     */
    protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( product_Id, store_Id, quantity, unity_Value, total_Value, state ) VALUES ( ?, ?, ?, ?, ?, ? )";

    /** 
     * SQL UPDATE statement for this table.
     */
    protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET product_Id = ?, store_Id = ?, quantity = ?, unity_Value = ?, total_Value = ?, state = ? WHERE product_Id = ? AND store_Id = ?";

    /** 
     * SQL DELETE statement for this table
     */
    protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE product_Id = ? AND store_Id = ?";
    
    /** 
     * Indexes of the columns in the inventory_ table.
     */
    protected static final int COLUMN_PRODUCT_ID = 1;
    protected static final int COLUMN_STORE_ID = 2;
    protected static final int COLUMN_QUANTITY = 3;
    protected static final int COLUMN_UNITY_VALUE = 4;
    protected static final int COLUMN_TOTAL_VALUE = 5;
    protected static final int COLUMN_STATE = 6;

    /** 
     * Number of columns in the inventory_ table.
     */
    protected static final int NUMBER_OF_COLUMNS = 6;


    /** 
     * Index of primary-key column product_Id.
     */
    protected static final int PK_COLUMN_PRODUCT_ID = 1;

    /** 
     * Index of primary-key column store_Id.
     */
    protected static final int PK_COLUMN_STORE_ID = 2;
    
    /** 
     * Indexes of the columns in the inventory_ view.
     */
    protected static final int COLUMN_VIEW_PRODUCT_ID = 1;
    protected static final int COLUMN_VIEW_PRODUCT_NAME = 2;
    protected static final int COLUMN_VIEW_UNITY_DESCRIPTION = 3;
    protected static final int COLUMN_VIEW_STORE_ID = 4;
    protected static final int COLUMN_VIEW_STORE_NAME = 5;
    protected static final int COLUMN_VIEW_QUANTITY = 6;
    protected static final int COLUMN_VIEW_UNITY_VALUE = 7;

    /** 
     * Number of columns in the vi_inventory_ view.
     */
    protected static final int INVENTORY_VIEW_COLUMNS = 14;

    /** 
     * Inserts a new row in the inventory_ table.
     * 
     * @param inventory_Dto
     * @return KardexPk
     * @throws hulkstore_.model.dao.inventory_.KardexDaoException
     */
    public KardexPk insert(KardexDto inventory_Dto) throws KardexDaoException
    {
        // declare variables
        long t1 = System.currentTimeMillis();
        boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            statement = connection.prepareStatement( SQL_INSERT );
            int index = 1;
            statement.setInt( index++, inventory_Dto.getProductId() );
            statement.setInt( index++, inventory_Dto.getStoreId() );
            statement.setDouble( index++, inventory_Dto.getQuantity() );
            statement.setDouble( index++, inventory_Dto.getUnityValue() );
            
            if (inventory_Dto.isTotalValueNull()) { statement.setNull( index++, java.sql.Types.DOUBLE ); } 
            else { statement.setDouble( index++, inventory_Dto.getTotalValue() ); }

            statement.setShort( index++, inventory_Dto.getState() );
            System.out.println( "Executing " + SQL_INSERT + " with DTO: " + inventory_Dto );
            int rows = statement.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
            reset(inventory_Dto);
            return inventory_Dto.createPk();
        
        } catch (SQLException exception) { throw new KardexDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }

    /** 
     * Updates a single row in the inventory_ table.
     * 
     * @param inventory_Pk
     * @param inventory_Dto
     * @return boolean
     * @throws hulkstore_.model.dao.inventory_.KardexDaoException
     */
    public boolean update(KardexPk inventory_Pk, KardexDto inventory_Dto) throws KardexDaoException
    {
        long t1 = System.currentTimeMillis();
        // declare variables
        boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            System.out.println( "Executing " + SQL_UPDATE + " with DTO: " + inventory_Dto );
            statement = connection.prepareStatement( SQL_UPDATE );
            int index=1;
            statement.setInt( index++, inventory_Dto.getProductId() );
            statement.setInt( index++, inventory_Dto.getStoreId() );
            statement.setDouble( index++, inventory_Dto.getQuantity() );
            statement.setDouble( index++, inventory_Dto.getUnityValue() );

            if (inventory_Dto.isTotalValueNull()) { statement.setNull( index++, java.sql.Types.DOUBLE ); }
            else { statement.setDouble( index++, inventory_Dto.getTotalValue() ); }

            statement.setShort( index++, inventory_Dto.getState() );
            statement.setInt( 7, inventory_Pk.getProductId() );
            statement.setInt( 8, inventory_Pk.getStoreId() );
            int rows = statement.executeUpdate();
            reset(inventory_Dto);
            long t2 = System.currentTimeMillis();
            System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
            
            return rows > 0;
            
        } catch (SQLException exception) { throw new KardexDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }
    
    /** 
     * Deletes a single row in the inventory_ table.
     * 
     * @param inventory_Pk
     * @return boolean
     * @throws hulkstore_.model.dao.inventory_.KardexDaoException
     */
    public boolean delete(KardexPk inventory_Pk) throws KardexDaoException
    {
        // declare variables
        long t1 = System.currentTimeMillis();
        final boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            System.out.println( "Executing " + SQL_DELETE + " with PK: " + inventory_Pk );
            statement = connection.prepareStatement( SQL_DELETE );
            statement.setInt( 1, inventory_Pk.getProductId() );
            statement.setInt( 2, inventory_Pk.getStoreId() );
            int rows = statement.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
            
            return rows > 0;
        
        } catch (SQLException exception) { throw new KardexDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
   }

    /** 
     * Returns all rows from the inventory_ table that match the criteria ''.
     * 
     * @return KardexDto
     * @throws hulkstore_.model.dao.inventory_.KardexDaoException
     */
    public KardexDto[] findAll() throws KardexDaoException
    {
        return findByDynamicSelect( SQL_SELECT + " ORDER BY product_Id, store_Id", null );
    }

    /**
     * Empmty Constructor.
     * 
     */
    public KardexDao() {}

    /**
     * Constructor.
     * 
     * @param userConn
     */
    public KardexDao(final java.sql.Connection userConn) { this.userConn = userConn; }

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
    public String getTableName() { return "bd_Todo1_.inventory_"; }

    /** 
     * Fetches multiple rows from the result set.
     * 
     * @param rs
     * @return KardexView
     * @throws java.sql.SQLException
     */
    protected KardexView[] fetchKardexView(ResultSet rs) throws SQLException
    {
        ArrayList resultList = fetchMultiResults(rs, true);   
        KardexView inventory_View[] = new KardexView[ resultList.size() ];
        resultList.toArray( inventory_View );
        return inventory_View;
    }

    /** 
     * Fetches multiple rows from the result set.
     * 
     * @param resultSet
     * @return KardexDto
     * @throws java.sql.SQLException
     */
    protected KardexDto[] fetchKardexDto(ResultSet resultSet) throws SQLException
    {
        ArrayList resultList = fetchMultiResults(resultSet, false);   
        KardexDto inventory_Dto[] = new KardexDto[ resultList.size() ];
        resultList.toArray( inventory_Dto );
        return inventory_Dto;
    }

    /** 
     * Fetches multiple rows from the result set
     * @param resultSet
     * @param view
     * @return ArrayList
     * @throws java.sql.SQLException
     */
    protected ArrayList fetchMultiResults(ResultSet resultSet, boolean view) throws SQLException
    {
        ArrayList resultList = new ArrayList();

        while (resultSet.next()) {

            if(view) {
                KardexView inventory_View = new KardexView();
                populateKardexView( inventory_View, resultSet);
                resultList.add( inventory_View );
            } else {
                KardexDto inventory_Dto = new KardexDto();
                populateDto( inventory_Dto, resultSet);
                resultList.add( inventory_Dto );
            }
        }

        return resultList;
    }

    /** 
     * Populates a DTO with data from a ResultSet.
     * 
     * @param inventory_Dto
     * @param resultSet
     * @throws java.sql.SQLException
     */
    protected void populateDto(KardexDto inventory_Dto, ResultSet resultSet) throws SQLException
    {
        inventory_Dto.setProductId( resultSet.getInt( COLUMN_PRODUCT_ID ) );
        inventory_Dto.setStoreId( resultSet.getInt( COLUMN_STORE_ID ) );
        inventory_Dto.setQuantity( resultSet.getDouble( COLUMN_QUANTITY ) );
        inventory_Dto.setUnityValue( resultSet.getDouble( COLUMN_UNITY_VALUE ) );
        inventory_Dto.setTotalValue( resultSet.getDouble( COLUMN_TOTAL_VALUE ) );
        inventory_Dto.setState( resultSet.getShort( COLUMN_STATE ) );            
        if (resultSet.wasNull()) { inventory_Dto.setTotalValueNull( true ); }
    }

    /** 
     * Populates a DTO with data from a ResultSet.
     * 
     * @param inventory_View
     * @param resultSet
     * @throws java.sql.SQLException
     */
    protected void populateKardexView(KardexView inventory_View, ResultSet resultSet) throws SQLException
    {
        inventory_View.setProductId( resultSet.getInt( COLUMN_VIEW_PRODUCT_ID ) );
        inventory_View.setProductName(resultSet.getString( COLUMN_VIEW_PRODUCT_NAME ) );
        inventory_View.setStoreId( resultSet.getInt( COLUMN_VIEW_STORE_ID ) );
        inventory_View.setStoreName(resultSet.getString( COLUMN_VIEW_STORE_NAME ) );
        inventory_View.setQuantity( resultSet.getDouble( COLUMN_VIEW_QUANTITY ) );
        inventory_View.setUnityDescription(resultSet.getString( COLUMN_VIEW_UNITY_DESCRIPTION ) );
        inventory_View.setUnityValue( resultSet.getDouble( COLUMN_VIEW_UNITY_VALUE ) );	
    }

    /** 
     * Resets the modified attributes in the DTO.
     * 
     * @param inventory_Dto
     */
    protected void reset(KardexDto inventory_Dto) {}

    /** 
     * Returns all rows from the inventory_ table that match the specified arbitrary SQL statement.
     * 
     * @param sql
     * @param sqlParams
     * @return KardexDto
     * @throws hulkstore_.model.dao.inventory_.KardexDaoException
     */
    public KardexDto[] findByDynamicSelect(String sql, Object[] sqlParams) throws KardexDaoException
    {
        // declare variables
        boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resulSet = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            System.out.println( "Executing " + sql );
            // prepare statement
            statement = connection.prepareStatement( sql );
            statement.setMaxRows( maxRows );

            // bind parameters
            for (int i = 0; sqlParams != null && i < sqlParams.length; i++ ) {
                statement.setObject( i + 1, sqlParams[i] );
            }

            resulSet = statement.executeQuery();

            // fetch the results
            return fetchKardexDto(resulSet);
        
        } catch (SQLException exception) { throw new KardexDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(resulSet);
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }

    /** 
     * Returns all rows from the inventory_ table that match the specified arbitrary SQL statement.
     * 
     * @param sql
     * @param sqlParams
     * @return KardexDto
     * @throws hulkstore_.model.dao.inventory_.KardexDaoException
     */
    public KardexDto[] findByDynamicWhere(String sql, Object[] sqlParams) throws KardexDaoException
    {
        // declare variables
        boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resulSet = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            // construct the SQL statement
            String SQL = SQL_SELECT + " WHERE " + sql;

            System.out.println( "Executing " + SQL );
            // prepare statement
            statement = connection.prepareStatement( SQL );
            statement.setMaxRows( maxRows );

            // bind parameters
            for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
                    statement.setObject( i+1, sqlParams[i] );
            }

            resulSet = statement.executeQuery();

            // fetch the results
            return fetchKardexDto(resulSet);
            
        } catch (SQLException exception) { throw new KardexDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(resulSet);
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }

    /** 
     * Returns all rows from the inventory_ view.
     * 
     * @param product_Id
     * @param store_Id
     * @return KardexView
     * @throws hulkstore_.model.dao.inventory_.KardexDaoException
     */
    public KardexView[] getKardexView(int product_Id, int store_Id) throws KardexDaoException {
        // declare variables
        boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            // construct the SQL statement
            String SQL = "SELECT * FROM bd_Todo1_.vi_inventory_ WHERE product_Id = " + product_Id + " and store_Id = " + store_Id;


            System.out.println( "Executing " + SQL );
            // prepare statement
            statement = connection.prepareStatement( SQL );
            statement.setMaxRows( maxRows );

            resultSet = statement.executeQuery();

            // fetch the results
            return fetchKardexView(resultSet);
            
        } catch (SQLException exception) { throw new KardexDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(resultSet);
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }
}