package yanovski.master_thesis.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Account;
import yanovski.master_thesis.ui.utils.CircleTransform;
import yanovski.master_thesis.utils.MailHelper;
import yanovski.master_thesis.utils.PhoneHelper;
import yanovski.master_thesis.utils.SkypeHelper;

/**
 * Created by Samuil on 12/30/2015.
 */
public abstract class BaseProfileFragment extends BaseFragment {

    protected abstract Account getCurrentAccount();

    @Bind(R.id.avatar)
    ImageView avatar;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.email)
    TextView email;
    @Bind(R.id.phone)
    TextView phone;
    @Bind(R.id.skype)
    TextView skype;
    @Bind(R.id.skype_container)
    View skypeContainer;
    @Bind(R.id.phone_container)
    View phoneContainer;
    @Bind(R.id.email_container)
    View emailContainer;

    @Inject
    Picasso picasso;
    @Inject
    PhoneHelper phoneHelper;
    @Inject
    MailHelper mailHelper;
    @Inject
    SkypeHelper skypeHelper;

    Account currentAccount;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentAccount = getCurrentAccount();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        picasso.load(currentAccount.avatar)
            .placeholder(R.drawable.ic_person_white)
            .error(R.drawable.ic_person_white)
            .transform(new CircleTransform())
            .into(avatar);

        name.setText(currentAccount.name);

        bind(currentAccount.contacts.email, email, emailContainer);
        bind(currentAccount.contacts.phone, phone, phoneContainer);
        bind(currentAccount.contacts.skype, skype, skypeContainer);
    }

    private void bind(String value, TextView field, View container) {
        if (!TextUtils.isEmpty(value)) {
            field.setText(value);
        } else {
            container.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.phone_container)
    public void onPhoneClicked() {
        phoneHelper.call(currentAccount.contacts.phone);
    }

    @OnClick(R.id.email_container)
    public void onMailClicked() {
        mailHelper.sendTo(currentAccount.contacts.email);
    }

    @OnClick(R.id.skype_container)
    public void onSkypeClicked() {
        skypeHelper.chatTo(currentAccount.contacts.skype);
    }
}
