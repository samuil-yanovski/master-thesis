package yanovski.master_thesis.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import yanovski.master_thesis.ui.adapters.InfoAdapter;
import yanovski.master_thesis.ui.adapters.base.creators.TeacherVHCreator;
import yanovski.master_thesis.ui.adapters.base.creators.ThesisVHCreator;
import yanovski.master_thesis.ui.base.BaseDrawerActivity;
import yanovski.master_thesis.ui.base.BaseFragment;
import yanovski.master_thesis.ui.fragments.InternshipFragment;
import yanovski.master_thesis.ui.fragments.LoginFragment;
import yanovski.master_thesis.ui.fragments.StudentProfileFragment;
import yanovski.master_thesis.ui.utils.StudentNavigationViewListener;
import yanovski.master_thesis.utils.MailHelper;
import yanovski.master_thesis.utils.PhoneHelper;
import yanovski.master_thesis.utils.SkypeHelper;
import yanovski.master_thesis.utils.UrlHelper;

/**
 * Created by Samuil on 12/29/2015.
 */
@Singleton
@Component(modules = {ApplicationModule.class, DBModule.class, PicassoModule.class})
public interface MainComponent {
    void inject(BaseFragment fragment);
    void inject(LoginFragment fragment);
    void inject(InternshipFragment fragment);
    void inject(InfoAdapter adapter);
    void inject(TeacherVHCreator creator);
    void inject(ThesisVHCreator creator);
    void inject(ThesisVHCreator.AvatarTarget target);
    void inject(BaseDrawerActivity activity);
    void inject(StudentProfileFragment fragment);
    void inject(StudentNavigationViewListener listener);
    Context getContext();
    UrlHelper getUrlHelper();
    PhoneHelper getPhoneHelper();
    MailHelper getMailHelper();
    SkypeHelper getSkypeHelper();
}
