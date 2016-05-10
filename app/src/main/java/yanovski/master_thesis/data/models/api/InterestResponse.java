package yanovski.master_thesis.data.models.api;

import android.os.Parcel;
import android.os.Parcelable;

import yanovski.master_thesis.data.models.Interest;

/**
 * Created by Samuil on 5/8/2016.
 */
public class InterestResponse extends BaseResponse implements Parcelable {
    public Interest data;

    public InterestResponse() {}

    protected InterestResponse(Parcel in) {
        super(in);
        data = in.readParcelable(Interest.class.getClassLoader());
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

    public static final Creator<InterestResponse> CREATOR = new Creator<InterestResponse>() {
        @Override
        public InterestResponse createFromParcel(Parcel in) {
            return new InterestResponse(in);
        }

        @Override
        public InterestResponse[] newArray(int size) {
            return new InterestResponse[size];
        }
    };
}
