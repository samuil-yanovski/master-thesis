package yanovski.master_thesis.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.data.models.Category;
import yanovski.master_thesis.data.models.Person;
import yanovski.master_thesis.data.models.Teacher;
import yanovski.master_thesis.data.models.api.CategoriesResponse;
import yanovski.master_thesis.network.MasterThesisServices;
import yanovski.master_thesis.ui.adapters.ThesesAdapter;
import yanovski.master_thesis.ui.base.BaseListFragment;

/**
 * Created by Samuil on 12/30/2015.
 */
public class ThesesFragment extends BaseListFragment implements Callback<CategoriesResponse> {

    @Inject
    @Nullable
    Person person;
    @Inject
    MasterThesisServices apiServices;

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
        apiServices.getCategories().enqueue(this);

    }

    @Override
    protected void addDecorations(RecyclerView list) {
    }

    @Override
    public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
        if (response.isSuccessful() && null != response.body()) {
            ThesesAdapter adapter = new ThesesAdapter();

            List<Category> categories = response.body().data;
            adapter.addCategories(categories);

            getList().setAdapter(adapter);
        }
    }

    @Override
    public void onFailure(Call<CategoriesResponse> call, Throwable t) {

    }
}
