package yanovski.master_thesis;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.karumi.dexter.Dexter;

import net.danlew.android.joda.JodaTimeAndroid;

import io.fabric.sdk.android.Fabric;
import pl.aprilapps.easyphotopicker.EasyImage;
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
        JodaTimeAndroid.init(this);

        Dexter.initialize(this);
        mainComponent = DaggerMainComponent.builder()
            .applicationModule(new ApplicationModule(this))
            .build();

        EasyImage.configuration(this)
            .setImagesFolderName(getString(R.string.app_name))
            .saveInRootPicturesDirectory();
    }

    public static MainComponent getMainComponent() {
        return mainComponent;
    }
}
