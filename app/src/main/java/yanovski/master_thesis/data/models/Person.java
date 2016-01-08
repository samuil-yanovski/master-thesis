package yanovski.master_thesis.data.models;

import android.os.Parcelable;

/**
 * Created by Samuil on 1/3/2016.
 */
public interface Person extends Parcelable {
    String getAvatar();
    String getName();
    Contacts getContacts();
    Types getType();
}
