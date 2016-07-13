package elton.dankroomblank;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import android.animation.ObjectAnimator;
import android.animation.Animator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    Button btnWood, btnStop, btnTraps;
    TextView textViewTime;
    TextView textViewTraps;
    TextView wood;
    TextView traps;
    TextView textViewGatherer;
    TextView textViewCharcutier;
    public int woodcount=0;
    public int trapcount=0;
    ProgressBar woodbar;
    ProgressBar furbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wood = (TextView) findViewById(R.id.woodStore);
        traps = (TextView) findViewById(R.id.trapStore);

        btnWood = (Button) findViewById(R.id.btnWood);
        btnTraps = (Button) findViewById(R.id.btnTraps);
        //btnStop = (Button) findViewById(R.id.btnStop);
        //textViewTime = (TextView) findViewById(R.id.textViewTime);
        //textViewTraps = (TextView) findViewById(R.id.textViewTraps);
        textViewGatherer = (TextView) findViewById(R.id.textViewGatherer);
        textViewCharcutier = (TextView) findViewById(R.id.textViewCharcutier);
        woodbar = (ProgressBar)findViewById(R.id.woodProgressBar);
        furbar = (ProgressBar) findViewById(R.id.furProgressBar);

        //textViewTime.setText("00:00:05");

        final CounterClass timer = new CounterClass(5000, 1000);
        btnWood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.start();
                btnWood.setEnabled(false);
                woodcount+=10;
                String message = "Wood: " + woodcount;
                wood.setText(message);

                ObjectAnimator animation = ObjectAnimator.ofInt(woodbar,"progress", 100, 0);
                animation.setDuration(5000);
                animation.setInterpolator(new DecelerateInterpolator());
                animation.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) { }

                    @Override
                    public void onAnimationEnd(Animator animator) { }

                    @Override
                    public void onAnimationCancel(Animator animator) { }

                    @Override
                    public void onAnimationRepeat(Animator animator) { }
                });
                animation.start();
            }
        });

        //textViewTraps.setText("00:00:05");

        final CounterClas time = new CounterClas(5000, 1000);
        btnTraps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time.start();
                btnTraps.setEnabled(false);
                trapcount++;
                String message2 = "Fur: " + trapcount;
                traps.setText(message2);

                ObjectAnimator animation = ObjectAnimator.ofInt(furbar,"progress", 100, 0);
                animation.setDuration(5000);
                animation.setInterpolator(new DecelerateInterpolator());
                animation.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) { }

                    @Override
                    public void onAnimationEnd(Animator animator) { }

                    @Override
                    public void onAnimationCancel(Animator animator) { }

                    @Override
                    public void onAnimationRepeat(Animator animator) { }
                });
                animation.start();
            }
        });


    }

    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);
            //textViewTime.setText(hms);
        }



        @Override
        public void onFinish() {
            //textViewTime.setText("00:00:05");
            btnWood.setEnabled(true);
            Timer t = new Timer();
            t.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            woodcount += 2;
                            wood.setText("Wood: " + woodcount);
                        }
                    });
                }
            }, 0, 10000);
        }
    }

    public class CounterClas extends CountDownTimer {
        public CounterClas(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);
            //textViewTraps.setText(hms);

        }

        @Override
        public void onFinish() {
            //textViewTraps.setText("00:00:05");
            btnTraps.setEnabled(true);
        }
    }
}
