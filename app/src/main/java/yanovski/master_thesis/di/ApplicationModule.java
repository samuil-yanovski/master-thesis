package yanovski.master_thesis.di;

import android.content.Context;
import android.content.res.TypedArray;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.data.models.Person;
import yanovski.master_thesis.ui.utils.NavigationViewListener;
import yanovski.master_thesis.ui.utils.StudentNavigationViewListener;
import yanovski.master_thesis.ui.utils.TeacherNavigationViewListener;

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
        TypedArray a = context
            .getTheme()
            .obtainStyledAttributes(new int[] {android.R.attr.colorPrimary});
        int attributeResourceId = a.getResourceId(0, 0);
        return context.getResources().getColor(attributeResourceId);
    }

    @Provides
    public NavigationViewListener getNavigationViewListener(Person person) {
        NavigationViewListener listener = null;

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

        return listener;
    }
}
