package facilities.samir.andrew.facilities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import facilities.samir.andrew.facilities.R;
import facilities.samir.andrew.facilities.interfaces.HandleRetrofitResp;
import facilities.samir.andrew.facilities.retorfitconfig.HandleCalls;

public class NotificationsFragment extends BaseFragment implements HandleRetrofitResp {

    //region fields

    //endregion

    //region views

    //endregion

    //region life cycle

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.notifications_fragment, container, false);

        unbinder = ButterKnife.bind(this, view);

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
    //endregion

}
