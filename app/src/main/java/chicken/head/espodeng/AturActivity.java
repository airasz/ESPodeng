package chicken.head.espodeng;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

import static chicken.head.espodeng.MainActivity.random_color_state;
import static chicken.head.espodeng.MainActivity.sharedPreferences;
import static chicken.head.espodeng.MainActivity.NUANSA;
import static chicken.head.espodeng.MainActivity.rColor;
import static chicken.head.espodeng.MainActivity.random_color_state;
import static chicken.head.espodeng.MainActivity.random_color_state;
//import static chicken.head.espodeng.MainActivity.auto_espodeng;

import static chicken.head.espodeng.MainActivity.RNDCOLSTATE;

import yuku.ambilwarna.AmbilWarnaDialog;
import chicken.head.espodeng.MainActivity;

public class AturActivity extends AppCompatActivity implements View.OnClickListener{
    public static Button btn_openD_color;
    public static Button btn_rnd_color;
    public static Button btn_rnd_color2;
    Button btn_adz_rmd, btn_adz_rmd2,btn_unf_adz, btn_unf_adz2,btn_auto_adz,btn_auto_adz2;
    public static int Scolor;
    public static SharedPreferences.Editor editor=sharedPreferences.edit();


    public static AlarmManager am1, am2;
    public static PendingIntent pendingIntent, pendingIntent2;
    public static Calendar cal ,cal2;
    public static int menit , notify_ID, waktu_ID;
    static int haripertaun;
    public static int sb_tmp_value=0;
    SeekBar seekBar_font_zoom;
    TextView tv_sb_val;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atur);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){

            getWindow().setStatusBarColor(Color.BLACK);
        }
        initializingComponent();
        final Typeface face=Typeface.createFromAsset(getAssets(),"weblysleekuil.ttf" );
        TextView tv_setting_title_b=(TextView)findViewById(R.id.tv_setting_title);
        TextView tv_setting_title_s=(TextView)findViewById(R.id.tv_setting_stitle);
        TextView tv_setting_name=(TextView)findViewById(R.id.tv_set_name);
        TextView tv_setting_name2=(TextView)findViewById(R.id.tv_set_name2);
        TextView tv_setting_name3=(TextView)findViewById(R.id.tv_set_name3);
        //tv_setting_name3.setVisibility(View.INVISIBLE);
        TextView tv_setting_name4=(TextView)findViewById(R.id.tv_set_name4);
//        tv_setting_title_b.setTypeface(face);
        TextView tv_setting_name5=(TextView)findViewById(R.id.tv_set_name5);
        tv_setting_name5.setVisibility(View.INVISIBLE);

        tv_setting_title_s.setTypeface(face);
        tv_setting_name.setTypeface(face);
        tv_setting_name2.setTypeface(face);
        tv_setting_name3.setTypeface(face);
        tv_setting_name4.setTypeface(face);

    }


    public void onStart(){
        super.onStart();
        btn_openD_color.setBackgroundColor(rColor);
        setReminder();
        //seekBar_font_zoom.setProgress(webView_teks_size-50);
        //String sbVal=Integer.toString(webView_teks_size);
        //tv_sb_val.setText(sbVal+"%");
//        if (MainActivity.remind_at_time == true) {
//
//            setReminder();
//        }
//        else {MainActivity.remind_at_time=false;
//
//            setReminder();
//            am1.cancel(pendingIntent);
//            am2.cancel(pendingIntent2);
//        }
    }

    public void initializingComponent(){
//
//        View v=(View)findViewById(R.id.ll_alarm);
//        v.setLayoutParams(ViewGroup.LayoutParams(0,0));
        btn_openD_color=(Button)findViewById(R.id.btn_open_colorD);
        btn_openD_color.setOnClickListener(this);
        btn_rnd_color=(Button)findViewById(R.id.btn_rnd_col_bool);
        btn_rnd_color.setOnClickListener(this);
        btn_rnd_color2=(Button)findViewById(R.id.btn_rnd_col_bool2);
        btn_rnd_color2.setOnClickListener(this);
        btn_adz_rmd=(Button)findViewById(R.id.btn_time_remind);
        btn_adz_rmd.setOnClickListener(this);
       // btn_adz_rmd.setVisibility(View.INVISIBLE);
        btn_adz_rmd2=(Button)findViewById(R.id.btn_time_remind2);
        btn_adz_rmd2.setOnClickListener(this);
        //btn_adz_rmd2.setVisibility(View.INVISIBLE);
        btn_unf_adz=(Button)findViewById(R.id.btn_un_dz);
        btn_unf_adz.setOnClickListener(this);
        btn_unf_adz2=(Button)findViewById(R.id.btn_un_dz2);
        btn_unf_adz2.setOnClickListener(this);
        btn_auto_adz=(Button)findViewById(R.id.btn_auto_dz);
        btn_auto_adz.setVisibility(View.INVISIBLE);
        btn_auto_adz.setOnClickListener(this);
        btn_auto_adz2=(Button)findViewById(R.id.btn_auto_dz2);
        btn_auto_adz2.setVisibility(View.INVISIBLE);
        btn_auto_adz2.setOnClickListener(this);


//        tv_sb_val=(TextView)findViewById(R.id.tv_set_font_zoom_val);
//        tv_sb_val.setTextColor(Color.LTGRAY);
//        seekBar_font_zoom=(SeekBar)findViewById(R.id.seekBar_Font_zoom);
//        seekBar_font_zoom.setMax(100);
//        seekBar_font_zoom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                handlingSeekbarchangevalue();
//                  //  String sbVal=Integer.toString(seekBar_font_zoom.getProgress()+50);
//
//                String sbVal=Integer.toString(sb_tmp_value);
//                tv_sb_val.setText(sbVal+"%");
//              //  MainActivity.webView_teks_size=seekBar_font_zoom.getProgress()+50;
//                webView_teks_size=sb_tmp_value;
//                seekBar_font_zoom.setProgress(sb_tmp_value-50);
//                editor.putInt(MainActivity.font_zoom_dz,webView_teks_size)
//                        .commit();
//            }
//        });

        RelativeLayout llPickColor=(RelativeLayout)findViewById(R.id.ll_PC);

        if (random_color_state==false){
            llPickColor.setVisibility(View.VISIBLE);
            btn_rnd_color.setBackgroundColor(getResources().getColor(R.color.button_switch_pasive));
            btn_rnd_color2.setBackgroundColor(getResources().getColor(R.color.button_switch_active));
        }else{
            llPickColor.setVisibility(View.INVISIBLE);
            btn_rnd_color.setBackgroundColor(getResources().getColor(R.color.button_switch_active));
            btn_rnd_color2.setBackgroundColor(getResources().getColor(R.color.button_switch_pasive));

        }

//        if (auto_espodeng==false){
//            btn_auto_adz.setBackgroundColor(getResources().getColor(R.color.button_switch_pasive));
//            btn_auto_adz2.setBackgroundColor(getResources().getColor(R.color.button_switch_active));
//        }else {
//            btn_auto_adz.setBackgroundColor(getResources().getColor(R.color.button_switch_active));
//            btn_auto_adz2.setBackgroundColor(getResources().getColor(R.color.button_switch_pasive));
//        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                //MainActivity.setting=false;
                  AturActivity.this.finish();
//                moveTaskToBack(true);
                return true;
            case KeyEvent.KEYCODE_VOLUME_UP:

                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:

                // custom.toast(context, "volume down", Toast.LENGTH_LONG);
                return true;
            case KeyEvent.KEYCODE_MENU:

                return true;
            default:
                return false;

        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_open_colorD:
                openDialog(false);
                //Toast.makeText(getApplicationContext(), "open dialog called", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_rnd_col_bool:

                if(random_color_state==false){random_color_state=true;}else {random_color_state=false;}
                initializingComponent();
                //SharedPreferences.Editor editor=MainActivity.sharedpreferences.edit();
                editor.putBoolean(RNDCOLSTATE, random_color_state);
                editor.commit();
                break;
            case R.id.btn_rnd_col_bool2:
                if(random_color_state==false){random_color_state=true;} else {random_color_state=false;}
                initializingComponent();
                //SharedPreferences.Editor editor=MainActivity.sharedpreferences.edit();

                editor.putBoolean(RNDCOLSTATE, random_color_state);
                editor.commit();
                break;
//
//            case R.id.btn_time_remind:
//                if (pauseonunplug == false) { pauseonunplug=true;}else {pauseonunplug=false; }
//                initializingComponent();
//                editor.putBoolean(pause_on_unplug,pauseonunplug);
//                editor.commit();
//
//                break;
//            case R.id.btn_time_remind2:
//                if (pauseonunplug == false) {pauseonunplug=true;}else {pauseonunplug=false; }
//                initializingComponent();
//                editor.putBoolean(pause_on_unplug,pauseonunplug);
//                editor.commit();
//
//                break;
//
//            case R.id.btn_un_dz:
//                if (resumeaftercall == false) {resumeaftercall=true;}
//                else {resumeaftercall=false;
//                }
//                initializingComponent();
//                editor.putBoolean(resume_after_call,resumeaftercall);
//                editor.commit();
//
//                break;
//            case R.id.btn_un_dz2:
//                if (resumeaftercall == false) {resumeaftercall=true;}
//                else {resumeaftercall=false;
//                }
//                initializingComponent();
//                editor.putBoolean(resume_after_call,resumeaftercall);
//                editor.commit();
//
//                break;
            
//            case R.id.btn_auto_dz:
//                if (auto_espodeng == false) {
//                  auto_espodeng=true;
//                }
//                else {auto_espodeng=false;
//                }
//
//
//                initializingComponent();
//                editor.putBoolean(auto_dz,auto_espodeng);
//                editor.commit();
//
//                break;
//            case R.id.btn_auto_dz2:
//                if (auto_espodeng == false) {
//                    auto_espodeng=true;
//                    setReminder();
//
//                }
//                else {auto_espodeng=false;
//                }
//
//
//                initializingComponent();
//                editor.putBoolean(auto_dz,auto_espodeng);
//                editor.commit();
//
//                break;

            default:
                break;


        }

    }




    void openDialog(boolean supportsAlpha) {
        AmbilWarnaDialog dialog = new AmbilWarnaDialog(AturActivity.this, Scolor, supportsAlpha, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                //Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_SHORT).show();
                AturActivity.this.Scolor = color;
                btn_openD_color.setBackgroundColor(Scolor);
                rColor=Scolor;
                if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){

                    getWindow().setStatusBarColor(Color.BLACK);
                }

                editor.putInt(NUANSA, rColor);
                editor.commit();
                //displayColor();
            }

            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                //Toast.makeText(getApplicationContext(), "cancel", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }
  public void setReminder(){

//      am1=(AlarmManager)getSystemService(ALARM_SERVICE);
//      Intent alarmIntent=new Intent(this, ReminderReceiver.class);
//      pendingIntent=PendingIntent.getBroadcast(this,0,alarmIntent,0);
//
//      Intent alarmIntent2=new Intent(this, ReminderReceiver2.class);
//      pendingIntent2=PendingIntent.getBroadcast(this,0,alarmIntent2,0);
//
//      am2=(AlarmManager)getSystemService(ALARM_SERVICE);
//      cal=Calendar.getInstance();
//
//      cal2=Calendar.getInstance();
//      haripertaun=cal2.get(Calendar.DAY_OF_YEAR);
//      int jam =new Time(System.currentTimeMillis()).getHours();
//      menit= new Time(System.currentTimeMillis()).getMinutes();
//      //if(waktu==1){
//          waktu_ID=1;
////          cal.set(Calendar.HOUR_OF_DAY,jam);
////          cal.set(Calendar.MINUTE, menit+1);
////          cal.set(Calendar.SECOND,0);
////
////
//      cal.set(Calendar.DAY_OF_YEAR,(haripertaun+1));
//      cal.set(Calendar.HOUR_OF_DAY,4);
//      cal.set(Calendar.MINUTE, 55);
//      cal.set(Calendar.SECOND,0);
//
//      am1.setRepeating(AlarmManager.RTC, cal.getTimeInMillis(), am1.INTERVAL_DAY, pendingIntent);
//      //    am1.setExact(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(), pendingIntent);
//
//     // }
//     // else  if(waktu==2){
//
//          waktu_ID=2;
////          cal2.set(Calendar.HOUR_OF_DAY,jam);
////          cal2.set(Calendar.MINUTE, menit+2);
////          cal2.set(Calendar.SECOND,new Time(System.currentTimeMillis()).getSeconds()+5);
//
//      cal.set(Calendar.DAY_OF_YEAR,(haripertaun+1));
//      cal2.set(Calendar.HOUR_OF_DAY,16);
//      cal2.set(Calendar.MINUTE, 30);
//      cal2.set(Calendar.SECOND,new Time(System.currentTimeMillis()).getSeconds()+5);
//
//
//
//      am2.setRepeating(AlarmManager.RTC, cal.getTimeInMillis(), am2.INTERVAL_DAY, pendingIntent2);
//         // am2.setExact(AlarmManager.RTC_WAKEUP,cal2.getTimeInMillis(), pendingIntent2);
//
//      //}

  }


  public void handlingSeekbarchangevalue(){
     sb_tmp_value=seekBar_font_zoom.getProgress()+50;

      //TextView tv=(TextView)findViewById(R.id.tv_set_font_zoom_val);
      tv_sb_val.setTextColor(Color.WHITE);
      if (sb_tmp_value>50&&sb_tmp_value<56){sb_tmp_value=50;}
      else if(sb_tmp_value>55&&sb_tmp_value<66){sb_tmp_value=60;}
      else if(sb_tmp_value>65&&sb_tmp_value<76){sb_tmp_value=70;}
      else if(sb_tmp_value>75&&sb_tmp_value<86){sb_tmp_value=80;}
      else if(sb_tmp_value>85&&sb_tmp_value<96){sb_tmp_value=90;}
      else if(sb_tmp_value>95&&sb_tmp_value<106){sb_tmp_value=100;
          tv_sb_val.setTextColor(Color.LTGRAY);
      }
      else if(sb_tmp_value>105&&sb_tmp_value<116){sb_tmp_value=110;}
      else if(sb_tmp_value>115&&sb_tmp_value<126){sb_tmp_value=120;}
      else if(sb_tmp_value>125&&sb_tmp_value<136){sb_tmp_value=130;}
      else if(sb_tmp_value>135&&sb_tmp_value<146){sb_tmp_value=140;}
      else if(sb_tmp_value>145){sb_tmp_value=150;}
  }
}
