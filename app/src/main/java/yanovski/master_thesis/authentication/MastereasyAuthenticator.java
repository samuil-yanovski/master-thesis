package yanovski.master_thesis.authentication;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import yanovski.master_thesis.BuildConfig;
import yanovski.master_thesis.R;
import yanovski.master_thesis.ui.LoginActivity;

/**
 * Created by samuil.yanovski on 06/01/2016.
 */
public class MastereasyAuthenticator extends AbstractAccountAuthenticator {

    Context context;

    public MastereasyAuthenticator(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        return null;
    }

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType,
        String authTokenType, String[] requiredFeatures,
        Bundle options) throws NetworkErrorException {
        return createRedirectToLoginIntent();
    }

    @NonNull
    private Bundle createRedirectToLoginIntent() {
        Intent intent = new Intent(context, LoginActivity.class);

        final Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);

        return bundle;
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account,
        Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account,
        String authTokenType, Bundle options) throws NetworkErrorException {
        String authToken = null;

        String accountTypeId =
            BuildConfig.APPLICATION_ID + context.getString(R.string.account_type_suffix);
        final AccountManager accountManager = AccountManager.get(context);
        Account[] accounts = accountManager.getAccountsByType(accountTypeId);
        if(accounts.length > 0) {
            final Account currentAccount = accounts[0];
            if(currentAccount.name.equals(account.name)) {
                authToken = accountManager.peekAuthToken(currentAccount, authTokenType);
            }
        }

        if (TextUtils.isEmpty(authToken)) {
            return createRedirectToLoginIntent();
        } else {
            final Bundle result = new Bundle();
            result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
            result.putString(AccountManager.KEY_AUTHTOKEN, authToken);
            return result;
        }
    }

    @Override
    public String getAuthTokenLabel(String authTokenType) {

        return null;
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account,
        String authTokenType, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account,
        String[] features) throws NetworkErrorException {
        final Bundle result = new Bundle();
        result.putBoolean(AccountManager.KEY_BOOLEAN_RESULT, false);
        return result;
    }
}
