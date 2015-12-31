package yanovski.master_thesis.ui.base;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import butterknife.Bind;
import yanovski.master_thesis.R;

/**
 * Created by Samuil on 12/30/2015.
 */
public class BaseListFragment extends BaseFragment {
    @Bind(R.id.list)
    RecyclerView list;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    public RecyclerView getList() {
        return list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        HorizontalDividerItemDecoration divider =
            new HorizontalDividerItemDecoration.Builder(getActivity()).drawable(
                R.drawable.list_divider)
                .showLastDivider()
                .build();
        list.addItemDecoration(divider);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}
