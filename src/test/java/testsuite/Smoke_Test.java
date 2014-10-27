package testsuite;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import scenario.SmokeScenario;

import static util.Helper.driver;
import static util.VentureText.setText;

public class Smoke_Test extends SmokeScenario {

    @BeforeMethod
    public void setUp() throws Exception {
        init();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        if (driver != null) driver.quit();
    }

    @DataProvider
    Object[][] getVenturesData() {
        return new Object[][]{
                {"Singapore"},
                {"Malaysia"},
                {"Philippines"},
                {"Thailand"},
                {"Vietnam"}
        };
    }

    @Test(dataProvider = "getVenturesData")
    public void test_SmokeScenario(String venture) throws Exception {

        wishListNotLoginAndAddToCart(venture, setText(venture).get("menuWiz"),
                setText(venture).get("wishList"), setText(venture).get("emptyWL"),
                setText(venture).get("categories"), setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"), setText(venture).get("addWL"));

        wishListDeleteProduct(setText(venture).get("wishList"), setText(venture).get("emptyWL"));

        checkOutProductInCart();
    }
}
