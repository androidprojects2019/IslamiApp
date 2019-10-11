package com.example.islamiapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.islamiapp.R;
import com.example.islamiapp.apis.model.RadiosItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RadioChannelsRecyclerAdapter extends RecyclerView.Adapter<RadioChannelsRecyclerAdapter.ViewHolder> {

    List<RadiosItem> channels;

    public RadioChannelsRecyclerAdapter(List<RadiosItem> channels) {
        this.channels = channels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_radio_channel,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(channels.get(position).getName() );
        if(onPlayClick!=null){
            holder.play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPlayClick.onItemClick(position,channels.get(position));
                }
            });
        }
       if(onStopClick!=null){
            holder.stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onStopClick.onItemClick(position,channels.get(position));
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if(channels==null)return 0;
        return channels.size();
    }

    public void setOnPlayClick(OnItemClickListener onPlayClick) {
        this.onPlayClick = onPlayClick;
    }

    public void setOnStopClick(OnItemClickListener onStopClick) {
        this.onStopClick = onStopClick;
    }

    OnItemClickListener onPlayClick;
    OnItemClickListener onStopClick;

    public interface OnItemClickListener{
        void onItemClick(int pos, RadiosItem channel);
    }
    public void changeData(List<RadiosItem> radiosItems) {
        this.channels =radiosItems;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView play;
        ImageView stop;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            play = itemView.findViewById(R.id.play);
            stop = itemView.findViewById(R.id.stop);
        }
    }
}
