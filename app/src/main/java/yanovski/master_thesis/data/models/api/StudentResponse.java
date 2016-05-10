package yanovski.master_thesis.data.models.api;

import android.os.Parcel;
import android.os.Parcelable;

import yanovski.master_thesis.data.models.Student;

/**
 * Created by Samuil on 5/8/2016.
 */
public class StudentResponse extends BaseResponse implements Parcelable {
    public Student data;

    public StudentResponse() {}

    protected StudentResponse(Parcel in) {
        super(in);
        data = in.readParcelable(Student.class.getClassLoader());
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

    public static final Creator<StudentResponse> CREATOR = new Creator<StudentResponse>() {
        @Override
        public StudentResponse createFromParcel(Parcel in) {
            return new StudentResponse(in);
        }

        @Override
        public StudentResponse[] newArray(int size) {
            return new StudentResponse[size];
        }
    };
}
