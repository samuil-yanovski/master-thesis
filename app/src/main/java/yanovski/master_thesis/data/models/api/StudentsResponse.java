package yanovski.master_thesis.data.models.api;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import yanovski.master_thesis.data.models.Student;

/**
 * Created by Samuil on 5/8/2016.
 */
public class StudentsResponse extends BaseResponse implements Parcelable {
    public List<Student> data;

    public StudentsResponse() {}

    protected StudentsResponse(Parcel in) {
        super(in);
        data = in.createTypedArrayList(Student.CREATOR);
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

    public static final Creator<StudentsResponse> CREATOR = new Creator<StudentsResponse>() {
        @Override
        public StudentsResponse createFromParcel(Parcel in) {
            return new StudentsResponse(in);
        }

        @Override
        public StudentsResponse[] newArray(int size) {
            return new StudentsResponse[size];
        }
    };
}
