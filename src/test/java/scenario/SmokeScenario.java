package scenario;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.AppiumSetupTest;

import java.util.List;

import static util.Helper.*;

public class SmokeScenario extends AppiumSetupTest {

    protected void checkOutProductInCart() throws InterruptedException {
        // Go to Cart from Home Page
        find(appPackage + ":id/cart_count").click();
        Thread.sleep(1000);
        find(appPackage + ":id/checkout_button").click();
        Thread.sleep(1000);

        //Login to CheckOut
        List<WebElement> editTextList = driver.findElements(By.className("android.widget.EditText"));
        editTextList.get(0).sendKeys("qa000@mail.com");
        editTextList.get(1).sendKeys("a12345");

        driver.findElement(By.className("android.widget.CheckBox")).click();
        find(appPackage + ":id/middle_login_button_signin").click();

        Thread.sleep(2000);
        wait_web(By.id(appPackage + ":id/rocket_app_checkoutweb"));
        driver.findElement(By.id(appPackage + ":id/rocket_app_checkoutweb"));
        Thread.sleep(2000);
        back();
    }

    protected void wishListNotLoginAndAddToCart(String venture, String menuWiz, String wishList, String emptyWL,
                                                String categories, String filterWiz, String prodWiz, String addWL) throws InterruptedException {
        selectVenture(venture, menuWiz);
        Thread.sleep(1000);
        findByUISelector("resourceID", "abs__home", appPackage).click();
        Thread.sleep(1000);

        // Find & Click on Wishlist
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id='" + appPackage + ":id/component_text' and @text='" + wishList + "']")).click();
        // Verify the Wishlist is EMPTY
        find_xpath_forText(appPackage, ":id/wishlist_no_items_text", emptyWL);
        find(appPackage + ":id/wishlist_no_items_bt_continue").click();
        // Verify Home Page is loaded
        isElementPresent(By.id(appPackage + ":id/product_features_title"));

        // Go to Categories & select random product
        find(appPackage + ":id/abs__home").click();
        Thread.sleep(1000);
        randomSelectProduct(categories, appPackage, filterWiz, prodWiz);
        find(appPackage + ":id/btn_wishlist").click(); //Add to Wishlist

        // Verify the message appear:
        find_xpath_forText(appPackage, ":id/items_count", addWL);
        find(appPackage + ":id/button1").click(); //Click on OK button

        // Go to WishList
        findByUISelector("resourceID", "abs__home", appPackage).click();
        findByUISelector("textcontains", wishList, appPackage).click();

        // Add 1 product to Cart from WishList
        findByUISelector("resourceID", "wishlist_item_bt_add", appPackage).click();
        findByUISelector("resourceID", "button2", appPackage).click();

        //Back to Home Page :id/ic_logo
        find(appPackage + ":id/ic_logo").click();
    }

    protected void wishListDeleteProduct(String wishList, String emptyWL) throws InterruptedException {
        // Go to WishList
        findByUISelector("resourceID", "abs__home", appPackage).click();
        driver.findElementByAndroidUIAutomator("UiSelector().textContains(\"" + wishList + "\")").click();

        // Delete product from WishList
        findByUISelector("resourceID", "wishlist_item_bt_delete", appPackage).click();// Click on OK button

        org.testng.Assert.assertTrue(findByUISelector("resourceID", "wishlist_no_items_text", appPackage).isDisplayed());
        org.testng.Assert.assertEquals(emptyWL, findByUISelector("resourceID", "wishlist_no_items_text", appPackage).getText());

        //Back to Home Page :id/ic_logo
        find(appPackage + ":id/ic_logo").click();
    }
}