package yanovski.master_thesis.data.models.api;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Samuil on 5/8/2016.
 */
public class NewThesis implements Parcelable {
    public String title;
    public String description;
    public String category;

    public NewThesis() {}

    protected NewThesis(Parcel in) {
        title = in.readString();
        description = in.readString();
        category = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(category);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewThesis> CREATOR = new Creator<NewThesis>() {
        @Override
        public NewThesis createFromParcel(Parcel in) {
            return new NewThesis(in);
        }

        @Override
        public NewThesis[] newArray(int size) {
            return new NewThesis[size];
        }
    };
}
