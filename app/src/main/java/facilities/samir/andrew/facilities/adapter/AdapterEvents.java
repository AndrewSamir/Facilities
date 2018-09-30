package facilities.samir.andrew.facilities.adapter;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import facilities.samir.andrew.facilities.R;
import facilities.samir.andrew.facilities.activities.BaseActivity;
import facilities.samir.andrew.facilities.fragments.UnitDetailsFragment;
import facilities.samir.andrew.facilities.models.ModelEvents.ModelEvents;


/**
 * Created by andre on 07-May-17.
 */

public class AdapterEvents extends RecyclerView.Adapter<AdapterEvents.MyViewHolder> {

    private List<ModelEvents> adapterList;
    private Activity activity;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvRvItemTitle, tvRvItemContent;
        ImageView imgRvItemEvent;

        public MyViewHolder(View view) {
            super(view);

            tvRvItemTitle = view.findViewById(R.id.tvRvItemTitle);
            tvRvItemContent = view.findViewById(R.id.tvRvItemContent);
            imgRvItemEvent = view.findViewById(R.id.imgRvItemEvent);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            ((BaseActivity) activity).addContentFragment(UnitDetailsFragment.init(adapterList.get(getAdapterPosition())), true);
        }
    }

    public AdapterEvents(List<ModelEvents> adapterList, Activity activity) {
        this.adapterList = adapterList;
        this.activity = activity;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_event, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ModelEvents modelEvents = adapterList.get(position);

        holder.tvRvItemTitle.setText(modelEvents.getTitle());
        holder.tvRvItemContent.setText(modelEvents.getContent());

        Picasso.with(activity)
                .load(modelEvents.getImage());

    }

    @Override
    public int getItemCount() {
        return adapterList.size();
    }


    //region helper methods

    public void addItem(ModelEvents item) {
        insertItem(item, adapterList.size());
        notifyDataSetChanged();
    }

    public void insertItem(ModelEvents item, int position) {
        adapterList.add(position, item);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        adapterList.remove(position);
        notifyItemRemoved(position);
    }

    public void clearAllListData() {
        int size = adapterList.size();
        adapterList.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void addAll(List<ModelEvents> items) {
        int startIndex = adapterList.size();
        adapterList.addAll(items);
        notifyItemRangeInserted(startIndex, items.size());
    }

    public List<ModelEvents> getAllData() {
        return adapterList;
    }


    //endregion


}

