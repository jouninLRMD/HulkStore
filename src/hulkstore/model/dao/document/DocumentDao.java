package hulkstore_.model.dao.document;

import hulkstore_.model.dto.document.DocumentDto;
import hulkstore_.model.dto.document.DocumentPk;
import hulkstore_.model.dao.AbstractDao;
import hulkstore_.model.dao.ResourceManager;
import java.sql.Connection;
import java.util.Collection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/** 
 * This class handles queries to the document table.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public final class DocumentDao extends AbstractDao
{
    /** 
     * The factory class for this DAO has two versions of the create() method - one that
     * takes no arguments and one that takes a Connection argument. If the Connection version
     * is chosen then the connection will be store_d in this attribute and will be used by all
     * calls to this DAO, otherwise a new Connection will be allocated for each operation.
     */
    private java.sql.Connection userConn;

    /** 
     * All finder methods in this class use this SELECT constant to build their queries.
     */
    private final String SQL_SELECT = "SELECT document_Id, documentDescription, state FROM " + getTableName() + "";

    /** 
     * Finder methods will pass this value to the JDBC setMaxRows method.
     */
    private int maxRows;

    /** 
     * SQL INSERT statement for document table.
     */
    private final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( document_Id, documentDescription, state ) VALUES ( ?, ?, ? )";

    /** 
     * SQL UPDATE statement for document table.
     */
    private final String SQL_UPDATE = "UPDATE " + getTableName() + " SET document_Id = ?, documentDescription = ?, state = ? WHERE document_Id = ?";
    
    /** 
     * SQL DELETE statement for this table
     */
    protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE document_Id = ?";

    /** 
     * Indexes of the columns in the document table.
     */
    private static final int COLUMN_DOCUMENT_ID = 1;
    private static final int COLUMN_DOCUMENT_DESCRIPTION = 2;
    private static final int COLUMN_STATE = 3;

    /** 
     * Number of columns in the document table.
     */
    protected static final int NUMBER_OF_COLUMNS = 3;

    /** 
     * Inserts a new row in the document table.
     * 
     * @param documentDto
     * @return DocumentPk
     * @throws hulkstore_.model.dao.document.DocumentDaoException
     */
    public DocumentPk insert(DocumentDto documentDto) throws DocumentDaoException
    {
        // declare variables
        long t1 = System.currentTimeMillis();
        boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            statement = connection.prepareStatement( SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS );
            int index = 1;
            statement.setInt( index++, documentDto.getDocumentId() );
            statement.setString( index++, documentDto.getDocumentDescription() );
            statement.setShort( index++, documentDto.getState() );
            System.out.println( "Executing " + SQL_INSERT + " with DTO: " + documentDto );
            int rows = statement.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );

            // retrieve values from auto-increment columns
            resultSet = statement.getGeneratedKeys();
            if (resultSet != null && resultSet.next()) {
                    documentDto.setDocumentId( resultSet.getInt( 1 ) );
            }

            reset(documentDto);
            return documentDto.createPk();
            
        } catch (Exception exception) { throw new DocumentDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }

    /** 
     * Updates a single row in the document table.
     * 
     * @param documentPk
     * @param documentDto
     * @return boolean
     * @throws hulkstore_.model.dao.document.DocumentDaoException
     */
    public boolean update(DocumentPk documentPk, DocumentDto documentDto) throws DocumentDaoException
    {
        // declare variables
        long t1 = System.currentTimeMillis();
        boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement Statement = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            System.out.println( "Executing " + SQL_UPDATE + " with DTO: " + documentDto );
            Statement = connection.prepareStatement( SQL_UPDATE );
            int index = 1;
            Statement.setInt( index++, documentDto.getDocumentId() );
            Statement.setString( index++, documentDto.getDocumentDescription() );
            Statement.setShort( index++, documentDto.getState() );
            Statement.setInt( 4, documentPk.getDocumentId() );
            int rows = Statement.executeUpdate();
            reset(documentDto);
            long t2 = System.currentTimeMillis();
            System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
            
            return rows > 0;
            
        } catch (Exception exception) { throw new DocumentDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(Statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }
    
    /** 
     * Deletes a single row in the document table.
     * 
     * @param documentPk
     * @return boolean
     * @throws hulkstore_.model.dao.document.DocumentDaoException
     */
    public boolean delete(DocumentPk documentPk) throws DocumentDaoException
    {
        // declare variables
        long t1 = System.currentTimeMillis();
        final boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();

            System.out.println( "Executing " + SQL_DELETE + " with PK: " + documentPk );
            statement = connection.prepareStatement( SQL_DELETE );
            statement.setInt( 1, documentPk.getDocumentId() );
            int rows = statement.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
            
            return rows > 0;
        
        } catch (Exception exception) { throw new DocumentDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
   }

    /** 
     * Returns the rows from the document table that matches the specified primary-key value.
     * 
     * @param documentPk
     * @return DocumentDto
     * @throws hulkstore_.model.dao.document.DocumentDaoException
     */
    public DocumentDto findByPrimaryKey(DocumentPk documentPk) throws DocumentDaoException
    {
        return findByPrimaryKey( documentPk.getDocumentId() );
    }

    /** 
     * Returns all rows from the document table that match the criteria 'document_Id = :document_Id'.
     * 
     * @param document_Id
     * @return DocumentDto
     * @throws hulkstore_.model.dao.document.DocumentDaoException 
     */
    public DocumentDto findByPrimaryKey(int document_Id) throws DocumentDaoException
    {
        DocumentDto documentDto[] = findByDynamicSelect( SQL_SELECT + " WHERE document_Id = ?", new Object[] { document_Id } );
        return documentDto.length==0 ? null : documentDto[0];
    }

    /** 
     * Returns all rows from the document table that match the criteria ''.
     * 
     * @return DocumentDto[]
     * @throws hulkstore_.model.dao.document.DocumentDaoException
     */
    public DocumentDto[] findAll() throws DocumentDaoException
    {
        return findByDynamicSelect( SQL_SELECT + " ORDER BY document_Id", null );
    }

    /** 
     * Returns all rows from the document table that match the criteria 'state = :state'.
     * 
     * @param state
     * @return DocumentDto[]
     * @throws hulkstore_.model.dao.document.DocumentDaoException
     */
    public DocumentDto[] findWhereStateEquals(short state) throws DocumentDaoException
    {
        return findByDynamicSelect( SQL_SELECT + " WHERE state = ? ORDER BY state", new Object[] { state } );
    }

    /**
     * Empty Constructor. 
     */
    public DocumentDao() {}

    /**
     * Constructor.
     * 
     * @param userConn
    */
    public DocumentDao(final java.sql.Connection userConn) { this.userConn = userConn; }

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
    private String getTableName() { return "bd_Todo1_.document"; }

    /** 
     * Fetches multiple rows from the result set.
     * 
     * @param resultSet
     * @return DocumentDto[]
     * @throws java.sql.SQLException
    */
    private DocumentDto[] fetchMultiResults(ResultSet resultSet) throws SQLException
    {
        Collection resultList = new ArrayList();
        while (resultSet.next()) {
            DocumentDto documentDto = new DocumentDto();
            populateDto( documentDto, resultSet);
            resultList.add( documentDto );
        }

        DocumentDto documentDto[] = new DocumentDto[ resultList.size() ];
        resultList.toArray( documentDto );
        return documentDto;
    }

    /** 
     * Populates a DTO with data from a ResultSet.
     * 
     * @param documentDto
     * @param resultSet
     * @throws java.sql.SQLException
    */
    private void populateDto(DocumentDto documentDto, ResultSet resultSet) throws SQLException
    {
        documentDto.setDocumentId( resultSet.getInt( COLUMN_DOCUMENT_ID ) );
        documentDto.setDocumentDescription( resultSet.getString( COLUMN_DOCUMENT_DESCRIPTION ) );
        documentDto.setState( resultSet.getShort( COLUMN_STATE ) );
    }

    /** 
     * Resets the modified attributes in the DTO
     * @param documentDto
    */
    private void reset(DocumentDto documentDto) {}

    /** 
     * Returns all rows from the document table that match the specified arbitrary SQL statement.
     * 
     * @param sql
     * @param sqlParams
     * @return DocumentDto[]
     * @throws hulkstore_.model.dao.document.DocumentDaoException
    */
    private DocumentDto[] findByDynamicSelect(String sql, Object[] sqlParams) throws DocumentDaoException
    {
        // declare variables
        boolean isConnSupplied = (userConn != null);
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
            for (int i = 0; sqlParams != null && i< sqlParams.length; i++ ) {
                statement.setObject( i + 1, sqlParams[i] );
            }

            resultSet = statement.executeQuery();

            // fetch the results
            return fetchMultiResults(resultSet);
            
        } catch (Exception exception) { throw new DocumentDaoException( "Exception: " + exception.getMessage(), exception );
            
        } finally {
            ResourceManager.close(resultSet);
            ResourceManager.close(statement);            
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }
    
    /** 
     * Returns the next available id for the next document.
     * 
     * @return String
     * @throws hulkstore_.model.dao.document.DocumentDaoException
    */
    public String findNextDocumentId() throws DocumentDaoException {
        // declare variables
        boolean isConnSupplied = (userConn != null);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            connection = isConnSupplied ? userConn : ResourceManager.getConnection();
            
            // construct the SQL statement
            String sql = "SELECT LPAD((SELECT COUNT(*) + 1 FROM " + getTableName() + "), 6, '0') AS nextDocumentId";
            
            System.out.println( "Executing " + sql);
            statement = connection.prepareStatement( sql );

            resultSet = statement.executeQuery();
            resultSet.next();
            
            return resultSet.getString(1);
            
        } catch (Exception exception) { throw new DocumentDaoException( "Exception: " + exception.getMessage(), exception );
        
        } finally {
            ResourceManager.close(resultSet);
            ResourceManager.close(statement);
            if (!isConnSupplied) { ResourceManager.close(connection); }
        }
    }
}