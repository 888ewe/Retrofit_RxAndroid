package activity.xiaobao.com.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends Activity {

    private TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test = (TextView) findViewById(R.id.test);

        getWeather();
    }
    private void getWeather() {
//        AppClient.ApiStores apiStores = AppClient.retrofit().create(AppClient.ApiStores.class);
//        Call<WeatherBean> call = apiStores.getWeather("101010100");
//        call.enqueue(new Callback<WeatherBean>() {
//
//            @Override
//            public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
//                test.setText(response.body().getWeatherinfo().getCity());
//            }
//
//            @Override
//            public void onFailure(Call<WeatherBean> call, Throwable t) {
//
//            }
//        });
        AppClient.ApiStores2 apiStores2 = AppClient.retrofit().create(AppClient.ApiStores2.class);
       Observable<WeatherBean> observable=apiStores2.getWeatherRxjava("101010100");
       observable.subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherBean>() {
                    @Override
                    public void onCompleted() {
                        Log.i("wxl", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("wxl", "e=" + e.getMessage());
                    }

                    @Override
                    public void onNext(WeatherBean weatherBean) {
                        Log.i("wxl", "getWeatherinfo=" + weatherBean.getWeatherinfo().getCity());
                        test.setText(weatherBean.getWeatherinfo().getCity());
                    }
                });
    }

}
