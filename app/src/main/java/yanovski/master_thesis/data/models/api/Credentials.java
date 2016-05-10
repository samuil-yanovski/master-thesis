package yanovski.master_thesis.data.models.api;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Samuil on 5/8/2016.
 */
public class Credentials implements Parcelable {
    public String email;
    public String password;

    public Credentials() {}

    protected Credentials(Parcel in) {
        email = in.readString();
        password = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(password);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Credentials> CREATOR = new Creator<Credentials>() {
        @Override
        public Credentials createFromParcel(Parcel in) {
            return new Credentials(in);
        }

        @Override
        public Credentials[] newArray(int size) {
            return new Credentials[size];
        }
    };
}
