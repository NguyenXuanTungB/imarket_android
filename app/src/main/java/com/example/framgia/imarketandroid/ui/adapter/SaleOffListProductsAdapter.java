package com.example.framgia.imarketandroid.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.framgia.imarketandroid.R;
import com.example.framgia.imarketandroid.data.model.ItemProduct;
import com.example.framgia.imarketandroid.ui.activity.DetailsProductActivity;
import com.example.framgia.imarketandroid.util.Constants;

import java.util.ArrayList;

/**
 * Created by hoavt on 20/07/2016.
 */
public class SaleOffListProductsAdapter
    extends RecyclerView.Adapter<SaleOffListProductsAdapter.ViewHolder> {
    private ArrayList<ItemProduct> mItems = new ArrayList<>();
    private Context mContext;

    // Provide a suitable constructor (depends on the kind of dataset)
    public SaleOffListProductsAdapter(Context context, ArrayList<ItemProduct> myItems) {
        mContext = context;
        mItems = myItems;
    }

    public void setItems(ArrayList<ItemProduct> items) {
        mItems = items;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SaleOffListProductsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                    int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_list_product_saleoff, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(SaleOffListProductsAdapter.ViewHolder holder, int position) {
        ItemProduct itemProduct = mItems.get(position);
        ImageView ivPresentIcon = holder.mIvPresentIcon;
        ivPresentIcon.setImageResource(itemProduct.getPresentIcon());
        TextView nameProduct = holder.mTvNameProduct;
        nameProduct.setText(itemProduct.getNameProduct());
        holder.mTvPrince.setText(itemProduct.getPrice());
        holder.mTvPrince
            .setPaintFlags(holder.mTvPrince.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.mTvPrincePromotion.setText(itemProduct.getPricePromotion());
        FrameLayout promotionView = holder.mPromotionView;
        TextView percentSale = holder.mTvPercentSale;
        percentSale.setText(itemProduct.getPromotionPercent());
        if (itemProduct.getPromotionPercent().equals(Constants.NO_PERCENT)) {
            promotionView.setVisibility(View.GONE);
        } else {
            promotionView.setVisibility(View.VISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailsProductActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mIvPresentIcon;
        public TextView mTvNameProduct;
        public FrameLayout mPromotionView;
        public TextView mTvPercentSale, mTvPrince, mTvPrincePromotion;

        public ViewHolder(View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View parentView) {
            mIvPresentIcon = (ImageView) parentView.findViewById(R.id.iv_present_icon);
            mTvNameProduct = (TextView) parentView.findViewById(R.id.tv_name_product);
            mPromotionView = (FrameLayout) parentView.findViewById(R.id.fl_promotion_view);
            mTvPercentSale = (TextView) parentView.findViewById(R.id.tv_percent_sale);
            mTvPrince = (TextView) parentView.findViewById(R.id.tv_price_product);
            mTvPrincePromotion = (TextView) parentView.findViewById(R.id.tv_price_product_saleoff);
        }
    }
}
