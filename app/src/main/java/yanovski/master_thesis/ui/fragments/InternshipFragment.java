package yanovski.master_thesis.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import javax.inject.Inject;

import butterknife.OnClick;
import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.ui.base.BaseFragment;
import yanovski.master_thesis.utils.UrlHelper;

/**
 * Created by Samuil on 12/30/2015.
 */
public class InternshipFragment extends BaseFragment {

    @Inject
    UrlHelper helper;
    @Inject
    Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MasterThesisApplication.getMainComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_internship;
    }

    @OnClick(R.id.offer)
    public void onOfferClicked(View v) {
        helper.loadDataUrl(v, context.getString(R.string.internship_offer_url));
    }

    @OnClick(R.id.score)
    public void onScoreClicked(View v) {
        helper.loadDataUrl(v, context.getString(R.string.internship_score_url));
    }
}
