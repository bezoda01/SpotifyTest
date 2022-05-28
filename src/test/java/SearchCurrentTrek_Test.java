import forms.MainForm;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SearchCurrentTrek_Test extends BaseClass{

    @Test
    public void searchTrekAsId() {
        MainForm mainForm = new MainForm();
        assertTrue(mainForm.isDisplayed(), "page was not open");
    }
}
