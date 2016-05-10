package yanovski.master_thesis.data.models.api;

import android.os.Parcel;
import android.os.Parcelable;

import yanovski.master_thesis.data.models.Teacher;

/**
 * Created by Samuil on 5/8/2016.
 */
public class TeacherResponse extends BaseResponse implements Parcelable {
    public Teacher data;

    public TeacherResponse() {}

    protected TeacherResponse(Parcel in) {
        super(in);
        data = in.readParcelable(Teacher.class.getClassLoader());
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

    public static final Creator<TeacherResponse> CREATOR = new Creator<TeacherResponse>() {
        @Override
        public TeacherResponse createFromParcel(Parcel in) {
            return new TeacherResponse(in);
        }

        @Override
        public TeacherResponse[] newArray(int size) {
            return new TeacherResponse[size];
        }
    };
}
