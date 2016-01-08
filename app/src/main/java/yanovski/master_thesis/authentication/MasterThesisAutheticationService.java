package yanovski.master_thesis.authentication;

import android.accounts.AbstractAccountAuthenticator;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MasterThesisAutheticationService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        AbstractAccountAuthenticator authenticator = new MasterThesisAuthenticator(this);
        return authenticator.getIBinder();
    }
}
