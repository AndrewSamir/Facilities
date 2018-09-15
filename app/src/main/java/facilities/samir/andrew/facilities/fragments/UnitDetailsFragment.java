package facilities.samir.andrew.facilities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.carbs.android.segmentcontrolview.library.SegmentControlView;
import facilities.samir.andrew.facilities.R;
import facilities.samir.andrew.facilities.adapter.PaymentAdapter;
import facilities.samir.andrew.facilities.interfaces.HandleRetrofitResp;
import facilities.samir.andrew.facilities.models.UnittDetails.UnitDetailsModel;
import facilities.samir.andrew.facilities.models.UnittDetails.UnitPayment;
import facilities.samir.andrew.facilities.retorfitconfig.HandleCalls;

public class UnitDetailsFragment extends BaseFragment implements HandleRetrofitResp, SegmentControlView.OnSegmentChangedListener {

    //region fields
    PaymentAdapter paymentAdapter;
    static UnitDetailsModel unitDetailsModel;
    //endregion

    //region views

    @BindView(R.id.csvUnitDetails)
    SegmentControlView csvUnitDetails;

    @BindView(R.id.cardDetails)
    CardView cardDetails;

    @BindView(R.id.rvUnitDetailsPayment)
    RecyclerView rvUnitDetailsPayment;

    @BindView(R.id.tvProjectNameFragmentUnitDetails)
    TextView tvProjectNameFragmentUnitDetails;
    @BindView(R.id.tvReservationDateFragmentUnitDetails)
    TextView tvReservationDateFragmentUnitDetails;
    @BindView(R.id.tvUnitCodeFragmentUnitDetails)
    TextView tvUnitCodeFragmentUnitDetails;
    @BindView(R.id.tvUnitTotalPriceFragmentUnitDetails)
    TextView tvUnitTotalPriceFragmentUnitDetails;
    @BindView(R.id.tvUnitTypeFragmentUnitDetails)
    TextView tvUnitTypeFragmentUnitDetails;

    //endregion

    //region life cycle

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.unit_details_fragment, container, false);

        unbinder = ButterKnife.bind(this, view);
        adjustViews();
        csvUnitDetails.setOnSegmentChangedListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        HandleCalls.getInstance(getBaseActivity()).setonRespnseSucess(this);
    }

    @Override
    public void onStop() {
        super.onStop();
//               appHeader.setRight(0, 0, 0);
    }

    //endregion

    //region parent methods
    @Override
    protected boolean canShowAppHeader() {
        return false;
    }

    @Override
    protected boolean canShowBottomBar() {
        return false;
    }

    @Override
    protected boolean canShowBackArrow() {
        return false;
    }

    @Override
    protected String getTitle() {
        return null;
    }

    @Override
    public int getSelectedMenuId() {
        return 0;
    }

    //endregion

    //region calls response
    @Override
    public void onResponseSuccess(String flag, Object o) {

    }

    @Override
    public void onNoContent(String flag, int code) {

    }

    @Override
    public void onResponseSuccess(String flag, Object o, int position) {

    }

    //endregion

    //region clicks


    //endregion

    //region calls

    //endregion

    //region functions

    public static UnitDetailsFragment init(UnitDetailsModel unitDetailsModel) {
        setUnitDetailsModel(unitDetailsModel);
        return new UnitDetailsFragment();
    }

    public static UnitDetailsModel getUnitDetailsModel() {
        return unitDetailsModel;
    }

    public static void setUnitDetailsModel(UnitDetailsModel unitDetailsModel) {
        UnitDetailsFragment.unitDetailsModel = unitDetailsModel;
    }

    private void adjustViews() {

        tvProjectNameFragmentUnitDetails.setText(unitDetailsModel.getUnitDetails().getProjectName());
        tvReservationDateFragmentUnitDetails.setText(unitDetailsModel.getUnitDetails().getReservationDate());
        tvUnitCodeFragmentUnitDetails.setText(unitDetailsModel.getUnitDetails().getUnitCode());
        tvUnitTotalPriceFragmentUnitDetails.setText(unitDetailsModel.getUnitDetails().getUnitTotalPrice() + "");
        tvUnitTypeFragmentUnitDetails.setText(unitDetailsModel.getUnitDetails().getUnitType());

        if(unitDetailsModel.getUnitPayment()!=null) {
            paymentAdapter = new PaymentAdapter(unitDetailsModel.getUnitPayment());
            rvUnitDetailsPayment.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
            rvUnitDetailsPayment.setAdapter(paymentAdapter);
        }
    }

    @Override
    public void onSegmentChanged(int newSelectedIndex) {
        switch (newSelectedIndex) {
            case 0:
                cardDetails.setVisibility(View.VISIBLE);
                rvUnitDetailsPayment.setVisibility(View.GONE);
                break;

            case 1:

                cardDetails.setVisibility(View.GONE);
                rvUnitDetailsPayment.setVisibility(View.VISIBLE);
                break;
        }
    }
    //endregion

}
