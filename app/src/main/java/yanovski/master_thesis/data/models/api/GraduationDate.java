package yanovski.master_thesis.data.models.api;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Samuil on 5/10/2016.
 */
public class GraduationDate implements Parcelable {
    public String name;
    public DateTimeData day;

    public GraduationDate() {}

    protected GraduationDate(Parcel in) {
        name = in.readString();
        day = in.readParcelable(DateTimeData.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeParcelable(day, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GraduationDate> CREATOR = new Creator<GraduationDate>() {
        @Override
        public GraduationDate createFromParcel(Parcel in) {
            return new GraduationDate(in);
        }

        @Override
        public GraduationDate[] newArray(int size) {
            return new GraduationDate[size];
        }
    };
}
