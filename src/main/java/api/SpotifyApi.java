package api;

import models.ResponseModel;
import org.apache.http.message.BasicNameValuePair;

import static utils.APIUtils.*;
import static api.SpotifyApiParam.*;
import static api.ApiEndPoints.*;

public class SpotifyApi {
    private static String authenticationForm = "Authorization";
    private String authentication;

    public SpotifyApi(String tokenType, String token) {
        authentication = tokenType + " " + token;
    }

    public static ResponseModel getToken(String clientId, String clientSecret) {
        return postRequest(
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
}
