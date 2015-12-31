package yanovski.master_thesis.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;

import com.karumi.dexter.Dexter;

import javax.inject.Inject;

import yanovski.master_thesis.R;
import yanovski.master_thesis.permissions.SnackBarInfoPermissionListener;

/**
 * Created by Samuil on 12/31/2015.
 */
public class UrlHelper {

    @Inject
    public UrlHelper() {

    }

    public void loadDataUrl(View contextView, String url) {
        Context context = contextView.getContext();
        SnackBarInfoPermissionListener permissionListener =
            SnackBarInfoPermissionListener.Builder.with((ViewGroup) contextView.getParent(),
                R.string.storage_permission_rationale, R.string.denied_permission_text)
                .withOnDeniedOpenSettingsButton(R.string.settings)
                .withCallback(new RequestHandler(context, url))
                .build();
        Dexter.checkPermission(permissionListener, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    private static final class RequestHandler implements SnackBarInfoPermissionListener.Callback {

        private Context context;
        private String url;

        public RequestHandler(Context context, String url) {
            this.context = context;
            this.url = url;
        }

        @Override
        public void onPermissionRequestResult(boolean granted) {
            if (granted) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        }
    }
}
