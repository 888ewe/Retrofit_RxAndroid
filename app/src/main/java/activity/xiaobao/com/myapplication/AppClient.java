package activity.xiaobao.com.myapplication;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by song on 2017/1/4.
 * 作者:沉默
 * QQ:823925783
 */
public class AppClient {
    static Retrofit mRetrofit;


    public static Retrofit retrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl("http://www.weather.com.cn/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
    public interface ApiStores {
        @GET("adat/sk/{cityId}.html")
        Call<WeatherBean> getWeather(@Path("cityId") String cityId);
    }
    public interface ApiStores2 {
        @GET("adat/sk/{cityId}.html")
        rx.Observable<WeatherBean> getWeatherRxjava(@Path("cityId") String cityId);
    }

}
