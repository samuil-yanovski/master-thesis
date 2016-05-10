package yanovski.master_thesis.data.models.api;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import yanovski.master_thesis.data.models.Teacher;

/**
 * Created by Samuil on 5/8/2016.
 */
public class TeachersResponse extends BaseResponse implements Parcelable {
    public List<Teacher> data;

    public TeachersResponse() {}

    protected TeachersResponse(Parcel in) {
        super(in);
        data = in.createTypedArrayList(Teacher.CREATOR);
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

    public static final Creator<TeachersResponse> CREATOR = new Creator<TeachersResponse>() {
        @Override
        public TeachersResponse createFromParcel(Parcel in) {
            return new TeachersResponse(in);
        }

        @Override
        public TeachersResponse[] newArray(int size) {
            return new TeachersResponse[size];
        }
    };
}
