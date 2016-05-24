package yanovski.master_thesis.data.models.api;

import android.os.Parcel;
import android.os.Parcelable;

import yanovski.master_thesis.data.models.Student;
import yanovski.master_thesis.data.models.Teacher;

/**
 * Created by Samuil on 5/8/2016.
 */
public class Credentials implements Parcelable {
    public String email;
    public String password;
    public Student student;
    public Teacher teacher;

    public Credentials() {}

    protected Credentials(Parcel in) {
        email = in.readString();
        password = in.readString();
        student = in.readParcelable(Student.class.getClassLoader());
        teacher = in.readParcelable(Teacher.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(password);
        dest.writeParcelable(student, flags);
        dest.writeParcelable(teacher, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Credentials> CREATOR = new Creator<Credentials>() {
        @Override
        public Credentials createFromParcel(Parcel in) {
            return new Credentials(in);
        }

        @Override
        public Credentials[] newArray(int size) {
            return new Credentials[size];
        }
    };
}
