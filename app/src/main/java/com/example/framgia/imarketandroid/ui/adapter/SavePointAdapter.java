package com.example.framgia.imarketandroid.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.framgia.imarketandroid.R;
import com.example.framgia.imarketandroid.data.listener.OnRecyclerItemInteractListener;
import com.example.framgia.imarketandroid.data.model.CartItem;
import com.example.framgia.imarketandroid.data.model.SavedPointItem;
import com.example.framgia.imarketandroid.util.SystemUtil;

import java.util.ArrayList;

/**
 * Created by framgia on 26/10/2016.
 */
public class SavePointAdapter extends RecyclerView.Adapter<SavePointAdapter.ViewHolder>  {
    private ArrayList<SavedPointItem> mItems = new ArrayList<>();
    private Context mContext;
    private OnRecyclerItemInteractListener mListener;
    // Provide a suitable constructor (depends on the kind of dataset)
    public SavePointAdapter(Context context, ArrayList<SavedPointItem> myItems) {
        mContext = context;
        mItems = myItems;
    }
    public void setOnRecyclerItemInteractListener(OnRecyclerItemInteractListener listener) {
        mListener = listener;
    }
    public void setItems(ArrayList<SavedPointItem> items) {
        mItems = items;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SavePointAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_save_point, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final SavePointAdapter.ViewHolder holder, int position) {
        final SavedPointItem savedPointItem = mItems.get(position);
        holder.mIvPointSaved.setImageResource(savedPointItem.getmAvatar());
        holder.mTvNamePoint.setText(savedPointItem.getmNamePoint());
        holder.mTvNotepoint.setText(savedPointItem.getmNotePoint());
        holder.mIvDelPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, mContext.getResources().getString(R.string.remove) + " "
                    + savedPointItem.getmNamePoint(), Toast.LENGTH_SHORT).show();
                savedPointItem.setIsDeleted(true);
                mItems.remove(savedPointItem);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mIvPointSaved;
        public TextView mTvNamePoint;
        public TextView mTvNotepoint;
        public ImageView mIvDelPoint;

        public ViewHolder(View itemView) {
            super(itemView);
            mIvPointSaved = (ImageView) itemView.findViewById(R.id.iv_save_point);
            mTvNamePoint= (TextView) itemView.findViewById(R.id.tv_name_point);
            mTvNotepoint = (TextView) itemView.findViewById(R.id.tv_note_point);
            mIvDelPoint = (ImageView) itemView.findViewById(R.id.iv_del_save_point);
        }
    }

    public ArrayList<SavedPointItem> getItems() {
        return mItems;
    }
}
