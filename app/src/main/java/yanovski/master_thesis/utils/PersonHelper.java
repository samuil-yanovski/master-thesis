package yanovski.master_thesis.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import javax.inject.Inject;

import yanovski.master_thesis.Constants;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.BasePerson;
import yanovski.master_thesis.data.models.Contacts;
import yanovski.master_thesis.data.models.Person;
import yanovski.master_thesis.data.models.Student;
import yanovski.master_thesis.data.models.Teacher;
import yanovski.master_thesis.data.models.Types;
import yanovski.master_thesis.ui.HomeActivity;
import yanovski.master_thesis.ui.StudentsActivity;

/**
 * Created by Samuil on 1/6/2016.
 */
public class PersonHelper {

    @Inject
    public PersonHelper() {
    }

    @SuppressLint("NewApi")
    public void clearAccount(Context context) {
        AccountManager accountManager = AccountManager.get(context);
        Account[] accounts =
            accountManager.getAccountsByType(context.getString(R.string.account_type));

        for (Account account : accounts) {
            if (Build.VERSION_CODES.LOLLIPOP_MR1 >= Build.VERSION.SDK_INT) {
                accountManager.removeAccountExplicitly(account);
            } else {
                accountManager.removeAccount(account, null, null);
            }
        }
    }

    public Person getCurrentPerson(Context context) {
        Person person = null;

        AccountManager accountManager = AccountManager.get(context);
        Account[] accounts =
            accountManager.getAccountsByType(context.getString(R.string.account_type));

        if (!ArrayUtils.isEmpty(accounts)) {
            person = toPerson(accountManager, accounts[0]);
        }

        return person;
    }

    public void tryAutoLogin(Activity activity) {
        Person person = getCurrentPerson(activity);
        if (null != person) {
            navigateToMainActivity(activity, person);
        }
    }

    public void enterApp(Activity activity, Person person) {
        Account account =
            new Account(person.getContacts().email, activity.getString(R.string.account_type));
        Bundle data = toBundle(person);
        AccountManager accountManager = AccountManager.get(activity);
        accountManager.addAccountExplicitly(account, null, data);
        navigateToMainActivity(activity, person);
    }

    private Bundle toBundle(Person person) {
        Bundle data = new Bundle();

        Contacts contacts = person.getContacts();
        data.putString(Constants.KEY_NAME, person.getName());
        data.putString(Constants.KEY_AVATAR, person.getAvatar());
        data.putString(Constants.KEY_TYPE, person.getType()
            .name());
        data.putString(Constants.KEY_PHONE, contacts.phone);
        data.putString(Constants.KEY_EMAIL, contacts.email);
        data.putString(Constants.KEY_SKYPE, contacts.skype);

        return data;
    }

    private Person toPerson(AccountManager accountManager, Account account) {
        String typeName = accountManager.getUserData(account, Constants.KEY_TYPE);
        Types type = Types.valueOf(typeName);

        BasePerson person = null;
        switch (type) {
            case STUDENT: {
                person = new Student();
                break;
            }
            case TEACHER: {
                person = new Teacher();
                break;
            }
        }
        person.contacts = new Contacts();

        person.name = accountManager.getUserData(account, Constants.KEY_NAME);
        person.avatar = accountManager.getUserData(account, Constants.KEY_AVATAR);
        person.contacts.phone = accountManager.getUserData(account, Constants.KEY_PHONE);
        person.contacts.email = accountManager.getUserData(account, Constants.KEY_EMAIL);
        person.contacts.skype = accountManager.getUserData(account, Constants.KEY_SKYPE);

        return person;
    }

    private void navigateToMainActivity(Activity activity, Person person) {
        switch (person.getType()) {
            case STUDENT: {
                Intent intent = new Intent(activity, HomeActivity.class);
                activity.startActivity(intent);
                activity.finish();
                break;
            }
            case TEACHER: {
                Intent intent = new Intent(activity, StudentsActivity.class);
                activity.startActivity(intent);
                activity.finish();
                break;
            }
        }
    }
}
