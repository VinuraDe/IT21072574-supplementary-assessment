package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SupplierAdapter extends RecyclerView.Adapter<SupplierAdapter.ViewHolder> {

    List<SupplierModel> supplier;
    Context context;
    DBHelper dbHelper;

    public SupplierAdapter(List<SupplierModel> supplier, Context context) {
        this.supplier = supplier;
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.supplier_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final SupplierModel supplierModel = supplier.get(position);

        holder.textViewID.setText(Integer.toString(supplierModel.getId()));
        holder.editText_Name.setText(supplierModel.getName());
        holder.editText_Email.setText(supplierModel.getEmail());
        holder.editText_Address.setText(supplierModel.getAddress());
        holder.editText_Phone.setText(supplierModel.getPhone());

        holder.button_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = holder.editText_Name.getText().toString();
                String stringEmail = holder.editText_Email.getText().toString();
                String stringAddress = holder.editText_Address.getText().toString();
                String stringPhone = holder.editText_Phone.getText().toString();

                dbHelper.updateSupplier(new SupplierModel(supplierModel.getId(),stringName,stringEmail, stringAddress, stringPhone));
                notifyDataSetChanged();
                Toast.makeText(context, "Supplier details updated successfully....", Toast.LENGTH_SHORT).show();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteSupplier(supplierModel.getId());
                supplier.remove(position);
                Toast.makeText(context, "Supplier Deleted.....", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return supplier.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        EditText editText_Name,editText_Email, editText_Address, editText_Phone;
        Button button_Edit;
        Button button_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            editText_Name = itemView.findViewById(R.id.edittext_name);
            editText_Email = itemView.findViewById(R.id.edittext_email);
            editText_Address = itemView.findViewById(R.id.edittext_address);
            editText_Phone = itemView.findViewById(R.id.edittext_phone);
            button_delete = itemView.findViewById(R.id.button_delete);
            button_Edit = itemView.findViewById(R.id.button_edit);

        }
    }
}