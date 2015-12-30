package yanovski.master_thesis.ui.fragments;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.karumi.dexter.Dexter;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.pushtorefresh.storio.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.queries.Query;
import com.trello.rxlifecycle.RxLifecycle;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;
import rx.android.schedulers.AndroidSchedulers;
import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.db.models.PhoneContact;
import yanovski.master_thesis.db.models.PhoneContactGetResolver;
import yanovski.master_thesis.permissions.SnackBarInfoPermissionListener;
import yanovski.master_thesis.ui.HomeActivity;
import yanovski.master_thesis.ui.base.BaseFragment;

/**
 * Created by Samuil on 12/29/2015.
 */
public class LoginFragment extends BaseFragment implements SnackBarInfoPermissionListener.Callback {
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    @Bind(R.id.email)
    @NotEmpty
    @Email
    AutoCompleteTextView mEmailView;
    @Bind(R.id.password)
    @NotEmpty
    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC)
    EditText mPasswordView;
    @Bind(R.id.login_progress)
    View mProgressView;
    @Bind(R.id.login_form)
    View mLoginFormView;
    @Bind(R.id.email_sign_in_button)
    View mEmailSignInButton;

    @Inject
    StorIOContentResolver storIOResolver;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MasterThesisApplication.getMainComponent()
            .inject(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        populateAutoComplete();
    }

    private void populateAutoComplete() {
        SnackBarInfoPermissionListener permissionListener =
            SnackBarInfoPermissionListener.Builder.with((ViewGroup) getView(),
                R.string.contacts_permission_rationale, R.string.denied_permission_text)
                .withOnDeniedOpenSettingsButton(R.string.settings)
                .withCallback(this)
                .build();
        Dexter.checkPermission(permissionListener, Manifest.permission.READ_CONTACTS);
    }

    @OnEditorAction(R.id.password)
    protected boolean onIMEClicked() {
        attemptLogin();
        return true;
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    @OnClick(R.id.email_sign_in_button)
    protected void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }
        validate();
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mEmailSignInButton.setEnabled(!show);
    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
            new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line,
                emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

    @Override
    public void onPermissionRequestResult(boolean granted) {
        if (granted) {
            storIOResolver.get()
                .listOfObjects(PhoneContact.class)
                .withQuery(Query.builder()
                    .uri(Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY))
                    .columns(PhoneContactGetResolver.ProfileQuery.PROJECTION)
                    .where(ContactsContract.Contacts.Data.MIMETYPE + " = ?")
                    .whereArgs(ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                    .sortOrder(ContactsContract.Contacts.Data.IS_PRIMARY + " DESC")
                    .build())
                .prepare()
                .createObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycle.bindFragment(lifecycle()))
                .subscribe((phoneContacts) -> {
                    List<String> emails = StreamSupport.stream(phoneContacts)
                        .map(c -> c.email)
                        .collect(Collectors.toList());
                    addEmailsToAutoComplete(emails);
                }, (throwable) -> {
                    addEmailsToAutoComplete(Collections.emptyList());
                });
        }
    }


    @Override
    public void onValidationSucceeded() {
        // Store values at the time of the login attempt.
        String email = mEmailView.getText()
            .toString();
        String password = mPasswordView.getText()
            .toString();

        showProgress(true);
        mAuthTask = new UserLoginTask(email, password);
        mAuthTask.execute((Void) null);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getActivity());

            // Display error messages ;)
            ((EditText) view).setError(message);
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        UserLoginTask(String email, String password) {
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                FragmentActivity activity = getActivity();
                Intent intent = new Intent(activity, HomeActivity.class);
                activity.startActivity(intent);
                activity.finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}