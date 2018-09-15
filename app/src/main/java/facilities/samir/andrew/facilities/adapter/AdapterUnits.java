package facilities.samir.andrew.facilities.adapter;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import facilities.samir.andrew.facilities.R;
import facilities.samir.andrew.facilities.activities.BaseActivity;
import facilities.samir.andrew.facilities.fragments.BaseFragment;
import facilities.samir.andrew.facilities.fragments.UnitDetailsFragment;
import facilities.samir.andrew.facilities.models.UnittDetails.UnitDetailsModel;


/**
 * Created by andre on 07-May-17.
 */

public class AdapterUnits extends RecyclerView.Adapter<AdapterUnits.MyViewHolder> {

    private List<UnitDetailsModel> adapterList;
    private Activity activity;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvRvItemUnitName;

        public MyViewHolder(View view) {
            super(view);

            tvRvItemUnitName = view.findViewById(R.id.tvRvItemUnitName);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ((BaseActivity) activity).addContentFragment(UnitDetailsFragment.init(adapterList.get(getAdapterPosition())), true);
        }
    }

    public AdapterUnits(List<UnitDetailsModel> adapterList, Activity activity) {
        this.adapterList = adapterList;
        this.activity = activity;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_unit, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        UnitDetailsModel unitDetailsModel = adapterList.get(position);

        holder.tvRvItemUnitName.setText(unitDetailsModel.getUnitDetails().getUnitCode());

    }

    @Override
    public int getItemCount() {
        return adapterList.size();
    }


    //region helper methods

    public void addItem(UnitDetailsModel item) {
        insertItem(item, adapterList.size());
        notifyDataSetChanged();
    }

    public void insertItem(UnitDetailsModel item, int position) {
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

    public void addAll(List<UnitDetailsModel> items) {
        int startIndex = adapterList.size();
        adapterList.addAll(items);
        notifyItemRangeInserted(startIndex, items.size());
    }

    public List<UnitDetailsModel> getAllData() {
        return adapterList;
    }


    //endregion


}

