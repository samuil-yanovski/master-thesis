package yanovski.master_thesis.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import javax.inject.Inject;

import yanovski.master_thesis.R;

/**
 * Created by Samuil on 12/31/2015.
 */
public class MailHelper {

    public static final String SCHEME = "mailto";

    Context context;

    @Inject
    public MailHelper(Context context) {
        this.context = context;
    }

    public void sendTo(String recipient) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(SCHEME,recipient, null));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {recipient});

        Intent chooser = Intent.createChooser(intent, context.getString(R.string.send_mail));
        chooser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(chooser);
    }

}
