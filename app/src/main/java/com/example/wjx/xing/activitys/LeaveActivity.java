package com.example.wjx.xing.activitys;

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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.wjx.xing.R;

import java.io.File;
import java.io.IOException;

/**
 * 点击请假按钮之后  跳转到这
 */
public class LeaveActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtn_submit;
    private View mEt_start;
    private View mEt_end;
    private View mEt_info;
    private View left;
    private View right;
    private Button mBtn_picture;
    String imageFilePath;
    private ImageView mPicture;
    Dialog mCameraDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        left = findViewById(R.id.leave_left);
        left.setOnClickListener(this);
        right = findViewById(R.id.leave_right);
        right.setOnClickListener(this);
        mEt_start = findViewById(R.id.et_leave_startLeave);
        mEt_end = findViewById(R.id.et_leave_endLeave);
        mEt_info = findViewById(R.id.et_leave_info);

        mPicture = (ImageView)findViewById(R.id.iv_picture);
        //上传照片
        mBtn_picture = (Button) findViewById(R.id.btn_leave_picture);
        mBtn_picture.setOnClickListener(this);
        mBtn_submit = (Button) findViewById(R.id.btn_leave_submit);
        mBtn_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.leave_left:
                //关闭当前界面 回到之前的页面
                finish();
                break;
            case R.id.leave_right:
                //我还不知道干什么
                break;

            case R.id.btn_leave_submit:
                //提交

                break;
            case R.id.btn_leave_picture:
                showDialoginfo();
                break;
            case R.id.btn_open_camera:
                //跳转到照相机 拍照
                //设置图片的保存路径,作为全局变量
                imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/filename.jpg";
                Log.i("ceshi", "onClick: imageFilePATH"+imageFilePath);
                File temp = new File(imageFilePath);
                Uri imageFileUri = Uri.fromFile(temp);//获取文件的Uri	 
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//跳转到相机Activity
                it.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);//告诉相机拍摄完毕输出图片到指定的Uri
                startActivityForResult(it, 102);
                //取消dialog
                mCameraDialog.cancel();
                break;
            case R.id.btn_choose_img:
                // 相册选取
                Intent intent1 = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image*//*");
                startActivityForResult(intent1, 103);
                mCameraDialog.cancel();
                break;
            case R.id.btn_cancel:
                //取消
                mCameraDialog.cancel();
                break;
        }
    }

    private void showDialoginfo() {
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
        mCameraDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
		 case 102:
			 if (resultCode == Activity.RESULT_OK) {
				 Bitmap bmp = BitmapFactory.decodeFile(imageFilePath);
                 mPicture.setImageBitmap(bmp);
			 }
		 break;
		 case 103:
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
                String path = cursor.getString(column_index);
                mPicture.setImageURI(originalUri);
			 } catch (IOException e) {
                e.printStackTrace();
			 }
			 break;
		 }
        super.onActivityResult(requestCode,resultCode,data);

    }
}
