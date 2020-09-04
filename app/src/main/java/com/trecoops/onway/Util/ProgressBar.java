package com.trecoops.onway.Util;
import android.app.Activity;
import android.graphics.Color;
import com.taishi.flipprogressdialog.FlipProgressDialog;
import com.trecoops.onway.R;

import java.util.ArrayList;
import java.util.List;

public class ProgressBar {
    private FlipProgressDialog flipProgressDialog;
    private List<Integer> imgList = new ArrayList<>();

    public ProgressBar() {
        flipProgressDialog = new FlipProgressDialog();
        imgList.add(R.drawable.logo);
        imgList.add(R.drawable.logo_invert);

        flipProgressDialog.setImageList(imgList);
        flipProgressDialog.setDimAmount(0.8f);
        flipProgressDialog.setCanceledOnTouchOutside(false);
        flipProgressDialog.setBackgroundColor(Color.parseColor("#313131"));
        flipProgressDialog.setImageSize(300);

        flipProgressDialog.setOrientation("rotationY");
    }

    public void showProgressBar(Activity activity){
        flipProgressDialog.show(activity.getFragmentManager(), "Please Wait..");
    }

    public void dismissProgressBar(){
        flipProgressDialog.dismiss();
    }
}
