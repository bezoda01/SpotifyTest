package api;

import models.ResponseModel;
import org.apache.http.message.BasicNameValuePair;

import static selen.settings.Settings.*;
import static utils.APIUtils.*;
import static api.SpotifyApiParam.*;
import static api.ApiEndPoints.*;
import static api.ApiInquiryForm.*;

public class SpotifyApi {
    private static String authenticationForm = "Authorization";
    private String authentication;

    public SpotifyApi(String tokenType, String token) {
        authentication = tokenType + " " + token;
    }

    public static ResponseModel getToken(String clientId, String clientSecret) {
        return postRequest(settings.get("apiUrlToken").toString(),
                new BasicNameValuePair(GRANT_TYPE.getValue(), "client_credentials"),
                new BasicNameValuePair(CLIENT_ID.getValue(), clientId),
                new BasicNameValuePair(CLIENT_SECRET.getValue(), clientSecret));
    }

    public ResponseModel getAlbum(String albumId) {
        return getRequest(ALBUM.getValue() + albumId, authenticationForm, authentication);
    }

    public ResponseModel getTrack(String trackId) {
        return getRequest(TRACK.getValue() + trackId, authenticationForm, authentication);
    }

    public ResponseModel searchWithNameAndType(String name, String artist, SpotifyApiParam type) {
        return getRequest(String.format(SEARCH.getValue(), name, artist, type.getValue()), authenticationForm, authentication);
    }
}
