package yanovski.master_thesis.data.resolvers;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.contentresolver.operations.get.DefaultGetResolver;

import yanovski.master_thesis.data.models.PhoneContact;

/**
 * Created by Samuil on 12/30/2015.
 */
public class PhoneContactGetResolver extends DefaultGetResolver<PhoneContact> {
    @NonNull
    @Override
    public PhoneContact mapFromCursor(@NonNull Cursor cursor) {
        PhoneContact contact = new PhoneContact();
        contact.email = cursor.getString(ProfileQuery.ADDRESS);
        contact.isPrimary = cursor.getInt(ProfileQuery.IS_PRIMARY) != 0;
        contact.namespace = cursor.getString(ProfileQuery.NAMESPACE);
        contact.identity = cursor.getString(ProfileQuery.IDENTITY);
        return contact;
    }

    public interface ProfileQuery {
        String[] PROJECTION = {ContactsContract.CommonDataKinds.Email.ADDRESS,
            ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
            ContactsContract.CommonDataKinds.Identity.NAMESPACE,
            ContactsContract.CommonDataKinds.Identity.IDENTITY};

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
        int NAMESPACE = 2;
        int IDENTITY = 3;;
    }
}
