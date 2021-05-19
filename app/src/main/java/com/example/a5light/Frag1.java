package com.example.a5light;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.util.Util;

import pyxis.uzuki.live.richutilskt.utils.RichUtils;

public class Frag1 extends Fragment
{
    // creating a variable for exoplayerview.
    SimpleExoPlayerView exoPlayerView;

    // creating a variable for exoplayer
    SimpleExoPlayer exoPlayer;

    // url of video which we are loading.
    String videoURL = "https://kw.commonscdn.com/contents/KW10000001/5ea6b4a1b0f03/contents/media_files/screen.mp4";
//https://media.geeksforgeeks.org/wp-content/uploads/20201217163353/Screenrecorder-2020-12-17-16-32-03-350.mp4
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = (ViewGroup) inflater.inflate(R.layout.fragment_menu1,container,false);
        Context context = view.getContext();
        exoPlayerView = view.findViewById(R.id.player_view);
        try {
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));

            // we are adding our track selector to exoplayer.
            exoPlayer = ExoPlayerFactory.newSimpleInstance(inflater.getContext(), trackSelector);

            DataSource.Factory dataSourceFactory1 = new DefaultDataSourceFactory(requireActivity(), Util.getUserAgent(requireActivity(), requireActivity().getPackageName()));
            MediaSource firstSource = new ExtractorMediaSource.Factory(dataSourceFactory1).createMediaSource(RawResourceDataSource.buildRawResourceUri(R.raw.panorama_sample));

            exoPlayerView.setPlayer(exoPlayer);

            exoPlayer.prepare(firstSource);

//            exoPlayer.setPlayWhenReady(true);

        } catch (Exception e) {
            // below line is used for
            // handling our errors.
            Log.e("TAG", "Error : " + e.toString());
        }
        return view;
    };
}
