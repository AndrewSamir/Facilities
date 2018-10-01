package facilities.samir.andrew.facilities.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import facilities.samir.andrew.facilities.FirebaseHandler.HandleGetDataFromFirebase;
import facilities.samir.andrew.facilities.R;
import facilities.samir.andrew.facilities.adapter.SlidingImageAdapter;
import facilities.samir.andrew.facilities.interfaces.HandleRetrofitResp;
import facilities.samir.andrew.facilities.interfaces.InterfaceGetDataFromFirebase;
import facilities.samir.andrew.facilities.retorfitconfig.HandleCalls;

public class HomeFragment extends BaseFragment implements HandleRetrofitResp, InterfaceGetDataFromFirebase {

    //region fields
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    List<String> imagesArray;
    //endregion

    //region views

    @BindView(R.id.mpager)
    ViewPager mPager;

    @BindView(R.id.indicator)
    CirclePageIndicator indicator;
    //endregion

    //region life cycle

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.home_fragment, container, false);

        unbinder = ButterKnife.bind(this, view);

        imagesArray = new ArrayList<>();

        HandleGetDataFromFirebase.getInstance(getBaseActivity()).setGetDataFromFirebaseInterface(this);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        HandleGetDataFromFirebase.getInstance(getBaseActivity()).callGet("getImages", databaseReference
                .child("Facilities")
                .child("SlidingImages"));
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
        return getString(R.string.home);
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

    @Override
    public void onGetDataFromFirebase(DataSnapshot dataSnapshot, String flag) {

        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

            imagesArray.add(snapshot.getValue().toString());

        }
        initSlidiningImages();
    }
    //endregion

    //region clicks

    @OnClick(R.id.imgHomeEvent)
    public void onClickimgHomeEvent() {
        addFragment(EventsFragments.init(), true);
    }

    @OnClick(R.id.imgHomeServices)
    public void onClickimgHomeServices() {
        addFragment(ServicesFragment.init(), true);
    }

    @OnClick(R.id.imgHomeVisitor)
    public void onClickimgHomeVisitor() {
        addFragment(VisitorFragment.init(), true);
    }

    @OnClick(R.id.imgHomeFacility)
    public void onClickimgHomeFacility() {
        // TODO submit data to server...
    }

    //endregion

    //region calls

    //endregion

    //region functions

    public static HomeFragment init() {
        return new HomeFragment();
    }

    private void initSlidiningImages() {

        mPager.setAdapter(new SlidingImageAdapter(getBaseActivity(), imagesArray));

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES = imagesArray.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }


    //endregion

}
