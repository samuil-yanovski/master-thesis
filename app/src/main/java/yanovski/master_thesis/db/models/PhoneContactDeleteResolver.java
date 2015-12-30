package yanovski.master_thesis.db.models;

import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.contentresolver.operations.delete.DefaultDeleteResolver;
import com.pushtorefresh.storio.contentresolver.queries.DeleteQuery;

/**
 * Created by Samuil on 12/30/2015.
 */
public class PhoneContactDeleteResolver extends DefaultDeleteResolver<PhoneContact> {
    @NonNull
    @Override
    protected DeleteQuery mapToDeleteQuery(@NonNull PhoneContact object) {
        return DeleteQuery.builder()
            .uri(Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                ContactsContract.Contacts.Data.CONTENT_DIRECTORY))
            .where(ContactsContract.CommonDataKinds.Identity.NAMESPACE + " = ? AND " +
                ContactsContract.CommonDataKinds.Identity.IDENTITY + " = ?")
            .whereArgs(object.namespace, object.identity)
            .build();
    }
}
