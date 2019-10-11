package com.example.islamiapp.fragments.radio;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.islamiapp.R;
import com.example.islamiapp.adapter.RadioChannelsRecyclerAdapter;
import com.example.islamiapp.apis.ApiManger;
import com.example.islamiapp.apis.model.RadioChannelsResponse;
import com.example.islamiapp.apis.model.RadiosItem;

import java.io.IOException;
import java.util.List;

public class RadioFragment extends Fragment {


    public RadioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_radio, container, false);
    }

    RadioChannelsRecyclerAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    View progressLayout;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView =view.findViewById(R.id.recycler_view);
        progressLayout = view.findViewById(R.id.progress_layout);
        initRecyclerView();
        getRadioChannels();
    }

    public void initRecyclerView(){
        adapter =new RadioChannelsRecyclerAdapter(null);
        layoutManager= new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        SnapHelper snapHelper =new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        adapter.setOnPlayClick(new RadioChannelsRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, RadiosItem channel) {
                playChannel(channel.getRadioUrl());
            }
        });
        adapter.setOnStopClick(new RadioChannelsRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, RadiosItem channel) {
                stopRadio();
            }
        });

    }
    MediaPlayer mediaPlayer;
    public void playChannel(String url){
        stopRadio();
        mediaPlayer =new MediaPlayer();
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "cannot play channel", Toast.LENGTH_SHORT).show();
        }

    }
    public void stopRadio(){
        if(mediaPlayer==null)return;
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer=null;
    }

    private void getRadioChannels() {
        ApiManger.getApis()
                .getRadioChannels()
                .enqueue(new Callback<RadioChannelsResponse>() {
                    @Override
                    public void onResponse(Call<RadioChannelsResponse> call,
                                           Response<RadioChannelsResponse> response) {
                        List<RadiosItem> radiosItems= response.body().getRadios();
                        adapter.changeData(radiosItems);
                        progressLayout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<RadioChannelsResponse> call,
                                          Throwable t) {
                        Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        progressLayout.setVisibility(View.GONE);
                    }
                });
    }
}
