package yanovski.master_thesis.data.models.api;

import android.os.Parcel;
import android.os.Parcelable;

import yanovski.master_thesis.data.models.Thesis;

/**
 * Created by Samuil on 5/8/2016.
 */
public class ThesisResponse extends BaseResponse implements Parcelable {
    public Thesis data;

    public ThesisResponse() {}

    protected ThesisResponse(Parcel in) {
        super(in);
        data = in.readParcelable(Thesis.class.getClassLoader());
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

    public static final Creator<ThesisResponse> CREATOR = new Creator<ThesisResponse>() {
        @Override
        public ThesisResponse createFromParcel(Parcel in) {
            return new ThesisResponse(in);
        }

        @Override
        public ThesisResponse[] newArray(int size) {
            return new ThesisResponse[size];
        }
    };
}
