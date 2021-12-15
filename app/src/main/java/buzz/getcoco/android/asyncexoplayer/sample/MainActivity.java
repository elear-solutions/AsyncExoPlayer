package buzz.getcoco.android.asyncexoplayer.sample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button b = findViewById(R.id.btnNext);

    b.setOnClickListener(v -> {
      Log.d(TAG, "onCreate: starting player activity");
      startActivity(new Intent(this, PlayerActivity.class));
    });
  }
}
