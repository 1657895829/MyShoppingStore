package com.example.shop.adapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.example.shop.R;
import com.example.shop.activity.WebUrlActivity;
import com.example.shop.bean.RightBean;
import java.util.List;

/**
 * 分类页面右边数据适配器
 */
public class FenLei_Right_Adapter extends BaseExpandableListAdapter {
    private Context context;
    private List<RightBean.DataBean> list;

    public FenLei_Right_Adapter(Context context, List<RightBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getList().get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.fenlei_right, null);
        TextView expand_tv = (TextView) v.findViewById(R.id.you_tou);
        expand_tv.setText(list.get(i).getName());
        return v;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        //设置右边数据显示网格布局
        View v = View.inflate(context,R.layout.fenlei_right_child,null);
        RecyclerView fenlei_right_child_recyclerView = (RecyclerView) v.findViewById(R.id.fenlei_right_child_recyclerView);

        GridLayoutManager gridlayout = new GridLayoutManager(context,3);
        fenlei_right_child_recyclerView.setLayoutManager(gridlayout);

        //实现右边数据子条目布局适配器
        final Set_FenLei_Child_Adapter childAdapter = new Set_FenLei_Child_Adapter(context,list,i,i1);
        fenlei_right_child_recyclerView.setAdapter(childAdapter);

        //条目点击事件
        childAdapter.setOnItemClickListener(new FenLei_Left_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(context, WebUrlActivity.class);
                intent.putExtra("id",list.get(position).getPcid());
                context.startActivity(intent);
            }
        });
        return v;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
