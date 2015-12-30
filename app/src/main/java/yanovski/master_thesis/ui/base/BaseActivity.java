package yanovski.master_thesis.ui.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

import butterknife.ButterKnife;
import yanovski.master_thesis.MasterThesisApplication;

/**
 * Created by Samuil on 12/28/2015.
 */
public abstract class BaseActivity extends RxAppCompatActivity implements
    Validator.ValidationListener {

    private Validator validator;

    protected abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        init();
    }

    private void init() {
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    public void validate() {
        validator.validate();
    }

    @Override
    public void onValidationSucceeded() {
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
    }
}
