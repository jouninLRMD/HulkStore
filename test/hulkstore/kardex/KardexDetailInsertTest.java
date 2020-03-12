package hulkstore_.inventory_;

import hulkstore_.model.dao.inventory_.KardexDetailDaoException;
import hulkstore_.model.dao.inventory_.KardexDetailDao;
import hulkstore_.model.dto.inventory_.KardexDetailPk;
import hulkstore_.model.dto.inventory_.KardexDetailDto;
import hulkstore_.model.dao.DaoFactory;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class KardexDetailInsertTest 
{
    private static final KardexDetailDao INVENTORYDETAILDAO = DaoFactory.createKardexDetailDao();
    private int detailId;
    private int product_Id;
    private int store_Id;
    private int inventory_DetailYear;
    private int inventory_DetailMonth;
    private int inventory_Detailday;
    private int userId;
    private int document_Id;
    private int documentNumber;
    private short operation;
    private double quantity;
    private double unity_Value;
    private double total_Value;
    private String observations;
    private short state;
    private KardexDetailPk expected;

    public KardexDetailInsertTest(int detailId, int product_Id, int store_Id, int inventory_DetailYear, int inventory_DetailMonth, int inventory_Detailday, int userId, int document_Id, int documentNumber, short operation, double quantity, double unity_Value, double total_Value, short state, String observations) 
    {
        this.detailId = detailId;
        this.product_Id = product_Id;
        this.store_Id = store_Id;
        this.inventory_DetailYear = inventory_DetailYear;
        this.inventory_DetailMonth = inventory_DetailMonth;
        this.inventory_Detailday = inventory_Detailday;
        this.userId = userId;
        this.document_Id = document_Id;
        this.documentNumber = documentNumber;
        this.operation = operation;
        this.quantity = quantity;
        this.unity_Value = unity_Value;
        this.total_Value = total_Value;
        this.observations = observations;
        this.state = state;
        this.expected = new KardexDetailPk(this.detailId, this.product_Id, this.store_Id);
    }
    
    @Parameterized.Parameters
    public static Collection data()
    {
        return  Arrays.asList(new Object[][]
        {
            {999997, 999999, 999999, 2000, 1, 1, 999999, 999999, 61914591, (short) 1, 10.0, 1.0, 10.0, (short) 1, "Entry Test"},
            {999998, 999999, 999999, 2000, 1, 1, 999999, 999999, 61914591, (short) 1, 10.0, 2.0, 20.0, (short) 1, "Entry Test"},
            {999999, 999999, 999999, 2000, 1, 1, 999999, 999999, 12365478, (short) 0, 5.0, 1.0, 5.0, (short) 1, "Exit Test"}
        });
    }
        
    @Test
    public void testInsert()
    {
        try {
            KardexDetailDto inventory_DetailDto = new KardexDetailDto(detailId, product_Id, store_Id, inventory_DetailYear, inventory_DetailMonth, inventory_Detailday, userId, document_Id, documentNumber, operation, quantity, unity_Value, total_Value, observations, state);
            
            assertEquals(expected, INVENTORYDETAILDAO.insert(inventory_DetailDto));      
            
        } catch (KardexDetailDaoException exception) { fail(exception.getMessage()); }
    } 
}