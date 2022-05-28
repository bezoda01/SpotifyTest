package utils;

import models.ResponseModel;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static con.Constants.config;

public class APIUtils {

    private static HttpClient client = HttpClientBuilder.create().build();

    public static ResponseModel getRequest(String inquiry) {
        HttpGet get = new HttpGet(config.get("apiUrl") + inquiry);
        HttpResponse response = null;
        String body = null;
        try {
            response = client.execute(get);
            if (response.containsHeader(null)) {
                body = null;
            } else {
                body = new BasicResponseHandler().handleResponse(response);
            }
        } catch (IllegalArgumentException | IOException e) {
            e.printStackTrace();
        }

        return new ResponseModel(body, response.getStatusLine().getStatusCode());
    }

    public static ResponseModel postRequest(String inquiry, NameValuePair... params) {
        HttpPost httppost = new HttpPost(config.get("apiUrl") + inquiry);
        HttpResponse response = null;
        String body = null;
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.addAll(Arrays.asList(params));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
            response = client.execute(httppost);
            body = new BasicResponseHandler().handleResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseModel(body, response.getStatusLine().getStatusCode());
    }
}
