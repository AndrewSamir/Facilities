package facilities.samir.andrew.facilities.customViews;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import facilities.samir.andrew.facilities.R;

public class AppHeader extends CardView {
    private ImageView imgAppHeaderBack, imgAppHeaderRight;
    private TextView tvAppHeaderTitle;


    //region constructor
    public AppHeader(Context context) {
        super(context);
        init(context, null);
    }

    public AppHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AppHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.app_header, this, true);

        tvAppHeaderTitle = findViewById(R.id.tvAppHeaderTitle);
        imgAppHeaderBack = findViewById(R.id.imgAppHeaderBack);
        imgAppHeaderRight = findViewById(R.id.imgAppHeaderRight);
    }
    //endregion

    /*   //region right container helpers

     */

    /*  public void setRight(@StringRes int textRes, @DrawableRes int imageRes, @DrawableRes int imageRes_2)
      {
          if (textRes == 0 && imageRes == 0 && imageRes_2 == 0)
          {
              appHeader_img_right.setVisibility(GONE);
              appHeader_img_right_second.setVisibility(GONE);
              txt_right.setVisibility(GONE);
          } else if (textRes == 0 && imageRes != 0 && imageRes_2 == 0)
          {
              txt_right.setVisibility(GONE);
              appHeader_img_right_second.setVisibility(GONE);
              appHeader_img_right.setVisibility(VISIBLE);

              appHeader_img_right.setImageResource(imageRes);
          } else if (textRes != 0 && imageRes == 0 && imageRes_2 == 0)
          {
              appHeader_img_right.setVisibility(GONE);
              appHeader_img_right_second.setVisibility(GONE);
              txt_right.setVisibility(VISIBLE);

              txt_right.setText(textRes);
          } else if (textRes == 0 && imageRes != 0)
          {
              txt_right.setVisibility(VISIBLE);
              appHeader_img_right.setVisibility(VISIBLE);
              appHeader_img_right_second.setVisibility(VISIBLE);

              appHeader_img_right.setImageResource(imageRes);
              appHeader_img_right_second.setImageResource(imageRes_2);
          }
      }
  */
    public void setRightImgClickListener(View.OnClickListener onClickListener) {
        imgAppHeaderRight.setOnClickListener(onClickListener);
    }

    public void setRightImg(@DrawableRes int imgRes) {
        imgAppHeaderRight.setImageDrawable(getResources().getDrawable(imgRes));
    }

    public void setSecondRightImgClickListener(View.OnClickListener onClickListener) {
//        appHeader_img_right_second.setOnClickListener(onClickListener);
    }

    public void setRightTxtClickListener(View.OnClickListener onClickListener) {
//        txt_right.setOnClickListener(onClickListener);
    }

    public void setBackClickListener(View.OnClickListener onClickListener) {
        imgAppHeaderBack.setOnClickListener(onClickListener);
    }
    //endregion

    //region left container helpers

    public void setBackArrow(boolean showBackArrow) {
        if (showBackArrow)
            imgAppHeaderBack.setVisibility(VISIBLE);
        else
            imgAppHeaderBack.setVisibility(GONE);
    }


    //endregion

    //region title
    public void setTitle(@StringRes int textRes) {
        tvAppHeaderTitle.setText(textRes);
    }

    public void setTitle(String text) {
        tvAppHeaderTitle.setText(text);
    }


    //endregion


    @Override
    public void setBackgroundResource(@DrawableRes int resid) {
        super.setBackgroundResource(resid);
//        container.setBackgroundResource(resid);
    }


    public void setDefaultBackground() {
        setBackgroundResource(R.color.colorWhite);
    }

}
