package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.Keys.ARROW_DOWN;
import static org.openqa.selenium.Keys.ENTER;

public class OrderPage {

    private WebDriver driver;
    private final By orderPageHeader = By.xpath(".//div[contains(@class, 'Order_Header')]");
    private final By firstName = By.xpath(".//input[@placeholder='* Имя']");
    private final By lastName = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By address = By.xpath(".//input[starts-with(@placeholder,'* Адрес')]");
    private final By metroStation = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phoneNumber = By.xpath(".//input[starts-with(@placeholder,'* Телефон')]");
    private final By orderDataSendButton = By.xpath(".//button[text()='Далее']");
    private final By inputDate = By.xpath(".//input[@placeholder ='* Когда привезти самокат']");
    private final By inputDatePicker = By.xpath(".//div[contains(@class, 'react-datepicker__day--today')]");
    private final By inputRentalPeriod = By.className("Dropdown-placeholder");
    private final By inputRentalDay = By.xpath(".//div[@class='Dropdown-menu']/div[text()='сутки']");
    private final By checkboxes = By.xpath(".//div[contains(@class, 'Order_Checkboxes')]//input");
    private final By comment = By.xpath(".//input[@placeholder ='Комментарий для курьера']");
    private final By orderButton = By.xpath("//div[contains(@class, 'Order_Buttons')]//button[contains(text(), 'Заказать')]");
    private final By confirmButton = By.xpath(".//button[text()='Да']");
    public final By successText = By.xpath("//div[(text()= 'Заказ оформлен')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setFirstName(String firstName) {
        driver.findElement(this.firstName).sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        driver.findElement(this.lastName).sendKeys(lastName);
    }

    public void setMetroStation(String metroStation) {
        driver.findElement(this.metroStation).sendKeys(metroStation + ARROW_DOWN + ENTER);
    }

    public void setAddress(String address) {
        driver.findElement(this.address).sendKeys(address);
    }

    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(this.phoneNumber).sendKeys(phoneNumber);
    }


    public void fillOrderForm(String firstName, String lastName, String address, String metroStation, String phoneNumber) {
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setMetroStation(metroStation);
        setPhoneNumber(phoneNumber);
    }

    public void waitForLoadOrderPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(orderPageHeader));
    }

    public void waitForLoadSuccessText() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(successText));
    }

    public void clickNextButton() {
        driver.findElement(orderDataSendButton).click();
    }

    public void sendTheSecondForm(String comment) {
        getInputDate().click();
        getInputDatePicker().click();
        getRentalPeriod().click();
        getRentalDay().click();
        clickCheckboxes();
        getComment().sendKeys(comment);
    }

    public WebElement getInputDate() {
        return driver.findElement(inputDate);
    }

    public WebElement getInputDatePicker() {
        return driver.findElement(inputDatePicker);
    }

    public WebElement getRentalPeriod() {
        return driver.findElement(inputRentalPeriod);
    }

    public WebElement getRentalDay() {
        return driver.findElement(inputRentalDay);
    }

    public void clickCheckboxes() {
        driver.findElements(checkboxes).forEach(checkbox -> checkbox.click());
    }

    public WebElement getComment() {
        return driver.findElement(comment);
    }

    public WebElement getOrderButton() {
        return driver.findElement(orderButton);
    }

    public WebElement getConfirmButton() {
        return driver.findElement(confirmButton);
    }

    public boolean isDisplayedSuccessText() {
        System.out.println(driver.findElement(successText).getText());
        return driver.findElement(successText).isDisplayed();
    }
}
