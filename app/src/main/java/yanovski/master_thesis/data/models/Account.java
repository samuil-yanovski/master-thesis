package yanovski.master_thesis.data.models;

/**
 * Created by Samuil on 1/3/2016.
 */
public class Account implements Person {
    public String name;
    public Contacts contacts;
    public String avatar;

    @Override
    public String getAvatar() {
        return avatar;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Contacts getContacts() {
        return contacts;
    }
}
