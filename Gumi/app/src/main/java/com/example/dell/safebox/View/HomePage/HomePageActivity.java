package com.example.dell.safebox.View.HomePage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dell.safebox.Adapter.MenuMainAdapter;
import com.example.dell.safebox.Object.MenuMain;
import com.example.dell.safebox.R;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {
    MenuMainAdapter mainAdapter;
    RecyclerView recyclerView;
    List<MenuMain> menuMainList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_layout);
        initData();
        addControl();
        setAdapter();
    }

    private void setAdapter() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(HomePageActivity.this, 3);
        mainAdapter = new MenuMainAdapter(HomePageActivity.this, menuMainList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainAdapter);
        mainAdapter.notifyDataSetChanged();
    }

    private void initData() {
        menuMainList = new ArrayList<>();
        menuMainList.add(new MenuMain(R.drawable.image, "Image"));
        menuMainList.add(new MenuMain(R.drawable.camera, "Video"));
        menuMainList.add(new MenuMain(R.drawable.audio, "Audio"));
        menuMainList.add(new MenuMain(R.drawable.note, "Note"));
        menuMainList.add(new MenuMain(R.drawable.contact, "Contact"));
        menuMainList.add(new MenuMain(R.drawable.password, "Password"));
        menuMainList.add(new MenuMain(R.drawable.upgrade, "Upgrade"));
        menuMainList.add(new MenuMain(R.drawable.setting, "Setting"));
    }

    private void addControl() {
        recyclerView = findViewById(R.id.recycleViewMain);
    }
}
