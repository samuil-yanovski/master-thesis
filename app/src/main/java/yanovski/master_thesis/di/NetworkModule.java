package yanovski.master_thesis.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yanovski.master_thesis.network.MasterThesisServices;

/**
 * Created by Samuil on 12/31/2015.
 */
@Module
public class NetworkModule {
    @Singleton
    @Provides
    public MasterThesisServices getApiServices() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(logging)
            .build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://mastereasy.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();
        return retrofit.create(MasterThesisServices.class);
    }
}
