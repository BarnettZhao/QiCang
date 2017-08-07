package com.qicang.qicang.base.main;

import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.qicang.qicang.R;
import com.qicang.qicang.base.base.BaseActivity;

/**
 * Created by zww on 2017/8/7.
 */

public abstract class TabActivity extends BaseActivity implements TabHost.OnTabChangeListener {

    private FragmentTabHost mTabHost;
    /**
     * Tab控件
     */
    protected TabWidget mTabWidget;
    /**
     * 当前Tab页下标
     */
    protected int currentIndex;
    protected int preIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_tab);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this,getSupportFragmentManager(),R.id.fcontainer);
        addTabs();

        mTabHost.setOnTabChangedListener(this);
        mTabWidget = mTabHost.getTabWidget();
        mTabWidget.setDividerDrawable(null);
        if (savedInstanceState != null) {
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
            currentIndex = mTabHost.getCurrentTab();
        }
    }

    protected void setCurrentTab(int tabIndex) {
        mTabHost.setCurrentTab(tabIndex);
    }

    protected abstract void addTabs();

    protected void addTab(View tabView, Class<?> cls, Bundle bundle) {
        mTabHost.addTab(mTabHost.newTabSpec(cls.getSimpleName()).setIndicator(tabView), cls, bundle);
    }

    protected void addTab(View tabView, Class<?> cls, String tabName, Bundle bundle) {
        mTabHost.addTab(mTabHost.newTabSpec(tabName).setIndicator(tabView), cls, bundle);
    }

    @Override
    public void onTabChanged(String tabId) {
        preIndex = currentIndex;
        currentIndex = mTabHost.getCurrentTab();
    }
}
