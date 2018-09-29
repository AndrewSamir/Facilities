package facilities.samir.andrew.facilities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationMenu;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import facilities.samir.andrew.facilities.adapter.AdapterNotifications;
import facilities.samir.andrew.facilities.interfaces.HandleRetrofitResp;
import facilities.samir.andrew.facilities.interfaces.InterfaceGetDataFromFirebase;
import facilities.samir.andrew.facilities.models.NotificationModel;
import facilities.samir.andrew.facilities.retorfitconfig.HandleCalls;

public class NotificationsFragment extends BaseFragment implements HandleRetrofitResp, InterfaceGetDataFromFirebase {

    //region fields
    List<NotificationModel> notificationModelList;
    AdapterNotifications adapterNotifications;
    //endregion

    //region views

    @BindView(R.id.rvNotifications)
    RecyclerView rvNotifications;
    //endregion

    //region life cycle

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.notifications_fragment, container, false);

        unbinder = ButterKnife.bind(this, view);

        notificationModelList = new ArrayList<>();
        adapterNotifications = new AdapterNotifications(notificationModelList, getBaseActivity());
        rvNotifications.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        rvNotifications.setAdapter(adapterNotifications);

        HandleGetDataFromFirebase.getInstance(getBaseActivity()).setGetDataFromFirebaseInterface(this);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        HandleGetDataFromFirebase.getInstance(getBaseActivity()).callGet("getAllUnits", databaseReference
                .child("Facilities")
                .child("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("Notifications"));
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
        return getString(R.string.notifications);
    }

    @Override
    public int getSelectedMenuId() {
        return R.id.bottomItem_notifications;
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

    public static NotificationsFragment init() {
        return new NotificationsFragment();
    }

    @Override
    public void onGetDataFromFirebase(DataSnapshot dataSnapshot, String flag) {
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

            adapterNotifications.addItem(snapshot.getValue(NotificationModel.class));
        }
    }
    //endregion

}
