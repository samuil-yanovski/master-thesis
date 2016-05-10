package yanovski.master_thesis.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import yanovski.master_thesis.data.models.api.DateTimeData;

/**
 * Created by Samuil on 5/8/2016.
 */
public class Token implements Parcelable {
    public String auth;
    public String refresh;
    public DateTimeData expirationDate;

    public Token() {}

    protected Token(Parcel in) {
        auth = in.readString();
        refresh = in.readString();
        expirationDate = in.readParcelable(DateTimeData.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(auth);
        dest.writeString(refresh);
        dest.writeParcelable(expirationDate, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Token> CREATOR = new Creator<Token>() {
        @Override
        public Token createFromParcel(Parcel in) {
            return new Token(in);
        }

        @Override
        public Token[] newArray(int size) {
            return new Token[size];
        }
    };
}
