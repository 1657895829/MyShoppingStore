package com.example.shop.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * 详情页面适配器
 */
public class ViewPagerAdapter extends PagerAdapter{
    List<String> listDa ;
    Context context;
    public ViewPagerAdapter(Context context) {
        this.context = context;
    }
    public void addData(List<String> listImage) {
        if(listDa ==null){
            listDa = new ArrayList<>();
        }
        listDa.addAll(listImage);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listDa==null?0:listDa.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageLoader.getInstance().displayImage(listDa.get(position),imageView);
        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
