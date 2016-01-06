package yanovski.master_thesis.di;

import android.content.Context;

import com.pushtorefresh.storio.contentresolver.ContentResolverTypeMapping;
import com.pushtorefresh.storio.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.impl.DefaultStorIOContentResolver;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import yanovski.master_thesis.data.LocalDataProvider;
import yanovski.master_thesis.data.models.Person;
import yanovski.master_thesis.data.models.PhoneContact;
import yanovski.master_thesis.data.resolvers.PhoneContactDeleteResolver;
import yanovski.master_thesis.data.resolvers.PhoneContactGetResolver;
import yanovski.master_thesis.data.resolvers.PhoneContactPutResolver;

/**
 * Created by Samuil on 12/30/2015.
 */
@Module
public class DBModule {

    @Singleton
    @Provides
    @ForPhoneContact
    public ContentResolverTypeMapping<PhoneContact> getPhoneContactMapping() {
        return ContentResolverTypeMapping.<PhoneContact>builder().putResolver(
            new PhoneContactPutResolver())
            .getResolver(new PhoneContactGetResolver())
            .deleteResolver(new PhoneContactDeleteResolver())
            .build();
    }

    @Singleton
    @Provides
    public StorIOContentResolver getStorIOContentResolver(Context context,
        @ForPhoneContact ContentResolverTypeMapping<PhoneContact> phoneContactTypeMapping) {
        return DefaultStorIOContentResolver.builder()
            .contentResolver(context.getContentResolver())
            .addTypeMapping(PhoneContact.class, phoneContactTypeMapping)
            .build();
    }

    @Singleton
    @Provides
    public Person getCurrentPerson() {
        return LocalDataProvider.createSamuil();
    }
}
