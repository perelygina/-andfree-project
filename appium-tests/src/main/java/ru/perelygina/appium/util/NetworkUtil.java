package ru.perelygina.appium.util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.mobile.NetworkConnection.ConnectionType;

@UtilityClass
public class NetworkUtil {

    public void changeNetwork(AndroidDriver driver, ConnectionType connectionType) {
        driver.setConnection(new ConnectionState(connectionType.hashCode()));
    }
}
