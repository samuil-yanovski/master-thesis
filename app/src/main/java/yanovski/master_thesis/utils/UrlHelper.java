package yanovski.master_thesis.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import javax.inject.Inject;

/**
 * Created by Samuil on 12/31/2015.
 */
public class UrlHelper {

    Context context;

    @Inject
    public UrlHelper(Context context) {
        this.context = context;
    }

    public void loadDataUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
