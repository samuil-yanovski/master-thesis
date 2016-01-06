package yanovski.master_thesis.data.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Samuil on 1/1/2016.
 */
public class ThesisProposal implements Parcelable{
    public String title;
    public String description;
    public Student student;

    public ThesisProposal() {}

    protected ThesisProposal(Parcel in) {
        title = in.readString();
        description = in.readString();
        student = in.readParcelable(Student.class.getClassLoader());
    }

    public static final Creator<ThesisProposal> CREATOR = new Creator<ThesisProposal>() {
        @Override
        public ThesisProposal createFromParcel(Parcel in) {
            return new ThesisProposal(in);
        }

        @Override
        public ThesisProposal[] newArray(int size) {
            return new ThesisProposal[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeParcelable(student, flags);
    }
}
