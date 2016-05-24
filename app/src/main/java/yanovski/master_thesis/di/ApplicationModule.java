package yanovski.master_thesis.di;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;

import com.cloudinary.Cloudinary;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.DateTime;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Person;
import yanovski.master_thesis.ui.utils.NavigationViewListener;
import yanovski.master_thesis.ui.utils.StudentNavigationViewListener;
import yanovski.master_thesis.ui.utils.TeacherNavigationViewListener;
import yanovski.master_thesis.utils.DateTimeTypeConverter;

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

    @Singleton
    @Provides
    @ForColorPrimary
    public int getColorPrimary(Context context) {
        TypedArray a = context.getTheme()
            .obtainStyledAttributes(new int[]{android.R.attr.colorPrimary});
        int attributeResourceId = a.getResourceId(0, 0);
        return context.getResources()
            .getColor(attributeResourceId);
    }

    @Provides
    public NavigationViewListener getNavigationViewListener(@Nullable Person person) {
        NavigationViewListener listener = null;

        if (null != person) {
            switch (person.getType()) {
                case STUDENT: {
                    listener = new StudentNavigationViewListener();
                    break;
                }
                case TEACHER: {
                    listener = new TeacherNavigationViewListener();
                    break;
                }
            }
        } else {
            listener = new StudentNavigationViewListener();
        }

        return listener;
    }

    @Singleton
    @Provides
    public Gson getGson() {
        return new GsonBuilder()
            .registerTypeAdapter(DateTime.class, new DateTimeTypeConverter())
            .create();
    }

    @Singleton
    @Provides
    public Cloudinary getCloudinary(Context context) {
        Cloudinary cloudinary = new Cloudinary(context.getString(R.string.cloudinary_url));
        return cloudinary;
    }
}
