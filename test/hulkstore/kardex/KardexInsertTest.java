package hulkstore_.inventory_;

import hulkstore_.model.dao.inventory_.KardexDao;
import hulkstore_.model.dao.inventory_.KardexDaoException;
import hulkstore_.model.dto.inventory_.KardexPk;
import hulkstore_.model.dto.inventory_.KardexDto;
import hulkstore_.model.dao.DaoFactory;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class KardexInsertTest
{
    private static final KardexDao INVENTORYDAO = DaoFactory.createKardexDao();
    private int product_Id;
    private int store_Id;
    private double quantity;
    private double unity_Value;
    private double total_Value;
    private short state;
    private KardexPk expected;

    public KardexInsertTest(int product_Id, int store_Id, double quantity, double unity_Value, double total_Value, short state) {
        this.product_Id = product_Id;
        this.store_Id = store_Id;
        this.quantity = quantity;
        this.unity_Value = unity_Value;
        this.total_Value = total_Value;
        this.state = state;
        this.expected = new KardexPk(product_Id, store_Id);
    }

    @Parameterized.Parameters
    public static Collection data()
    {
        return  Arrays.asList(new Object[][]
        {
            {999999, 999999, 0.0, 0.0, 0.0, (short) 1}
        });
    }

    @Test
    public void testInsert()
    {
        try {
            KardexDto inventory_Dto = new KardexDto(product_Id, store_Id, quantity, unity_Value, total_Value, state);

            assertEquals(expected, INVENTORYDAO.insert(inventory_Dto));

        } catch (KardexDaoException exception) { fail(exception.getMessage()); }
    }
}