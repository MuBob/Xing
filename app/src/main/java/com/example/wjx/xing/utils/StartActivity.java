package com.example.wjx.xing.utils;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.wjx.xing.R;
import com.example.wjx.xing.entitys.CircularAnim;


/**
 * Created by youzi on 2016/9/21 0021.
 */
public class StartActivity {
    public static void jumpTo(Activity fromActivity, Intent newIntent){
        fromActivity.startActivity(newIntent);
        fromActivity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
    public static void StartActivity(Activity fromActivity, Intent newIntent){
        fromActivity.startActivity(newIntent);
        fromActivity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        fromActivity.finish();
    }
    public static void StartActivity(Activity fromActivity, Class<?> toActivity){
        StartActivity(fromActivity, new Intent(fromActivity, toActivity));
    }
    public static void StartActivity(final Activity fromActivity,final Class<?> toActivity,final View view){
        CircularAnim.fullActivity(fromActivity, view)
                        .colorOrImageRes(R.color.colorPrimary)
                .go(new CircularAnim.OnAnimationEndListener() {
                    @Override
                    public void onAnimationEnd() {
                        fromActivity.startActivity(new Intent(fromActivity, toActivity));
                        fromActivity.finish();
                    }
                });
    }
}
