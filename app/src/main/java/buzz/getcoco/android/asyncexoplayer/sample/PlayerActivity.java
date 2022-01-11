package buzz.getcoco.android.asyncexoplayer.sample;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import buzz.getcoco.exoplayer2.AsyncExoPlayer;
import buzz.getcoco.exoplayer2.MediaItem;
import buzz.getcoco.exoplayer2.source.AsyncProgressiveMediaSource;
import buzz.getcoco.exoplayer2.ui.PlayerView;
import buzz.getcoco.exoplayer2.upstream.DefaultHttpDataSource;

public class PlayerActivity extends AppCompatActivity {

  private AsyncExoPlayer player;
  private TextView tv;
  private PlayerView pv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_player);

    pv = findViewById(R.id.pvTest);
    tv = findViewById(R.id.tvTest);
  }

  @Override
  protected void onStart() {
    super.onStart();
    pv.onResume();

    player = new AsyncExoPlayer.Builder(this).build();

    pv.setPlayer(player);

    player.setMediaSource(
        new AsyncProgressiveMediaSource.Factory(new DefaultHttpDataSource.Factory())
            .createMediaSource(MediaItem.fromUri("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4")));

    player.prepare();
    player.setPlayWhenReady(true);

    tv.setText(player.toString());
  }

  @Override
  protected void onStop() {
    player.release();
    pv.onPause();
    super.onStop();
  }
}
