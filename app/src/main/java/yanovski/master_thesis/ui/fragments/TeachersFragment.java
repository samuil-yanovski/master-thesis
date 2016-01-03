package yanovski.master_thesis.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
        TeacherVHCreator.TeacherViewHolder h = (TeacherVHCreator.TeacherViewHolder) holder;

        BaseActivity activity = (BaseActivity) getActivity();
        Pair<View, String> avatar =
            Pair.create(h.avatar, getString(R.string.transition_avatar));
        Pair<View, String> name = Pair.create(h.name, getString(R.string.transition_name));
        ActivityOptionsCompat options =
            ActivityOptionsCompat.makeSceneTransitionAnimation(activity, avatar, name);

        Intent intent = new Intent(getActivity(), TeacherProfileActivity.class);
        intent.putExtra(TeacherProfileActivity.KEY_TEACHER, teacher);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }
}
