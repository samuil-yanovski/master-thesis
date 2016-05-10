package yanovski.master_thesis.data.models.api;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import yanovski.master_thesis.data.models.Category;

/**
 * Created by Samuil on 5/8/2016.
 */
public class CategoriesResponse extends BaseResponse implements Parcelable {
    public List<Category> data;

    public CategoriesResponse() {}

    protected CategoriesResponse(Parcel in) {
        super(in);
        data = in.createTypedArrayList(Category.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(data);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CategoriesResponse> CREATOR = new Creator<CategoriesResponse>() {
        @Override
        public CategoriesResponse createFromParcel(Parcel in) {
            return new CategoriesResponse(in);
        }

        @Override
        public CategoriesResponse[] newArray(int size) {
            return new CategoriesResponse[size];
        }
    };
}
