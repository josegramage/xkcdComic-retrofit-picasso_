package es.schooleando.xkcdcomic.presenter;

import android.content.Context;
import android.util.Log;

import es.schooleando.xkcdcomic.model.ComicModel;
import es.schooleando.xkcdcomic.service.ComicService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by joso on 11/02/2017.
 */

public class ComicPresenter {

    private final Context context;
    private final ComicPresenterListener mListener;
    private final ComicService comicService;

    public interface ComicPresenterListener {
        void comicReady(ComicModel comic);
    }

    public ComicPresenter(ComicPresenterListener listener, Context context) {
        this.mListener = listener;
        this.context = context;
        this.comicService = new ComicService();
    }

    public void getComic(String num) {

        final ComicService.ComicAPI service = comicService.getAPI();

        Call<ComicModel> call = service.getComic(num);
        call.enqueue(new Callback<ComicModel>() {

            @Override
            public void onResponse(Call<ComicModel> call, Response<ComicModel> response) {
                ComicModel comicmodel = response.body();

                if (comicmodel != null) {
                    //si está lleno lo enviamos
                    mListener.comicReady(comicmodel);
                }
            }

            @Override
            public void onFailure(Call<ComicModel> call, Throwable t) {
                try {
                    throw new InterruptedException("No se ha podido comunicar con el servidor");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }



}
