package yanovski.master_thesis.ui.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import javax.inject.Inject;

import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.ui.fragments.DocumentsFragment;
import yanovski.master_thesis.ui.fragments.InternshipFragment;
import yanovski.master_thesis.ui.fragments.ResourcesFragment;

/**
 * Created by Samuil on 12/30/2015.
 */
public class InfoAdapter extends FragmentPagerAdapter {

    public static final int TABS_COUNT = 3;

    public static final int INDEX_DOCUMENTS = 0;
    public static final int INDEX_RESOURCES = 1;
    public static final int INDEX_INTERNSHIP = 2;

    @Inject
    Context context;

    public InfoAdapter(FragmentManager fm) {
        super(fm);
        MasterThesisApplication.getMainComponent().inject(this);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title;
        switch (position) {
            case INDEX_DOCUMENTS: {
                title = context.getString(R.string.tab_documents);
                break;
            }
            case INDEX_RESOURCES: {
                title = context.getString(R.string.tab_resources);
                break;
            }
            case INDEX_INTERNSHIP: {
                title = context.getString(R.string.tab_internship);
                break;
            }
            default: {
                title = context.getString(R.string.tab_documents);
                break;
            }
        }
        return title;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case INDEX_DOCUMENTS: {
                fragment = new DocumentsFragment();
                break;
            }
            case INDEX_RESOURCES: {
                fragment = new ResourcesFragment();
                break;
            }
            case INDEX_INTERNSHIP: {
                fragment = new InternshipFragment();
                break;
            }
            default: {
                fragment = new DocumentsFragment();
                break;
            }
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return TABS_COUNT;
    }
}
