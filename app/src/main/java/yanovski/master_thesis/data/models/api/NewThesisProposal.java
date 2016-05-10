package yanovski.master_thesis.data.models.api;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Samuil on 5/8/2016.
 */
public class NewThesisProposal implements Parcelable {
    public String title;
    public String description;
    public String category;
    public String author;

    public NewThesisProposal() {}

    protected NewThesisProposal(Parcel in) {
        title = in.readString();
        description = in.readString();
        category = in.readString();
        author = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(category);
        dest.writeString(author);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewThesisProposal> CREATOR = new Creator<NewThesisProposal>() {
        @Override
        public NewThesisProposal createFromParcel(Parcel in) {
            return new NewThesisProposal(in);
        }

        @Override
        public NewThesisProposal[] newArray(int size) {
            return new NewThesisProposal[size];
        }
    };
}
