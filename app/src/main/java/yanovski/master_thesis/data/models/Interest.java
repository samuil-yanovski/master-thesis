package yanovski.master_thesis.data.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Samuil on 12/31/2015.
 */
public class Interest implements Parcelable {

    public String name;

    public Interest() {}

    protected Interest(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Interest> CREATOR = new Creator<Interest>() {
        @Override
        public Interest createFromParcel(Parcel in) {
            return new Interest(in);
        }

        @Override
        public Interest[] newArray(int size) {
            return new Interest[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
