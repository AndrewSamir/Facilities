package facilities.samir.andrew.facilities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import facilities.samir.andrew.facilities.R;
import facilities.samir.andrew.facilities.adapter.AdapterEvents;
import facilities.samir.andrew.facilities.interfaces.HandleRetrofitResp;
import facilities.samir.andrew.facilities.models.ModelEvents.ModelEvents;
import facilities.samir.andrew.facilities.retorfitconfig.HandleCalls;

public class EventsFragments extends BaseFragment implements HandleRetrofitResp {

    //region fields

    AdapterEvents adapterEvents;
    List<ModelEvents> modelEventsList;

    //endregion

    //region views

    @BindView(R.id.rvEvents)
    RecyclerView rvEvents;

    //endregion

    //region life cycle

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.events_fragments, container, false);

        unbinder = ButterKnife.bind(this, view);

        modelEventsList = new ArrayList<>();
        ModelEvents modelEvents = new ModelEvents();
        modelEvents.setContent("content test ");
        modelEvents.setDate("12-6-2018");
        modelEvents.setTitle("Title");
        modelEvents.setImage("https://www.google.com.eg/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwi7j8r-zOXdAhWQKlAKHZwRB-sQjRx6BAgBEAU&url=https%3A%2F%2Fwww.booking.com%2Fhotel%2Feg%2Fel-gouna-ms-red-sea.html&psig=AOvVaw0CQ-npzvQ2S_-Z2fneDkGu&ust=1538495629460045");

        modelEventsList.add(modelEvents);
        modelEventsList.add(modelEvents);
        adapterEvents = new AdapterEvents(modelEventsList, getBaseActivity());
        rvEvents.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        rvEvents.setAdapter(adapterEvents);

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
        appHeader.setRight(0);
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
        return true;
    }

    @Override
    protected String getTitle() {
        return getString(R.string.events);
    }

    @Override
    public int getSelectedMenuId() {
        return R.id.bottomItem_home;
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

    public static EventsFragments init() {
        return new EventsFragments();
    }
    //endregion

}
