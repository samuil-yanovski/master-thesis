package yanovski.master_thesis.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import javax.inject.Inject;

import icepick.State;
import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.LocalDataProvider;
import yanovski.master_thesis.data.models.Contacts;
import yanovski.master_thesis.data.models.Person;
import yanovski.master_thesis.data.models.Student;
import yanovski.master_thesis.ui.base.BaseEditProfileFragment;
import yanovski.master_thesis.utils.PersonHelper;

/**
 * Created by Samuil on 12/29/2015.
 */
public class RegisterStudentFragment extends BaseEditProfileFragment {

    @Inject
    PersonHelper helper;

    @State
    Student currentStudent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        MasterThesisApplication.getMainComponent().inject(this);
        currentStudent = new Student();
        currentStudent.contacts = new Contacts();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_edit_student_profile;
    }


    @Override
    public void onValidationSucceeded() {
        FragmentActivity activity = getActivity();
        activity.setResult(Activity.RESULT_OK);
        activity.finish();

        Student samuil = LocalDataProvider.createSamuil();
        helper.enterApp(activity, null);
        //        TODO: invoke network calls + show progress
    }

    @Override
    public Person getPerson() {
        return currentStudent;
    }
}
