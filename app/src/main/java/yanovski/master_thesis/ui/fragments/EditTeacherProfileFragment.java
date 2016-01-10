package yanovski.master_thesis.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.OnClick;
import yanovski.master_thesis.Constants;
import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Interest;
import yanovski.master_thesis.data.models.Person;
import yanovski.master_thesis.data.models.Teacher;
import yanovski.master_thesis.ui.InterestsActivity;
import yanovski.master_thesis.ui.base.BaseEditProfileFragment;

/**
 * Created by Samuil on 12/29/2015.
 */
public class EditTeacherProfileFragment extends BaseEditProfileFragment {

    private static final int REQUEST_CODE_SELECT_INTERESTS = 400;

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

    @OnClick(R.id.interests)
    public void onInterestsClicked() {
        ArrayList<Interest> interests = new ArrayList<>();
        if (null != ((Teacher) person).interests) {
            interests.addAll(((Teacher) person).interests);
        }
        Intent intent = new Intent(getActivity(), InterestsActivity.class);
        intent.putExtra(Constants.KEY_ITEMS, interests);
        startActivityForResult(intent, REQUEST_CODE_SELECT_INTERESTS);
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
