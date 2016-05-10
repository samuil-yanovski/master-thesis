package yanovski.master_thesis.data.models.api;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Samuil on 5/8/2016.
 */
public class Account extends Credentials implements Parcelable {
    public String name;
    public String image;
    public String type;

    public Account() {}

    protected Account(Parcel in) {
        super(in);
        name = in.readString();
        image = in.readString();
        type = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(name);
        dest.writeString(image);
        dest.writeString(type);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };
}
