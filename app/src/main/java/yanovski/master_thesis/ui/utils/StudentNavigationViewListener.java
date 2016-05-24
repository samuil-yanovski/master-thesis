package yanovski.master_thesis.ui.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.ButterKnife;
import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Person;
import yanovski.master_thesis.ui.CalendarActivity;
import yanovski.master_thesis.ui.HomeActivity;
import yanovski.master_thesis.ui.LoginActivity;
import yanovski.master_thesis.ui.MyProfileActivity;
import yanovski.master_thesis.ui.TeachersActivity;
import yanovski.master_thesis.ui.ThesesActivity;
import yanovski.master_thesis.ui.base.BaseActivity;
import yanovski.master_thesis.utils.PersonHelper;

/**
 * Created by Samuil on 1/3/2016.
 */
public class StudentNavigationViewListener implements NavigationViewListener, View.OnClickListener {

    ImageView profile;
    TextView username;
    TextView email;

    @Inject
    @Nullable
    Person currentPerson;
    @Inject
    Picasso picasso;
    @Inject
    PersonHelper helper;

    public StudentNavigationViewListener() {
        MasterThesisApplication.getMainComponent()
            .inject(this);
    }

    @Override
    public void load(NavigationView navigationView) {
        navigationView.inflateMenu(R.menu.students_drawer);
        View header = navigationView.inflateHeaderView(R.layout.nav_header_home);
        header.setOnClickListener(this);

        profile = ButterKnife.findById(header, R.id.profile);
        username = ButterKnife.findById(header, R.id.username);
        email = ButterKnife.findById(header, R.id.email);
    }

    @Override
    public void personalize() {
        if (null != currentPerson) {
            username.setText(currentPerson.getName());
            email.setText(currentPerson.getContacts().email);
            picasso.load(currentPerson.getAvatar())
                .placeholder(R.drawable.ic_person_white)
                .error(R.drawable.ic_person_white)
                .transform(new CircleTransform())
                .into(profile);
        }
    }

    @Override
    public boolean onItemSelected(BaseActivity activity, MenuItem item) {
        boolean handled = false;

        int id = item.getItemId();
        if (id == R.id.nav_info) {
            Intent intent = new Intent(activity, HomeActivity.class);
            activity.startActivity(intent);
            handled = true;
        } else if (id == R.id.nav_profile) {
            onProfileClicked(activity);
            handled = true;
        } else if (id == R.id.nav_teachers) {
            Intent intent = new Intent(activity, TeachersActivity.class);
            activity.startActivity(intent);
            handled = true;
        } else if (id == R.id.nav_theses) {
            Intent intent = new Intent(activity, ThesesActivity.class);
            activity.startActivity(intent);
            handled = true;
        } else if (id == R.id.nav_calendar) {
            Intent intent = new Intent(activity, CalendarActivity.class);
            activity.startActivity(intent);
            handled = true;
        } else if (id == R.id.nav_exit) {
            helper.clearAccount(activity);
            Intent intent = new Intent(activity, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
            activity.finish();
            handled = true;
        }

        return handled;
    }

    private void onProfileClicked(Activity activity) {
        Pair<View, String> avatar =
            Pair.create(profile, activity.getString(R.string.transition_avatar));
        Pair<View, String> name =
            Pair.create(username, activity.getString(R.string.transition_name));
        ActivityOptionsCompat options =
            ActivityOptionsCompat.makeSceneTransitionAnimation(activity, avatar, name);

        Intent intent = new Intent(activity, MyProfileActivity.class);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    private void onProfileClicked(Context context) {
        Intent intent = new Intent(context, MyProfileActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Context context = v.getContext();
        if (context instanceof ContextThemeWrapper) {
            context = ((ContextThemeWrapper) context).getBaseContext();
        } else if (context instanceof android.support.v7.view.ContextThemeWrapper) {
            context = ((android.support.v7.view.ContextThemeWrapper) context).getBaseContext();
        }

        if (context instanceof Activity) {
            onProfileClicked((Activity) context);
        } else {
            onProfileClicked(context);
        }
    }
}
