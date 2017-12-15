package com.example.shop.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.shop.R;
import com.example.shop.ZhiFuBao.PayDemoActivity;
import com.example.shop.activity.LoginActivity;
import com.example.shop.activity.RegActivity;
import com.example.shop.adapter.MineAdapter;
import com.example.shop.bean.DengluBean;
import com.example.shop.bean.PersonInfoBean;
import com.example.shop.bean.ShouyeLunBoBean;
import com.example.shop.presenter.MyPresenter1;
import com.example.shop.util.ImageUtils;
import com.example.shop.view.ViewCallBack1;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.io.File;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import static android.app.Activity.RESULT_OK;

//我的fragment
public class MineFragment extends Fragment implements ViewCallBack1 {
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;
    @Bind(R.id.wode_touxiang)
    SimpleDraweeView wodeTouxiang;
    @Bind(R.id.hot_XRecyclerView)
    XRecyclerView hot_XRecyclerView;
    @Bind(R.id.denglu)
    TextView denglu;
    @Bind(R.id.zhuce)
    TextView zhuce;
    private MyPresenter1 presenter1;
    private MineAdapter adapter;
    private Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //设置布局
        View view = inflater.inflate(R.layout.mine_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //调用P层 设置适配器
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        hot_XRecyclerView.setLayoutManager(manager);
        adapter = new MineAdapter(getActivity());
        hot_XRecyclerView.setAdapter(adapter);

        presenter1 = new MyPresenter1(this);
        presenter1.getLunBo();//获取数据

        //XRecyclerview的上拉下拉方法
        hot_XRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //在子线程内完成下拉加载数据
                        presenter1.getLunBo();
                        adapter.notifyDataSetChanged();
                        hot_XRecyclerView.refreshComplete();
                    }
                }, 888);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //在子线程内完成下拉加载数据
                        presenter1.getLunBo();
                        adapter.notifyDataSetChanged();
                        hot_XRecyclerView.loadMoreComplete();
                    }
                }, 888);
            }
        });


        //注册eventbus
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onM(DengluBean dengluBean) {
        // Toast.makeText(getActivity(), "dengluBean.getData().getUid():" + dengluBean.getData().getUsername(), Toast.LENGTH_SHORT).show();

        //设置个人头像
        wodeTouxiang.setImageResource(R.mipmap.ath);
        denglu.setText(dengluBean.getData().getUsername());      //登录文字被用户名替换
        zhuce.setVisibility(View.GONE);                         //注册文字隐藏
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onM(PersonInfoBean personInfoBean) {
//
//        //设置个人头像
//        wodeTouxiang.setImageURI(personInfoBean.getData().getIcon());
//    }

    @Override
    public void success(ShouyeLunBoBean shouyeLunBoBean) {
        //添加适配器数据
        adapter.addData(shouyeLunBoBean.getMiaosha().getList());
    }

    @Override
    public void failure(Exception e) {
        System.out.println("我的 看相似数据错误：" + e);
    }

    @OnClick({R.id.wode_touxiang, R.id.denglu, R.id.zhuce, R.id.zhifu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wode_touxiang:    //点击选择头像
                showChoosePicDialog(view);
                break;

            case R.id.denglu://跳转登录页面
                Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent1);
                break;

            case R.id.zhuce://跳转注册页面
                Intent intent2 = new Intent(getActivity(), RegActivity.class);
                startActivity(intent2);
                break;

            case R.id.zhifu://点击进行  第三方支付宝 支付
                Intent intent = new Intent(getActivity(), PayDemoActivity.class);
                startActivity(intent);
                break;
        }
    }

    //销毁EventBus，取消注册
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 显示修改头像的对话框
     */
    public void showChoosePicDialog(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("设置头像");
        String[] items = {"选择本地照片", "拍照"};
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(
                                Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        takePicture();
                        break;
                }
            }
        });
        builder.create().show();
    }

    private void takePicture() {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= 23) {
            // 需要申请动态权限
            int check = ContextCompat.checkSelfPermission(getActivity(), permissions[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (check != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        Intent openCameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment
                .getExternalStorageDirectory(), "image.jpg");
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= 24) {
            openCameraIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            tempUri = FileProvider.getUriForFile(getActivity(), "com.lt.uploadpicdemo.fileProvider", file);
        } else {
            tempUri = Uri.fromFile(new File(Environment
                    .getExternalStorageDirectory(), "image.jpg"));
        }
        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");

            photo = ImageUtils.toRoundBitmap(photo); // 这个时候的图片已经被处理成圆形的了
            wodeTouxiang.setImageBitmap(photo);
            uploadPic(photo);
        }
    }

    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了
        String imagePath = ImageUtils.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf(System.currentTimeMillis()));
        Log.e("imagePath", imagePath + "");
        if (imagePath != null) {
            // 拿着imagePath上传了

            Log.d("TAG", "imagePath:" + imagePath);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        } else {
            // 没有获取 到权限，从新请求，或者关闭app
            Toast.makeText(getActivity(), "需要存储权限", Toast.LENGTH_SHORT).show();
        }
    }

}
