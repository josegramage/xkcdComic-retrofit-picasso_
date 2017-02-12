package es.schooleando.xkcdcomic.service;

import java.io.File;
import java.io.IOException;

import es.schooleando.xkcdcomic.model.ComicModel;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by joso on 11/02/2017.
 */

public class ComicService {

    private static final String BASE_URL = "https://xkcd.com/";

    public interface ComicAPI {

        // TODO: Completar
        @GET("{num}/info.0.json")
        Call<ComicModel> getComic(@Path("num") String num);
    }

    public static ComicAPI getAPI() {
        // TODO: creamos la instancia del interfaz del API mediante Retrofit
        // Retrofit retrofit ...

        OkHttpClient client = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ComicAPI.class);
    }
}
