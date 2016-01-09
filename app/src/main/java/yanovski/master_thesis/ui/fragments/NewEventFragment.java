package yanovski.master_thesis.ui.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.calendardatepicker.MonthAdapter;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import icepick.State;
import yanovski.master_thesis.Constants;
import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Person;
import yanovski.master_thesis.data.models.Student;
import yanovski.master_thesis.ui.StudentsActivity;
import yanovski.master_thesis.ui.base.BaseFragment;
import yanovski.master_thesis.ui.utils.UIModes;
import yanovski.master_thesis.utils.CollectionUtils;
import yanovski.master_thesis.utils.DateUtils;

/**
 * Created by Samuil on 12/29/2015.
 */
public class NewEventFragment extends BaseFragment implements
    CalendarDatePickerDialogFragment.OnDateSetListener {

    private static final String TAG_DATE_FRAGMENT = "date_fragment";
    private static final int REQUEST_CODE_SELECT_STUDENTS = 300;

    // UI references.
    @Bind(R.id.date)
    TextView date;
    @Bind(R.id.title)
    @NotEmpty
    EditText title;
    @Bind(R.id.login_progress)
    View progressView;
    @Bind(R.id.recipients_container)
    View container;
    @Bind(R.id.recipients)
    TextView recipients;

    @Inject
    Person person;

    @State
    DateTime selectedDate;
    @State
    ArrayList<Student> recipientsList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_event;
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
        switch (person.getType()) {
            case TEACHER: {
                container.setVisibility(View.VISIBLE);
                break;
            }
        }
    }

    @OnClick(R.id.recipients)
    protected void onRecipientsClicked() {
        Intent intent = new Intent(getActivity(), StudentsActivity.class);
        intent.putExtra(Constants.KEY_MODE, UIModes.MultiSelect.name());
        startActivityForResult(intent, REQUEST_CODE_SELECT_STUDENTS);
    }

    @OnClick(R.id.date)
    protected void onDateClicked() {
        DateTime now = DateTime.now();
        FragmentManager fm = getFragmentManager();
        CalendarDatePickerDialogFragment calendarDatePickerDialogFragment =
            CalendarDatePickerDialogFragment.newInstance(this, now.getYear(),
                now.getMonthOfYear() - 1, now.getDayOfMonth());
        calendarDatePickerDialogFragment.setDateRange(
            new MonthAdapter.CalendarDay(now.getYear(), now.getMonthOfYear() - 2,
                now.getDayOfMonth()), null);
        calendarDatePickerDialogFragment.show(fm, TAG_DATE_FRAGMENT);
    }

    @OnEditorAction(R.id.title)
    protected boolean onIMEClicked() {
        send();
        return true;
    }

    public void send() {
        if (null == selectedDate) {
            Snackbar.make(getView(), R.string.error_select_date, Snackbar.LENGTH_LONG)
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

    private void showDate() {
        date.setText(DateUtils.formatDate(selectedDate));
    }

    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear,
        int dayOfMonth) {
        selectedDate = new DateTime(year, monthOfYear + 1, dayOfMonth, 0, 0);
        showDate();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Activity.RESULT_OK == resultCode) {
            if (REQUEST_CODE_SELECT_STUDENTS == requestCode && null != data &&
                data.hasExtra(Constants.KEY_ITEMS)) {
                recipientsList = data.getParcelableArrayListExtra(Constants.KEY_ITEMS);
                showRecipients();
            }
        }
    }

    public void showRecipients() {
        if (!CollectionUtils.isEmpty(recipientsList)) {
            if (1 == recipientsList.size()) {
                Student student = recipientsList.get(0);
                recipients.setText(student.getName());
            } else {
                recipients.setText(getString(R.string.recipients_format, recipientsList.size()));
            }
        } else {
            recipients.setText(R.string.select_recipients);
        }
    }
}
