package yanovski.master_thesis.data.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Samuil on 12/31/2015.
 */
public class Contacts implements Parcelable {
    public String phone;
    public String skype;
    public String email;

    public Contacts() {}

    protected Contacts(Parcel in) {
        phone = in.readString();
        skype = in.readString();
        email = in.readString();
    }

    public static final Creator<Contacts> CREATOR = new Creator<Contacts>() {
        @Override
        public Contacts createFromParcel(Parcel in) {
            return new Contacts(in);
        }

        @Override
        public Contacts[] newArray(int size) {
            return new Contacts[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(phone);
        dest.writeString(skype);
        dest.writeString(email);
    }
}
