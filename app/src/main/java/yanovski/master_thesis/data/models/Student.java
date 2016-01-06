package yanovski.master_thesis.data.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Samuil on 1/3/2016.
 */
public class Student implements Person, Parcelable {
    public final Types type = Types.STUDENT;
    public String name;
    public Contacts contacts;
    public String avatar;
    public Thesis thesis;

    public Student() {}

    protected Student(Parcel in) {
        name = in.readString();
        contacts = in.readParcelable(Contacts.class.getClassLoader());
        avatar = in.readString();
        thesis = in.readParcelable(Thesis.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeParcelable(contacts, flags);
        dest.writeString(avatar);
        dest.writeParcelable(thesis, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public String getAvatar() {
        return avatar;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Contacts getContacts() {
        return contacts;
    }

    @Override
    public Types getType() {
        return type;
    }

}
