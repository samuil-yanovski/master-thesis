package yanovski.master_thesis.ui.adapters.base.creators;

import android.view.ViewGroup;

import yanovski.master_thesis.ui.adapters.base.BaseRecyclerViewAdapter;
import yanovski.master_thesis.ui.adapters.base.BaseViewHolder;


/**
 * Created by samuil.yanovski on 22/10/2015.
 */
public interface ViewHolderCreator<T> {
    int getItemViewType();
    BaseViewHolder onCreateViewHolder(BaseRecyclerViewAdapter<T> adapter, ViewGroup parent);
    void onBindViewHolder(BaseRecyclerViewAdapter<T> adapter, BaseViewHolder holder, int position);
    boolean canHandle(BaseRecyclerViewAdapter<T> adapter, int position);
    Class getItemClass();
}
