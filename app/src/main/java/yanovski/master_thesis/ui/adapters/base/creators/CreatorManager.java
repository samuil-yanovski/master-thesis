package yanovski.master_thesis.ui.adapters.base.creators;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.view.ViewGroup;

import java.util.concurrent.atomic.AtomicInteger;

import yanovski.master_thesis.ui.adapters.base.BaseRecyclerViewAdapter;
import yanovski.master_thesis.ui.adapters.base.BaseViewHolder;

/**
 * Created by samuil.yanovski on 22/10/2015.
 */
public class CreatorManager {
    static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);

    private SparseArrayCompat<ViewHolderCreator> creators = new SparseArrayCompat<>();

    public void add(@NonNull ViewHolderCreator creator) {
        add(creator, false);
    }

    public void add(@NonNull ViewHolderCreator creator, boolean allowReplace) {
        int itemViewType = creator.getItemViewType();

        Object current = creators.get(itemViewType);
        if (!allowReplace && null != current) {
            throw new IllegalArgumentException(
                String.format("Creator for viewType %s is already registered", itemViewType));
        }
        creators.put(itemViewType, creator);
    }

    public void remove(@NonNull ViewHolderCreator creator) {
        creators.delete(creator.getItemViewType());
    }

    public int getItemViewType(@NonNull BaseRecyclerViewAdapter adapter, int position) {
        int creatorsCount = creators.size();
        for (int i = 0; i < creatorsCount; i++) {
            ViewHolderCreator creator = creators.valueAt(i);
            if (creator.canHandle(adapter, position)) {
                return creator.getItemViewType();
            }
        }

        throw new IllegalArgumentException(
            String.format("No Creator available for item at position: %s", position));
    }

    @NonNull
    public BaseViewHolder onCreateViewHolder(@NonNull BaseRecyclerViewAdapter adapter,
        ViewGroup parent, int viewType) {
        ViewHolderCreator creator = creators.get(viewType);
        if (null == creator) {
            throw new NullPointerException(
                String.format("No Creator available for viewType: %s", viewType));
        }

        BaseViewHolder vh = creator.onCreateViewHolder(adapter, parent);
        if (null == vh) {
            throw new NullPointerException(
                "Creator " + creator + " returned a null view holder for ViewType = " + viewType);
        }
        return vh;
    }

    public void onBindViewHolder(@NonNull BaseRecyclerViewAdapter adapter,
        @NonNull BaseViewHolder viewHolder, int position) {
        ViewHolderCreator creator = creators.get(viewHolder.getItemViewType());
        if (creator == null) {
            throw new NullPointerException(String.format("No Creator available for viewType: %s",
                viewHolder.getItemViewType()));
        }

        creator.onBindViewHolder(adapter, viewHolder, position);
    }
}
