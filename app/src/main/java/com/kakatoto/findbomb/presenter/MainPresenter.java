package com.kakatoto.findbomb.presenter;

import android.util.Log;

import com.kakatoto.findbomb.adapter.SquareListRecyclerAdapter;
import com.kakatoto.findbomb.model.Square;
import com.kakatoto.findbomb.presenter.impl.IMainContract;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ohyowan on 2017. 6. 3..
 */

public class MainPresenter implements IMainContract.Presenter {
    private final String TAG = MainPresenter.class.getSimpleName();
    private IMainContract.View view;
    private SquareListRecyclerAdapter adapter;

    int row = 7; // 줄
    int column = 7; // 칸
    int max = row * column;
    int bomb = 3; // 지뢰
    private ArrayList<Square> list = new ArrayList<>();

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
    public void onSettingClick() {
        list.clear();
        setBoard();
        setMine();
        setMineCount();
        view.setRecycler(column);
    }

    public void setBoard(){
        int count = 1;
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                list.add(setSquare(count));
                count++;
            }
        }
    }

    public Square setSquare(int num) {
        Square square = new Square();
        int center = num;
        int left = num - 1 > 0 ? num - 1 : -1;
        int right = num + 1 <= max ? num + 1 : -1;
        int up = num - row > 0 ? num - row : -1;
        int down = num + row <= max ? num + row : -1;
        int leftUp = num - row - 1 > 0 ? num - row - 1 : -1;
        int rightUp = num - (row - 1) > 0 ? num - (row - 1) : -1;
        int leftDown = num + row - 1 <= max ? num + row - 1 : -1;
        int rightDown = num + (row + 1) <= max ? num + (row + 1) : -1;

        if (num % row == 0) {
            right = rightUp = rightDown = -1;
        }

        if (num % row == 1) {
            left = leftUp = leftDown = -1;
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


    public void setMine() {
        for (int i = 0; i < bomb; i++) {
            int random = (int) (Math.random() * list.size());
            if (!list.get(random).getBomb()) {
                list.get(random).setBomb(true);
            }
        }
    }

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

    public void addMineCount(int num) {
        if (num == -1)
            return;

        int currentCount = list.get(num-1).getMineCount();
        list.get(num-1).setMineCount(currentCount + 1);
    }
}
