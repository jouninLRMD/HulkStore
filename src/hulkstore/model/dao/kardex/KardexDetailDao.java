package hulkstore_.model.dao.inventory_;

import hulkstore_.model.dto.inventory_.KardexDetailView;
import hulkstore_.model.dto.inventory_.KardexDetailPk;
import hulkstore_.model.dto.inventory_.KardexDetailDto;
import hulkstore_.model.dao.AbstractDao;
import hulkstore_.model.dao.ResourceManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/** 
 * This class handles queries to the inventory_ detail table.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public final class KardexDetailDao extends AbstractDao
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
    protected final String SQL_SELECT = "SELECT detailId, product_Id, store_Id, inventory_DetailYear, inventory_DetailMonth, inventory_Detailday, userId, document_Id, documentNumber, operation, quantity, unity_Value, total_Value, observations, state FROM " + getTableName() + "";

    /** 
     * Finder methods will pass this value to the JDBC setMaxRows method.
     */
    protected int maxRows;

    /** 
     * SQL INSERT statement for this table.
     */
    protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( detailId, product_Id, store_Id, inventory_DetailYear, inventory_DetailMonth, inventory_Detailday, userId, document_Id, documentNumber, operation, quantity, unity_Value, total_Value, observations, state ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

    /** 
     * SQL UPDATE statement for this table.
     */
    protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET detailId = ?, product_Id = ?, store_Id = ?, inventory_DetailYear = ?, inventory_DetailMonth = ?, inventory_Detailday = ?, userId = ?, document_Id = ?, documentNumber = ?, operation = ?, quantity = ?, unity_Value = ?, total_Value = ?, observations = ?, state = ? WHERE detailId = ? AND product_Id = ? AND store_Id = ?";

    /** 
     * SQL DELETE statement for this table
     */
    protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE detailId = ? AND product_Id = ? AND store_Id = ?";
    
    /** 
     * Indexes of the columns in the inventory__detail table.
     */
    protected static final int COLUMN_DETAIL_ID = 1;
    protected static final int COLUMN_PRODUCT_ID = 2;
    protected static final int COLUMN_STORE_ID = 3;
    protected static final int COLUMN_INVENTORY_DETAIL_YEAR = 4;
    protected static final int COLUMN_INVENTORY_DETAIL_MONTH = 5;
    protected static final int COLUMN_INVENTORY_DETAILDAY = 6;
    protected static final int COLUMN_USER_ID = 7;
    protected static final int COLUMN_DOCUMENT_ID = 8;
    protected static final int COLUMN_DOCUMENT_NUMBER = 9;
    protected static final int COLUMN_OPERATION = 10;
    protected static final int COLUMN_QUANTITY = 11;
    protected static final int COLUMN_UNITY_VALUE = 12;
    protected static final int COLUMN_TOTAL_VALUE = 13;
    protected static final int COLUMN_OBSERVATIONS = 14;
    protected static final int COLUMN_STATE = 15;

    /** 
     * Number of columns in the inventory__detail table
     */
    protected static final int INVENTORY_DETAIL_COLUMNS = 15;

    /** 
     * Index of primary-key column detailId in the inventory__detail table
     */
    protected static final int PK_COLUMN_DETAIL_ID = 1;

    /** 
     * Index of primary-key column product_Id in the inventory__detail table
     */
    protected static final int PK_COLUMN_PRODUCT_ID = 2;

    /** 
     * Index of primary-key column store_Id in the inventory__detail table
     */
    protected static final int PK_COLUMN_STORE_ID = 3;

    /** 
     * Indexes of the columns in the vi_inventory_detail view.
     */
    protected static final int COLUMN_VIEW_DETAIL_ID = 1;
    protected static final int COLUMN_VIEW_INVENTORY_DETAIL_YEAR = 2;
    protected static final int COLUMN_VIEW_INVENTORY_DETAIL_MONTH = 3;
    protected static final int COLUMN_VIEW_INVENTORY_DETAILDAY = 4;
    protected static final int COLUMN_VIEW_DOCUMENT_ID = 5;
    protected static final int COLUMN_VIEW_DOCUMENT_DESCRIPTION = 6;
    protected static final int COLUMN_VIEW_DOCUMENT_NUMBER = 7;
    protected static final int COLUMN_VIEW_OPERATION = 8;
    protected static final int COLUMN_VIEW_QUANTITY = 9;
    protected static final int COLUMN_VIEW_UNITY_VALUE = 10;
    protected static final int COLUMN_VIEW_TOTAL_VALUE = 11;
    protected static final int COLUMN_VIEW_OBSERVATIONS = 12;
    protected static final int COLUMN_VIEW_PRODUCT_ID = 13;
    protected static final int COLUMN_VIEW_STORE_ID = 14;

    /** 
     * Number of columns in the vi_inventory_detail view.
     */
    protected static final int INVENTORY_DETAIL_VIEW_COLUMNS = 14;

    /** 
     * Inserts a new row in the inventory__detail table.
     * 
     * @param inventory_DetailDto
     * @return KardexDetailPk
     * @throws hulkstore_.model.dao.inventory_.KardexDetailDaoException
     */
    public KardexDetailPk insert(KardexDetailDto inventory_DetailDto) throws KardexDetailDaoException
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
            statement.setInt( index++, inventory_DetailDto.getDetailId() );
            statement.setInt( index++, inventory_DetailDto.getProductId() );
            statement.setInt( index++, inventory_DetailDto.getStoreId() );
            statement.setInt( index++, inventory_DetailDto.getKardexDetailYear() );
            statement.setInt( index++, inventory_DetailDto.getKardexDetailMonth() );
            statement.setInt( index++, inventory_DetailDto.getKardexDetailday() );
            
            if (inventory_DetailDto.isUserIdNull()) { statement.setNull( index++, java.sql.Types.INTEGER ); }
            else { statement.setInt( index++, inventory_DetailDto.getUserId() ); }

            statement.setInt( index++, inventory_DetailDto.getDocumentId() );
            statement.setInt( index++, inventory_DetailDto.getDocumentNumber() );
            statement.setShort( index++, inventory_DetailDto.getOperation() );
            statement.setDouble( index++, inventory_DetailDto.getQuantity() );
            statement.setDouble( index++, inventory_DetailDto.getUnityValue() );
            statement.setDouble( index++, inventory_DetailDto.getTotalValue() );
            statement.setString( index++, inventory_DetailDto.getObservations() );
            statement.setShort( index++, inventory_DetailDto.getState() );
            System.out.println( "Executing " + SQL_INSERT + " with DTO: " + inventory_DetailDto );
            int rows = statement.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );

            // retrieve values from auto-increment columns
            resultSet = statement.getGeneratedKeys();
            if (resultSet != null && resultSet.next()) { inventory_DetailDto.setDetailId( resultSet.getInt( 1 ) ); }

            reset(inventory_DetailDto);
            return inventory_DetailDto.createPk();
            
        } catch (Exception exception) { throw new KardexDetailDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }

    /** 
     * Updates a single row in the inventory__detail table.
     * 
     * @param inventory_DetailPk
     * @param inventory_DetailDto
     * @return boolean
     * @throws hulkstore_.model.dao.inventory_.KardexDetailDaoException 
     */
    public boolean update(KardexDetailPk inventory_DetailPk, KardexDetailDto inventory_DetailDto) throws KardexDetailDaoException
    {
        long t1 = System.currentTimeMillis();
        // declare variables
        final boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            System.out.println( "Executing " + SQL_UPDATE + " with DTO: " + inventory_DetailDto );
            statement = connection.prepareStatement( SQL_UPDATE );
            int index=1;
            statement.setInt( index++, inventory_DetailDto.getDetailId() );
            statement.setInt( index++, inventory_DetailDto.getProductId() );
            statement.setInt( index++, inventory_DetailDto.getStoreId() );
            statement.setInt( index++, inventory_DetailDto.getKardexDetailYear() );
            statement.setInt( index++, inventory_DetailDto.getKardexDetailMonth() );
            statement.setInt( index++, inventory_DetailDto.getKardexDetailday() );

            if (inventory_DetailDto.isUserIdNull()) { statement.setNull( index++, java.sql.Types.INTEGER ); }
            else { statement.setInt( index++, inventory_DetailDto.getUserId() ); }

            statement.setInt( index++, inventory_DetailDto.getDocumentId() );
            statement.setInt( index++, inventory_DetailDto.getDocumentNumber() );
            statement.setShort( index++, inventory_DetailDto.getOperation() );
            statement.setDouble( index++, inventory_DetailDto.getQuantity() );
            statement.setDouble( index++, inventory_DetailDto.getUnityValue() );
            statement.setDouble( index++, inventory_DetailDto.getTotalValue() );
            statement.setString( index++, inventory_DetailDto.getObservations() );
            statement.setShort( index++, inventory_DetailDto.getState() );
            statement.setInt( index++, inventory_DetailPk.getDetailId() );
            statement.setInt( index++, inventory_DetailPk.getProductId() );
            statement.setInt( index++, inventory_DetailPk.getStoreId() );
            int rows = statement.executeUpdate();
            reset(inventory_DetailDto);
            long t2 = System.currentTimeMillis();
            System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
            
            return rows > 0;
                    
        } catch (Exception exception) { throw new KardexDetailDaoException( "Exception: " + exception.getMessage(), exception );

        } finally {
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }
    
    /** 
     * Deletes a single row in the inventory__detail table.
     *
     * @param inventory_DetailPk
     * @return boolean
     * @throws hulkstore_.model.dao.inventory_.KardexDetailDaoException
     */
    public boolean delete(KardexDetailPk inventory_DetailPk) throws KardexDetailDaoException
    {
        // declare variables
        long t1 = System.currentTimeMillis();
        final boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            System.out.println( "Executing " + SQL_DELETE + " with PK: " + inventory_DetailPk );
            statement = connection.prepareStatement( SQL_DELETE );
            statement.setInt( 1, inventory_DetailPk.getDetailId() );
            statement.setInt( 2, inventory_DetailPk.getProductId() );
            statement.setInt( 3, inventory_DetailPk.getStoreId() );
            int rows = statement.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
            
            return rows > 0;
        
        } catch (Exception exception) { throw new KardexDetailDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }

    /** 
     * Returns a single row from the inventory__detail table that match the criteria 'product_Id = :product_Id and store_Id = :store_Id'.
     * 
     * @param product_Id
     * @param store_Id
     * @return KardexDetailDto[]
     * @throws hulkstore_.model.dao.inventory_.KardexDetailDaoException
     */
    public KardexDetailDto[] findWhereProductIdAndStoreIdEquals(int product_Id, int store_Id) throws KardexDetailDaoException
    {
        return findByDynamicSelect( SQL_SELECT + " WHERE product_Id = ? and store_Id = ?", new Object[] {product_Id, store_Id} );
    }

    /** 
     * Returns the last record of the inventory__detail table that match the criteria 'product_Id =: product_Id and store_Id =: store_Id'.
     * 
     * @param product_Id
     * @param store_Id
     * @return KardexDetailDto
     * @throws hulkstore_.model.dao.inventory_.KardexDetailDaoException 
     */
    public KardexDetailDto findLastKardexDetail(int product_Id, int store_Id) throws KardexDetailDaoException
    {
        KardexDetailDto ret[] = findByDynamicSelect( SQL_SELECT + " WHERE product_Id = ? and store_Id = ?", new Object[] {product_Id, store_Id} );
        return ret[ret.length - 1];
    }

    /**
     * Empty Constructor.
     */
    public KardexDetailDao() {}

    /**
     * Constructor.
     * 
     * @param userConn
     */
    public KardexDetailDao(final java.sql.Connection userConn) { this.userConn = userConn; }

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
    public String getTableName() { return "bd_Todo1_.inventory__detail"; }

    /** 
     * Fetches a single row from the result set.
     * 
     * @param resultSet
     * @return KardexDetailDto
     * @throws java.sql.SQLException
     */
    protected KardexDetailDto fetchSingleResult(ResultSet resultSet) throws SQLException
    {
        if (resultSet.next()) {
            KardexDetailDto dto = new KardexDetailDto();
            populateDto( dto, resultSet);
            return dto;

        } else { return null; }		
    }

    /** 
     * Fetches multiple rows from the result set.
     * 
     * @param resultSet
     * @return KardexDetailView[]
     * @throws java.sql.SQLException
     */
    protected KardexDetailView[] fetchKardexDetailView(ResultSet resultSet) throws SQLException
    {
        ArrayList resultList = fetchMultiResults(resultSet, true);   
        KardexDetailView inventory_DetailView[] = new KardexDetailView[ resultList.size() ];
        resultList.toArray( inventory_DetailView );
        return inventory_DetailView;
    }

    /** 
     * Fetches multiple rows from the result set.
     * 
     * @param resultSet
     * @return KardexDetailDto
     * @throws java.sql.SQLException
     */
    protected KardexDetailDto[] fetchKardexDetailDto(ResultSet resultSet) throws SQLException
    {
        ArrayList resultList = fetchMultiResults(resultSet, false);   
        KardexDetailDto inventory_DetailDto[] = new KardexDetailDto[ resultList.size() ];
        resultList.toArray( inventory_DetailDto );
        return inventory_DetailDto;
    }

    /** 
     * Fetches multiple rows from the result set.
     * 
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
                KardexDetailView inventory_DetailView = new KardexDetailView();
                populateKardexDetailView( inventory_DetailView, resultSet);
                resultList.add( inventory_DetailView );
            } else {
                KardexDetailDto inventory_DetailDto = new KardexDetailDto();
                populateDto( inventory_DetailDto, resultSet);
                resultList.add( inventory_DetailDto );
            }                    
        }            
        return resultList;
    }

    /** 
     * Populates a DTO with data from a ResultSet.
     * 
     * @param inventory_DetailDto
     * @param resultSet
     * @throws java.sql.SQLException
     */
    protected void populateDto(KardexDetailDto inventory_DetailDto, ResultSet resultSet) throws SQLException
    {
        inventory_DetailDto.setDetailId( resultSet.getInt( COLUMN_DETAIL_ID ) );
        inventory_DetailDto.setProductId( resultSet.getInt( COLUMN_PRODUCT_ID ) );
        inventory_DetailDto.setStoreId( resultSet.getInt( COLUMN_STORE_ID ) );
        inventory_DetailDto.setKardexDetailYear( resultSet.getInt( COLUMN_INVENTORY_DETAIL_YEAR ) );
        inventory_DetailDto.setKardexDetailMonth( resultSet.getInt( COLUMN_INVENTORY_DETAIL_MONTH ) );
        inventory_DetailDto.setKardexDetailday( resultSet.getInt( COLUMN_INVENTORY_DETAILDAY ) );
        inventory_DetailDto.setUserId( resultSet.getInt( COLUMN_USER_ID ) );
        if (resultSet.wasNull()) { inventory_DetailDto.setUserIdNull( true ); }		
        inventory_DetailDto.setDocumentId( resultSet.getInt( COLUMN_DOCUMENT_ID ) );
        inventory_DetailDto.setDocumentNumber( resultSet.getInt( COLUMN_DOCUMENT_NUMBER ) );
        inventory_DetailDto.setOperation( resultSet.getShort( COLUMN_OPERATION ) );
        inventory_DetailDto.setQuantity( resultSet.getDouble( COLUMN_QUANTITY ) );
        inventory_DetailDto.setUnityValue( resultSet.getDouble( COLUMN_UNITY_VALUE ) );
        inventory_DetailDto.setTotalValue( resultSet.getDouble( COLUMN_TOTAL_VALUE ) );
        inventory_DetailDto.setObservations( resultSet.getString( COLUMN_OBSERVATIONS ) );
        inventory_DetailDto.setState( resultSet.getShort( COLUMN_STATE ) );
    }

    /** 
     * Populates a DTO with data from a ResultSet.
     * 
     * @param inventory_DetailView
     * @param resultSet
     * @throws java.sql.SQLException
     */
    protected void populateKardexDetailView(KardexDetailView inventory_DetailView, ResultSet resultSet) throws SQLException
    {
        inventory_DetailView.setDetailId( resultSet.getInt( COLUMN_VIEW_DETAIL_ID ) );
        inventory_DetailView.setKardexDetailYear( resultSet.getInt( COLUMN_VIEW_INVENTORY_DETAIL_YEAR ) );
        inventory_DetailView.setKardexDetailMonth( resultSet.getInt( COLUMN_VIEW_INVENTORY_DETAIL_MONTH ) );
        inventory_DetailView.setKardexDetailday( resultSet.getInt( COLUMN_VIEW_INVENTORY_DETAILDAY ) );
        inventory_DetailView.setDocumentId( resultSet.getInt( COLUMN_VIEW_DOCUMENT_ID ) );
        inventory_DetailView.setDocumentDescription(resultSet.getString( COLUMN_VIEW_DOCUMENT_DESCRIPTION ) );
        inventory_DetailView.setDocumentNumber( resultSet.getInt( COLUMN_VIEW_DOCUMENT_NUMBER ) );
        inventory_DetailView.setOperation( resultSet.getShort( COLUMN_VIEW_OPERATION ) );
        inventory_DetailView.setQuantity( resultSet.getDouble( COLUMN_VIEW_QUANTITY ) );
        inventory_DetailView.setUnityValue( resultSet.getDouble( COLUMN_VIEW_UNITY_VALUE ) );
        inventory_DetailView.setTotalValue( resultSet.getDouble( COLUMN_VIEW_TOTAL_VALUE ) );
        inventory_DetailView.setObservations( resultSet.getString( COLUMN_VIEW_OBSERVATIONS ) );                
        inventory_DetailView.setProductId( resultSet.getInt( COLUMN_VIEW_PRODUCT_ID ) );
        inventory_DetailView.setStoreId( resultSet.getInt( COLUMN_VIEW_STORE_ID ) );
    }

    /** 
     * Resets the modified attributes in the DTO.
     * 
     * @param inventory_DetailDto
     */
    protected void reset(KardexDetailDto inventory_DetailDto) {}

    /** 
     * Returns all rows from the inventory__detail table that match the specified arbitrary SQL statement.
     * 
     * @param sql
     * @param sqlParams
     * @return KardexDetailDto[]
     * @throws hulkstore_.model.dao.inventory_.KardexDetailDaoException 
     */
    public KardexDetailDto[] findByDynamicSelect(String sql, Object[] sqlParams) throws KardexDetailDaoException
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
            final String SQL = sql;

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
            return fetchKardexDetailDto(resultSet);
            
        } catch (Exception exception) { throw new KardexDetailDaoException( "Exception: " + exception.getMessage(), exception );
            
        } finally {
            ResourceManager.close(resultSet);
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }

    /** 
     * Returns all rows from the inventory__detail table that match the specified arbitrary SQL statement.
     * 
     * @param sql
     * @param sqlParams
     * @return KardexDetailDto[]
     * @throws hulkstore_.model.dao.inventory_.KardexDetailDaoException
     */
    public KardexDetailDto[] findByDynamicWhere(String sql, Object[] sqlParams) throws KardexDetailDaoException
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
            return fetchKardexDetailDto(resultSet);
            
        } catch (Exception exception) { throw new KardexDetailDaoException( "Exception: " + exception.getMessage(), exception );
            
        } finally {
            ResourceManager.close(resultSet);
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }
        
    /** 
     * Returns the next available id for the next record.
     * 
     * @return String
     * @throws hulkstore_.model.dao.inventory_.KardexDetailDaoException
     */
    public String findNextDetailId() throws KardexDetailDaoException
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
            final String SQL = "SELECT LPAD((SELECT COUNT(*) + 1 FROM " + getTableName() + "), 6, '0') AS nextDetailId";

            System.out.println( "Executing " + SQL);
            statement = connection.prepareStatement( SQL );

            resultSet = statement.executeQuery();
            resultSet.next();

            return resultSet.getString(1);
        
        } catch (Exception exception) { throw new KardexDetailDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(resultSet);
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }
        
    /**
     * Returns all rows from the inventory_ detail view.
     * 
     * @param product_Id
     * @param store_Id
     * @return KardexDetailView[]
     * @throws KardexDaoException 
     */
    public KardexDetailView[] getKardexDetailView(int product_Id, int store_Id) throws KardexDaoException
    {
        // declare variables
        final boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement Statement = null;
        ResultSet resultSet = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            // construct the SQL statement
            final String SQL = "SELECT * FROM bd_Todo1_.vi_inventory_detail WHERE product_Id = " + product_Id + " and store_Id = " + store_Id;


            System.out.println( "Executing " + SQL );
            // prepare statement
            Statement = connection.prepareStatement( SQL );
            Statement.setMaxRows( maxRows );

            resultSet = Statement.executeQuery();

            // fetch the results
            return fetchKardexDetailView(resultSet);

        } catch (Exception exception) { throw new KardexDaoException( "Exception: " + exception.getMessage(), exception );

        } finally {
            ResourceManager.close(resultSet);
            ResourceManager.close(Statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }
}