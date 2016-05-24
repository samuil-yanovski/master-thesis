package yanovski.master_thesis.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import javax.inject.Inject;

import yanovski.master_thesis.Constants;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.BasePerson;
import yanovski.master_thesis.data.models.Person;
import yanovski.master_thesis.data.models.Student;
import yanovski.master_thesis.data.models.Teacher;
import yanovski.master_thesis.data.models.Token;
import yanovski.master_thesis.data.models.Types;
import yanovski.master_thesis.ui.HomeActivity;
import yanovski.master_thesis.ui.StudentsActivity;

/**
 * Created by Samuil on 1/6/2016.
 */
public class PersonHelper {

    private Gson gson;

    @Inject
    public PersonHelper(Gson gson) {
        this.gson = gson;
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

    @Nullable
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

    public void enterApp(Activity activity, Token token) {
        Person person = getPerson(token);
        Account account =
            new Account(person.getContacts().email, activity.getString(R.string.account_type));
        Bundle data = toBundle(token);
        AccountManager accountManager = AccountManager.get(activity);
        accountManager.addAccountExplicitly(account, null, data);
        navigateToMainActivity(activity, person);
    }

    private Person getPerson(Token token) {
        return null != token.owner.student ? token.owner.student : token.owner.teacher;
    }


    private Bundle toBundle(Token token) {
        Bundle data = null;

        Person person = getPerson(token);
        data = toBundle(person);

        if (null == data) {
            data = new Bundle();
        }
        data.putString(Constants.KEY_AUTH_TOKEN, token.auth);
        data.putString(Constants.KEY_REFRESH_TOKEN, token.refresh);

        return data;
    }

    private Bundle toBundle(Person person) {
        Bundle data = new Bundle();

        data.putString(Constants.KEY_TYPE, person.getType()
            .name());
        data.putString(Constants.KEY_PERSON, gson.toJson(person));

        return data;
    }

    private Person toPerson(AccountManager accountManager, Account account) {
        String typeName = accountManager.getUserData(account, Constants.KEY_TYPE);
        Types type = Types.valueOf(typeName);

        String personJson = accountManager.getUserData(account, Constants.KEY_PERSON);
        BasePerson person = null;
        switch (type) {
            case STUDENT: {
                person = gson.fromJson(personJson, Student.class);
                break;
            }
            case TEACHER: {
                person = gson.fromJson(personJson, Teacher.class);
                break;
            }
        }

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
