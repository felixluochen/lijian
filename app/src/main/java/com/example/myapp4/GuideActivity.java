package com.example.myapp4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {

    private int[] imgs= {R.drawable.guide1,R.drawable.guide2,R.drawable.guide3};
    private List<ImageView> list = new ArrayList<>();
    private ViewPager viewPager;
    private Button button;
    private LinearLayout layout;
    private Myvv myvv;
    private ImageView[] icon;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guideactivity);

        viewPager = findViewById(R.id.guide_viewPager);
        button = findViewById(R.id.guide_button);
        layout = findViewById(R.id.guide_lv);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        setImg();

        seticon();

        myvv = new Myvv();
        viewPager.setAdapter(myvv);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0;i<icon.length;i++){
                    if (i == position){
                        icon[i].setImageResource(R.drawable.guide_icon_selected);
                    }else {
                        icon[i].setImageResource(R.drawable.guide_icon_unselect);
                    }
                }

                if (position == list.size()-1){
                    button.setVisibility(View.VISIBLE);
                }else {
                    button.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void seticon(){
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(50,50);
        layoutParams.setMargins(10,0,10,0);
        icon = new ImageView[imgs.length];
        for (int i=0;i<imgs.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(R.drawable.guide_icon_unselect);
            if (i == 0){
                imageView.setImageResource(R.drawable.guide_icon_selected);
            }else {
                imageView.setImageResource(R.drawable.guide_icon_unselect);
            }
            icon[i] = imageView;
            final int i1 = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setCurrentItem(i1);
                }
            });
            layout.addView(imageView);
        }


    }

    private void setImg(){
        for (int i=0;i<imgs.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imgs[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new ViewPager.LayoutParams());
            list.add(imageView);
        }
    }







    public class Myvv extends PagerAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(list.get(position));
        //    super.destroyItem(container, position, object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }
    }
}
