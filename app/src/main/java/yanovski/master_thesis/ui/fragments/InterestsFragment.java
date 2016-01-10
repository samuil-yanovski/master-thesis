package yanovski.master_thesis.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import icepick.State;
import yanovski.master_thesis.Constants;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Interest;
import yanovski.master_thesis.ui.adapters.InterestsAdapter;
import yanovski.master_thesis.ui.base.BaseListFragment;

/**
 * Created by Samuil on 12/30/2015.
 */
public class InterestsFragment extends BaseListFragment implements MaterialDialog.InputCallback {

    @State
    ArrayList<Interest> interests;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_with_fab;
    }

    public ArrayList<Interest> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<Interest> interests) {
        this.interests = interests;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InterestsAdapter adapter = new InterestsAdapter();
        adapter.setItems(interests);

        RecyclerView list = getList();
        list.setAdapter(adapter);

        ItemTouchHelper helper = new ItemTouchHelper(new TouchHelperCallback(this));
        helper.attachToRecyclerView(list);
    }

    public void returnInterests() {
        FragmentActivity activity = getActivity();
        Intent data = new Intent();
        InterestsAdapter adapter = (InterestsAdapter) getList().getAdapter();

        data.putExtra(Constants.KEY_ITEMS, new ArrayList<>(adapter.getItems()));
        activity.setResult(Activity.RESULT_OK, data);
        activity.finish();
    }

    @OnClick(R.id.add)
    public void onAddClicked() {
        new MaterialDialog.Builder(getActivity()).title(R.string.add_interest)
            .input(R.string.interest, 0, false, this)
            .show();
    }

    @Override
    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
        InterestsAdapter adapter = (InterestsAdapter) getList().getAdapter();

        Interest interest = new Interest();
        interest.name = input.toString();

        List<Interest> items = adapter.getItems();
        items.add(interest);
        adapter.setItems(items);
        adapter.notifyItemInserted(items.size() - 1);
    }

    private static class TouchHelperCallback extends ItemTouchHelper.Callback {

        WeakReference<InterestsFragment> fragmentRef;

        public TouchHelperCallback(InterestsFragment fragment) {
            this.fragmentRef = new WeakReference<>(fragment);
        }

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.END);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
            RecyclerView.ViewHolder target) {
            InterestsFragment fragment = fragmentRef.get();
            if (null != fragment) {
                int sourcePosition = viewHolder.getAdapterPosition();
                int targetPosition = target.getAdapterPosition();

                InterestsAdapter adapter = (InterestsAdapter) fragment.getList()
                    .getAdapter();
                List<Interest> items = adapter.getItems();
                Interest item = items.remove(sourcePosition);
                items.add(targetPosition, item);
                adapter.setItems(items);
                adapter.notifyItemMoved(sourcePosition, targetPosition);
                return true;
            }


            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            InterestsFragment fragment = fragmentRef.get();
            if (null != fragment) {
                int position = viewHolder.getAdapterPosition();

                InterestsAdapter adapter = (InterestsAdapter) fragment.getList()
                    .getAdapter();
                List<Interest> items = adapter.getItems();
                items.remove(position);

                adapter.setItems(items);
                adapter.notifyItemRemoved(position);
            }
        }
    }
}
