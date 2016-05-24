package yanovski.master_thesis.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yanovski.master_thesis.Constants;
import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Student;
import yanovski.master_thesis.data.models.api.StudentsResponse;
import yanovski.master_thesis.network.MasterThesisServices;
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
    BaseRecyclerViewAdapter.OnItemClickListener, Callback<StudentsResponse> {

    @Inject
    MasterThesisServices apiServices;

    public StudentsFragment() {
        MasterThesisApplication.getMainComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiServices.getStudents().enqueue(this);
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

    public void returnStudents() {
        ArrayList<Student> students = new ArrayList<>();
        StudentsAdapter adapter = (StudentsAdapter) getList().getAdapter();
        int count = adapter.getItemCount();
        SparseBooleanArray selectionStates =  adapter.getSelectionStates();
        for (int index = 0; index < count; ++index) {
            boolean selected = selectionStates.get(index);
            if (selected)  {
                students.add(adapter.getItem(index));
            }
        }

        FragmentActivity activity = getActivity();
        Intent data = new Intent();
        data.putParcelableArrayListExtra(Constants.KEY_ITEMS, students);
        activity.setResult(Activity.RESULT_OK, data);
        activity.finish();
    }

    public void returnStudent(Student student) {
        FragmentActivity activity = getActivity();
        Intent data = new Intent();
        data.putExtra(Constants.KEY_ITEM, student);
        activity.setResult(Activity.RESULT_OK, data);
        activity.finish();
    }

    public void showStudent(StudentVHCreator.StudentViewHolder holder, Student student) {
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

    @Override
    public void onResponse(Call<StudentsResponse> call, Response<StudentsResponse> response) {
        if (response.isSuccessful() && null != response.body()) {
            List<Student> students = response.body().data;
            StudentsAdapter adapter = new StudentsAdapter();
            adapter.setItems(students);
            adapter.setOnItemClickListener(this);
            getList().setAdapter(adapter);
            switch (getMode()) {
                case MultiSelect:Select: {
                    adapter.setSupportsSelections(true);
                    break;
                }
            }
        }
    }

    @Override
    public void onFailure(Call<StudentsResponse> call, Throwable t) {

    }
}
