package com.example.quora.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quora.R;
import com.example.quora.model.BusinessItem;
import com.example.quora.model.UserItem;

import java.util.List;

public class BusinessAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<BusinessItem> businessItems;

   OnClickInterface onClickInterface;

    public BusinessAdapter(List<BusinessItem> businessItems,OnClickInterface onClickInterface) {
        this.businessItems = businessItems;
        this.onClickInterface = onClickInterface;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listof_businessprofile_layout, parent, false);
        return new BusinessViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((BusinessViewHolder)holder).bind(businessItems.get(position));
    }

    @Override
    public int getItemCount() {
        return businessItems.size();
    }

    public class BusinessViewHolder extends RecyclerView.ViewHolder
    {
        View itemView;

        public BusinessViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
            LinearLayout layout=itemView.findViewById(R.id.take_to_business);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickInterface.onclick(businessItems.get(getAdapterPosition()),getAdapterPosition());
                    System.out.println("something");
                }
            });

        }
        public void bind(BusinessItem businessItem)
        {
            TextView businessname = itemView.findViewById(R.id.tv_list_business_name);
            TextView businessbio = itemView.findViewById(R.id.tv_list_business_bio);
            ImageView businessimage=itemView.findViewById(R.id.image_for_list_business_name);
            businessname.setText(businessItem.getBusinessName());
            businessbio.setText(businessItem.getBio());
            Glide.with(businessimage.getContext()).load(String.valueOf(businessItem.getImage())).into(businessimage);

        }
    }

    public interface OnClickInterface
    {
        void onclick(BusinessItem businessItem,int position);
    }
}
