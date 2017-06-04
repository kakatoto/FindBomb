package com.kakatoto.findbomb.presenter.impl;

import com.kakatoto.findbomb.adapter.SquareListRecyclerAdapter;

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

        void onSettingClick();
    }
}
