package yanovski.master_thesis.data.models.api;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Samuil on 5/8/2016.
 */
public class GraduationDateData implements Parcelable {
    public String name;
    public DateTimeData day;

    public GraduationDateData() {}

    protected GraduationDateData(Parcel in) {
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

    public static final Creator<GraduationDateData> CREATOR =
        new Creator<GraduationDateData>() {
            @Override
            public GraduationDateData createFromParcel(Parcel in) {
                return new GraduationDateData(in);
            }

            @Override
            public GraduationDateData[] newArray(int size) {
                return new GraduationDateData[size];
            }
        };
}
