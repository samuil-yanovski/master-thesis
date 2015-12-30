package yanovski.master_thesis.db.models;

import android.content.ContentValues;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.contentresolver.operations.put.DefaultPutResolver;
import com.pushtorefresh.storio.contentresolver.queries.InsertQuery;
import com.pushtorefresh.storio.contentresolver.queries.UpdateQuery;

/**
 * Created by Samuil on 12/30/2015.
 */
public class PhoneContactPutResolver extends DefaultPutResolver<PhoneContact> {
    @NonNull
    @Override
    protected InsertQuery mapToInsertQuery(@NonNull PhoneContact object) {

        return InsertQuery.builder()
            .uri(Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                ContactsContract.Contacts.Data.CONTENT_DIRECTORY))
            .build();
    }

    @NonNull
    @Override
    protected UpdateQuery mapToUpdateQuery(@NonNull PhoneContact object) {
        return UpdateQuery.builder()
            .uri(Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                ContactsContract.Contacts.Data.CONTENT_DIRECTORY))
            .where(ContactsContract.CommonDataKinds.Identity.NAMESPACE + " = ? AND " +
                ContactsContract.CommonDataKinds.Identity.IDENTITY + " = ?")
            .whereArgs(object.namespace, object.identity)
            .build();
    }

    @NonNull
    @Override
    protected ContentValues mapToContentValues(@NonNull PhoneContact object) {
        ContentValues values = new ContentValues();
        values.put(ContactsContract.CommonDataKinds.Email.ADDRESS, object.email);
        values.put(ContactsContract.CommonDataKinds.Email.IS_PRIMARY, object.isPrimary);
        values.put(ContactsContract.CommonDataKinds.Identity.NAMESPACE, object.namespace);
        values.put(ContactsContract.CommonDataKinds.Identity.IDENTITY, object.identity);
        return values;
    }
}
