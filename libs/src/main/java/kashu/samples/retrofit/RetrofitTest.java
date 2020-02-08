package kashu.samples.retrofit;

import lombok.Data;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;

import java.io.IOException;
import java.util.List;

/**
 * @author 卡叔
 * @date 2020/02/08
 */
public class RetrofitTest {
	@Data
	static class Authority {
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
				.baseUrl("xxx")
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		OAuth service = retrofit.create(OAuth.class);
		List<Authority> authorities = service.getAuthorities("Bearer " + "xxx").execute().body();
		System.out.println(authorities);

	}
}
