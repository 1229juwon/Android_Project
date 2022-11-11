package com.example.myproject.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.myproject.Adapter.CategoryAdapter;
import com.example.myproject.Adapter.ImageSliderAdapter;
import com.example.myproject.Adapter.PopularAdapter;
import com.example.myproject.Domain.CategoryDomain;
import com.example.myproject.Domain.PopularDomain;
import com.example.myproject.Interface.NotifyingScrollView;
import com.example.myproject.R;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView view1, view2;
    CategoryAdapter adapter;
    PopularAdapter adapter2;
    ViewPager2 sliderViewPager;
    LinearLayout layoutIndicator;
    String[] images;
    Toolbar toolbar;
    int i;
    AppBarLayout appBarLayout;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 카테고리 리사이클러뷰
        View view = inflater.inflate(R.layout.home, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        view1 = (RecyclerView)view.findViewById(R.id.view1);
        view1.setLayoutManager(linearLayoutManager);

        //FirebaseRecyclerOptions<CategoryDomain> options =
        //        new FirebaseRecyclerOptions.Builder<CategoryDomain>()
        //                .setQuery(FirebaseDatabase.getInstance().getReference().child("requests"), CategoryDomain.class)
        //                .build();
        // ex) firebase에서 데이터 가져오기

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("game","game_icon"));
        category.add(new CategoryDomain("exercise","exercise_icon"));
        category.add(new CategoryDomain("drinking","drinking_icon"));
        category.add(new CategoryDomain("exhibition","exhibition_icon"));
        category.add(new CategoryDomain("bowling","bowling_icon"));
        category.add(new CategoryDomain("cafe","cafe_icon"));

        adapter = new CategoryAdapter(category);
        view1.setAdapter(adapter);

        // popular 리사이클러뷰
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        view2 = (RecyclerView)view.findViewById(R.id.view2);
        view2.setLayoutManager(linearLayoutManager2);

        ArrayList<PopularDomain> popular = new ArrayList<>();
        popular.add(new PopularDomain("game","game_icon",10));
        popular.add(new PopularDomain("e","exercise_icon",20));
        popular.add(new PopularDomain("x","drinking_icon",30));
        adapter2 = new PopularAdapter(popular);
        view2.setAdapter(adapter2);


        //광고 슬라이드 배너

        sliderViewPager = view.findViewById(R.id.sliderViewPager);
        layoutIndicator = view.findViewById(R.id.layoutIndicators);


        images = new String[] {
                // 이미지
                "https://cdn.pixabay.com/photo/2020/09/02/18/03/girl-5539094_1280.jpg",
                "https://cdn.pixabay.com/photo/2014/03/03/16/15/mosque-279015_1280.jpg",
                "https://cdn.pixabay.com/photo/2020/03/08/21/41/landscape-4913841_1280.jpg",
                "https://cdn.pixabay.com/photo/2020/09/02/18/03/girl-5539094_1280.jpg",
                "https://cdn.pixabay.com/photo/2014/03/03/16/15/mosque-279015_1280.jpg"
        };

        sliderViewPager.setOffscreenPageLimit(1);
        sliderViewPager.setAdapter(new ImageSliderAdapter(getContext(), images));

        sliderViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }
        });

        setupIndicators(images.length);


        // toolbar
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        toolbar = view.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("");

        i = view.findViewById(R.id.viewFadingEdge).getHeight();
        appBarLayout = view.findViewById(R.id.appBarLayout2);




        //((NotifyingScrollView) view.findViewById(R.id.scroll_view)).setOnScrollChangedListener(mOnScrollChangedListener);

        toolbar.setBackground(getResources().getDrawable(R.drawable.background));
        // 앱바x 앱바 레이아웃의 높이 불러오는 매소드
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int ver = Math.abs(verticalOffset)/ 4;
                final int headerHeight = 300 - toolbar.getHeight();
                final float ratio = (float) Math.min(Math.max(ver, 0), headerHeight) / headerHeight;
                final int newAlpha = (int) (ratio * 255);
                //toolbar.setAlpha(ratio);
                toolbar.getBackground().setAlpha(newAlpha);
                //Log.i("d",Float.toString(newAlpha));
                //Log.d("tag_scroll", "recycler_view current offset: "+verticalOffset);
            }
        });




        return view;
    }


    // 광고밑에있는 사진 갯수에 따른 동그라미 갯수 설정
    private void setupIndicators(int count) {
        ImageView[] indicators = new ImageView[count];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins(16, 8, 16, 8);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(getContext(),
                    R.drawable.bg_indicator_inactive));
            indicators[i].setLayoutParams(params);
            layoutIndicator.addView(indicators[i]);
        }
        setCurrentIndicator(0);
    }

    // 광고 밑에 있는 사진 현재사진일 경우 보라색깔
    private void setCurrentIndicator(int position) {
        int childCount = layoutIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutIndicator.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        getContext(),
                        R.drawable.bg_indicator_active
                ));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        getContext(),
                        R.drawable.bg_indicator_inactive
                ));
            }
        }
    }


}