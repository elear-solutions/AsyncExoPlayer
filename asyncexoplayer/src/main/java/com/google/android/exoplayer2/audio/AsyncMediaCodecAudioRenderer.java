package com.google.android.exoplayer2.audio;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderReuseEvaluation;
import com.google.android.exoplayer2.mediacodec.MediaCodecAdapter;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;

public class AsyncMediaCodecAudioRenderer extends MediaCodecAudioRenderer {

  private static final String TAG = "AsyncMediaCodecAudioRndr";

  @Nullable
  private Format inputFormat;

  public AsyncMediaCodecAudioRenderer(Context context,
                                      MediaCodecAdapter.Factory codecAdapterFactory,
                                      MediaCodecSelector mediaCodecSelector,
                                      boolean enableDecoderFallback,
                                      @Nullable Handler eventHandler,
                                      @Nullable AudioRendererEventListener eventListener,
                                      AudioSink audioSink) {

    super(context, codecAdapterFactory, mediaCodecSelector,
        enableDecoderFallback, eventHandler,
        eventListener, audioSink);

    Log.w(TAG, "instantiated async audio renderer");
  }


  @Nullable
  @Override
  protected DecoderReuseEvaluation onInputFormatChanged(FormatHolder formatHolder)
      throws ExoPlaybackException {
    @Nullable
    DecoderReuseEvaluation evaluation = super.onInputFormatChanged(formatHolder);

    inputFormat = formatHolder.format;

    return evaluation;
  }

  @Override
  protected void onDisabled() {
    super.onDisabled();
    inputFormat = null;
  }

  @Override
  public boolean isReady() {
    return null != inputFormat;
  }
}
