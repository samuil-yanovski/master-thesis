package yanovski.master_thesis.di;

import android.content.Context;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Samuil on 12/31/2015.
 */
@Module
public class PicassoModule {
    @Singleton
    @Provides
    public Picasso getPhoneContactMapping(Context context) {
        Picasso picasso = Picasso.with(context);
        return picasso;
    }
}
