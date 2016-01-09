package yanovski.master_thesis.ui.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import icepick.State;
import yanovski.master_thesis.Constants;
import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Category;
import yanovski.master_thesis.data.models.Person;
import yanovski.master_thesis.data.models.Teacher;
import yanovski.master_thesis.ui.CategoriesActivity;
import yanovski.master_thesis.ui.TeachersActivity;
import yanovski.master_thesis.ui.base.BaseFragment;
import yanovski.master_thesis.ui.utils.UIModes;

/**
 * Created by Samuil on 12/29/2015.
 */
public class NewThesisFragment extends BaseFragment {

    private static final int REQUEST_CODE_SELECT_AUTHOR = 100;
    private static final int REQUEST_CODE_SELECT_CATEGORY = 101;

    // UI references.
    @Bind(R.id.author)
    TextView author;
    @Bind(R.id.title)
    @NotEmpty
    EditText title;
    @Bind(R.id.description)
    @NotEmpty
    EditText description;
    @Bind(R.id.login_progress)
    View progressView;
    @Bind(R.id.category_container)
    View container;
    @Bind(R.id.category)
    TextView categoryLabel;

    @Inject
    Person person;

    @State
    Teacher teacher;
    @State
    Category category;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_thesis;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MasterThesisApplication.getMainComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        switch (person.getType()) {
            case TEACHER: {
                container.setVisibility(View.VISIBLE);
                break;
            }
        }
    }

    @OnClick(R.id.category)
    protected void onCategoryClicked() {
        Intent intent = new Intent(getActivity(), CategoriesActivity.class);
        intent.putExtra(Constants.KEY_MODE, UIModes.Select.name());
        startActivityForResult(intent, REQUEST_CODE_SELECT_CATEGORY);
    }

    @OnClick(R.id.author)
    protected void onAuthorClicked() {
        Intent intent = new Intent(getActivity(), TeachersActivity.class);
        intent.putExtra(Constants.KEY_MODE, UIModes.Select.name());
        startActivityForResult(intent, REQUEST_CODE_SELECT_AUTHOR);
    }

    @OnEditorAction(R.id.description)
    protected boolean onIMEClicked() {
        send();
        return true;
    }

    public void send() {
        if (null == teacher) {
            Snackbar.make(getView(), R.string.error_select_teacher, Snackbar.LENGTH_LONG)
                .show();
            return;
        }
        validate();
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        progressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onValidationSucceeded() {
        getActivity().finish();
        //        TODO: invoke network calls + show progress
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE_SELECT_AUTHOR == requestCode) {
            if (Activity.RESULT_OK == resultCode && null != data &&
                data.hasExtra(Constants.KEY_ITEM)) {
                teacher = data.getParcelableExtra(Constants.KEY_ITEM);
                showTeacher();
            }

        } else if (REQUEST_CODE_SELECT_CATEGORY == requestCode) {
            if (Activity.RESULT_OK == resultCode && null != data &&
                data.hasExtra(Constants.KEY_ITEM)) {
                category = data.getParcelableExtra(Constants.KEY_ITEM);
                showCategory();
            }
        }
    }

    private void showTeacher() {
        if (null != teacher) {
            author.setText(teacher.name);
        } else {
            author.setText(R.string.select_teacher);
        }
    }

    private void showCategory() {
        if (null != category) {
            categoryLabel.setText(category.name);
        } else {
            categoryLabel.setText(R.string.select_category);
        }
    }
}
