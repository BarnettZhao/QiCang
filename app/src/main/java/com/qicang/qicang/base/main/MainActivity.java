package com.qicang.qicang.base.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qicang.qicang.R;
import com.qicang.qicang.base.collections.CollectionsFragment;
import com.qicang.qicang.base.moments.MomentsFragment;
import com.qicang.qicang.base.news.NewsFragment;
import com.qicang.qicang.base.person.PersonFragment;
import com.qicang.qicang.base.utils.ExitManager;

public class MainActivity extends TabActivity {

    public static final long DIFF_DEFAULT_BACK_TIME = 2000;

    private long mBackTime = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void addTabs() {
        addTab(initTabView(R.drawable.navigation_ic_home_selector, R.string.main_tab_home), MomentsFragment.class, null);
//		addTab(initTabView(R.drawable.navigation_ic_store_selector, R.string.main_tab_store), StoreFragment.class, null);
        addTab(initTabView(R.drawable.tab_custom_normal, R.string.main_tab_custom), null, getString(R.string.main_tab_custom), null);
//		addTab(initTabView(R.drawable.navigation_ic_activity_selector, R.string.main_tab_activity), ActivityFragment.class, null);
        addTab(initTabView(R.drawable.navigation_ic_store_selector, R.string.main_tab_store), CollectionsFragment.class, null);
        addTab(initTabView(R.drawable.navigation_ic_shopcar_selector, R.string.main_tab_cart), NewsFragment.class, null);
        addTab(initTabView(R.drawable.navigation_ic_person_selector, R.string.main_tab_my), PersonFragment.class, null);
    }

    private View initTabView(int tabIcon, int tabText) {
        ViewGroup tab = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.item_main_tab, null);
        ImageView imageView = (ImageView) tab.findViewById(R.id.navigation);
        imageView.setImageResource(tabIcon);

        TextView textView = (TextView) tab.findViewById(R.id.txt_navigation);
        textView.setText(tabText);
        return tab;
    }

    @Override
    public void onBackPressed() {
        long nowTime = System.currentTimeMillis();
        long diff = nowTime - mBackTime;
        if (diff >= DIFF_DEFAULT_BACK_TIME) {
            mBackTime = nowTime;
            Toast.makeText(getApplicationContext(), R.string.toast_back_again_exit, Toast.LENGTH_SHORT).show();
        } else {
            ExitManager.instance.exit();
        }
    }
}
