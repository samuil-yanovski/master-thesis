package yanovski.master_thesis.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Samuil on 1/1/2016.
 */
public class Category implements Parcelable {
    public String id;
    public String name;
    public ArrayList<Thesis> theses;

    public Category() {}

    protected Category(Parcel in) {
        id = in.readString();
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
            if (null == id && null == other.id) {
                equal = true;
            } else if (null != id) {
                equal = id.equals(other.id);
            }
        }
        return equal;
    }

    @Override
    public int hashCode() {
        return null != id ? id.hashCode() : 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeTypedList(theses);
    }
}
