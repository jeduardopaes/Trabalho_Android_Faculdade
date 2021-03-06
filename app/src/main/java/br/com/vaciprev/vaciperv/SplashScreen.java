package br.com.vaciprev.vaciperv;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    private Animation animationFadeIn;
    private ImageView ivSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        ivSplash = (ImageView) findViewById(R.id.ivSplash);
        ivSplash.startAnimation(animationFadeIn);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, TelaLogin.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }



}
