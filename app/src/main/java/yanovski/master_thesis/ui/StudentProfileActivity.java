package yanovski.master_thesis.ui;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import yanovski.master_thesis.R;
import yanovski.master_thesis.ui.base.BaseDrawerActivity;

/**
 * Created by Samuil on 12/31/2015.
 */
public class StudentProfileActivity extends BaseDrawerActivity {

    @Bind(R.id.avatar)
    ImageView avatar;
    @Bind(R.id.name)
    TextView name;

    @Override
    protected int getContentLayoutId() {
        return R.layout.content_student_profile;
    }

    @Override
    protected int getCurrentCheckedItemId() {
        return R.id.nav_profile;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {
            Pair<View, String> avatarPair =
                Pair.create(avatar, getString(R.string.transition_avatar));
            Pair<View, String> namePair = Pair.create(name, getString(R.string.transition_name));
            ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this, avatarPair, namePair);

            Intent intent = new Intent(this, EditStudentProfileActivity.class);
            ActivityCompat.startActivity(this, intent, options.toBundle());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
