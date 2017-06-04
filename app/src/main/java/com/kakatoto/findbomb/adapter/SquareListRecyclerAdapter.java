package com.kakatoto.findbomb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kakatoto.findbomb.R;
import com.kakatoto.findbomb.model.Square;
import com.kakatoto.findbomb.util.CommonUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SquareListRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String TAG = SquareListRecyclerAdapter.class.getSimpleName();
    //Data
    private ArrayList<Square> itemList;
    private Context context;
    private OnItemSelectListener onItemSelectListener;


    public SquareListRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setItemList(ArrayList<Square> itemList) {
        this.itemList = itemList;
    }

    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener) {
        this.onItemSelectListener = onItemSelectListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_square, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int pos) {

        Square item = itemList.get(pos);
        ViewHolder holder = (ViewHolder) viewHolder;

       // holder.txtNum.setText(String.valueOf(item.getCenter()));

        if(item.getBomb()) {
            holder.txtNum.setText("*");
            holder.txtNum.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }else{
            if(!CommonUtil.isNull(item.getMineCount())) {
                holder.txtNum.setText(String.valueOf(item.getMineCount()));
            }
        }



        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSelectListener.onItemSelect(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtNum)
        TextView txtNum;
        @BindView(R.id.txtCount)
        TextView txtCount;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemSelectListener {
        void onItemSelect(int pos);
    }
}
