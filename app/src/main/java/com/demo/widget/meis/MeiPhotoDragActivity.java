package com.demo.widget.meis;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.widget.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.meis.widget.photodrag.PhotoDragHelper;
import com.meis.widget.photodrag.PhotoDragRelativeLayout;

import me.relex.photodraweeview.PhotoDraweeView;

/**
 * Created by wenshi on 2018/5/18.
 * Description
 */
public class MeiPhotoDragActivity extends AppCompatActivity {

    Toolbar mToolbar;

    ViewPager pager;
    CustomPagerAdapter pagerAdapter;
       PhotoDragRelativeLayout mPdrLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mei_photo_drag_activity);
        pager=findViewById(R.id.pager);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mPdrLayout = findViewById(R.id.pdr_content);
        mPdrLayout.setDragListener(new PhotoDragHelper().setOnDragListener(new PhotoDragHelper.OnDragListener() {
            @Override
            public void onAlpha(float alpha) {
                mPdrLayout.setAlpha(alpha);
            }

            @Override
            public View getDragView() {
                return pager;
            }

            @Override
            public void onAnimationEnd(boolean mSlop) {
                if (mSlop) {
                    finish();
                    overridePendingTransition(0, 0);
                }
            }
        }));
        pagerAdapter=new CustomPagerAdapter();
        pager.setAdapter(pagerAdapter);
    }

    class  CustomPagerAdapter extends PagerAdapter{


        public CustomPagerAdapter() {
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override public Object instantiateItem(ViewGroup container, int position) {
            View view= LayoutInflater.from(container.getContext()).inflate(R.layout.item_vp,null) ;

            final PhotoDraweeView mPdvView;

            mPdvView =view. findViewById(R.id.pdv_photo);
            if(position==0){
                mPdvView.setPhotoUri(Uri.parse("res://" + getPackageName() + "/" + R.mipmap.ic_mei_ripple));
            }else if(position==1){
                mPdvView.setPhotoUri(Uri.parse("res://" + getPackageName() + "/" + R.mipmap.test1));
            }else {
                mPdvView.setPhotoUri(Uri.parse("res://" + getPackageName() + "/" + R.mipmap.test2));
            }


            container.addView(view);
            return view;
        }
          @Override public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object); }


    }
}
