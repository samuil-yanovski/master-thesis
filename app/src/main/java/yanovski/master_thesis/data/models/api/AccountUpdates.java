package yanovski.master_thesis.data.models.api;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Samuil on 5/25/2016.
 */
public class AccountUpdates implements Parcelable {
    public String name;
    public String image;
    public String skype;
    public String email;
    public String phone;

    public AccountUpdates() {}

    protected AccountUpdates(Parcel in) {
        name = in.readString();
        image = in.readString();
        skype = in.readString();
        email = in.readString();
        phone = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(image);
        dest.writeString(skype);
        dest.writeString(email);
        dest.writeString(phone);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AccountUpdates> CREATOR = new Creator<AccountUpdates>() {
        @Override
        public AccountUpdates createFromParcel(Parcel in) {
            return new AccountUpdates(in);
        }

        @Override
        public AccountUpdates[] newArray(int size) {
            return new AccountUpdates[size];
        }
    };
}
