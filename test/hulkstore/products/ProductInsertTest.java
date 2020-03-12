package hulkstore_.product_s;

import hulkstore_.model.dto.product_.ProductDto;
import hulkstore_.model.dao.product_.*;
import hulkstore_.model.dao.DaoFactory;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ProductInsertTest
{
    private static final ProductDao PRODUCTDAO = DaoFactory.createProductDao();
    private int product_Id;
    private String product_Name;
    private int unity_Id;
    private short state;
    private int expected;

    public ProductInsertTest(int product_Id, String product_Name, int unity_Id, short state)
    {
        this.product_Id = product_Id;
        this.product_Name = product_Name;
        this.unity_Id = unity_Id;
        this.state = state;
        this.expected = this.product_Id;
    }

    @Parameterized.Parameters
    public static Collection data()
    {
        return  Arrays.asList(new Object[][]
        {
            {999999, "Insert 1", 999999, (short) 1},
            {999998, "Insert 2", 999998, (short) 0}
        });
    }

    @Test
    public void testInsert()
    {
        try {
            ProductDto store_Dto = new ProductDto(product_Id, product_Name, unity_Id, state);

            assertEquals(expected, PRODUCTDAO.insert(store_Dto).getProductId());

        } catch (ProductDaoException exception) { fail(exception.getMessage()); }
    }
}