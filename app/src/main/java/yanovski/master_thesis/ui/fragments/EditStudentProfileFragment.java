package yanovski.master_thesis.ui.fragments;

import javax.inject.Inject;

import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Account;
import yanovski.master_thesis.data.models.Person;
import yanovski.master_thesis.di.ForStudent;
import yanovski.master_thesis.ui.base.BaseEditProfileFragment;

/**
 * Created by Samuil on 12/29/2015.
 */
public class EditStudentProfileFragment extends BaseEditProfileFragment {

    @Inject
    @ForStudent
    Account currentAccount;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_edit_student_profile;
    }


    @Override
    public void onValidationSucceeded() {
        getActivity().finish();
        //        TODO: invoke network calls + show progress
    }

    @Override
    public Person getPerson() {
        return currentAccount;
    }
}
