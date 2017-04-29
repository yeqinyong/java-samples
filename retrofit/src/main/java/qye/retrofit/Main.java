package qye.retrofit;

import lombok.Data;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;

import java.io.IOException;
import java.util.List;

/**
 * Created by yeqinyong on 17/3/24.
 */
public class Main {
    @Data
    static class Authority{
        String authId;
        String authName;
        String authDesc;
    }

    public static interface OAuth {
        @GET("authorities")
        Call<List<Authority>> getAuthorities(@Header("Authorization") String authorization);
    }

    public static void main(String[] args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://oa-oauth.laobai.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OAuth service = retrofit.create(OAuth.class);
        List<Authority> authorities = service.getAuthorities("Bearer " + "da541f73-e35f-44f4-b788-91fca980e646").execute().body();
        System.out.println(authorities);

    }
}
