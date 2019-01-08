package chicken.head.espodeng;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView ;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.RotateAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

//import com.jaredrummler.android.device.DeviceName;
//import com.readystatesoftware.systembartint.SystemBarTintManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AboutActivity extends AppCompatActivity {
    TextView tv1;
//
//    RotateAnimation rotate = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF, 0.55f,Animation.RELATIVE_TO_SELF ,0.63f);
//    RotateAnimation rotate_sc = new RotateAnimation(-90,0, Animation.RELATIVE_TO_SELF, 0.39f,Animation.RELATIVE_TO_SELF ,0.69f);
//    RotateAnimation rotate_h = new RotateAnimation(90, 0, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF ,0.5f);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //final ActionBar actionBar=getSupportActionBar();

        SystemBarTintManager systemBarTintManager=new SystemBarTintManager(this);
        systemBarTintManager.setStatusBarTintEnabled(true);
        systemBarTintManager.setStatusBarTintColor(MainActivity.rColor);

        if (null!=toolbar){
            toolbar.setBackgroundDrawable(new ColorDrawable(MainActivity.rColor));
            if(custom.rgbne >500){
                SpannableString ss=new SpannableString(getTitle());
                //ss.setSpan(new ForegroundColorSpan(getRandomColor()),0, getTitle().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                ss.setSpan(new ForegroundColorSpan(Color.BLACK),0, getTitle().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                getSupportActionBar().setTitle(ss);
            }

            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){

                getWindow().setStatusBarColor(Color.BLACK);
            }
        }
        setCustomActionBar("sRadio", "About Application");
        View v= getLayoutInflater().inflate(R.layout.custom_actionbar,null);
        //v.getHeight();
        RelativeLayout rl=(RelativeLayout)findViewById(R.id.rl_about);
        //rl.setPadding(6,240,6,6);
        //setCustomActionBar();
        String versionName="";
        try
        {
            versionName = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0).versionName ;
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace() ;
        }

//
//        rotate.setDuration(5000);
//        rotate.setRepeatMode(Animation.ABSOLUTE);
//        rotate.setInterpolator(new LinearInterpolator() );
//        rotate.setFillAfter(true );
//
//        rotate_sc.setDuration(5000) ;
//        rotate_sc.setRepeatMode(Animation.ABSOLUTE);
//        rotate_sc.setInterpolator(new LinearInterpolator() );
//        rotate_sc.setFillAfter(true );
//
//        rotate_h.setDuration(5000) ;
//        rotate_h.setRepeatMode(Animation.ABSOLUTE);
//        rotate_h.setInterpolator(new LinearInterpolator() );
//        rotate_h.setFillAfter(true );



        ImageView ivbigc = (ImageView)  findViewById(R.id.ivbigc );

        //ivbigc.setImageResource(R.drawable.company_logo_black);
        ivbigc.setImageResource(R.mipmap.espodeng_icon_new);


        /**
        ImageView ivsmallc=(ImageView)  findViewById(R.id.ivsmallc );
        ImageView ivh=(ImageView)  findViewById(R.id.ivh );
        Bitmap bigc= BitmapFactory.decodeResource(getResources(),R.drawable.logo_big_c  );
        ivbigc.setImageBitmap(bigc);
        //ivbigc.startAnimation(rotate );
        Bitmap smallc= BitmapFactory.decodeResource(getResources(),R.drawable.logo_small_c  );
        ivsmallc.setImageBitmap(smallc);
        //ivsmallc.startAnimation(rotate_sc );
        Bitmap h= BitmapFactory.decodeResource(getResources(),R.drawable.logo_h  );
        ivh.setImageBitmap(h);
        //ivh.startAnimation(rotate_h );
**/
        tv1=(TextView )findViewById(R.id.textViewCP );
        SimpleDateFormat dfDate=new SimpleDateFormat("yyyy");
        String dt="";
        Calendar c=Calendar.getInstance();
        dt=dfDate.format(c.getTime());

//        tv1.setTypeface(MainActivity.typeface);
        tv1.setText(Html.fromHtml(getResources().getString(R.string.action_restart) ) + System.getProperty("line.separator")+" " + (dt) );
        //tv1.setText(R.string.copy_left + System.getProperty("line.sparator")+ R.string.copy_left );
        //tv1.setText(R.string.copy_left+" " +(dt));

        //tv1.setText(dt);

        TextView tvapp=(TextView)  findViewById(R.id.tv_app );
        tvapp.setText(R.string.app_name );
//        tvapp.setTypeface(MainActivity.typeface);
        TextView tvversion=(TextView)  findViewById(R.id.tv_version ) ;
        tvversion.setText("Version " + versionName );
//        tvversion.setTypeface(MainActivity.typeface);
        TextView tvauth=(TextView)findViewById(R.id.tv_auth);
        tvauth.setText("Author : Ibnu Sartono");
//        tvauth.setTypeface(MainActivity.typeface);
    }

    @Override
    public void onBackPressed(){
        //zoonk

//        MainActivity.home=true;
       finish();
    }
   /* public void setCustomActionBar(){
        final ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        // getSupportActionBar().setCustomView(R.layout.custom_actionbar);
        actionBar.setCustomView(getLayoutInflater().inflate(R.layout.custom_actionbar, null),
                new ActionBar.LayoutParams(
                        ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.MATCH_PARENT,
                        Gravity.CENTER
                ));

        actionBar.setTitle(R.string.app_name);
//        TextView tvtitle=(TextView)findViewById(R.id.ab_text);
        TextView tvtitle=(TextView)findViewById(R.id.ab_text);

        tvtitle.setText(R.string.about);
        tvtitle.setTextColor(Color.LTGRAY);
        //Drawable d=getResources().getDrawable(R.drawable.w95_form_top_header);
        if (null!=actionBar){
            actionBar.setBackgroundDrawable(new ColorDrawable(MainActivity.rColor));
          //  actionBar.setBackgroundDrawable(d);

        }

        //if (custom.rgbne>500){
            //tvtitle.setTextColor(Color.BLACK);
            SpannableString ss=new SpannableString(getTitle());
            //ss.setSpan(new ForegroundColorSpan(getRandomColor()),0, getTitle().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new ForegroundColorSpan(Color.LTGRAY),0, getTitle().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            //getSupportActionBar().setTitle(ss);
            actionBar.setTitle(ss);
        //}

    }*/

    public void setCustomActionBar(String abtitle, String abstitle){
        final ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        //getSupportActionBar().setCustomView(R.layout.custom_actionbar);
        actionBar.setCustomView(getLayoutInflater().inflate(R.layout.custom_actionbar, null),
                new ActionBar.LayoutParams(
                        ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.MATCH_PARENT,
                        Gravity.CENTER

                ));

        //actionBar.setTitle(R.string.app_name);

        actionBar.setTitle(abtitle);
        actionBar.setSubtitle(abstitle);


//        TextView tvtitle=(TextView)findViewById(R.id.ab_text);
        TextView tvtitle=(TextView)findViewById(R.id.ab_text);
        TextView tvstitle=(TextView)findViewById(R.id.abs_text) ;

//        tvtitle.setTypeface(MainActivity.typeface);
        tvtitle.setText(R.string.app_name);
        tvtitle.setText(abtitle);
        tvtitle.setTextColor(Color.WHITE);

//        tvstitle.setTypeface(MainActivity.typeface);
        tvstitle.setText(R.string.app_name);
        tvstitle.setText(abstitle);
        tvstitle.setTextColor(Color.WHITE);

//        Drawable d=getResources().getDrawable(R.drawable.w95_form_top_header);
//       // getActionBar().setBackgroundDrawable(d);
        if (null!=actionBar){
            // actionBar.setBackgroundDrawable(new ColorDrawable(R.color.w95_base_color));
            //actionBar.setBackgroundDrawable(d);
            actionBar.setElevation(0);
            actionBar.setBackgroundDrawable(new ColorDrawable(MainActivity.rColor));


        }

        //setStatusbarTranslucent(true);

        if (custom.rgbne>400){
            tvtitle.setTextColor(Color.BLACK);
            tvstitle.setTextColor(Color.BLACK);
            MainActivity.headColor=(Color.BLACK);
            SpannableString ss=new SpannableString(getTitle());
            //ss.setSpan(new ForegroundColorSpan(getRandomColor()),0, getTitle().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            //ss.setSpan(new ForegroundColorSpan(Color.BLACK),0, getTitle().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(ss);
            actionBar.setTitle(ss);

        }else{MainActivity.headColor=Color.WHITE;}

    }
}
