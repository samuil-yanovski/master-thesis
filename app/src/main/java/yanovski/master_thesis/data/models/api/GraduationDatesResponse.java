package yanovski.master_thesis.data.models.api;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Samuil on 5/8/2016.
 */
public class GraduationDatesResponse extends BaseResponse implements Parcelable {
    public List<GraduationDate> data;

    public GraduationDatesResponse() {}

    protected GraduationDatesResponse(Parcel in) {
        super(in);
        data = in.createTypedArrayList(GraduationDate.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(data);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GraduationDatesResponse> CREATOR =
        new Creator<GraduationDatesResponse>() {
            @Override
            public GraduationDatesResponse createFromParcel(Parcel in) {
                return new GraduationDatesResponse(in);
            }

            @Override
            public GraduationDatesResponse[] newArray(int size) {
                return new GraduationDatesResponse[size];
            }
        };
}
