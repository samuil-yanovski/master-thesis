package yanovski.master_thesis.ui.fragments;

import android.annotation.TargetApi;
import android.os.Build;
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

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import icepick.State;
import yanovski.master_thesis.R;
import yanovski.master_thesis.ui.base.BaseFragment;
import yanovski.master_thesis.utils.DateUtils;

/**
 * Created by Samuil on 12/29/2015.
 */
public class NewEventFragment extends BaseFragment implements CalendarDatePickerDialogFragment.OnDateSetListener {

    private static final String TAG_DATE_FRAGMENT = "date_fragment";

    // UI references.
    @Bind(R.id.date)
    TextView date;
    @Bind(R.id.title)
    @NotEmpty
    EditText title;
    @Bind(R.id.login_progress)
    View progressView;

    @State
    DateTime selectedDate;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_event;
    }

    @OnClick(R.id.date)
    protected void onDateClicked() {
        DateTime now = DateTime.now();
        FragmentManager fm = getFragmentManager();
        CalendarDatePickerDialogFragment calendarDatePickerDialogFragment = CalendarDatePickerDialogFragment
            .newInstance(this, now.getYear(), now.getMonthOfYear() - 1, now.getDayOfMonth());
        calendarDatePickerDialogFragment.setDateRange(new MonthAdapter.CalendarDay(now.getYear(), now.getMonthOfYear() - 2, now.getDayOfMonth()), null);
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
}
