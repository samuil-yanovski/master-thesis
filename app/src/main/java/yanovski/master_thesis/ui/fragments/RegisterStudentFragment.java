package yanovski.master_thesis.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import icepick.State;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Account;
import yanovski.master_thesis.data.models.Contacts;
import yanovski.master_thesis.data.models.Person;
import yanovski.master_thesis.ui.HomeActivity;
import yanovski.master_thesis.ui.base.BaseEditProfileFragment;

/**
 * Created by Samuil on 12/29/2015.
 */
public class RegisterStudentFragment extends BaseEditProfileFragment {

    @State
    Account currentAccount;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentAccount = new Account();
        currentAccount.contacts = new Contacts();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_edit_student_profile;
    }


    @Override
    public void onValidationSucceeded() {
        FragmentActivity activity = getActivity();
        Intent intent = new Intent(activity, HomeActivity.class);
        startActivity(intent);

        activity.setResult(Activity.RESULT_OK);
        activity.finish();
        //        TODO: invoke network calls + show progress
    }

    @Override
    public Person getPerson() {
        return currentAccount;
    }
}
