package yanovski.master_thesis.data.models.api;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Samuil on 5/10/2016.
 */
public class GraduationDateRequest implements Parcelable {
    public String name;
    public long timestamp;

    public GraduationDateRequest() {}

    protected GraduationDateRequest(Parcel in) {
        name = in.readString();
        timestamp = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeLong(timestamp);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GraduationDateRequest> CREATOR =
        new Creator<GraduationDateRequest>() {
            @Override
            public GraduationDateRequest createFromParcel(Parcel in) {
                return new GraduationDateRequest(in);
            }

            @Override
            public GraduationDateRequest[] newArray(int size) {
                return new GraduationDateRequest[size];
            }
        };
}
