package yanovski.master_thesis.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import yanovski.master_thesis.MasterThesisApplication;

/**
 * Created by Samuil on 12/29/2015.
 */
@Module
public class ApplicationModule {
    private MasterThesisApplication application;

    public ApplicationModule(MasterThesisApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public MasterThesisApplication provideApplication() {
        return application;
    }

    @Singleton
    @Provides
    public Context provideAppContext() {
        return application;
    }
}
