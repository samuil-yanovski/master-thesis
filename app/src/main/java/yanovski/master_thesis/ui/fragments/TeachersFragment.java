package yanovski.master_thesis.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import yanovski.master_thesis.Constants;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.LocalDataProvider;
import yanovski.master_thesis.data.models.Teacher;
import yanovski.master_thesis.ui.TeacherProfileActivity;
import yanovski.master_thesis.ui.adapters.TeachersAdapter;
import yanovski.master_thesis.ui.adapters.base.BaseRecyclerViewAdapter;
import yanovski.master_thesis.ui.adapters.base.creators.TeacherVHCreator;
import yanovski.master_thesis.ui.base.BaseActivity;
import yanovski.master_thesis.ui.base.BaseListFragment;

/**
 * Created by Samuil on 12/30/2015.
 */
public class TeachersFragment extends BaseListFragment implements
    BaseRecyclerViewAdapter.OnItemClickListener {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TeachersAdapter adapter = new TeachersAdapter();
        adapter.setItems(LocalDataProvider.getAllTeachers());
        adapter.setOnItemClickListener(this);
        getList().setAdapter(adapter);
    }

    @Override
    public void onItemClick(BaseRecyclerViewAdapter<?> adapter, RecyclerView.ViewHolder holder,
        int position) {
        Teacher teacher = (Teacher) adapter.getItem(position);
        switch (getMode()) {
            case View: {
                showTeacher((TeacherVHCreator.TeacherViewHolder) holder, teacher);
                break;
            }
            case Select: {
                returnTeacher(teacher);
                break;
            }
        }
    }

    private void returnTeacher(Teacher teacher) {
        FragmentActivity activity = getActivity();
        Intent data = new Intent();
        data.putExtra(Constants.KEY_ITEM, teacher);
        activity.setResult(Activity.RESULT_OK, data);
        activity.finish();
    }

    private void showTeacher(TeacherVHCreator.TeacherViewHolder holder, Teacher teacher) {
        BaseActivity activity = (BaseActivity) getActivity();
        Pair<View, String> avatar =
            Pair.create(holder.avatar, getString(R.string.transition_avatar));
        Pair<View, String> name = Pair.create(holder.name, getString(R.string.transition_name));
        ActivityOptionsCompat options =
            ActivityOptionsCompat.makeSceneTransitionAnimation(activity, avatar, name);

        Intent intent = new Intent(getActivity(), TeacherProfileActivity.class);
        intent.putExtra(TeacherProfileActivity.KEY_TEACHER, teacher);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }
}
