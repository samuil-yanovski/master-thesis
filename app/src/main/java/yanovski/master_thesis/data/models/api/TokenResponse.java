package yanovski.master_thesis.data.models.api;

import android.os.Parcel;
import android.os.Parcelable;

import yanovski.master_thesis.data.models.Token;

/**
 * Created by Samuil on 5/8/2016.
 */
public class TokenResponse extends BaseResponse implements Parcelable {
    public Token data;

    public TokenResponse() {}

    protected TokenResponse(Parcel in) {
        super(in);
        data = in.readParcelable(Token.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(data, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TokenResponse> CREATOR = new Creator<TokenResponse>() {
        @Override
        public TokenResponse createFromParcel(Parcel in) {
            return new TokenResponse(in);
        }

        @Override
        public TokenResponse[] newArray(int size) {
            return new TokenResponse[size];
        }
    };
}
