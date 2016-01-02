package yanovski.master_thesis.ui.utils;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarCellView;

import org.joda.time.LocalDate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.data.models.Event;

/**
 * Created by Samuil on 1/2/2016.
 */
public class EventCellDecorator implements CalendarCellDecorator {

    private Map<LocalDate, List<Event>> dailyEvents = new HashMap<>();
    Context context;
    String newline;
    String eventSymbol = "\u2B24";

    public EventCellDecorator(Map<LocalDate, List<Event>> dailyEvents) {
        this.dailyEvents = dailyEvents;
        context = MasterThesisApplication.getMainComponent()
            .getContext();
        newline = System.getProperty("line.separator", "\n");
    }

    @Override
    public void decorate(CalendarCellView cellView, Date date) {
        List<Event> events = dailyEvents.get(new LocalDate(date));
        if (null != events) {
            CharSequence current = cellView.getText();
            String concatenated = current + newline + eventSymbol;
            SpannableString spans = new SpannableString(concatenated);
            spans.setSpan(new ForegroundColorSpan(cellView.getCurrentTextColor()),
                current.length() + 1, concatenated.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            cellView.setText(spans);
        }
    }
}
