package yanovski.master_thesis.data.models;

/**
 * Created by samuil.yanovski on 08/01/2016.
 */
public abstract class BasePerson implements Person {
    public String avatar;
    public String name;
    public Contacts contacts;

    @Override
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }
}
