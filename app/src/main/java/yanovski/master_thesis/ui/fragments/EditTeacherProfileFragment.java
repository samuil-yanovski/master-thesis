package yanovski.master_thesis.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Person;
import yanovski.master_thesis.ui.base.BaseEditProfileFragment;

/**
 * Created by Samuil on 12/29/2015.
 */
public class EditTeacherProfileFragment extends BaseEditProfileFragment {

    @Inject
    Person person;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MasterThesisApplication.getMainComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_edit_teacher_profile;
    }


    @Override
    public void onValidationSucceeded() {
        getActivity().finish();
        //        TODO: invoke network calls + show progress
    }

    @Override
    public Person getPerson() {
        return person;
    }
}