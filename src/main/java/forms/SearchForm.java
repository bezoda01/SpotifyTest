package forms;

import org.openqa.selenium.By;
import selen.base.BaseForm;
import selen.base.elements.TextField;
import selen.waits.TypeWait;

public class SearchForm extends BaseForm {

    private final TextField searchInput = getIFactory().getTextField(By.xpath("//input[@data-testid]"), "input field", TypeWait.PRESENCE_OF_ELEMENT_LOCATED);
    private TextField currentTrack = null;
    final String addButtonForm = "//*[contains(text(),'%s')]//ancestor::div[@data-testid = 'tracklist-row']//button[@role]";
    final String correctTrackForm = "//*[contains(text(),'%s')]//ancestor::div[@data-testid = 'tracklist-row']";

    public SearchForm() {
        super(new TextField(By.xpath("//div[@class = 'iKwGKEfAfW7Rkx2_Ba4E Z4InHgCs2uhk0MU93y_a BtbiwMynlB4flsYu_hA2']"), "Search page", TypeWait.PRESENCE_OF_ELEMENT_LOCATED));
    }

    public void search(String text) {
        searchInput.sendText(text);
    }

    public void playTrack(String trackName) {
        new TextField(By.xpath("//a[@title = '" + trackName + "']"), "Track", TypeWait.PRESENCE_OF_ELEMENT_LOCATED).actions().moveToElement();
        currentTrack = new TextField(By.xpath("//a[@title = '" + trackName + "']//ancestor::div[@draggable]//button"), "play button");
        currentTrack.click();
    }

    public void addToLib(String track) {
        new TextField(By.xpath(String.format(correctTrackForm, track)), "track form").actions().moveToElement();
        new TextField(By.xpath(String.format(addButtonForm, track)), "Button add to lib").click();
    }
}
