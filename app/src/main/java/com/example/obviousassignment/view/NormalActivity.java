package com.example.obviousassignment.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.example.obviousassignment.R;
import com.example.obviousassignment.Util;
import com.example.obviousassignment.model.ImageListModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;





public class NormalActivity extends AppCompatActivity {
    public static final String TAG = "ViewImageActivity";
    public static final String EXTRA_NAME = "images";
    int imagePosition;
    private ArrayList<ImageListModel> mImages;
    private GalleryPagerAdapter mAdapter;
ViewPager mPager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        if (!Util.Companion.isOnline(this))
        {
            Toast.makeText(this,"You are offline",Toast.LENGTH_LONG).show();
            return;
        }
        initView();
    }

    public void initView() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPager = findViewById(R.id.pager);
        Type listType = new TypeToken<ArrayList<ImageListModel>>() {
        }.getType();

        mImages = new Gson().fromJson(Util.Companion.readJSONFromAsset(this), listType);
        Bundle extras = getIntent().getExtras();
        imagePosition = Integer.parseInt(extras.getString("Current_position"));
        mAdapter = new GalleryPagerAdapter(this);
        mPager.setAdapter(mAdapter);
        mPager.setOffscreenPageLimit(6);
        mPager.setCurrentItem(imagePosition);
        getSupportActionBar().setTitle(mImages.get(imagePosition).getTitle());
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {}
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            public void onPageSelected(int position) {
                getSupportActionBar().setTitle(mImages.get(position).getTitle());
            }
        });




    }



    class GalleryPagerAdapter extends PagerAdapter {

        Context _context;
        LayoutInflater _inflater;

        public GalleryPagerAdapter(Context context) {
            _context = context;
            _inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mImages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((RelativeLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View itemView = _inflater.inflate(R.layout.pager_view_image_item, container, false);
            container.addView(itemView);
            final SubsamplingScaleImageView image = itemView.findViewById(R.id.images);

            Glide.with(_context)
                    .load(mImages.get(position).getHdurl())
                    .asBitmap().override(400, 600)

                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                            image.setImage(ImageSource.bitmap(bitmap));

                        }
                    });

            image.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_INSIDE);




            return itemView;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((RelativeLayout) object);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.detail:
                Intent intent = new  Intent(this,ImageDetailActivity.class);
                intent.putExtra("data",mImages.get(mPager.getCurrentItem()));
                startActivity(intent);
                return true;
            case android.R.id.home:

                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;

        }
        return false;
    };
    }
