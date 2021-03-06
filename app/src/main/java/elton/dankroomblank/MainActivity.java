package elton.dankroomblank;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;


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

    Button btnWood, btnStop, btnTraps, btncharcutierup, btncharcutierdown;
    TextView textViewTime;
    TextView textViewTraps;
    TextView wood;
    TextView traps;
    TextView textViewGatherer;
    TextView textViewCharcutier;
    int woodcount = 0;
    public int trapcount = 0;
    public int charcutiercount = 0;
    ProgressBar woodbar;
    ProgressBar furbar;
    TextView charcutier;
    boolean wood1 =true;


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
        woodbar = (ProgressBar) findViewById(R.id.woodProgressBar);
        furbar = (ProgressBar) findViewById(R.id.furProgressBar);
        btncharcutierup = (Button) findViewById(R.id.charcutierup);
        btncharcutierdown = (Button) findViewById(R.id.charcutierdown);
        charcutier = (TextView) findViewById(R.id.textViewCharcutiercount);

        //textViewTime.setText("00:00:05");

        final CounterClass timer = new CounterClass(5000, 1000);
        btnWood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.start();
                btnWood.setEnabled(false);
                String message = "Wood: " + woodstore(woodcount + 10);
                wood.setText(message);

                ObjectAnimator animation = ObjectAnimator.ofInt(woodbar, "progress", 100, 0);
                animation.setDuration(5000);
                animation.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {
                    }
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

                ObjectAnimator animation = ObjectAnimator.ofInt(furbar, "progress", 100, 0);
                animation.setDuration(5000);
                animation.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {
                    }
                });
                animation.start();
            }
        });

        btncharcutierup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int wd = woodstore(woodcount);
                if (wd >= 10) {
                    charcutiercount += 1;
                    charcutier.setText(" " + charcutiercount);
                    wood.setText("Wood: " + woodstore(woodcount - 10));
                } else if (wd < 10) {
                    btncharcutierup.setEnabled(false);
                }
            }
        });

        final Handler h = new Handler();
        final int delay = 1000; //milliseconds
        if (wood1=true)
        h.postDelayed(new Runnable(){
            public void run(){
                woodcount+=2;
                wood.setText("Wood: " + woodcount);
                h.postDelayed(this, delay);
            }
        }, delay);
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
            wood1 = true;

            /*final Handler h = new Handler();
            final int delay = 1000; //milliseconds

            h.postDelayed(new Runnable(){
                public void run(){
                    woodcount+=2;
                    wood.setText("Wood: " + woodcount);
                    h.postDelayed(this, delay);
                }
            }, delay);*/
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

    public int woodstore(int x) {
        woodcount = x;
        return woodcount;
    }
}
