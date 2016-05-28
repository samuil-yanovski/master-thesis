package yanovski.master_thesis.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.List;

import butterknife.ButterKnife;
import icepick.Icepick;
import icepick.State;
import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.ui.utils.UIModes;

/**
 * Created by Samuil on 12/29/2015.
 */
public abstract class BaseFragment extends RxFragment implements Validator.ValidationListener {
    private Validator validator;

    protected abstract int getLayoutId();

    @State
    UIModes mode = UIModes.View;

    public void setMode(UIModes mode) {
        this.mode = mode;
    }

    public UIModes getMode() {
        return mode;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        validator = new Validator(this);
        validator.setValidationListener(this);
        MasterThesisApplication.getMainComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void validate() {
        validator.validate();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    public void onValidationSucceeded() {
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
    }
}
