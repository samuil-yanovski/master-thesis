package yanovski.master_thesis.ui.adapters.base;

/**
 * Created by samuil.yanovski on 22/10/2015.
 */
public interface OnDataLoadedListener<T> {
    void onDataLoadStarted(T adapter);
    void onDataLoaded(T adapter);
}
