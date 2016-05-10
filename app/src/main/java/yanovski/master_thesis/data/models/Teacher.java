package yanovski.master_thesis.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samuil on 12/31/2015.
 */
public class Teacher extends BasePerson {
    public final Types type = Types.TEACHER;
    public String key;
    public List<Interest> interests;

    public Teacher() {
    }

    protected Teacher(Parcel in) {
        key = in.readString();
        name = in.readString();
        avatar = in.readString();
        contacts = in.readParcelable(Contacts.class.getClassLoader());

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
    public Types getType() {
        return type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(name);
        dest.writeString(avatar);
        dest.writeParcelable(contacts, 0);
        dest.writeParcelableArray(null != interests ? interests.toArray(new Interest[0]) : null, 0);
    }

    @Override
    public boolean equals(Object o) {
        boolean equal = false;
        if (o instanceof Teacher) {
            Teacher other = (Teacher) o;
            if (null == key && null == other.key) {
                equal = true;
            } else if (null != key) {
                equal = key.equals(other.key);
            }
        }
        return equal;
    }

    @Override
    public int hashCode() {
        return null != key ? key.hashCode() : 0;
    }
}
