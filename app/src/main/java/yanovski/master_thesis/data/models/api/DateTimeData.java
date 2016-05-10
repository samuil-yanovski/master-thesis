package yanovski.master_thesis.data.models.api;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Samuil on 5/8/2016.
 */
public class DateTimeData implements Parcelable {
    public long millis;

    public DateTimeData() {}

    protected DateTimeData(Parcel in) {
        millis = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(millis);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DateTimeData> CREATOR = new Creator<DateTimeData>() {
        @Override
        public DateTimeData createFromParcel(Parcel in) {
            return new DateTimeData(in);
        }

        @Override
        public DateTimeData[] newArray(int size) {
            return new DateTimeData[size];
        }
    };
}
