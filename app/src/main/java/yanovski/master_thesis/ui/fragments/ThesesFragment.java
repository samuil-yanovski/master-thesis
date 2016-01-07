package yanovski.master_thesis.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.data.LocalDataProvider;
import yanovski.master_thesis.data.models.Category;
import yanovski.master_thesis.data.models.Person;
import yanovski.master_thesis.data.models.Teacher;
import yanovski.master_thesis.data.models.ThesisProposal;
import yanovski.master_thesis.data.models.Types;
import yanovski.master_thesis.ui.adapters.ThesesAdapter;
import yanovski.master_thesis.ui.base.BaseListFragment;

/**
 * Created by Samuil on 12/30/2015.
 */
public class ThesesFragment extends BaseListFragment {

    @Inject
    Person person;

    Teacher teacher;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MasterThesisApplication.getMainComponent().inject(this);
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ThesesAdapter adapter = new ThesesAdapter();

        if (Types.TEACHER.equals(person.getType())) {
            List<ThesisProposal> proposals = LocalDataProvider.getAllThesisProposals();
            adapter.addThesisProposal(proposals);
        }

        List<Category> categories = LocalDataProvider.getAllCategories(teacher);
        adapter.addCategories(categories);

        getList().setAdapter(adapter);
    }

    @Override
    protected void addDecorations(RecyclerView list) {
    }
}
