import api.SpotifyApi;
import forms.LoginForm;
import forms.MainForm;
import forms.MyMediaLibForm;
import forms.SearchForm;
import io.qameta.allure.Step;
import models.ResponseModel;
import models.TokenModel;
import models.album.AlbumModel;
import models.track.TrackModel;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.Map;

import static api.HttpStatusCode.OK;
import static api.SpotifyApi.getToken;
import static base.driver.BrowserUtils.*;
import static con.Constants.testData;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.JsonUtils.jsonStringToObject;

public class SearchCurrentTrek_Test extends BaseClass{
    public SpotifyApi api;
    public LoginForm loginForm;
    public AlbumModel albumModel;
    public Map<String, String> tracksAndId;
    public TrackModel trackModel;

    @Test
    public void searchTrekAsId() {
        goTo();
        MainForm mainForm = new MainForm();
        checkMainFormIsDisplayed(mainForm);
        mainForm.closeCookie();
        mainForm.clickToLogOn();
        loginForm = new LoginForm();
        checkLoginFormIsDisplayed(loginForm);
        logOg(System.getenv("LOGIN"), System.getenv("PASSWORD"));
        assertTrue(mainForm.isDisplayed(), "Log on was not sucessfully");
        mainForm.clickOnSearchBtn();
        SearchForm searchForm = new SearchForm();
        checkSearchFormIsDisplayed(searchForm);
        ResponseModel token = getToken(testData.get("clientId").toString(), testData.get("clientSecret").toString());
        checkInquiryIsCorrect(token.getStatusCode());
        TokenModel tokenModel = jsonStringToObject(token.getBody(), TokenModel.class);
        api = new SpotifyApi(tokenModel.getTokenType(), tokenModel.getAccessToken());
        ResponseModel album = api.getAlbum(testData.get("albumId").toString());
        checkInquiryIsCorrect(album.getStatusCode());
        albumModel = jsonStringToObject(album.getBody(), AlbumModel.class);
        tracksAndId = AlbumModel.getMapTracks(albumModel.getTracks().getItems());
        checkTrackIsExist(tracksAndId, testData.get("trackName").toString());
        ResponseModel track = api.getTrack(tracksAndId.get(testData.get("trackName").toString()));
        checkInquiryIsCorrect(track.getStatusCode());
        trackModel = jsonStringToObject(track.getBody(), TrackModel.class);
        checkTrackIsExistInAlbumInquiry(tracksAndId, trackModel);
        searchForm.search(trackModel.getName());
        searchForm.playTrack(trackModel.getName());
        checkTrackIdIsContains(getPageSource(), tracksAndId.get(testData.get("trackName").toString()));
    }

    @Test(dependsOnMethods = "searchTrekAsId")
    public void addTrackToUserLib() {
        MainForm mainForm = new MainForm();
        SearchForm searchForm = new SearchForm();
        searchForm.addToLib(trackModel.getName());
        mainForm.clickOnUserMediaLib();
        MyMediaLibForm mediaLib = new MyMediaLibForm();
        checkMyMediaLibFormIsDisplayed(mediaLib);
        checkCurrentTrackIsContains(albumModel.getName());
        mediaLib.deleteTrack();
    }

    @Step("Log on")
    public void logOg(String userName, String password) {
        loginForm.inputUsername(userName);
        loginForm.inputPassword(password);
    }

    @Step("is displayed")
    public void checkMainFormIsDisplayed(MainForm form) {
        assertTrue(form.isDisplayed(), "main page was not open");
        makeScreenShotByByte("Step");
    }

    @Step("is displayed")
    public void checkMyMediaLibFormIsDisplayed(MyMediaLibForm form) {
        assertTrue(form.isDisplayed(), "media lib page was not open");
        makeScreenShotByByte("Step");
    }

    @Step("is displayed")
    public void checkLoginFormIsDisplayed(LoginForm form) {
        assertTrue(form.isDisplayed(), "login page was not open");
        makeScreenShotByByte("Step");
    }

    @Step("is displayed")
    public void checkSearchFormIsDisplayed(SearchForm form) {
        assertTrue(form.isDisplayed(), "search page was not open");
        makeScreenShotByByte("Step");
    }

    @Step("request is correct")
    public void checkInquiryIsCorrect(int statusCode) {
        assertEquals(OK.getValue(), statusCode, "request was error");
        makeScreenShotByByte("Step");
    }

    @Step("request contains track name")
    public void checkTrackIsExist(Map<String, String> tracksAndId, String trackName) {
        assertTrue(tracksAndId.containsKey(trackName), "track was not founded");
        makeScreenShotByByte("Step");
    }

    @Step("request contains correct tack name and correct id ")
    public void checkTrackIsExistInAlbumInquiry(Map<String, String> tracksAndId, TrackModel trackModel) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(tracksAndId.containsKey(trackModel.getName()), "Tracks are different");
        softAssert.assertTrue(tracksAndId.containsValue(trackModel.getId()), "Tracks are different");
        softAssert.assertAll();
        makeScreenShotByByte("Step");
    }

    @Step("Page source contains current track id")
    public void checkTrackIdIsContains(String pageSource, String trackId) {
        assertTrue(pageSource.contains(trackId), "track was not founded");
        makeScreenShotByByte("Step");
    }

    @Step("Media lib contains current track")
    public void checkCurrentTrackIsContains(String track) {
        assertTrue(getPageSource().contains(track), "media lib do not contains current track");
        makeScreenShotByByte("Step");
    }
}
