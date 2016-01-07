package yanovski.master_thesis.ui.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import javax.inject.Inject;

import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.di.ForColorPrimary;

/**
 * Created by Samuil on 1/7/2016.
 */
public class AvatarTarget implements Target, Palette.PaletteAsyncListener {

    private ImageView avatar;
    private TextView name;
    private View header;

    @Inject
    @ForColorPrimary
    int colorPrimary;
    int defaultTextColor;

    public AvatarTarget(ImageView avatar, TextView name, View header) {
        this.avatar = avatar;
        this.name = name;
        this.header = header;
        this.defaultTextColor = ContextCompat.getColor(avatar.getContext(),
            android.R.color.primary_text_dark);
        MasterThesisApplication.getMainComponent()
            .inject(this);
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        avatar.setImageBitmap(bitmap);
        Palette.from(bitmap)
            .generate(this);
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {
        avatar.setImageDrawable(errorDrawable);
        header.setBackgroundColor(colorPrimary);
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {
        avatar.setImageDrawable(placeHolderDrawable);
    }

    @Override
    public void onGenerated(Palette palette) {
        int background = palette.getDarkMutedColor(colorPrimary);
        int textColor = palette.getLightVibrantColor(defaultTextColor);

        header.setBackgroundColor(background);
        name.setTextColor(textColor);
    }
}
