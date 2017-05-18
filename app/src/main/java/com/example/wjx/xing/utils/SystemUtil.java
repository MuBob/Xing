package com.example.wjx.xing.utils;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.example.wjx.xing.activitys.BaseActivity;

/**
 * Created by Administrator on 2017/5/18.
 */

public class SystemUtil {

    /**
     * 启动照相机
     *
     * @param activity
     */
    public synchronized static void toCamera(BaseActivity activity, String savePath, int requestCode){
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                try {
                    Intent toCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    ContentValues contentValues = new ContentValues(1);
                    contentValues.put(MediaStore.Images.Media.DATA, savePath);
                    Uri uri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);
                    toCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    activity.startActivityForResult(toCameraIntent, requestCode);
                }catch (Exception e){
                    e.printStackTrace();
                }
            } else {
            activity.showToastShort("没有足够空间");
        }
    }

    /**
     * 选择图片
     *
     * @param activity
     */
    public synchronized static void toPicture(BaseActivity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        activity.startActivityForResult(intent, requestCode);
    }
}
