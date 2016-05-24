package yanovski.master_thesis.data.models;

import org.joda.time.DateTime;

import yanovski.master_thesis.data.models.api.GraduationDate;

/**
 * Created by Samuil on 1/2/2016.
 */
public class Event {
    public DateTime date;
    public String name;

    public Event() {}

    public Event(GraduationDate date) {
        this.date = new DateTime(null != date.day ? date.day.millis : 0);
        this.name = date.name;
    }
}
