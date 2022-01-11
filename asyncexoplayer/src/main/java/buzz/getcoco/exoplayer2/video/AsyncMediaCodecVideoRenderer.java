package buzz.getcoco.exoplayer2.video;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.Nullable;
import buzz.getcoco.exoplayer2.ExoPlaybackException;
import buzz.getcoco.exoplayer2.Format;
import buzz.getcoco.exoplayer2.FormatHolder;
import buzz.getcoco.exoplayer2.decoder.DecoderReuseEvaluation;
import buzz.getcoco.exoplayer2.mediacodec.MediaCodecAdapter;
import buzz.getcoco.exoplayer2.mediacodec.MediaCodecSelector;
import buzz.getcoco.exoplayer2.video.MediaCodecVideoRenderer;
import buzz.getcoco.exoplayer2.video.VideoRendererEventListener;

public class AsyncMediaCodecVideoRenderer extends MediaCodecVideoRenderer {

  private static final String TAG = "AsyncMediaCodecVideoRnd";

  @Nullable
  private Format inputFormat;

  public AsyncMediaCodecVideoRenderer(Context context,
                                      MediaCodecAdapter.Factory codecAdapterFactory,
                                      MediaCodecSelector mediaCodecSelector,
                                      long allowedJoiningTimeMs,
                                      boolean enableDecoderFallback,
                                      @Nullable Handler eventHandler,
                                      @Nullable VideoRendererEventListener eventListener,
                                      int maxDroppedFramesToNotify) {

    super(context, codecAdapterFactory, mediaCodecSelector, allowedJoiningTimeMs,
        enableDecoderFallback, eventHandler, eventListener, maxDroppedFramesToNotify);

    Log.w(TAG, "instantiated async video renderer");
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
