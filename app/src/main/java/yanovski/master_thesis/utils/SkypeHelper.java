package yanovski.master_thesis.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import javax.inject.Inject;

import yanovski.master_thesis.R;

/**
 * Created by Samuil on 12/31/2015.
 */
public class SkypeHelper {

    public static final String PROTOCOL_FORMAT = "skype:%s?chat";

    Context context;

    @Inject
    public SkypeHelper(Context context) {
        this.context = context;
    }

    public void chatTo(String recipient) {
        String skypeProtocol = String.format(PROTOCOL_FORMAT, recipient);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(skypeProtocol));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});

        Intent chooser =
            Intent.createChooser(intent, context.getString(R.string.send_skype_message));
        chooser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(chooser);
    }

}
