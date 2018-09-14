
package facilities.samir.andrew.facilities.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.view.MenuItem;
import android.view.View;

import facilities.samir.andrew.facilities.R;
import facilities.samir.andrew.facilities.fragments.BaseFragment;
import facilities.samir.andrew.facilities.fragments.HomeFragment;
import facilities.samir.andrew.facilities.interfaces.HandleRetrofitResp;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * This will mainly be used to show most of the app. <br></br>
 * It has a header and a BottomNavigationView, and can easily be shown from the functions required from each Fragment.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener, HandleRetrofitResp {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        appHeader.setBackgroundResource(R.color.colorPrimary);
        initBottomBar();

        addContentFragment(HomeFragment.init(), false);
     /*   if (SharedPrefHelper.getInstance(this).getAccessToken() == null)
            addContentFragment(new IntroFragment(), false);
        else
            addContentFragment(new RegisterFragment(), false);
*/
//            addContentFragment(ReservationsFragments.init(intent.getStringExtra(DataEnum.extraReservationID.name())), true);

    }

    //region parent
    @Override
    public int getFragmentContainerID() {
        return R.id.mainActivity_container;
    }

    @Override
    protected int getBaseLayoutID() {
        return R.layout.main_activity;
    }

    //endregion

    //region bottom bar

    private void initBottomBar() {
//        adjustMenuItemFont();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {//open correct view when clicked

                if (item.getItemId() == R.id.bottomItem_feeds) {
//                    addContentFragment(new ReservationsFragments(), true);
                } else if (item.getItemId() == R.id.bottomItem_poems) {
//                    addContentFragment(new IntervalsFragment(), true);
                } else if (item.getItemId() == R.id.bottomItem_chat) {
//                    addContentFragment(new MyAccountFragment(), true);
                } else if (item.getItemId() == R.id.bottomItem_search) {
//                    addContentFragment(new MyAccountFragment(), true);
                } else if (item.getItemId() == R.id.bottomItem_more) {
//                    addContentFragment(new MyAccountFragment(), true);
                }

                return true;
            }
        });


    }

    private Badge addBadgeAt(int position, int number) {
        // add badge
        return new QBadgeView(this)
                .setBadgeNumber(number)
                .setGravityOffset(30, 2, true)
                .bindTarget(bottomNavigationView.getBottomNavigationItemView(position))
                .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                    @Override
                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                        if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState) ;
//                            Toast.makeText(BadgeViewActivity.this, R.string.tips_badge_removed, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void adjustMenuItemFont() {
        int menuSize = bottomNavigationView.getMenu().size();
        for (int j = 0; j < menuSize; j++) {//change the type face for the menu font
            MenuItem mi = bottomNavigationView.getMenu().getItem(j);
            Typeface font = Typeface.createFromAsset(getAssets(), "fonts/TheSansArabic-Plain.otf");
            if (mi.getTitle() != null) {
                SpannableString mNewTitle = new SpannableString(mi.getTitle());
//                mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                mi.setTitle(mNewTitle);
            }
        }
    }


    //endregion

    //region helpers
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void updateNavigationBarState() {
        //TODO adjust bottom navigation view buttons to select the correct one
        BaseFragment fragment = (BaseFragment) getSupportFragmentManager().findFragmentById(getFragmentContainerID());
        int actionId = fragment.getSelectedMenuId();
        if (actionId != 0) {
            //            Menu menu = bottomNavigationView.getMenu();

            //            for (int i = 0, size = menu.size(); i < size; i++)
            //            {
            //                MenuItem item = menu.getItem(i);
            //                item.setChecked(item.getItemId() == actionId);
            //            }

            bottomNavigationView.getMenu().findItem(actionId).setChecked(true);
        }
    }

    @Override
    public void addContentFragment(Fragment fragment, boolean addToBackStack, @AnimRes int enterAnim, @AnimRes int exitAnim) {
        super.addContentFragment(fragment, addToBackStack, enterAnim, exitAnim);
    }

    //endregion

    //region initiate the activity
    public static void init(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        } else {
//            Logger.INSTANCE.e("MainActivity", "Context is null during init() function");
        }
    }

    //endregion

    @Override
    public void onClick(View v) {
       /* Category category = new Category();

        switch (v.getId())
        {
            case R.id.floatingAction_homeMade:
                category.setCategoryId(Category.Companion.getType_homeMade());
                category.setName(getString(R.string.homeMade));
                closeFloatingActionMenu();
                break;
            case R.id.floatingAction_sweets:
                category.setCategoryId(Category.Companion.getType_sweets());
                category.setName(getString(R.string.sweets));
                closeFloatingActionMenu();
                break;
            case R.id.floatingAction_coffee:
                category.setCategoryId(Category.Companion.getType_coffee());
                category.setName(getString(R.string.coffee));
                closeFloatingActionMenu();
                break;
            case R.id.floatingAction_restaurant:
                category.setCategoryId(Category.Companion.getType_restaurant());
                category.setName(getString(R.string.restaurants));
                closeFloatingActionMenu();
                break;

        }

        addContentFragment(FilterFragment.init(category), true);*/
    }

    @Override
    public void onResponseSuccess(String flag, Object o) {

    }

    @Override
    public void onNoContent(String flag, int code) {

    }

    @Override
    public void onResponseSuccess(String flag, Object o, int position) {

    }
}
