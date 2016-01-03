package yanovski.master_thesis.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samuil on 12/31/2015.
 */
public class Teacher implements Person, Parcelable {
    public String name;
    public String avatar;
    public List<Interest> interests;
    public Contacts contact;

    public Teacher() {
    }

    protected Teacher(Parcel in) {
        name = in.readString();
        avatar = in.readString();
        contact = in.readParcelable(Contacts.class.getClassLoader());

        Parcelable[] parcelables = in.readParcelableArray(Interest.class.getClassLoader());
        if (null != parcelables) {
            interests = new ArrayList<>();
            for (Parcelable p : parcelables) {
                interests.add((Interest) p);
            }
        }
    }

    public static final Creator<Teacher> CREATOR = new Creator<Teacher>() {
        @Override
        public Teacher createFromParcel(Parcel in) {
            return new Teacher(in);
        }

        @Override
        public Teacher[] newArray(int size) {
            return new Teacher[size];
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
        return contact;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(avatar);
        dest.writeParcelable(contact, 0);
        dest.writeParcelableArray(null != interests ? interests.toArray(new Interest[0]) : null, 0);
    }
}
