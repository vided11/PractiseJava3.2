package ru.mirea.shlyk.jsonparsing;

import com.fasterxml.jackson.databind.ObjectMapper;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AsteroidClient {

    public static void main(String[] args) {

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://data.nasa.gov/resource/")
                .addConverterFactory(JacksonConverterFactory.create(new ObjectMapper()))
                .build();

        AsteroidService asteroidService = retrofit.create(AsteroidService.class);
        Call<List<DTO>> call = asteroidService.getAsteroids();

        try {
            retrofit2.Response<List<DTO>> response = call.execute();

            if (response.isSuccessful()) {
                List<DTO> asteroids = response.body();

                Optional<DTO> latestAsteroid = asteroids.stream()
                        .max(Comparator.comparing(DTO::getDate));

                if (latestAsteroid.isPresent()) {
                    System.out.println("Последний обнаруженный астероид:");
                    System.out.println("Обозначение: " + latestAsteroid.get().getName());
                    System.out.println("Дата обнаружения: " + LocalDateTime.parse(latestAsteroid.get().getDate()));
                } else {
                    System.out.println("Астероиды не найдены.");
                }
            } else {
                System.err.println("Ошибка при выполнении запроса: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
