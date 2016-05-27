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

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yanovski.master_thesis.Constants;
import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Teacher;
import yanovski.master_thesis.data.models.api.TeachersResponse;
import yanovski.master_thesis.network.MasterThesisServices;
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
    BaseRecyclerViewAdapter.OnItemClickListener, Callback<TeachersResponse> {

    @Inject
    MasterThesisServices apiServices;

    public TeachersFragment() {
        MasterThesisApplication.getMainComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiServices.getTeachers().enqueue(this);
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

    @Override
    public void onResponse(Call<TeachersResponse> call, Response<TeachersResponse> response) {
        if (response.isSuccessful() && null != response.body()) {
            TeachersAdapter adapter = new TeachersAdapter();
            adapter.setItems(response.body().data);
            adapter.setOnItemClickListener(this);
            getList().setAdapter(adapter);
        }
    }

    @Override
    public void onFailure(Call<TeachersResponse> call, Throwable t) {

    }
}
