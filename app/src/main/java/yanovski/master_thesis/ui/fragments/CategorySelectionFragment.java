package yanovski.master_thesis.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yanovski.master_thesis.Constants;
import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.data.models.Category;
import yanovski.master_thesis.data.models.api.CategoriesResponse;
import yanovski.master_thesis.network.MasterThesisServices;
import yanovski.master_thesis.ui.adapters.CategoriesSelectionAdapter;
import yanovski.master_thesis.ui.adapters.base.BaseRecyclerViewAdapter;
import yanovski.master_thesis.ui.base.BaseListFragment;

/**
 * Created by Samuil on 12/30/2015.
 */
public class CategorySelectionFragment extends BaseListFragment implements
    BaseRecyclerViewAdapter.OnItemClickListener, Callback<CategoriesResponse> {

    @Inject
    MasterThesisServices apiServices;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        MasterThesisApplication.getMainComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiServices.getCategories().enqueue(this);
    }

    @Override
    public void onItemClick(BaseRecyclerViewAdapter<?> adapter, RecyclerView.ViewHolder holder,
        int position) {
        Category category = (Category) adapter.getItem(position);
        switch (getMode()) {
            case Select: {
                returnCategory(category);
                break;
            }
        }
    }

    private void returnCategory(Category category) {
        FragmentActivity activity = getActivity();
        Intent data = new Intent();
        data.putExtra(Constants.KEY_ITEM, category);
        activity.setResult(Activity.RESULT_OK, data);
        activity.finish();
    }

    @Override
    public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
        if (response.isSuccessful() && null != response.body()) {
            CategoriesSelectionAdapter adapter = new CategoriesSelectionAdapter();
            adapter.setItems(response.body().data);
            adapter.setOnItemClickListener(this);
            getList().setAdapter(adapter);
        }
    }

    @Override
    public void onFailure(Call<CategoriesResponse> call, Throwable t) {

    }
}
