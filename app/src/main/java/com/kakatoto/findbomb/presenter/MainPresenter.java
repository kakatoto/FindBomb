package com.kakatoto.findbomb.presenter;

import com.kakatoto.findbomb.adapter.SquareListRecyclerAdapter;
import com.kakatoto.findbomb.model.Block;
import com.kakatoto.findbomb.presenter.impl.IMainContract;
import com.kakatoto.findbomb.util.CommonUtil;

import java.util.ArrayList;

/**
 * Created by ohyowan on 2017. 6. 3..
 */

public class MainPresenter implements IMainContract.Presenter {
    private final String TAG = MainPresenter.class.getSimpleName();
    private IMainContract.View view;
    private SquareListRecyclerAdapter adapter;
    private final int NONE = -999;
    int row = 0; // 줄
    int column = 0; // 칸
    int max = 0; // 최대칸
    int mine = 3; // 지뢰
    private ArrayList<Block> list = new ArrayList<>();

    @Override
    public void attatch(IMainContract.View view) {
        this.view = view;
    }

    @Override
    public void detatch() {
        this.view = null;
    }

    @Override
    public void setAdapter(SquareListRecyclerAdapter adapter) {
        this.adapter = adapter;
        this.adapter.setItemList(list);
        this.adapter.notifyDataSetChanged();
        this.adapter.setOnItemSelectListener(new SquareListRecyclerAdapter.OnItemSelectListener() {
            @Override
            public void onItemSelect(int pos) {

            }
        });
    }

    @Override
    public void onSettingClick(String c, String r, String m) {
        if (!CommonUtil.isNull(c) && !CommonUtil.isNull(r) && !CommonUtil.isNull(m)) {
            this.column = Integer.parseInt(c);
            this.row = Integer.parseInt(r);
            this.mine = Integer.parseInt(m);
            if (column > 0 && row > 0 && mine > 0) {
                this.max = row * column;
                this.setGameSet();
            }
        }
    }

    @Override
    public void setGameSet() {
        this.list.clear();
        setBoard();
        setMine();
        setMineCount();
        view.setRecycler(column);
    }

    @Override
    public void setBoard() {
        int pos = 1;
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                list.add(setBlock(pos));
                pos++;
            }
        }
    }

    @Override
    public Block setBlock(int pos) {
        Block square = new Block();
        int center = pos;
        int left = pos - 1 > 0 ? pos - 1 : NONE;
        int right = pos + 1 <= max ? pos + 1 : NONE;
        int up = pos - column > 0 ? pos - column : NONE;
        int down = pos + column <= max ? pos + column : NONE;
        int leftUp = pos - column - 1 > 0 ? pos - column - 1 : NONE;
        int rightUp = pos - (column - 1) > 0 ? pos - (column - 1) : NONE;
        int leftDown = pos + column - 1 <= max ? pos + column - 1 : NONE;
        int rightDown = pos + (column + 1) <= max ? pos + (column + 1) : NONE;

        if (pos % column == 0) {
            right = rightUp = rightDown = NONE;
        }

        if (pos % column == 1) {
            left = leftUp = leftDown = NONE;
        }

        square.setCenter(center);
        square.setLeft(left);
        square.setRight(right);
        square.setUp(up);
        square.setDown(down);
        square.setLeftUpConner(leftUp);
        square.setRightUpConner(rightUp);
        square.setLeftDownConner(leftDown);
        square.setRightDownConner(rightDown);
        square.setMineCount(0);
        square.setBomb(false);

        return square;
    }


    @Override
    public void setMine() {
        for (int i = 0; i < mine; i++) {
            int random = (int) (Math.random() * list.size());
            if (!list.get(random).getBomb()) {
                list.get(random).setBomb(true);
            }
        }
    }

    @Override
    public void setMineCount() {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getBomb()) {
                int left = list.get(i).getLeft();
                int right = list.get(i).getRight();
                int up = list.get(i).getUp();
                int down = list.get(i).getDown();
                int leftUp = list.get(i).getLeftUpConner();
                int rightUp = list.get(i).getRightUpConner();
                int reftDown = list.get(i).getLeftDownConner();
                int rightDown = list.get(i).getRightDownConner();

                addMineCount(left);
                addMineCount(right);
                addMineCount(up);
                addMineCount(down);
                addMineCount(leftUp);
                addMineCount(rightUp);
                addMineCount(reftDown);
                addMineCount(rightDown);
            }
        }
    }

    @Override
    public void addMineCount(int pos) {
        if (pos == NONE)
            return;

        int currentCount = list.get(pos - 1).getMineCount();
        list.get(pos - 1).setMineCount(currentCount + 1);
    }

}
