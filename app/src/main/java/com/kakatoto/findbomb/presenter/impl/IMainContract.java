package com.kakatoto.findbomb.presenter.impl;

import com.kakatoto.findbomb.adapter.SquareListRecyclerAdapter;
import com.kakatoto.findbomb.model.Block;

/**
 * Created by ohyowan on 2017. 6. 3..
 */

public interface IMainContract {
    interface View{
        void setRecycler(int column);
    }

    interface Presenter{
        void attatch(View view);
        void detatch();
        void setAdapter(SquareListRecyclerAdapter adapter);
        void setBoard();
        Block setBlock(int pos);

        void setGameSet();
        void setMine();
        void setMineCount();
        void onSettingClick(String column, String row, String mine);
        void addMineCount(int pos);
    }
}
