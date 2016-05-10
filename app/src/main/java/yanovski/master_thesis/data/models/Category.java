package yanovski.master_thesis.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Samuil on 1/1/2016.
 */
public class Category implements Parcelable {
    public String key;
    public String name;
    public ArrayList<Thesis> theses;

    public Category() {}

    protected Category(Parcel in) {
        key = in.readString();
        name = in.readString();
        theses = in.createTypedArrayList(Thesis.CREATOR);
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        boolean equal = false;
        if (o instanceof Category) {
            Category other = (Category) o;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(name);
        dest.writeTypedList(theses);
    }
}
