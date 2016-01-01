package yanovski.master_thesis.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import javax.inject.Inject;

/**
 * Created by Samuil on 12/31/2015.
 */
public class PhoneHelper {

    Context context;

    @Inject
    public PhoneHelper(Context context) {
        this.context = context;
    }

    public void call(String phone) {
        if (!TextUtils.isEmpty(phone)) {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:"+Uri.encode(phone.trim())));
            callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(callIntent);
        }
    }
}
