package yanovski.master_thesis.permissions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;

import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.EmptyPermissionListener;
import com.karumi.dexter.listener.single.SnackbarOnDeniedPermissionListener;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Samuil on 12/29/2015.
 */
public class SnackBarInfoPermissionListener extends EmptyPermissionListener {

    private final View rootView;
    private final String rationaleText;
    private final SnackbarOnDeniedPermissionListener innerListener;
    private final Callback callback;

    /**
     * @param rootView              Parent view to show the snackbar
     * @param rationaleText         Message displayed if rationale is requested
     * @param deniedText            Message displayed if permission is denied
     * @param buttonText            Message displayed in the snackbar button
     * @param onButtonClickListener Action performed when the user clicks the snackbar button
     */
    private SnackBarInfoPermissionListener(ViewGroup rootView, String rationaleText,
        String deniedText, String buttonText, View.OnClickListener onButtonClickListener,
        Callback callback) {
        this.rootView = rootView;
        this.rationaleText = rationaleText;
        this.innerListener = SnackbarOnDeniedPermissionListener.Builder.with(rootView, deniedText)
            .withButton(buttonText, onButtonClickListener)
            .build();
        this.callback = callback;
    }

    @Override
    public void onPermissionGranted(PermissionGrantedResponse response) {
        super.onPermissionGranted(response);
        if (null != callback) {
            callback.onPermissionRequestResult(response.getPermissionName(), true);
        }
    }

    @Override
    public void onPermissionDenied(PermissionDeniedResponse response) {
        super.onPermissionDenied(response);
        innerListener.onPermissionDenied(response);
        if (null != callback) {
            callback.onPermissionRequestResult(response.getPermissionName(), false);
        }
    }

    @Override
    public void onPermissionRationaleShouldBeShown(PermissionRequest permission,
        PermissionToken token) {
        super.onPermissionRationaleShouldBeShown(permission, token);
        SnackBarListener snackBarListener = new SnackBarListener(token);
        Snackbar snackbar = Snackbar.make(rootView, rationaleText, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(android.R.string.ok, snackBarListener);
        snackbar.setCallback(snackBarListener);
        snackbar.show();
    }

    private static class SnackBarListener extends Snackbar.Callback implements
        View.OnClickListener {

        private final PermissionToken token;
        private final AtomicBoolean isConsumed = new AtomicBoolean(false);

        public SnackBarListener(PermissionToken token) {
            this.token = token;
        }

        @Override
        public void onClick(View v) {
            if (!isConsumed.get()) {
                isConsumed.set(true);
                token.continuePermissionRequest();
            }
        }

        @Override
        public void onDismissed(Snackbar snackbar, int event) {
            super.onDismissed(snackbar, event);
            if (!isConsumed.get()) {
                isConsumed.set(true);
                token.continuePermissionRequest();
            }
        }
    }

    public static interface Callback {
        void onPermissionRequestResult(String permissionName, boolean granted);
    }

    public static class Builder {
        private final ViewGroup rootView;
        private final String rationaleText;
        private final String deniedText;
        private String buttonText;
        private View.OnClickListener onClickListener;
        private Callback callback;

        private Builder(ViewGroup rootView, String rationaleText, String deniedText) {
            this.rootView = rootView;
            this.rationaleText = rationaleText;
            this.deniedText = deniedText;
        }

        public static Builder with(ViewGroup rootView, String rationaleText, String deniedText) {
            return new Builder(rootView, rationaleText, deniedText);
        }

        public static Builder with(ViewGroup rootView, @StringRes int rationaleTextResourceId,
            @StringRes int deniedTextResourceId) {
            return Builder.with(rootView, rootView.getContext()
                .getString(rationaleTextResourceId), rootView.getContext()
                .getString(deniedTextResourceId));
        }

        /**
         * Adds a text button with the provided click listener
         */
        public Builder withOnDeniedButton(String buttonText, View.OnClickListener onClickListener) {
            this.buttonText = buttonText;
            this.onClickListener = onClickListener;
            return this;
        }

        /**
         * Adds a text button with the provided click listener
         */
        public Builder withOnDeniedButton(@StringRes int buttonTextResourceId,
            View.OnClickListener onClickListener) {
            return withOnDeniedButton(rootView.getContext()
                .getString(buttonTextResourceId), onClickListener);
        }

        /**
         * Adds a button that opens the application settings when clicked
         */
        public Builder withOnDeniedOpenSettingsButton(String buttonText) {
            this.buttonText = buttonText;
            this.onClickListener = (v) -> {
                Context context = rootView.getContext();
                Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + context.getPackageName()));
                myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
                myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(myAppSettings);
            };
            return this;
        }

        /**
         * Adds a button that opens the application settings when clicked
         */
        public Builder withOnDeniedOpenSettingsButton(@StringRes int buttonTextResourceId) {
            return withOnDeniedOpenSettingsButton(rootView.getContext()
                .getString(buttonTextResourceId));
        }

        public Builder withCallback(Callback callback) {
            this.callback = callback;
            return this;
        }

        /**
         * Builds a new instance of {@link SnackBarInfoPermissionListener}
         */
        public SnackBarInfoPermissionListener build() {
            return new SnackBarInfoPermissionListener(rootView, rationaleText, deniedText,
                buttonText, onClickListener, callback);
        }
    }
}
