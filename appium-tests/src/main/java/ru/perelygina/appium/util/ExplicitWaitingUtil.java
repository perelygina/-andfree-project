package ru.perelygina.appium.util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@UtilityClass
public class ExplicitWaitingUtil {

    public boolean isElementVisible(AndroidElement element, AndroidDriver driver, long timeOutInSeconds) {
        try {
            new WebDriverWait(driver, timeOutInSeconds).
                    until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
