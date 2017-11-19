package th.ac.kku.nu.injectionroom.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import th.ac.kku.nu.injectionroom.R;

/**
 * Created by Pikkanet on 05-Nov-17.
 */

public class TenRAdapter extends RecyclerView.Adapter<TenRAdapter.ViewHolder>  {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public int currentItem;
        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemDetail;

        public ViewHolder(View itemView) {
            super(itemView);
            itemImage = (ImageView) itemView.findViewById(R.id.picture);
            itemTitle = (TextView) itemView.findViewById(R.id.title);
            itemDetail = (TextView) itemView.findViewById(R.id.detail);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ten_r_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String[] titles = {context.getResources().getString(R.string.ten_r_1),
                context.getResources().getString(R.string.ten_r_2),
                context.getResources().getString(R.string.ten_r_3),
                context.getResources().getString(R.string.ten_r_4),
                context.getResources().getString(R.string.ten_r_5),
                context.getResources().getString(R.string.ten_r_6),
                context.getResources().getString(R.string.ten_r_7),
                context.getResources().getString(R.string.ten_r_8),
                context.getResources().getString(R.string.ten_r_9),
                context.getResources().getString(R.string.ten_r_10)};

        String[] details = {context.getResources().getString(R.string.detail_ten_r_1),
                context.getResources().getString(R.string.detail_ten_r_2), context.getResources().getString(R.string.detail_ten_r_3),
                context.getResources().getString(R.string.detail_ten_r_4), context.getResources().getString(R.string.detail_ten_r_5),
                context.getResources().getString(R.string.detail_ten_r_6), context.getResources().getString(R.string.detail_ten_r_7),
                context.getResources().getString(R.string.detail_ten_r_8), context.getResources().getString(R.string.detail_ten_r_9),
                context.getResources().getString(R.string.detail_ten_r_10)};

        int[] images = {R.drawable.right_1,
                R.drawable.right_2,
                R.drawable.right_3,
                R.drawable.right_4,
                R.drawable.right_5,
                R.drawable.right_6,
                R.drawable.right_7,
                R.drawable.right_8,
                R.drawable.right_9,
                R.drawable.right_10};

        holder.itemTitle.setText(titles[position]);
        holder.itemDetail.setText(details[position]);
        Glide.with(context).load(images[position]).into(holder.itemImage);

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    private Context context;

    public TenRAdapter(Context mContext) {
        this.context = mContext;
        notifyDataSetChanged();
    }

}
