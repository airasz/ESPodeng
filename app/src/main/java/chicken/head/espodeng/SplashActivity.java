package chicken.head.espodeng;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity ;
import android.os.Bundle;
import android.content.Intent ;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random ;

public class SplashActivity extends Activity {
    RelativeLayout relativeLayout ;
    private Random random = new Random() ;
    private final String[] satu = new String[] {"satu", "uno", "one", "siji", "bir", "ahad", "un",
    "jedan","en", "eine","yonn","moja","viens", "vienas","jeden","um", "ichi", "Shu", "mot", "odin",
    "ena", "tunggal", "një" ,"አንድ" , "մեկ", "bat","адзін", "এক","един","sa usa ka","chimodzi", "一", "üks",
    "isa", "yksi", "ერთ-ერთი", "ένας", "એક", "daya", "kekahi", "אחד" , "एक", "ib tug", "egy", "otu",
    "amháin", "бір", "មួយ", "한", "yek", "unum", "unung", "oru", "hiji"}  ;
    //String qandomQuote
    private final ArrayList<String> katasatu=new ArrayList<String>(Arrays.asList(satu) );
//    public static ImageView iv_jar_line;

    RotateAnimation rotat = new RotateAnimation(0, 40, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF ,0.5f);
    RotateAnimation rotat2 = new RotateAnimation(0, 59, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF ,0.5f);
    RotateAnimation rotat3 = new RotateAnimation(0, 79, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF ,0.5f);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //Random rnd = new Random();
        //View view= (view)findViewById(R.layout.activity_splash);

        //int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256),rnd.nextInt(256));

        relativeLayout = (RelativeLayout)findViewById(R.id.activity_splash )  ;
        //relativeLayout.setBackgroundColor(getRandomColor());
        relativeLayout.setBackgroundColor(Color.BLACK);
        final TextView tv_s= (TextView)findViewById(R.id.tv_s);
        tv_s.setText(katasatu.get(random.nextInt(katasatu.size())));
/**
         ImageView iv_jar1=(ImageView)findViewById(R.id.ivjr_1 );
         ImageView iv_jar2=(ImageView)findViewById(R.id.ivjr_2 );
         ImageView iv_jar3=(ImageView)findViewById(R.id.ivjr_3 );
**/

        rotat.setDuration(2000);
        rotat.setRepeatCount(Animation.INFINITE) ;
        rotat.setRepeatMode(Animation.REVERSE);
        rotat.setInterpolator(new LinearInterpolator() );
        rotat.setFillAfter(true );

        rotat2.setDuration(1000);
        rotat2.setRepeatCount(Animation.INFINITE) ;
        rotat2.setRepeatMode(Animation.REVERSE );
        rotat2.setInterpolator(new LinearInterpolator() );
        rotat2.setFillAfter(true );


        rotat3.setDuration(500);
        rotat3.setRepeatCount(Animation.INFINITE) ;
        rotat3.setRepeatMode(Animation.REVERSE);
        rotat3.setInterpolator(new LinearInterpolator() );
        rotat3.setFillAfter(true );

/**
        Bitmap jar_r1=BitmapFactory.decodeResource(getResources() ,R.drawable.jar_ring_1  );
        iv_jar1.setImageBitmap(jar_r1);
      //  iv_jar1.startAnimation(rotat );
        Bitmap jar_r2=BitmapFactory.decodeResource(getResources() ,R.drawable.jar_ring_2 );
        iv_jar2.setImageBitmap(jar_r2);
      //  iv_jar2.startAnimation(rotat2 );
        Bitmap jar_r3=BitmapFactory.decodeResource(getResources() ,R.drawable.jar_ring_3  );
        iv_jar3.setImageBitmap(jar_r3);
     //   iv_jar3.startAnimation(rotat3);

        iv_jar1.setImageResource(R.drawable.jar_ring_1) ;
        iv_jar2.setImageResource(R.drawable.jar_ring_2) ;
        iv_jar2.startAnimation(rotate2 );
        iv_jar3.setImageResource(R.drawable.jar_ring_3) ;
        iv_jar3.startAnimation(rotate3 );
        iv_jar_line.setImageResource(R.drawable.jar_line_1) ;


        ImageView iv_jar_line=(ImageView)findViewById(R.id.iv_jar_line);
       // iv_jar_line.setImageResource(R.drawable.jar_line_a) ;
 **/

        ImageView ivs=(ImageView)findViewById(R.id.iv_splash );
        ivs.setImageResource(R.drawable.espradio) ;

        /**
         *
         ImageView ivd=(ImageView )findViewById(R.id.ivdua );
         ivd.setImageResource(R.drawable.company_logo_alumunium );
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent );
         **/
    }

    public void onStart(){
        super.onStart() ;

        Thread timerThread = new Thread(){
            public void run (){
                try{
                    sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace() ;
                }finally {
                    rotat.cancel() ;
                    rotat2.cancel() ;
                    rotat3.cancel() ;

                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent );
                    SplashActivity.this.finish();
                }
            }
        };
        timerThread.start() ;
    }
    @Override
    protected void onPause(){
        super.onPause() ;
       // finish() ;
    }
    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(180),rnd.nextInt(180),rnd.nextInt(180));

    }
}
