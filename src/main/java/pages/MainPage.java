package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;

    // локатор "Верхняя кнопка 'Заказать'"
    private final By orderUpButton = By.xpath(".//button[@class='Button_Button__ra12g']");

    // локатор "Нижняя кнопка 'Заказать'"
    private final By orderDownButton = By.className("Button_Middle__1CSJM");

    //локатор "Раздел 'Вопросы о важном'"
    private final By listOfFAQ = By.className("Home_FAQ__3uVm4");
    private final By ofFaqButton = By.className("accordion__button");
    private final By ofFaqText = By.cssSelector(".accordion__panel p");

    public MainPage() {
    }

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    public void clickOrderUpButton() {
        driver.findElement(orderUpButton).click();
    }


    public void scrollToOrderDownButton() {
        WebElement element = driver.findElement(orderDownButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickOrderLowerButton() {
        driver.findElement(orderDownButton).click();
    }


    public void scrollToListOfFaqAndClick(int index) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElements(ofFaqButton).get(index));
        driver.findElements(ofFaqButton).get(index).click();
    }

    public void waitForLoadFaq() {
        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.visibilityOfElementLocated(listOfFAQ));
    }

    public String getTextFromListFaq(int index) {
        return driver.findElements(ofFaqText).get(index).getText();
    }

    public By getOrderUpButton() {
        return orderUpButton;
    }

    public By getOrderDownButton() {
        return orderDownButton;
    }
}
