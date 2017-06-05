package com.kakatoto.findbomb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kakatoto.findbomb.adapter.SquareListRecyclerAdapter;
import com.kakatoto.findbomb.presenter.MainPresenter;
import com.kakatoto.findbomb.presenter.impl.IMainContract;
import com.kakatoto.findbomb.util.CommonUtil;

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
        settingPopup();
    }

    @OnClick(R.id.btnRefresh)
    public void refreshButton() {
        presenter.setGameSet();
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

    public void settingPopup() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.setting_popup, (ViewGroup) findViewById(R.id.layout_root));
        ButterKnife.bind(layout, this);
        final EditText edtColumn = ButterKnife.findById(layout, R.id.edtColumn);
        final EditText edtRow = ButterKnife.findById(layout, R.id.edtRow);
        final EditText edtMine = ButterKnife.findById(layout, R.id.edtMine);


        new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT).setView(layout).setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        presenter.onSettingClick(edtColumn.getText().toString(), edtRow.getText().toString(), edtMine.getText().toString());
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        })
                .show();
    }
}
