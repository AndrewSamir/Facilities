package facilities.samir.andrew.facilities.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import facilities.samir.andrew.facilities.R;
import facilities.samir.andrew.facilities.models.UnittDetails.UnitPayment;


public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.MyViewHolder> {

    private List<UnitPayment> paymentDataList;

    public PaymentAdapter(List<UnitPayment> paymentDataList) {
        this.paymentDataList = paymentDataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview_payment, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        UnitPayment paymentData = paymentDataList.get(position);
        holder.paymentTypeName.setText(paymentData.getPaymentTypeName());
        holder.installmentAmount.setText(paymentData.getInstallmentAmount() + "");
        holder.dueDate.setText(paymentData.getDueDate());
        // if(paymentData.isPaid())
        if (paymentData.isPaid())
            holder.paid.setText("Paid");
        else
            holder.paid.setText("Not Paid");

        holder.paidAmount.setText(paymentData.getPaidAmount() + "");
        holder.dateOfPayment.setText(paymentData.getDateOfPayment());

    }

    @Override
    public int getItemCount() {
        return paymentDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView paymentTypeName, installmentAmount, dueDate, paid, paidAmount, dateOfPayment;

        MyViewHolder(View itemView) {
            super(itemView);
            paymentTypeName =  itemView.findViewById(R.id.tvPaymentTypeNamePayment);
            installmentAmount =  itemView.findViewById(R.id.tvInstallmentAmountPayment);
            dueDate =  itemView.findViewById(R.id.tvDueDatePayment);
            paid =  itemView.findViewById(R.id.tvPaidPayment);
            paidAmount =  itemView.findViewById(R.id.tvPaidAmountPayment);
            dateOfPayment =  itemView.findViewById(R.id.tvDateOfPaymentPayment);
        }
    }
}
