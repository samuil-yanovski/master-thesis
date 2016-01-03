package yanovski.master_thesis.ui.fragments;

import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.squareup.timessquare.CalendarPickerView;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.LocalDataProvider;
import yanovski.master_thesis.data.models.Event;
import yanovski.master_thesis.ui.adapters.EventsAdapter;
import yanovski.master_thesis.ui.base.BaseListFragment;


/**
 * Created by Samuil on 12/30/2015.
 */
public class CalendarFragment extends BaseListFragment implements
    CalendarPickerView.OnDateSelectedListener, ValueAnimator.AnimatorUpdateListener {

    @Bind(R.id.calendar)
    CalendarPickerView calendar;
    @Bind(R.id.footer)
    View footer;
    @Bind(R.id.empty)
    View empty;

    Map<LocalDate, List<Event>> dailyEvents = new HashMap<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_calendar;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DateTime today = DateTime.now();
        Date minDate = today.withDayOfMonth(1)
            .toDate();
        Date maxDate = today.plusYears(1)
            .toDate();

        List<Event> allEvents = LocalDataProvider.getAllEvents();
        dailyEvents = StreamSupport.stream(allEvents)
            .collect(Collectors.groupingBy(e -> e.date.toLocalDate()));

        calendar.init(minDate, maxDate)
            .withSelectedDate(today.toDate())
            .withHighlightedDates(StreamSupport.stream(allEvents)
                .map(e -> e.date.toDate())
                .collect(Collectors.toList()));
        calendar.setOnDateSelectedListener(this);
    }

    @Override
    public void onDateSelected(Date date) {
        RecyclerView list = getList();
        int measuredHeight = footer.getMeasuredHeight();
        if (0 == measuredHeight) {
            showEventsList();
        }

        List<Event> events = dailyEvents.get(new LocalDate(date));

        if (null == events) {
            events = Collections.emptyList();
        }

        int size = events.size();
        EventsAdapter adapter = new EventsAdapter();
        adapter.setItems(events);
        list.setAdapter(adapter);

        list.setVisibility(0 < size ? View.VISIBLE : View.GONE);
        empty.setVisibility(0 == size ? View.VISIBLE : View.GONE);
    }

    private void showEventsList() {
        Resources res = getContext().getResources();
        int height = res
            .getDimensionPixelSize(R.dimen.events_list_height);
        int duration = res
            .getInteger(android.R.integer.config_shortAnimTime);
        ValueAnimator animator = ValueAnimator.ofInt(0, height);
        animator.setDuration(duration);
        animator.addUpdateListener(this);
        animator.start();
    }

    @Override
    public void onDateUnselected(Date date) {

    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        Integer value = (Integer) animation.getAnimatedValue();
        footer.getLayoutParams().height = value.intValue();
        footer.requestLayout();
    }
}
