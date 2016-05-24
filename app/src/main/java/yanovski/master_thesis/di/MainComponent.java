package yanovski.master_thesis.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import yanovski.master_thesis.ui.MyProfileActivity;
import yanovski.master_thesis.ui.adapters.InfoAdapter;
import yanovski.master_thesis.ui.adapters.base.creators.StudentVHCreator;
import yanovski.master_thesis.ui.adapters.base.creators.TeacherVHCreator;
import yanovski.master_thesis.ui.adapters.base.creators.ThesisProposalVHCreator;
import yanovski.master_thesis.ui.adapters.base.creators.ThesisVHCreator;
import yanovski.master_thesis.ui.base.BaseDrawerActivity;
import yanovski.master_thesis.ui.base.BaseEditProfileFragment;
import yanovski.master_thesis.ui.base.BaseFragment;
import yanovski.master_thesis.ui.base.BaseProfileFragment;
import yanovski.master_thesis.ui.fragments.EditStudentProfileFragment;
import yanovski.master_thesis.ui.fragments.EditTeacherProfileFragment;
import yanovski.master_thesis.ui.fragments.InternshipFragment;
import yanovski.master_thesis.ui.fragments.LoginFragment;
import yanovski.master_thesis.ui.fragments.NewEventFragment;
import yanovski.master_thesis.ui.fragments.NewThesisFragment;
import yanovski.master_thesis.ui.fragments.RegisterStudentFragment;
import yanovski.master_thesis.ui.fragments.StudentProfileFragment;
import yanovski.master_thesis.ui.fragments.ThesesFragment;
import yanovski.master_thesis.ui.utils.AvatarTarget;
import yanovski.master_thesis.ui.utils.StudentNavigationViewListener;
import yanovski.master_thesis.ui.utils.TeacherNavigationViewListener;
import yanovski.master_thesis.utils.MailHelper;
import yanovski.master_thesis.utils.PhoneHelper;
import yanovski.master_thesis.utils.SkypeHelper;
import yanovski.master_thesis.utils.UrlHelper;

/**
 * Created by Samuil on 12/29/2015.
 */
@Singleton
@Component(modules = {ApplicationModule.class, DBModule.class, PicassoModule.class,
    NetworkModule.class})
public interface MainComponent {
    void inject(LoginFragment.UserLoginTask task);
    void inject(BaseFragment fragment);
    void inject(LoginFragment fragment);
    void inject(InternshipFragment fragment);
    void inject(InfoAdapter adapter);
    void inject(TeacherVHCreator creator);
    void inject(StudentVHCreator creator);
    void inject(ThesisVHCreator creator);
    void inject(ThesisProposalVHCreator creator);
    void inject(AvatarTarget target);
    void inject(StudentProfileFragment fragment);
    void inject(BaseProfileFragment fragment);
    void inject(StudentNavigationViewListener listener);
    void inject(TeacherNavigationViewListener listener);
    void inject(EditStudentProfileFragment fragment);
    void inject(EditTeacherProfileFragment fragment);
    void inject(BaseEditProfileFragment fragment);
    void inject(RegisterStudentFragment fragment);
    void inject(ThesesFragment fragment);
    void inject(NewEventFragment fragment);
    void inject(NewThesisFragment fragment);
    void inject(BaseDrawerActivity activity);
    void inject(MyProfileActivity activity);
    Context getContext();
    UrlHelper getUrlHelper();
    PhoneHelper getPhoneHelper();
    MailHelper getMailHelper();
    SkypeHelper getSkypeHelper();
}
