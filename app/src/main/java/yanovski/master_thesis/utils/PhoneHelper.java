package yanovski.master_thesis.utils;

import android.content.Context;

import javax.inject.Inject;

/**
 * Created by Samuil on 12/31/2015.
 */
public class PhoneHelper {

    Context context;

    @Inject
    public PhoneHelper(Context context) {
        this.context = context;
    }

    public void call(String phone) {
    }
}
