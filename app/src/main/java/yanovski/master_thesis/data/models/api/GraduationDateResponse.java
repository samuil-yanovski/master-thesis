package yanovski.master_thesis.data.models.api;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Samuil on 5/8/2016.
 */
public class GraduationDateResponse extends BaseResponse implements Parcelable {
    public GraduationDate data;

    public GraduationDateResponse() {}

    protected GraduationDateResponse(Parcel in) {
        super(in);
        data = in.readParcelable(GraduationDate.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(data, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GraduationDateResponse> CREATOR =
        new Creator<GraduationDateResponse>() {
            @Override
            public GraduationDateResponse createFromParcel(Parcel in) {
                return new GraduationDateResponse(in);
            }

            @Override
            public GraduationDateResponse[] newArray(int size) {
                return new GraduationDateResponse[size];
            }
        };
}
