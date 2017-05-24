package com.example.wjx.xing.activitys.normal;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;
import com.example.wjx.xing.utils.SystemUtil;

import java.io.File;
import java.io.IOException;

/**
 * 请假界面
 */
public class LeaveActivity extends BaseActivity {

    private Button mBtn_submit;
    private Button mBtn_picture;
    private String imageFilePath;
    private ImageView mPicture;
    Dialog mCameraDialog;
    public static final int REQUEST_CODE_CAMERA=102;
    public static final int REQUEST_CODE_PICTURE=103;

    @Override
    protected void initTitle(TextView left, TextView title, TextView right) {
        super.initTitle(left, title, right);
        right.setVisibility(View.GONE);
    }

    @Override
    protected CharSequence getTitleText() {
        return "请假";
    }

    @Override
    protected void initSet() {
        mBtn_picture.setOnClickListener(this);
        mBtn_submit.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        initDialoginfo();
    }

    /**
     * 初始化
     */
    protected void initView() {
        mPicture = (ImageView)findViewById(R.id.iv_picture);
        //上传照片
        mBtn_picture = (Button) findViewById(R.id.btn_leave_picture);
        mBtn_submit = (Button) findViewById(R.id.btn_leave_submit);
    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_leave;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch(v.getId()){
            case R.id.btn_leave_submit:
                // TODO: 2017/5/18  网络请求，提交请假申请
                break;
            case R.id.btn_leave_picture:
                mCameraDialog.show();
                break;
            case R.id.btn_open_camera:
                //跳转到照相机 拍照
                //设置图片的保存路径,作为全局变量
                imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/filename.jpg";
                Log.i("ceshi", "onClick: imageFilePATH"+imageFilePath);
                File temp = new File(imageFilePath);
//                Uri imageFileUri = Uri.fromFile(temp);//获取文件的Uri
//                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//跳转到相机Activity
//                it.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);//告诉相机拍摄完毕输出图片到指定的Uri
//                startActivityForResult(it, 102);
                SystemUtil.toCamera(this, temp.getAbsolutePath(), REQUEST_CODE_CAMERA);
                //取消dialog
                mCameraDialog.cancel();
                break;
            case R.id.btn_choose_img:
                // 相册选取
//                Intent intent1 = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
////                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image*//*");
//                startActivityForResult(intent1, 103);
                SystemUtil.toPicture(this, REQUEST_CODE_PICTURE);
                mCameraDialog.cancel();
                break;
            case R.id.btn_cancel:
                //取消
                mCameraDialog.cancel();
                break;
        }
    }

    @Override
    protected void onClickRight() {
        //do nothing
    }

    private void initDialoginfo() {
        mCameraDialog = new Dialog(this,R.style.my_dialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.layout_camera_control,null);
        root.findViewById(R.id.btn_open_camera).setOnClickListener(this);
        root.findViewById(R.id.btn_choose_img).setOnClickListener(this);
        root.findViewById(R.id.btn_cancel).setOnClickListener(this);
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogstyle);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = -20;
        lp.width = (int)getResources().getDisplayMetrics().widthPixels;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.alpha = 9f;
        root.measure(0,0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f;
        dialogWindow.setAttributes(lp);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
		 case REQUEST_CODE_CAMERA:
			 if (resultCode == Activity.RESULT_OK) {
                 Log.i(TAG, "LeaveActivity.onActivityResult: imageFile="+imageFilePath);
                 Bitmap bmp = BitmapFactory.decodeFile(imageFilePath);
                 mPicture.setImageBitmap(bmp);
			 }
		 break;
		 case REQUEST_CODE_PICTURE:
			 Bitmap bm = null;
			 // 外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
			 ContentResolver resolver = getContentResolver();
			 try {
                Uri originalUri = data.getData(); // 获得图片的uri
                bm = MediaStore.Images.Media.getBitmap(resolver, originalUri); // 显得到bitmap图片
                // 这里开始的第二部分，获取图片的路径：
                String[] proj = { MediaStore.Images.Media.DATA };
                // 好像是android多媒体数据库的封装接口，具体的看Android文档
                @SuppressWarnings("deprecation")
                Cursor cursor = managedQuery(originalUri, proj, null, null, null);
                // 按我个人理解 这个是获得用户选择的图片的索引值
                 int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                // 将光标移至开头 ，这个很重要，不小心很容易引起越界
                cursor.moveToFirst();
                // 最后根据索引值获取图片路径
                imageFilePath = cursor.getString(column_index);
                mPicture.setImageURI(originalUri);
                 Log.i(TAG, "LeaveActivity.onActivityResult: filePath="+imageFilePath);
             } catch (IOException e) {
                e.printStackTrace();
			 }
			 break;
		 }
        super.onActivityResult(requestCode,resultCode,data);
    }

    private static final String TAG = "LeaveActivity";

}
