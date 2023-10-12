package ru.mirea.shlyk.jsonparsing;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface AsteroidService {
    @GET("2vr3-k9wn.json")
    Call<List<DTO>> getAsteroids();
}
