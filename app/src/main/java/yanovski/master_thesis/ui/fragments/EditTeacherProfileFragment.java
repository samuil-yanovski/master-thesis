package yanovski.master_thesis.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yanovski.master_thesis.Constants;
import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Interest;
import yanovski.master_thesis.data.models.Person;
import yanovski.master_thesis.data.models.Teacher;
import yanovski.master_thesis.data.models.api.AccountUpdates;
import yanovski.master_thesis.data.models.api.TeacherResponse;
import yanovski.master_thesis.network.MasterThesisServices;
import yanovski.master_thesis.ui.InterestsActivity;
import yanovski.master_thesis.ui.base.BaseEditProfileFragment;

/**
 * Created by Samuil on 12/29/2015.
 */
public class EditTeacherProfileFragment extends BaseEditProfileFragment implements
    Callback<TeacherResponse> {

    private static final int REQUEST_CODE_SELECT_INTERESTS = 400;

    @Inject
    @Nullable
    Person person;

    @Inject
    MasterThesisServices apiServices;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        MasterThesisApplication.getMainComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_edit_teacher_profile;
    }

    @OnClick(R.id.interests)
    public void onInterestsClicked() {
        ArrayList<Interest> interests = new ArrayList<>();
        if (null != person && null != ((Teacher) person).interests) {
            interests.addAll(((Teacher) person).interests);
        }
        Intent intent = new Intent(getActivity(), InterestsActivity.class);
        intent.putExtra(Constants.KEY_ITEMS, interests);
        startActivityForResult(intent, REQUEST_CODE_SELECT_INTERESTS);
    }

    @Override
    public void onValidationSucceeded() {
        AccountUpdates account = new AccountUpdates();
        account.email = email.getText().toString();
        account.name = name.getText().toString();
        account.phone = phone.getText().toString();
        account.skype = skype.getText().toString();
        apiServices.updateCurrentTeacher(account).enqueue(this);
    }

    @Override
    @Nullable
    public Person getPerson() {
        return person;
    }

    @Override
    public void onResponse(Call<TeacherResponse> call, Response<TeacherResponse> response) {
        showProgress(false);
        if (response.isSuccessful()) {
            getActivity().finish();
        } else {
            TeacherResponse body = response.body();
            String message = null;
            if (null != body && !TextUtils.isEmpty(body.message)) {
                message = body.message;
            } else {
                message = getString(R.string.error_general);
            }
            Snackbar.make(getView(), message, Snackbar.LENGTH_LONG)
                .show();
        }

    }

    @Override
    public void onFailure(Call<TeacherResponse> call, Throwable t) {
        showProgress(false);
        Snackbar.make(getView(), R.string.error_general, Snackbar.LENGTH_LONG)
            .show();
    }
}
