package ru.perelygina.appium.screen;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import ru.perelygina.appium.screen.widget.PodWidget;
import ru.perelygina.appium.util.ExplicitWaitingUtil;

import java.time.Duration;
import java.util.List;

public class MainScreen {

    private final AndroidDriver driver;

    @AndroidFindBy(id = "text_input_edit")
    private AndroidElement queryInput;

    @AndroidFindBy(id = "progress_bar")
    private AndroidElement requestLoader;

    private List<PodWidget> podList;

    @AndroidFindBy(id = "snackbar_text")
    private AndroidElement snackbarText;

    @AndroidFindBy(id = "snackbar_action")
    private AndroidElement snackbarOkButton;

    public MainScreen(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(15)), this);
    }

    public void enterQuery(String query) {
        queryInput.setValue(query);
    }

    public boolean isLoaderDisplayed(long timeOutInSeconds) {
        return ExplicitWaitingUtil.isElementVisible(
                requestLoader,
                driver,
                timeOutInSeconds);
    }

    public List<PodWidget> getPodList() {
        return podList;
    }

    public String getSnackbarText() {
        return snackbarText.getText();
    }

    public boolean isSnackbarTextDisplayed(long timeOutInSeconds) {
        return ExplicitWaitingUtil.isElementVisible(
                snackbarText,
                driver,
                timeOutInSeconds);
    }

    public void tapSnackbarOkButton() {
        snackbarOkButton.click();
    }
}
