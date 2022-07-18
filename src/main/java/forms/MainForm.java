package forms;

import org.openqa.selenium.By;
import selen.base.BaseForm;
import selen.base.elements.Button;
import selen.base.elements.TextField;
import selen.waits.TypeWait;

public class MainForm extends BaseForm {

    private final Button cookie = getIFactory().getButton(By.id("onetrust-close-btn-container"), "cookie");
    private final Button loginBtn = getIFactory().getButton(By.xpath("//div[@class = 'ButtonInner-sc-14ud5tc-0 iebPZv encore-inverted-light-set']"), "login button");
    private final Button searchBtn = getIFactory().getButton(By.xpath("//li[@class = 'eNs6P3JYpf2LScgTDHc6 InvalidDropTarget'][2]"), "search button");
    private final Button myLib = new Button(By.xpath("//div[@class = 'GlueDropTarget GlueDropTarget--tracks']"), "user media lib");

    public MainForm() {
        super(new TextField(By.xpath("//section[@data-testid='home-page']"), "UNIQUE element", TypeWait.PRESENCE_OF_ELEMENT_LOCATED));
    }

    public void closeCookie() {
        cookie.click();
    }

    public void clickToLogOn() {
        loginBtn.click();
    }

    public void clickOnSearchBtn() {
        searchBtn.click();
    }

    public void clickOnUserMediaLib() {
        myLib.click();
    }
}
