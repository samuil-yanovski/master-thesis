package yanovski.master_thesis;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.karumi.dexter.Dexter;

import io.fabric.sdk.android.Fabric;
import java8.util.stream.StreamSupport;
import yanovski.master_thesis.di.ApplicationModule;
import yanovski.master_thesis.di.DaggerMainComponent;
import yanovski.master_thesis.di.MainComponent;

/**
 * Created by Samuil on 12/28/2015.
 */
public class MasterThesisApplication extends Application {

    private static MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        Dexter.initialize(this);
        mainComponent = DaggerMainComponent.builder()
            .applicationModule(new ApplicationModule(this))
            .build();
    }

    public static MainComponent getMainComponent() {
        return mainComponent;
    }
}
