package yanovski.master_thesis.ui.adapters.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;

/**
 * Created by samuil.yanovski on 22/10/2015.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private WeakReference<OnItemViewClickListener> onItemViewClickListener;

    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void setOnItemViewClickListener(OnItemViewClickListener listener) {
        onItemViewClickListener = new WeakReference<>(listener);
    }

    public OnItemViewClickListener getOnItemViewClickListener() {
        return null != onItemViewClickListener ? onItemViewClickListener.get() : null;
    }

    @Override
    public void onClick(View v) {
        OnItemViewClickListener listener = getOnItemViewClickListener();
        if (null != listener) {
            listener.onItemViewClick(this);
        }
    }
}