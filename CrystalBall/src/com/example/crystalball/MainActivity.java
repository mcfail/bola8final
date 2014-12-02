package com.example.crystalball;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.media.*;

public class MainActivity extends Activity {
	
	private CrystalBall mCrystalBall = new CrystalBall();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Declare our View variables and assign them the Views from the layout file
        final TextView answerLabel = (TextView) findViewById(R.id.textView1);
        Button getAnswerButton = (Button) findViewById(R.id.button1);

        getAnswerButton.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
            	String answer = mCrystalBall.getAnAnswer();
                animateBall();
                animateAnswer();
                playSound();
                // Update the label with our dynamic answer
                answerLabel.setText(answer);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void animateBall(){
    	ImageView imagen =(ImageView)findViewById(R.id.imageView1);
    	imagen.setImageResource(R.drawable.ball_animation);
    	AnimationDrawable anima=(AnimationDrawable)imagen.getDrawable();
    	
    	anima.start();
    	
    	if (anima.isRunning()){
    		anima.stop();
    	}

    }
    
    private void animateAnswer(){
    	
    	float fromAlpha=0;
        float toAlpha=1;
    	AlphaAnimation anim=new AlphaAnimation(fromAlpha, toAlpha);
    	
    	anim.setDuration(1500);
    	anim.setFillAfter(true);
    	
    	TextView answer =(TextView) findViewById(R.id.textView1);
    	
    	answer.setAnimation(anim);    	
    }
    
    private void playSound(){
    	
    	MediaPlayer m = null;
    	m.create(this,  R.raw.magic_ball);
    	m.start();
    	
    	m.setOnCompletionListener(new OnCompletionListener(){
    		@Override
    		public void onCompletion (MediaPlayer m){
    			m.release();
    		}
    	});
    	
    	
    }
}
