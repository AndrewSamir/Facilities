package facilities.samir.andrew.facilities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import facilities.samir.andrew.facilities.FirebaseHandler.HandleGetDataFromFirebase;
import facilities.samir.andrew.facilities.R;
import facilities.samir.andrew.facilities.adapter.AdapterUnits;
import facilities.samir.andrew.facilities.interfaces.HandleRetrofitResp;
import facilities.samir.andrew.facilities.interfaces.InterfaceGetDataFromFirebase;
import facilities.samir.andrew.facilities.models.UnittDetails.UnitDetailsModel;
import facilities.samir.andrew.facilities.models.UnittDetails.UnitPayment;
import facilities.samir.andrew.facilities.models.UnittDetails.unitDetails;
import facilities.samir.andrew.facilities.retorfitconfig.HandleCalls;

public class UnitsFragment extends BaseFragment implements HandleRetrofitResp, InterfaceGetDataFromFirebase {

    //region fields
    AdapterUnits adapterUnits;
    List<UnitDetailsModel> unitDetailsModelList;
    List<UnitPayment> unitPaymentList;

    //endregion

    //region views

    @BindView(R.id.rvUnits)
    RecyclerView rvUnits;
    //endregion

    //region life cycle

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.units_fragment, container, false);

        unbinder = ButterKnife.bind(this, view);

        unitDetailsModelList = new ArrayList<>();
        unitPaymentList = new ArrayList<>();
        adapterUnits = new AdapterUnits(unitDetailsModelList, getBaseActivity());
        rvUnits.setLayoutManager(new GridLayoutManager(getBaseActivity(), 1));
        rvUnits.setAdapter(adapterUnits);
        HandleGetDataFromFirebase.getInstance(getBaseActivity()).setGetDataFromFirebaseInterface(this);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        HandleGetDataFromFirebase.getInstance(getBaseActivity()).callGet("getAllUnits", databaseReference
                .child("Facilities")
                .child("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("Units"));

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
//        appHeader.setRight(0, 0, 0);
    }

    //endregion

    //region parent methods
    @Override
    protected boolean canShowAppHeader() {
        return true;
    }

    @Override
    protected boolean canShowBottomBar() {
        return true;
    }

    @Override
    protected boolean canShowBackArrow() {
        return false;
    }

    @Override
    protected String getTitle() {
        return getString(R.string.units);
    }

    @Override
    public int getSelectedMenuId() {
        return R.id.bottomItem_units;
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

    @Override
    public void onGetDataFromFirebase(DataSnapshot dataSnapshot, String flag) {

        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            UnitDetailsModel unitDetailsModel = snapshot.getValue(UnitDetailsModel.class);
            for (DataSnapshot snap : snapshot.child("UnitPayment").getChildren()) {
                unitPaymentList.add(snap.getValue(UnitPayment.class));
            }
            unitDetailsModel.setUnitPayment(unitPaymentList);
            adapterUnits.addItem(unitDetailsModel);
        }
    }
    //endregion

    //region clicks


    //endregion

    //region calls

    //endregion

    //region functions

    public static UnitsFragment init() {
        return new UnitsFragment();
    }


    //endregion

}
