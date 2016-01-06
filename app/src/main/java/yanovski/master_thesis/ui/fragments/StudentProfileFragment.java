package yanovski.master_thesis.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Person;
import yanovski.master_thesis.ui.base.BaseProfileFragment;

/**
 * Created by Samuil on 12/30/2015.
 */
public class StudentProfileFragment extends BaseProfileFragment {

    @Inject
    Person currentPerson;

    @Override
    protected Person getPerson() {
        return currentPerson;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        MasterThesisApplication.getMainComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_student_profile;
    }

}
