package com.example.myproject.Activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myproject.R;
import com.example.myproject.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.os.Bundle;

import android.view.Menu;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    BottomNavigationView bottomNavigationView;
    Menu menu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new com.example.myproject.fragment.HomeFragment());

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        menu = bottomNavigationView.getMenu();


        binding.bottomNavigationView.setOnItemSelectedListener(item ->{

            switch(item.getItemId()){
                case R.id.home:
                    item.setIcon(R.drawable.selected_home_icon);
                    menu.findItem(R.id.recipt).setIcon(R.drawable.recipt_icon);
                    menu.findItem(R.id.create).setIcon(R.drawable.create_icon);
                    menu.findItem(R.id.chat).setIcon(R.drawable.chat_icon);
                    menu.findItem(R.id.mypage).setIcon(R.drawable.mypage_icon);
                    //title.setText("홈");
                    replaceFragment(new com.example.myproject.fragment.HomeFragment());
                    break;
                case R.id.recipt:
                    item.setIcon(R.drawable.selected_recipt_icon);
                    menu.findItem(R.id.home).setIcon(R.drawable.home_icon);
                    menu.findItem(R.id.create).setIcon(R.drawable.create_icon);
                    menu.findItem(R.id.chat).setIcon(R.drawable.chat_icon);
                    menu.findItem(R.id.mypage).setIcon(R.drawable.mypage_icon);
                    //title.setText("이용내역");
                    replaceFragment(new com.example.myproject.fragment.ReciptFragment());
                    break;
                case R.id.create:
                    item.setIcon(R.drawable.selected_create_icon);
                    menu.findItem(R.id.home).setIcon(R.drawable.home_icon);
                    menu.findItem(R.id.recipt).setIcon(R.drawable.recipt_icon);
                    menu.findItem(R.id.chat).setIcon(R.drawable.chat_icon);
                    menu.findItem(R.id.mypage).setIcon(R.drawable.mypage_icon);
                    //title.setText("요청서 작성");
                    replaceFragment((new com.example.myproject.fragment.CreateFragment()));
                    break;
                case R.id.chat:
                    item.setIcon(R.drawable.selected_chat_icon);
                    menu.findItem(R.id.home).setIcon(R.drawable.home_icon);
                    menu.findItem(R.id.recipt).setIcon(R.drawable.recipt_icon);
                    menu.findItem(R.id.create).setIcon(R.drawable.create_icon);
                    menu.findItem(R.id.mypage).setIcon(R.drawable.mypage_icon);
                    //title.setText("채팅");
                    replaceFragment((new com.example.myproject.fragment.ChatFragment()));
                    break;
                case R.id.mypage:
                    item.setIcon(R.drawable.selected_mypage_icon);
                    menu.findItem(R.id.home).setIcon(R.drawable.home_icon);
                    menu.findItem(R.id.recipt).setIcon(R.drawable.recipt_icon);
                    menu.findItem(R.id.create).setIcon(R.drawable.create_icon);
                    menu.findItem(R.id.chat).setIcon(R.drawable.chat_icon);
                    //title.setText("마이페이지");
                    replaceFragment((new com.example.myproject.fragment.MypageFragment()));
                    break;
            }
            return true;
        });
    }


    public void replaceFragment(Fragment fragment){
        // 프래그먼트 화면을 바꾸는 메소드
        FragmentManager fragmentManager = getSupportFragmentManager();
        //FragmentManager 백 스택을 관리하고 프래그먼트 트랜잭션을 생성
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // FragmentTransaction 프래그먼트를 추가/교체/삭제하는 작업 제공
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}
