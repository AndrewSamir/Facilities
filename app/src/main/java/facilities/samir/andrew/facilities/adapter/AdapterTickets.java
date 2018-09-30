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
import facilities.samir.andrew.facilities.models.ModelTickets.Ticket;


/**
 * Created by andre on 07-May-17.
 */

public class AdapterTickets extends RecyclerView.Adapter<AdapterTickets.MyViewHolder> {

    private List<Ticket> adapterList;
    private Activity activity;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvRvItemTicketUnit, tvRvItemTicketNumber, tvRvItemTicketStatus;

        public MyViewHolder(View view) {
            super(view);

            tvRvItemTicketUnit = view.findViewById(R.id.tvRvItemTicketUnit);
            tvRvItemTicketNumber = view.findViewById(R.id.tvRvItemTicketNumber);
            tvRvItemTicketStatus = view.findViewById(R.id.tvRvItemTicketStatus);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            ((BaseActivity) activity).addContentFragment(UnitDetailsFragment.init(adapterList.get(getAdapterPosition())), true);
        }
    }

    public AdapterTickets(List<Ticket> adapterList, Activity activity) {
        this.adapterList = adapterList;
        this.activity = activity;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_ticket, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Ticket ticket = adapterList.get(position);

        holder.tvRvItemTicketUnit.setText(ticket.getUnit());
        holder.tvRvItemTicketNumber.setText(ticket.getTicketnumber());
        holder.tvRvItemTicketStatus.setText(ticket.getStatus());

    }

    @Override
    public int getItemCount() {
        return adapterList.size();
    }


    //region helper methods

    public void addItem(Ticket item) {
        insertItem(item, adapterList.size());
        notifyDataSetChanged();
    }

    public void insertItem(Ticket item, int position) {
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

    public void addAll(List<Ticket> items) {
        int startIndex = adapterList.size();
        adapterList.addAll(items);
        notifyItemRangeInserted(startIndex, items.size());
    }

    public List<Ticket> getAllData() {
        return adapterList;
    }


    //endregion


}

