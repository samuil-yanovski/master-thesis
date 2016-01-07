package yanovski.master_thesis.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import icepick.State;
import java8.util.stream.StreamSupport;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Interest;
import yanovski.master_thesis.data.models.Person;
import yanovski.master_thesis.data.models.Teacher;
import yanovski.master_thesis.ui.base.BaseProfileFragment;

/**
 * Created by Samuil on 12/30/2015.
 */
public class TeacherProfileFragment extends BaseProfileFragment {

    @Bind(R.id.interests)
    ViewGroup interestsContainer;

    @State
    Teacher teacher;

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    protected Person getPerson() {
        return teacher;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_teacher_profile;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Interest> interests = teacher.interests;

        if (null != interests) {
            interestsContainer.removeAllViews();
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            StreamSupport.stream(interests)
                .forEach(i -> {
                    interestsContainer.addView(createInterestRow(inflater, interestsContainer, i));
                    inflater.inflate(R.layout.divider_interest, interestsContainer, true);
                });
        }
    }

    private View createInterestRow(LayoutInflater inflater, ViewGroup container, Interest interest) {
        View view = inflater.inflate(R.layout.item_interest, container, false);
        TextView title = ButterKnife.findById(view, R.id.title);
        title.setText(interest.name);

        return view;
    }
}
