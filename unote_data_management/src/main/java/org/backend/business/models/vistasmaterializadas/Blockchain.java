package org.backend.business.models.vistasmaterializadas;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.backend.business.gateways.EventRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Component
@Slf4j
public class Blockchain {


    private final EventRepository repository;
    private URL POST_URL;

    private URL GET_URL;

    private final String APP_TOKEN;

    private static final OkHttpClient client = new OkHttpClient();

    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse(
            "application/json; charset=utf-8");

    public Blockchain(EventRepository repository){
        String post = "https://albertus-main.herokuapp.com/create/block";
        String get = "https://albertus-view.herokuapp.com/block/";
        this.repository = repository;
        this.APP_TOKEN = "e7bdf2f0-76ee-41ed-9a74-158f0dec2c56";

        buildUrls(post, get);
    }

    public void saveBlock(Object objet, String name, String id){

        var body = new Gson().toJson(objet);


        var request = new Request.Builder().url(POST_URL)
                .post(okhttp3.RequestBody.create(body, MEDIA_TYPE_JSON))
                .addHeader("Authorization", String.format("Bearer %s", APP_TOKEN)).build();

        log.info("Guardando en bloc{}",request);

        try (Response response = client.newCall(request).execute()) {

            log.info("Guardando en bloc{}",response);
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            var hashBody = response.body().string().split(":")[1].replace("\"", "").replace("}", "");

            var hashToSave = new SavedHash(hashBody, name, id);

            repository.saveEventHash(hashToSave).subscribe(
                    );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    private void buildUrls(String postUrl, String getUrl) {
        try {
            POST_URL = new URL(postUrl);
            GET_URL = new URL(getUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
