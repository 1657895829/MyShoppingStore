package com.example.shop.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.example.shop.R;

/**
 * 购物车加减号 自定义View
 */
public class CustomJiaJian extends LinearLayout {
    private EditText editText;
    private Button revserse;
    private Button add;
    private int mCount = 1 ;


    public CustomJiaJian(Context context) {
        super(context);
    }

    public CustomJiaJian(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View view =   LayoutInflater.from(context).inflate(R.layout.plus_layout,null,false);

        revserse = (Button) view.findViewById(R.id.revserse);
        add = (Button) view.findViewById(R.id.add);
        editText = (EditText) view.findViewById(R.id.content);


        revserse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                //减号
                try {
                    String content =  editText.getText().toString().trim() ;
                    int count =  Integer.valueOf(content);

                    if(count > 1){
                        mCount = count-1;
                        editText.setText(mCount+"");
                        //回调给adapter里面
                        if(customListener != null){
                            customListener.jiajian(mCount);
                        }
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //加号
                try {
                    String content =  editText.getText().toString().trim() ;
                    int count =  Integer.valueOf(content)+1;
                    mCount = count;

                    editText.setText(count+"");
                    //接口回调给adapter
                    if(customListener != null){
                        customListener.jiajian(count);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        addView(view);
    }

    public CustomJiaJian(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //这个方法是供recyadapter设置 数量时候调用的
    public void setEditText(int num){
        if(editText != null){
            editText.setText(num+"");
        }
    }

    public CustomListener customListener;

    public void setCustomListener(CustomListener customListener){
        this.customListener = customListener;
    }

    /**
     * 加减的接口
     */
    public interface CustomListener {
        public void jiajian(int count);
    }
}
