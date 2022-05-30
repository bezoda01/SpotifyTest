package api;

public enum SpotifyApiParam {
    GRANT_TYPE("grant_type"),
    CLIENT_ID("client_id"),
    CLIENT_SECRET("client_secret");

    private final String value;

    SpotifyApiParam(String value) {
        this.value  = value;
    }

    public String getValue() {
        return value;
    }
}
