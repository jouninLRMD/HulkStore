package hulkstore_.model.dao.product_;

import hulkstore_.model.dao.AbstractDao;
import hulkstore_.model.dao.ResourceManager;
import hulkstore_.model.dto.product_.ProductDto;
import hulkstore_.model.dto.product_.ProductPk;
import hulkstore_.model.dto.product_.ProductView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/** 
 * This class handles queries to the product_ table.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class ProductDao extends AbstractDao
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
    protected final String SQL_SELECT = "SELECT product_Id, product_Name, unity_Id, state FROM " + getTableName() + "";

    /** 
     * Finder methods will pass this value to the JDBC setMaxRows method.
     */
    protected int maxRows;

    /** 
     * SQL INSERT statement for this table.
     */
    protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( product_Id, product_Name, unity_Id, state ) VALUES ( ?, ?, ?, ? )";

    /** 
     * SQL UPDATE statement for this table.
     */
    protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET product_Id = ?, product_Name = ?, unity_Id = ?, state = ? WHERE product_Id = ?";
    
    /** 
     * SQL DELETE statement for this table
     */
    protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE product_Id = ?";

    /** 
     * Indexes of the columns in the product_ table.
     */
    protected static final int COLUMN_PRODUCT_ID = 1;
    protected static final int COLUMN_PRODUCT_NAME = 2;
    protected static final int COLUMN_UNITY_ID = 3;
    protected static final int COLUMN_STATE = 4;

    /** 
     * Number of columns in the product_ table.
     */
    protected static final int NUMBER_OF_COLUMNS = 4;

    /** 
     * Index of primary-key column product_Id.
     */
    protected static final int PK_COLUMN_PRODUCT_ID = 1;

    /** 
     * Indexes of the columns in the product_ view.
     */
    protected static final int COLUMN_VIEW_PRODUCT_ID = 1;
    protected static final int COLUMN_VIEW_PRODUCT_NAME = 2;
    protected static final int COLUMN_VIEW_UNITY_DESCRIPTION = 3;

    /** 
     * Number of columns in the product_ view.
     */
    protected static final int PRODUCT_VIEW_COLUMNS = 4;

    /** 
     * Inserts a new row in the product_ table.
     * 
     * @param product_Dto
     * @return ProductPk
     * @throws hulkstore_.model.dao.product_.ProductDaoException 
     */
    public ProductPk insert(ProductDto product_Dto) throws ProductDaoException
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
            statement.setInt( index++, product_Dto.getProductId() );
            statement.setString( index++, product_Dto.getProductName() );
            
            if (product_Dto.isUnityIdNull()) { statement.setNull( index++, java.sql.Types.INTEGER ); }
            else { statement.setInt( index++, product_Dto.getUnityId() ); }

            statement.setShort( index++, product_Dto.getState() );
            System.out.println( "Executing " + SQL_INSERT + " with DTO: " + product_Dto );
            int rows = statement.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );

            // retrieve values from auto-increment columns
            resultSet = statement.getGeneratedKeys();
            if (resultSet != null && resultSet.next()) {
                product_Dto.setProductId( resultSet.getInt( 1 ) );
            }

            reset(product_Dto);
            return product_Dto.createPk();
            
        } catch (Exception exception) { throw new ProductDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }		
        }		
    }

    /** 
     * Updates a single row in the product_ table.
     * 
     * @param product_Pk
     * @param product_Dto
     * @return booelan
     * @throws hulkstore_.model.dao.product_.ProductDaoException
     */
    public boolean update(ProductPk product_Pk, ProductDto product_Dto) throws ProductDaoException
    {
        // declare variables
        long t1 = System.currentTimeMillis();
        final boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            System.out.println( "Executing " + SQL_UPDATE + " with DTO: " + product_Dto );
            statement = connection.prepareStatement( SQL_UPDATE );
            int index=1;
            statement.setInt( index++, product_Dto.getProductId() );
            statement.setString( index++, product_Dto.getProductName() );
            
            if (product_Dto.isUnityIdNull()) { statement.setNull( index++, java.sql.Types.INTEGER ); }
            else { statement.setInt( index++, product_Dto.getUnityId() ); }

            statement.setShort( index++, product_Dto.getState() );
            statement.setInt( 5, product_Pk.getProductId() );
            int rows = statement.executeUpdate();
            reset(product_Dto);
            long t2 = System.currentTimeMillis();
            System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
            
            return rows > 0;
            
        } catch (Exception exception) { throw new ProductDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }
    
    /** 
     * Deletes a single row in the product_ table.
     * 
     * @param product_Pk
     * @return boolean
     * @throws hulkstore_.model.dao.product_.ProductDaoException
     */
    public boolean delete(ProductPk product_Pk) throws ProductDaoException
    {
        // declare variables
        long t1 = System.currentTimeMillis();
        final boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            System.out.println( "Executing " + SQL_DELETE + " with PK: " + product_Pk );
            statement = connection.prepareStatement( SQL_DELETE );
            statement.setInt( 1, product_Pk.getProductId() );
            int rows = statement.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
            
            return rows > 0;
        
        } catch (Exception exception) { throw new ProductDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
   }

    /** 
     * Returns the rows from the product_ table that matches the specified primary-key value.
     * 
     * @param product_Pk
     * @return ProductDto
     * @throws hulkstore_.model.dao.product_.ProductDaoException
     */
    public ProductDto findByPrimaryKey(ProductPk product_Pk) throws ProductDaoException
    {
        return findByPrimaryKey( product_Pk.getProductId() );
    }

    /** 
     * Returns all rows from the product_ table that match the criteria 'product_Id = :product_Id'.
     * 
     * @param product_Id
     * @return ProductDto
     * @throws hulkstore_.model.dao.product_.ProductDaoException
     */
    public ProductDto findByPrimaryKey(int product_Id) throws ProductDaoException
    {
        ProductDto response[] = findByDynamicSelect( SQL_SELECT + " WHERE product_Id = ?", new Object[] {product_Id} );
        return response.length==0 ? null : response[0];
    }

    /** 
     * Returns all rows from the product_ table that match the criteria ''.
     * 
     * @return ProductDto[]
     * @throws hulkstore_.model.dao.product_.ProductDaoException
     */
    public ProductDto[] findAll() throws ProductDaoException
    {
        return findByDynamicSelect( SQL_SELECT + " ORDER BY product_Id", null );
    }

    /**
     * Empty Constructor.
     * 
     */
    public ProductDao() {}

    /**
     * Constructor.
     * 
     * @param userConn
     */
    public ProductDao(final java.sql.Connection userConn) { this.userConn = userConn; }

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
    public String getTableName() { return "bd_Todo1_.product_"; }

    /** 
     * Fetches a single row from the result set.
     * 
     * @param resultSet
     * @return ProductDto
     * @throws java.sql.SQLException
     */
    protected ProductDto fetchSingleResult(ResultSet resultSet) throws SQLException
    {
        if (resultSet.next()) {
            ProductDto product_Dto = new ProductDto();
            populateDto( product_Dto, resultSet);
            return product_Dto;
            
        } else { return null; }
    }

    /** 
     * Fetches multiple rows from the result set.
     * 
     * @param resultSet
     * @return ProductView[]
     * @throws java.sql.SQLException
     */
    protected ProductView[] fetchProductView(ResultSet resultSet) throws SQLException
    {
        ArrayList resultList = fetchMultiResults(resultSet, true);   
        ProductView product_View[] = new ProductView[ resultList.size() ];
        resultList.toArray( product_View );
        return product_View;
    }

    /** 
     * Fetches multiple rows from the result set.
     * 
     * @param resultSet
     * @return ProductDto[]
     * @throws java.sql.SQLException
     */
    protected ProductDto[] fetchProductDto(ResultSet resultSet) throws SQLException
    {
        ArrayList resultList = fetchMultiResults(resultSet, false);   
        ProductDto product_Dto[] = new ProductDto[ resultList.size() ];
        resultList.toArray( product_Dto );
        return product_Dto;
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
                ProductView product_View = new ProductView();
                populateProductView( product_View, resultSet);
                resultList.add( product_View );
            } else {                            
                ProductDto product_Dto = new ProductDto();
                populateDto( product_Dto, resultSet);
                resultList.add( product_Dto );
            }			
        }
        return resultList;
    }

    /** 
     * Populates a DTO with data from a ResultSet.
     * 
     * @param product_Dto
     * @param resultSet
     * @throws java.sql.SQLException
     */
    protected void populateDto(ProductDto product_Dto, ResultSet resultSet) throws SQLException
    {
        product_Dto.setProductId( resultSet.getInt( COLUMN_PRODUCT_ID ) );
        product_Dto.setProductName( resultSet.getString( COLUMN_PRODUCT_NAME ) );
        product_Dto.setUnityId( resultSet.getInt( COLUMN_UNITY_ID ) );        
        if (resultSet.wasNull()) { product_Dto.setUnityIdNull( true ); }
        product_Dto.setState( resultSet.getShort( COLUMN_STATE ) );
    }

    /** 
     * Populates a view product_ with data from a ResultSet.
     * 
     * @param viewProduct
     * @param resultSet
     * @throws java.sql.SQLException
     */
    protected void populateProductView(ProductView viewProduct, ResultSet resultSet) throws SQLException
    {
        viewProduct.setProductId( resultSet.getInt( COLUMN_VIEW_PRODUCT_ID ) );
        viewProduct.setProductName( resultSet.getString( COLUMN_VIEW_PRODUCT_NAME ) );
        viewProduct.setUnityDescription( resultSet.getString( COLUMN_VIEW_UNITY_DESCRIPTION ) );
    }

    /** 
     * Resets the modified attributes in the DTO.
     * 
     * @param product_Dto
     */
    protected void reset(ProductDto product_Dto) {}

    /** 
     * Returns all rows from the product_ table that match the specified arbitrary SQL statement.
     * 
     * @param sql
     * @param sqlParams
     * @return ProductDto[]
     * @throws hulkstore_.model.dao.product_.ProductDaoException
     */
    public ProductDto[] findByDynamicSelect(String sql, Object[] sqlParams) throws ProductDaoException
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
            return fetchProductDto(resultSet);
            
        } catch (Exception exception) { throw new ProductDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(resultSet);
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }

    /** 
     * Returns all rows from the product_ table that match the specified arbitrary SQL statement.
     * 
     * @param sql
     * @param sqlParams
     * @return ProductDto[]
     * @throws hulkstore_.model.dao.product_.ProductDaoException
     */
    public ProductDto[] findByDynamicWhere(String sql, Object[] sqlParams) throws ProductDaoException
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
            return fetchProductDto(resultSet);
        
        } catch (Exception exception) { throw new ProductDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(resultSet);
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }
    
    /**
     * Returns the next available id for the next produt.
     * 
     * @return String
     * @throws ProductDaoException 
     */
    public String findNextProductId() throws ProductDaoException {
        // declare variables
        final boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();
            
            // construct the SQL statement
            final String SQL = "SELECT LPAD((SELECT COUNT(*) + 1 FROM " + getTableName() + "), 6, '0') AS nextProductId";
            
            System.out.println( "Executing " + SQL);
            statement = connection.prepareStatement( SQL );

            resultSet = statement.executeQuery();
            resultSet.next();
            
            return resultSet.getString(1);
            
        } catch (Exception exception) { throw new ProductDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(resultSet);
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }
        
    /**
     * Returns all rows from the product_ view.
     * 
     * @return ProductView[]
     * @throws ProductDaoException 
     */
    public ProductView[] getViewProduct() throws ProductDaoException {
        // declare variables
        final boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            // construct the SQL statement
            final String SQL = "SELECT * FROM bd_Todo1_.vi_Product";

            System.out.println( "Executing " + SQL );
            // prepare statement
            statement = connection.prepareStatement( SQL );
            statement.setMaxRows( maxRows );

            resultSet = statement.executeQuery();

            // fetch the results
            return fetchProductView(resultSet);
            
        } catch (Exception exception) { throw new ProductDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(resultSet);
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }
}