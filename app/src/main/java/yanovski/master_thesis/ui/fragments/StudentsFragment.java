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
import yanovski.master_thesis.data.models.Student;
import yanovski.master_thesis.ui.StudentProfileActivity;
import yanovski.master_thesis.ui.adapters.StudentsAdapter;
import yanovski.master_thesis.ui.adapters.base.BaseRecyclerViewAdapter;
import yanovski.master_thesis.ui.adapters.base.creators.StudentVHCreator;
import yanovski.master_thesis.ui.base.BaseActivity;
import yanovski.master_thesis.ui.base.BaseListFragment;

/**
 * Created by Samuil on 12/30/2015.
 */
public class StudentsFragment extends BaseListFragment implements
    BaseRecyclerViewAdapter.OnItemClickListener {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StudentsAdapter adapter = new StudentsAdapter();
        adapter.setItems(LocalDataProvider.getAllStudents());
        adapter.setOnItemClickListener(this);
        getList().setAdapter(adapter);
        switch (getMode()) {
            case MultiSelect:Select: {
                adapter.setSupportsSelections(true);
                break;
            }
        }
    }

    @Override
    public void onItemClick(BaseRecyclerViewAdapter<?> adapter, RecyclerView.ViewHolder holder,
        int position) {
        Student student = (Student) adapter.getItem(position);
        switch (getMode()) {
            case View: {
                showStudent((StudentVHCreator.StudentViewHolder) holder, student);
                break;
            }
            case Select: {
                returnStudent(student);
                break;
            }
        }

    }

    private void returnStudent(Student student) {
        FragmentActivity activity = getActivity();
        Intent data = new Intent();
        data.putExtra(Constants.KEY_ITEM, student);
        activity.setResult(Activity.RESULT_OK, data);
        activity.finish();
    }

    private void showStudent(StudentVHCreator.StudentViewHolder holder, Student student) {
        BaseActivity activity = (BaseActivity) getActivity();
        Pair<View, String> avatar =
            Pair.create(holder.avatar, getString(R.string.transition_avatar));
        Pair<View, String> name = Pair.create(holder.name, getString(R.string.transition_name));
        ActivityOptionsCompat options =
            ActivityOptionsCompat.makeSceneTransitionAnimation(activity, avatar, name);

        Intent intent = new Intent(getActivity(), StudentProfileActivity.class);
        intent.putExtra(StudentProfileActivity.KEY_STUDENT, student);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }
}
