package forms;

import base.driver.BaseForm;
import base.elements.BaseElement;
import base.elements.TextField;
import org.openqa.selenium.By;

public class MainForm extends BaseForm {

    public MainForm() {
        super(new TextField(By.xpath("//section[@data-testid='home-page']"), "UNIQUE element"), "UNIQUE element");
    }
}
