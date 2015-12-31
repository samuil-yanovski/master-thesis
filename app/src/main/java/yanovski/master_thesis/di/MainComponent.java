package yanovski.master_thesis.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import yanovski.master_thesis.ui.adapters.InfoAdapter;
import yanovski.master_thesis.ui.base.BaseFragment;
import yanovski.master_thesis.ui.fragments.InternshipFragment;
import yanovski.master_thesis.ui.fragments.LoginFragment;
import yanovski.master_thesis.utils.UrlHelper;

/**
 * Created by Samuil on 12/29/2015.
 */
@Singleton
@Component(modules = {ApplicationModule.class, DBModule.class})
public interface MainComponent {
    void inject(BaseFragment fragment);
    void inject(LoginFragment fragment);
    void inject(InternshipFragment fragment);
    void inject(InfoAdapter adapter);
    Context getContext();
    UrlHelper getHelper();
}
