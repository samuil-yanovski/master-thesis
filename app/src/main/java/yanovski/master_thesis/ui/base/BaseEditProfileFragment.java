package yanovski.master_thesis.ui.base;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.karumi.dexter.Dexter;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import icepick.State;
import pl.aprilapps.easyphotopicker.EasyImage;
import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Contacts;
import yanovski.master_thesis.data.models.Person;
import yanovski.master_thesis.permissions.SnackBarInfoPermissionListener;
import yanovski.master_thesis.ui.utils.CircleTransform;

/**
 * Created by Samuil on 12/29/2015.
 */
public abstract class BaseEditProfileFragment extends BaseFragment implements
    SnackBarInfoPermissionListener.Callback, EasyImage.Callbacks {

    public abstract Person getPerson();

    // UI references.
    @Bind(R.id.avatar)
    ImageView avatar;
    @Bind(R.id.name)
    @NotEmpty
    EditText name;
    @Bind(R.id.phone)
    EditText phone;
    @Bind(R.id.email)
    EditText email;
    @Bind(R.id.skype)
    EditText skype;
    @Bind(R.id.login_progress)
    View progressView;

    @Inject
    Picasso picasso;
    @Inject
    Context context;
    Transformation transformation;

    @State
    File imageFile;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MasterThesisApplication.getMainComponent().inject(this);
        transformation = new CircleTransform();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Person person = getPerson();
        if (null == person) {
            getActivity().finish();
            return;
        }

        picasso.load(person.getAvatar())
            .placeholder(R.drawable.ic_person_white)
            .error(R.drawable.ic_person_white)
            .transform(transformation)
            .into(avatar);

        Contacts contacts = person.getContacts();
        name.setText(person.getName());
        phone.setText(contacts.phone);
        email.setText(contacts.email);
        skype.setText(contacts.skype);
    }

    @OnClick(R.id.avatar)
    protected void onAvatarClicked() {
        SnackBarInfoPermissionListener permissionListener =
            SnackBarInfoPermissionListener.Builder.with((ViewGroup) getView(),
                R.string.profile_storage_permission_rationale, R.string.denied_permission_text)
                .withOnDeniedOpenSettingsButton(R.string.settings)
                .withCallback(this)
                .build();
        Dexter.checkPermission(permissionListener, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @OnEditorAction(R.id.skype)
    protected boolean onIMEClicked() {
        send();
        return true;
    }

    public void send() {
        validate();
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        progressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getActivity());

            // Display error messages ;)
            ((EditText) view).setError(message);
        }
    }

    @Override
    public void onPermissionRequestResult(String permissionName, boolean granted) {
        if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissionName) && granted) {
            SnackBarInfoPermissionListener permissionListener =
                SnackBarInfoPermissionListener.Builder.with((ViewGroup) getView(),
                    R.string.profile_camera_permission_rationale, R.string.denied_permission_text)
                    .withOnDeniedOpenSettingsButton(R.string.settings)
                    .withCallback(this)
                    .build();
            Dexter.checkPermission(permissionListener, Manifest.permission.CAMERA);
        } else if (Manifest.permission.CAMERA.equals(permissionName)) {
            if (granted) {
                EasyImage.openChooser(this, getString(R.string.avatar_chooser), true);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), this);
    }

    @Override
    public void onImagePickerError(Exception e, EasyImage.ImageSource imageSource) {

    }

    @Override
    public void onImagePicked(File file, EasyImage.ImageSource imageSource) {
        imageFile = file;
        loadAvatar();
    }

    @Override
    public void onCanceled(EasyImage.ImageSource imageSource) {
        //Cancel handling, you might wanna remove taken photo if it was canceled
        if (imageSource == EasyImage.ImageSource.CAMERA) {
            File photoFile = EasyImage.lastlyTakenButCanceledPhoto(context);
            if (photoFile != null) photoFile.delete();
        }
    }

    private void loadAvatar() {
        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        avatar.setImageBitmap(transformation.transform(bitmap));
    }
}
