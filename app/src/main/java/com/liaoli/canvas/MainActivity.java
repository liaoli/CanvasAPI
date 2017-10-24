package com.liaoli.canvas;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<PageItem> items = new ArrayList<>();

    {
        items.add(new PageItem(R.layout.color,"drawColor"));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        PagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                PageItem pageItem = items.get(position);



                return ItemFragment.newInstance(pageItem.getLayoutRes(),pageItem.getOther());
            }

            @Override
            public int getCount() {
                return items.size();
            }
        };

        pager.setAdapter(adapter);


    }

    public class PageItem {
        int layoutRes;
        String other;

        public PageItem(int layoutRes, String other) {
            this.layoutRes = layoutRes;
            this.other = other;
        }

        public int getLayoutRes() {
            return layoutRes;
        }

        public void setLayoutRes(int layoutRes) {
            this.layoutRes = layoutRes;
        }

        public String getOther() {
            return other;
        }

        public void setOther(String other) {
            this.other = other;
        }
    }

}