package yanovski.master_thesis.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Person;
import yanovski.master_thesis.data.models.api.AccountUpdates;
import yanovski.master_thesis.data.models.api.StudentsResponse;
import yanovski.master_thesis.network.MasterThesisServices;
import yanovski.master_thesis.ui.base.BaseEditProfileFragment;

/**
 * Created by Samuil on 12/29/2015.
 */
public class EditStudentProfileFragment extends BaseEditProfileFragment implements
    Callback<StudentsResponse> {

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
        return R.layout.fragment_edit_student_profile;
    }


    @Override
    public void onValidationSucceeded() {
        showProgress(true);
        AccountUpdates account = new AccountUpdates();
        account.email = email.getText().toString();
        account.name = name.getText().toString();
        account.phone = phone.getText().toString();
        account.skype = skype.getText().toString();
        apiServices.updateCurrentStudent(account).enqueue(this);
    }

    @Override
    @Nullable
    public Person getPerson() {
        return person;
    }

    @Override
    public void onResponse(Call<StudentsResponse> call, Response<StudentsResponse> response) {
        showProgress(false);
        if (response.isSuccessful()) {
            getActivity().finish();
        } else {
            StudentsResponse body = response.body();
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
    public void onFailure(Call<StudentsResponse> call, Throwable t) {
        showProgress(false);
        Snackbar.make(getView(), R.string.error_general, Snackbar.LENGTH_LONG)
            .show();
    }
}
