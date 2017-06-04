package com.kakatoto.findbomb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.kakatoto.findbomb.adapter.SquareListRecyclerAdapter;
import com.kakatoto.findbomb.model.Square;
import com.kakatoto.findbomb.presenter.MainPresenter;
import com.kakatoto.findbomb.presenter.impl.IMainContract;
import com.kakatoto.findbomb.util.CommonUtil;
import com.kakatoto.findbomb.util.RecyclerDecGrid;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IMainContract.View {
    private final String TAG = MainActivity.class.getSimpleName();
    private MainPresenter presenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenter();
        presenter.attatch(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detatch();
    }

    @OnClick(R.id.btnSetting)
    public void settingButton() {
        Log.d(TAG, "settingButton: ");
        presenter.onSettingClick();


    }

    @OnClick(R.id.btnRefresh)
    public void refreshButton() {
        Log.d(TAG, "refreshButton: ");

    }

    @Override
    public void setRecycler(int column) {
        SquareListRecyclerAdapter adapter = new SquareListRecyclerAdapter(this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(column, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        recyclerView.setLayoutManager(layoutManager);
        presenter.setAdapter(adapter);
        recyclerView.setAdapter(adapter);
    }
}
