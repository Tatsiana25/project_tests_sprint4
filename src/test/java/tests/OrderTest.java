package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.OrderPage;

import static org.junit.Assert.assertTrue;
@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String phoneNumber;
    private final String station;
    private final String comment;
    private final By clickOrderButton;
    String url = "https://qa-scooter.praktikum-services.ru/";

    public OrderTest(String name, String surname, String address, String phoneNumber, String station, String comment, By clickOrderButton) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.station = station;
        this.comment = comment;
        this.clickOrderButton = clickOrderButton;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        MainPage mainPage = new MainPage();
        return new Object[][]{
                {"Иван", "Иванов", "г. Москва, ул. Беломорская, д. 14", "89000000000", "Речной Вокзал", "Быстрее)", mainPage.getOrderUpButton()},
                {"Сигизмунд", "Бык", "г. Москва, ул. Адмирала Макарова, д. 18", "89990000000", "Войковская", "Отзвониться за 15 минут до приезда", mainPage.getOrderDownButton()},
        };
    }

    @Before
    public void start() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
    }

    @Test
    public void checkOrderProcess() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOrderUpButton();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.waitForLoadOrderPage();
        orderPage.fillOrderForm(name, surname, address, station, phoneNumber);
        orderPage.clickNextButton();
        orderPage.sendTheSecondForm(comment);
        orderPage.getOrderButton().click();
        orderPage.getConfirmButton().click();
        orderPage.waitForLoadSuccessText();

        assertTrue("Не отображено сообщение о заказе", orderPage.isDisplayedSuccessText());
    }

    @After
    public void stop() {
        driver.quit();
    }
}
