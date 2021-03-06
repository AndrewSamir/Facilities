package facilities.samir.andrew.facilities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import facilities.samir.andrew.facilities.R;
import facilities.samir.andrew.facilities.retorfitconfig.HandleCalls;

public class HomeMainFragment extends BaseFragment {

       //region fields

           //endregion

           //region views

           //endregion

           //region life cycle

           @Nullable
           @Override
           public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
               super.onCreateView(inflater, container, savedInstanceState);
               final View view = inflater.inflate(R.layout.home_main_fragment, container, false);

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
               appHeader.setRight(0, 0, 0);
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

           public static HomeMainFragment init() {
               return new HomeMainFragment();
           }
           //endregion

}
