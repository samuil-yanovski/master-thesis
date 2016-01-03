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
import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Contacts;
import yanovski.master_thesis.data.models.Person;
import yanovski.master_thesis.ui.utils.CircleTransform;
import yanovski.master_thesis.utils.MailHelper;
import yanovski.master_thesis.utils.PhoneHelper;
import yanovski.master_thesis.utils.SkypeHelper;

/**
 * Created by Samuil on 12/30/2015.
 */
public abstract class BaseProfileFragment extends BaseFragment {

    protected abstract Person getPerson();

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

    Person person;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        person = getPerson();
        MasterThesisApplication.getMainComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        picasso.load(person.getAvatar())
            .placeholder(R.drawable.ic_person_white)
            .error(R.drawable.ic_person_white)
            .transform(new CircleTransform())
            .into(avatar);

        name.setText(person.getName());

        Contacts contacts = person.getContacts();
        bind(contacts.email, email, emailContainer);
        bind(contacts.phone, phone, phoneContainer);
        bind(contacts.skype, skype, skypeContainer);
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
        phoneHelper.call(person.getContacts().phone);
    }

    @OnClick(R.id.email_container)
    public void onMailClicked() {
        mailHelper.sendTo(person.getContacts().email);
    }

    @OnClick(R.id.skype_container)
    public void onSkypeClicked() {
        skypeHelper.chatTo(person.getContacts().skype);
    }
}
