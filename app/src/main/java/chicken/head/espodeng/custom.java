package chicken.head.espodeng;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

//import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.Random;

import static chicken.head.espodeng.MainActivity.rColor;
import static chicken.head.espodeng.MainActivity.random_color_state;
/**
 * Created by airasz on 13/03/2017.
 */

public class custom {


    public static int rgbne ;
    public static int r,g,b;
    public static int colorMax=500;
    public static int getRandomColore(){

        Random rnd = new Random();
        int m=rnd.nextInt(3);
        if (m==0){
             r= rnd.nextInt(256);
             if(r>180){

                 g= rnd.nextInt(160);
             }
            if ((r+g)>colorMax){b= rnd.nextInt(80);}
        } else if (m==1){
            r= rnd.nextInt(256);

            b= rnd.nextInt(256);
            if ((r+b)>colorMax
                    ){g= rnd.nextInt(40);}
        } else if (m==2){
            g= rnd.nextInt(256);
            b= rnd.nextInt(256);
            if ((g+b)>colorMax){r= rnd.nextInt(40);}
        }



        rgbne=r+g+b;
        if (random_color_state){return Color.argb(255, r,g,b);}else{return rColor;}


        //return Color.argb(255, rnd.nextInt(256),rnd.nextInt(256),rnd.nextInt(256));

    }
    public static int getColore(int m){

        Random rnd = new Random();
//        int m=rnd.nextInt(3);

        if (m==0){          //red
            r= rnd.nextInt(120)+rnd.nextInt(120);
            g= rnd.nextInt(10);b= rnd.nextInt(30);
        } else if (m==1){           //green
            r= rnd.nextInt(20);
            b= rnd.nextInt(20);
            g= rnd.nextInt(120)+rnd.nextInt(120);
        } else if (m==2){           //blue
            g= rnd.nextInt(10);
            b= rnd.nextInt(120)+rnd.nextInt(120);
            r= rnd.nextInt(20);
        }



        rgbne=r+g+b;
        if (random_color_state){return Color.argb(255, r,g,b);}else{return rColor;}

        //return Color.argb(255, rnd.nextInt(256),rnd.nextInt(256),rnd.nextInt(256));

    }

    public static void toast(Context context, String massage, int dur, int bg, int cl){
        Animation toastenteranim;
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View layout = inflater.inflate(R.layout.ctoast,(ViewGroup)((Activity)context).findViewById(R.id.toast_view));
        LinearLayout ll=(LinearLayout)layout.findViewById(R.id.toast_view);
        ll.setBackgroundColor(bg);
        TextView thead=(TextView)layout.findViewById(R.id.toast_head_text);
        thead.setTextColor(cl);
        TextView textView=(TextView)layout.findViewById(R.id.tvtoast);
        textView.setTextColor(cl);
        textView.setText(massage);



        //toastenteranim=AnimationUtils.loadAnimation(context,R.anim.toast_enter);
        //toastenteranim.setAnimationListener();
        Toast toast=new Toast(context);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(dur);
        toast.setView(layout);

        //layout.startAnimation(toastenteranim);
        toast.show();
    }
    public static Bitmap t2b(String text, float textSize, int textColor){
        Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline=-paint.ascent();
        int width=(int)paint.measureText(text+0.0f);
        int height=(int)(baseline+paint.descent()+0.0f);
        Bitmap image=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(image);
        canvas.drawText(text,0,baseline,paint);
        return image;
    }

}
