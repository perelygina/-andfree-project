package ru.perelygina.appium.screen.widget;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.Widget;
import org.openqa.selenium.WebElement;

@AndroidFindBy(id = "item_pod")
public class PodWidget extends Widget {

    @AndroidFindBy(id = "title")
    private AndroidElement titleText;

    @AndroidFindBy(id = "content")
    private AndroidElement contentText;

    protected PodWidget(WebElement element) {
        super(element);
    }

    public String getTitleText() {
        return titleText.getText();
    }

    public String getContentText() {
        return contentText.getText();
    }
}
