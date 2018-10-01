package facilities.samir.andrew.facilities.fragments;

import android.app.Dialog;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.carbs.android.segmentcontrolview.library.SegmentControlView;
import facilities.samir.andrew.facilities.R;
import facilities.samir.andrew.facilities.adapter.AdapterTickets;
import facilities.samir.andrew.facilities.interfaces.HandleRetrofitResp;
import facilities.samir.andrew.facilities.models.ModelTickets.Ticket;
import facilities.samir.andrew.facilities.retorfitconfig.HandleCalls;

public class ServicesFragment extends BaseFragment implements HandleRetrofitResp, SegmentControlView.OnSegmentChangedListener, View.OnClickListener {

    //region fields
    List<Ticket> closedTicketList;
    List<Ticket> openTicketList;
    AdapterTickets closedAdapterTickets;
    AdapterTickets openadApterTickets;
    //endregion

    //region views

    @BindView(R.id.scvTickets)
    SegmentControlView scvTickets;

    @BindView(R.id.rvServicesFragmentOpen)
    RecyclerView rvServicesFragmentOpen;

    @BindView(R.id.rvServicesFragmentClosed)
    RecyclerView rvServicesFragmentClosed;
    //endregion

    //region life cycle

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.services_fragment, container, false);

        unbinder = ButterKnife.bind(this, view);
        appHeader.setRightImg(R.drawable.ic_action_arrow_back);
        appHeader.setRightImgClickListener(this);
        openTicketList = new ArrayList<>();
        closedTicketList = new ArrayList<>();


        Ticket ticket=new Ticket();
        ticket.setStatus("In Progress");
        ticket.setStatusid(1);
        ticket.setTicketnumber("326");
        ticket.setUnit("Unit ");

        openTicketList.add(ticket);
        openTicketList.add(ticket);
        closedTicketList.add(ticket);

        closedAdapterTickets = new AdapterTickets(closedTicketList, getBaseActivity());
        openadApterTickets = new AdapterTickets(openTicketList, getBaseActivity());

        rvServicesFragmentOpen.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        rvServicesFragmentClosed.setLayoutManager(new LinearLayoutManager(getBaseActivity()));

        rvServicesFragmentOpen.setAdapter(openadApterTickets);
        rvServicesFragmentClosed.setAdapter(closedAdapterTickets);

        scvTickets.setOnSegmentChangedListener(this);
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
        return getString(R.string.my_tickets);
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

    public static ServicesFragment init() {
        return new ServicesFragment();
    }

    @Override
    public void onSegmentChanged(int newSelectedIndex) {
        switch (newSelectedIndex) {
            case 0:
                rvServicesFragmentClosed.setVisibility(View.GONE);
                rvServicesFragmentOpen.setVisibility(View.VISIBLE);
                break;

            case 1:
                rvServicesFragmentClosed.setVisibility(View.VISIBLE);
                rvServicesFragmentOpen.setVisibility(View.GONE);
                break;
        }
    }

    private void showDialogAddTicket() {
        final Dialog dialog = new Dialog(getBaseActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_add_ticket);

//        TextView edtDialogLang = dialog.findViewById(R.id.edtDialogLang);

        TextView edtDialogCancel = dialog.findViewById(R.id.edtDialogCancel);

        edtDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    @Override
    public void onClick(View view) {
        showDialogAddTicket();
    }
    //endregion

}
