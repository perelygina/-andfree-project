package ru.perelygina.appium;

import io.appium.java_client.android.AndroidDriver;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.perelygina.appium.screen.MainScreen;
import ru.perelygina.appium.util.NetworkUtil;
import ru.perelygina.appium.util.PropertiesUtil;

import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.MobileCapabilityType.APP;
import static io.appium.java_client.remote.MobileCapabilityType.DEVICE_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.mobile.NetworkConnection.ConnectionType.ALL;
import static org.openqa.selenium.mobile.NetworkConnection.ConnectionType.NONE;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

@TestInstance(Lifecycle.PER_CLASS)
public class correctTextRequestTest {

    private static final String NONE_NETWORK_ERROR_TEXT = PropertiesUtil.getProperty("correctTextRequestWithoutNetwork.errorText");

    private AndroidDriver driver;
    private MainScreen mainScreen;

    @BeforeAll
    @SneakyThrows(value = MalformedURLException.class)
    public void setupDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(PLATFORM_NAME, Platform.ANDROID);
        capabilities.setCapability(DEVICE_NAME, "SomeDeviceName");
        capabilities.setCapability(APP, "/Users/a.perelygina/-andfree-project/app/build/outputs/apk/release/app-release-unsigned.apk");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
    }

    @BeforeEach
    public void setupNetwork() {
        NetworkUtil.changeNetwork(driver, ALL);
    }

    @Test
    @DisplayName("Выполнение корректного текстового запроса")
    public void correctTextRequest() {
        mainScreen = new MainScreen(driver);
        mainScreen.enterQuery("How big is the universe?");
        assertTrue(mainScreen.isLoaderDisplayed(3));
        assertFalse(mainScreen.isLoaderDisplayed(2));
        assertTrue(mainScreen.getPodList().size() > 0);
    }

    @Test
    @DisplayName("Выполнение корректного текстового запроса без Интернета")
    public void correctTextRequestWithoutNetwork() {
        mainScreen = new MainScreen(driver);
        NetworkUtil.changeNetwork(driver, NONE);
        mainScreen.enterQuery("How big is the universe?");
        assertTrue(mainScreen.isSnackbarTextDisplayed(5));
        assertEquals(NONE_NETWORK_ERROR_TEXT, mainScreen.getSnackbarText());
        mainScreen.tapSnackbarOkButton();
        assertFalse(mainScreen.isSnackbarTextDisplayed(5));
        assertEquals(0, mainScreen.getPodList().size());
    }

    @AfterAll
    public void quitDriver() {
        driver.quit();
    }
}
