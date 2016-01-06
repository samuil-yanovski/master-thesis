package yanovski.master_thesis.data.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Samuil on 1/1/2016.
 */
public class Thesis implements Parcelable{
    public String title;
    public String description;
    public Teacher author;
    public Category category;

    public Thesis() {}

    protected Thesis(Parcel in) {
        title = in.readString();
        description = in.readString();
        author = in.readParcelable(Teacher.class.getClassLoader());
    }

    public static final Creator<Thesis> CREATOR = new Creator<Thesis>() {
        @Override
        public Thesis createFromParcel(Parcel in) {
            return new Thesis(in);
        }

        @Override
        public Thesis[] newArray(int size) {
            return new Thesis[size];
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
        dest.writeParcelable(author, flags);
    }
}
