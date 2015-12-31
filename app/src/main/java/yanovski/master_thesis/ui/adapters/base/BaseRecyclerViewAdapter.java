package yanovski.master_thesis.ui.adapters.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import yanovski.master_thesis.ui.adapters.base.creators.CreatorManager;
import yanovski.master_thesis.ui.adapters.base.creators.ViewHolderCreator;


/**
 * Created by samuil.yanovski on 22/10/2015.
 */
public abstract class BaseRecyclerViewAdapter<I>
    extends RecyclerView.Adapter<yanovski.master_thesis.ui.adapters.base.BaseViewHolder> implements
    OnItemViewClickListener {

    public interface OnItemClickListener {
        void onItemClick(BaseRecyclerViewAdapter<?> adapter, RecyclerView.ViewHolder holder,
            int position);
    }

    public interface OnItemSelectionChangedListener {
        void onItemSelectionChanged(BaseRecyclerViewAdapter<?> adapter, int position,
            boolean selected);
    }

    private WeakReference<OnDataLoadedListener<BaseRecyclerViewAdapter>> listenerReference;
    private WeakReference<OnItemClickListener> onClickListenerReference;
    private WeakReference<OnItemSelectionChangedListener> onSelectionChangedListenerReference;
    private SparseBooleanArray selectionStates;
    private boolean supportsSelections = false;
    private yanovski.master_thesis.ui.adapters.base.creators.CreatorManager creatorManager;
    private List<I> items;

    public BaseRecyclerViewAdapter() {
        init();
    }

    protected void init() {
        selectionStates = new SparseBooleanArray();
        creatorManager = new CreatorManager();
    }

    protected void add(ViewHolderCreator creator) {
        creatorManager.add(creator);
    }

    protected void remove(ViewHolderCreator creator) {
        creatorManager.remove(creator);
    }

    public void setOnDataLoadedListener(OnDataLoadedListener<BaseRecyclerViewAdapter> listener) {
        if (null == listener) {
            listenerReference = null;
        } else {
            listenerReference = new WeakReference<>(listener);
            if (hasLoadedData()) {
                listener.onDataLoaded(this);
            }
        }
    }

    public OnDataLoadedListener<BaseRecyclerViewAdapter> getOnDataLoadedListener() {
        return null != listenerReference ? listenerReference.get() : null;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        if (null == listener) {
            onClickListenerReference = null;
        } else {
            onClickListenerReference = new WeakReference<>(listener);
        }
    }

    public OnItemClickListener getOnItemClickListener() {
        return null != onClickListenerReference ? onClickListenerReference.get() : null;
    }


    public void setOnItemSelectionChangedListener(OnItemSelectionChangedListener listener) {
        if (null == listener) {
            onSelectionChangedListenerReference = null;
        } else {
            onSelectionChangedListenerReference = new WeakReference<>(listener);
        }
    }

    public OnItemSelectionChangedListener getOnItemSelectionChangedListener() {
        return null != onSelectionChangedListenerReference ? onSelectionChangedListenerReference.get() : null;
    }

    public boolean isSupportsSelections() {
        return supportsSelections;
    }

    public void setSupportsSelections(boolean supportsSelections) {
        this.supportsSelections = supportsSelections;
    }

    @Override
    public void onItemViewClick(RecyclerView.ViewHolder holder) {
        int adapterPosition = holder.getAdapterPosition();
        if (supportsSelections) {
            boolean selection = !selectionStates.get(adapterPosition);
            holder.itemView.setActivated(selection);
            selectionStates.put(adapterPosition, selection);
            OnItemSelectionChangedListener listener =
                getOnItemSelectionChangedListener();
            if (null != listener) {
                listener.onItemSelectionChanged(this, adapterPosition, selection);
            }
        } else {
            OnItemClickListener listener = getOnItemClickListener();
            if (null != listener) {
                listener.onItemClick(this, holder, adapterPosition);
            }
        }
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.setOnItemViewClickListener(this);
    }

    @Override
    public void onViewDetachedFromWindow(BaseViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.setOnItemViewClickListener(null);
    }

    @Override
    public int getItemViewType(int position) {
        return creatorManager.getItemViewType(this, position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return creatorManager.onCreateViewHolder(this, parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        creatorManager.onBindViewHolder(this, holder, position);
        if (supportsSelections) {
            holder.itemView.setActivated(selectionStates.get(position));
        }
    }

    public void setItemSelectionState(int position, boolean checked) {
        selectionStates.put(position, checked);
        notifyItemChanged(position);
    }

    public int getSelectedItemsCount() {
        int selectedItemsCount = 0;
        int itemCount = getItemCount();
        for (int index = 0; index < itemCount; ++index) {
            if (selectionStates.get(index)) {
                ++selectedItemsCount;
            }
        }

        return selectedItemsCount;
    }

    public SparseBooleanArray getSelectionStates() {
        return selectionStates;
    }

    public boolean hasLoadedData() {
        return 0 < getItemCount();
    }

    @Override
    public int getItemCount() {
        return null != items ? items.size() : 0;
    }

    public I getItem(int position) {
        int itemCount = getItemCount();
        if (position >= itemCount) {
            throw new ArrayIndexOutOfBoundsException(
                String.format("index: %s, itemsCount: %s", position, itemCount));
        }

        return items.get(position);
    }

    public List<I> getItems() {
        return items;
    }

    public void setItems(List<I> items) {
        this.items = new ArrayList<>(items);
    }
}
