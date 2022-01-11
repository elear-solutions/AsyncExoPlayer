package buzz.getcoco.exoplayer2.audio;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.Nullable;
import buzz.getcoco.exoplayer2.ExoPlaybackException;
import buzz.getcoco.exoplayer2.Format;
import buzz.getcoco.exoplayer2.FormatHolder;
import buzz.getcoco.exoplayer2.audio.AudioRendererEventListener;
import buzz.getcoco.exoplayer2.audio.AudioSink;
import buzz.getcoco.exoplayer2.audio.MediaCodecAudioRenderer;
import buzz.getcoco.exoplayer2.decoder.DecoderReuseEvaluation;
import buzz.getcoco.exoplayer2.mediacodec.MediaCodecAdapter;
import buzz.getcoco.exoplayer2.mediacodec.MediaCodecSelector;

public class AsyncMediaCodecAudioRenderer extends MediaCodecAudioRenderer {

  private static final String TAG = "AsyncMediaCodecAudioRdr";

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
