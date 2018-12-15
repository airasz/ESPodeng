package chicken.head.espodeng;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.net.DhcpInfo;
import android.net.http.SslError;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {

    static String localip;

    public String myips;
    String tempip;

    //====preference
    public static final String MYPREF="mypref";
    public static final String MYIP="myip";
    public static final String NUANSA= "nuansa";
    public static final String RNDCOLSTATE= "rndcolstate";

    public static SharedPreferences sharedPreferences=null;

    public static Typeface typewakanda;

    public static Boolean random_color_state=false;
    Integer volnumb;


    int itempos=0;
    final String mid=new String("/?");
    final String start=new String("http:/");
    String url;


    public static  int rColor;
//    public static Typeface typeface;
    public static int headColor;


    Context context =this ;


    public static String   s_gateway;
    DhcpInfo d;
    WifiManager wifii;


//    public static SharedPreferences sharedpreferences=null;

    int packetnum=0;

    int indexipspinner;
    Spinner spinner;
    Spinner spinner2;
    Spinner spinnerhistory;
    ArrayAdapter<String> adapter;
    ArrayList<String> list;
    ArrayList<String>list2;
    ArrayList<String>listh;
    public TextView tvlog;
    public TextView mltext;
    TextView tvVol;
    public TextView tvVoll;
    SeekBar seekBarvol;
    Button btnstation;
    Boolean listingpreset=false;
    String  numbpreset;
    Toolbar toolbar;
    Boolean firstping=false;
    Boolean b_scannetwork=false;
    Boolean b_getsync=false;
    Boolean dontselectspinner=false;
// ip scanner
private Button btnScan;
    private ListView listViewIp;

    ArrayList<String> ipList;
//    ArrayAdapter<String> adapter;

    ClipboardManager clipboardManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
//        toolbar.setLogo(R.drawable.espradio);
        toolbar.setSubtitle("ESP radio controller");

        loadPreference();
        clipboardManager=(ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        wifii= (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        d=wifii.getDhcpInfo();
        s_gateway=String.valueOf(Formatter.formatIpAddress(d.gateway));
        seekBarvol=findViewById(R.id.seekBarvol);
//        tvVol=findViewById(R.id.tvVol);
//        Toast.makeText(MainActivity.this, s_gateway , Toast.LENGTH_LONG).show();


        if (random_color_state==true){
            rColor= custom.getRandomColore();
        }else{
            rColor=sharedPreferences.getInt(NUANSA,rColor);
        }
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
//        LayoutInflater inflater1=(Activity)
//        View layout = inflater.inflate(R.layout.spinner_row,ViewGroup,false);
//        LinearLayout ll=(LinearLayout)layout.findViewById(R.id.toast_view);
//        RelativeLayout rlspinner=layout.findViewById(R.id.csspinner_bg);
//        TextView textView = layout.findViewById(R.id.tvCategory);
//        textView.setBackgroundColor(Color.GREEN);
//
//        rlspinner.setBackgroundColor(Color.GREEN);
//
//        AppBarLayout appBarLayout=findViewById(R.id.appbar);
//        appBarLayout.setBackgroundColor(rColor);

        typewakanda=Typeface.createFromAsset(getAssets(),"wakanda.ttf");
        setSupportActionBar(toolbar);
        TextView tvvol=findViewById(R.id.textView3);
        tvVoll=findViewById(R.id.tvolume);
        TextView tvips=findViewById(R.id.textView);
        tvvol.setTypeface(typewakanda);
        tvvol.setText("quick volume");

        tvlog=findViewById(R.id.tvlog);
        mltext=findViewById(R.id.mltext);
        mltext.setVisibility(View.INVISIBLE);


        if(myips!=null){
//            tvip.setText(myips.substring(1));
//            tvip.setText(myips);
            toolbar.setSubtitle(myips.substring(1));
            localip=myips;
            Log.d("error", "myips = "+myips);
//            toolbar.setTitle("ESPradio@"+myips.substring(1));
            String ping =start+localip.toString()+mid+"ping";                        // fist request is send ping
            packetnum=0;
            reqqueue(ping);
            Log.d("send", "send: ping");
        }else {localip="0.0.0.0";}


        list = new ArrayList<String>();

//        spinnerhistory=findViewById(R.id.spinnerhistory);
//        spinnerhistory.setAdapter();
        spinner = findViewById(R.id.spinner);

        list.add("select here");
        customSpinnerAdapter csadapter=new customSpinnerAdapter(getApplicationContext(), list);
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setBackgroundColor(rColor);
        spinner.setAdapter(csadapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinner.getSelectedItem().toString() != "select here") {
                    //tvip.setText(spinner.getSelectedItem().toString());
                    localip=spinner.getSelectedItem().toString();
                    getstatus(10000);//

                    toolbar.setSubtitle(spinner.getSelectedItem().toString().substring(1));
                    save_pref();
                    spinner.setSelection(0);
                }
            }


        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            spinner.setVisibility(View.INVISIBLE);
        }
    });


        spinnerhistory=findViewById(R.id.spinnerhistory);
        ArrayAdapter<String>adapterh;

        listh= new ArrayList<String>();
        listh.add("command history");
        final customSpinnerAdapter csadapter_hystori=new customSpinnerAdapter(getApplicationContext(),listh);
        spinnerhistory.setAdapter(csadapter_hystori);
        spinnerhistory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinnerhistory.getSelectedItemPosition()!=0){
                    reqqueue(url);
                }
                spinnerhistory.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        }) ;

        spinner2 = findViewById(R.id.spinner2);
//        spinner2.setVisibility(View.INVISIBLE);
        ArrayAdapter<String> adapter2;
//        List<String> list2;
        list2 = new ArrayList<String>();
        list2.add("select preset");

        customSpinnerAdapter csadapter_station=new customSpinnerAdapter(getApplicationContext(), list2);
//        csadapter_station.setDropDownViewTheme(R)
        adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(csadapter_station);
        spinner2.setBackgroundColor(rColor);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!dontselectspinner){

                    numbpreset=spinner2.getSelectedItem().toString().substring(0,2);
//                int nump=Integer.parseInt(numbpreset);
//                String url =start+localip.toString()+mid+spinner2.getSelectedItem().toString();
//                String url =start+localip.toString()+mid+"preset="+nump;
                    url =start+localip.toString()+mid+"preset="+numbpreset;

//                TextView tvlog=findViewById(R.id.tvlog);
                    if (spinner2.getSelectedItem().toString()!="select preset"){

//                    tvlog.setText(url);

//                    //webView.loadUrl(url);
                        //localip=spinner.getSelectedItem().toString();
                        localip=localip.toString();
                        getstatus(8000);
                        reqqueue(url);

//                        dontselectspinner=true;
                    }
                }else {

                    dontselectspinner=false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final AutoCompleteTextView autoCompleteTextView =findViewById(R.id.autoCompleteTextView);
        ArrayAdapter<String> actv_adapter;
        List<String> list3;
        list3 = new ArrayList<String>();
//        list3.add("volume=");
//        list3.add("preset=");
        list3.add("stop");
        list3.add("station=");
        list3.add("ip=");
        list3.add("savepref");
        list3.add("getnetworks");
        list3.add("");
        list3.add("");
        list3.add("");
        list3.add("");
        list3.add("");
        actv_adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list3);
        autoCompleteTextView.setAdapter(actv_adapter);


        clipboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {

                ClipData clipData=clipboardManager.getPrimaryClip();
                ClipData.Item item= clipData.getItemAt(0);
                String cb=item.getText().toString();
                try{
                    if(cb.length()>8){
                        if (cb.substring(0,8).equals("station=")){
//                            cb=cb.substring(8);
                            autoCompleteTextView.setText(cb);
                            autoCompleteTextView.selectAll();
                        }else if(cb.substring(0,4).equals("http")){
                            autoCompleteTextView.setText("station="+cb);
                            autoCompleteTextView.selectAll();
                        }
                    }
                }catch (UnknownError e){
                    e.printStackTrace();
                }
            }
        });

        seekBarvol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                tvVoll.setText(String.valueOf(seekBarvol.getProgress()*2));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                url =start+localip.toString()+mid+"volume="+seekBarvol.getProgress()*2;
                reqqueue(url);
                int vo =seekBarvol.getProgress()*2;
                String f=String.valueOf(vo);
                    tvVoll.setText(String.valueOf(seekBarvol.getProgress()*2));
            }
        });

        btnstation=findViewById(R.id.button);
        btnstation.setVisibility(View.INVISIBLE);
        btnstation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(numbpreset!=""){
                    url =start+localip.toString()+mid+"";
                }
            }
        });
        ImageButton ibtn_send=findViewById(R.id.ibtn_send);
        ibtn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if((autoCompleteTextView.getText().toString()!=" ")&&((autoCompleteTextView.getText().toString()).substring(0,3) !="ip=")){

                    if((autoCompleteTextView.getText().toString()!=" ")){

                    url =start+localip.toString()+mid+autoCompleteTextView.getText().toString();
//                    TextView tvlog=(TextView)findViewById(R.id.tvlog);

//                        listh.add(autoCompleteTextView.getText().toString());
                        csadapter_hystori.insert(autoCompleteTextView.getText().toString(),1);
                        int insp=spinnerhistory.getAdapter().getCount();
                        if(insp>4){
//                            spinnerhistory.removeViewAt(4);
                              csadapter_hystori.remove((String)spinnerhistory.getAdapter().getItem(5));
                        }
//                        if(autoCompleteTextView.getText().toString().substring(0,7).equals("station")){
//
//                        }else {
//                            getstatus(10000);}
                    reqqueue(url);
                    localip=localip.toString();
                    ArrayAdapter<String> adapterh;

                    List<String> list2;
                    list2 = new ArrayList<String>();
                    list2.add(url);
//                    adapterh=new ArrayAdapter<>()
                    autoCompleteTextView.setText("");
                }
            }
        });
        final ImageButton ibtnprev=findViewById(R.id.imageButtonprev);
        final ImageButton ibtnplay=findViewById(R.id.imageButtonplay);
        final ImageButton ibtnnext=findViewById(R.id.imageButtonnext);
        final ImageButton ibtnvdown=findViewById(R.id.imageButtonvoldown);
        final ImageButton ibtnvup=findViewById(R.id.imageButtonvolup);
        ibtnprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url =start+localip.toString()+mid+"downpreset=1";
                reqqueue(url);
                localip=localip.toString();
                getstatus(5000);
            }
        });
        ibtnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url =start+localip.toString()+mid+"stop";
                reqqueue(url);
                localip=localip.toString();
                getstatus(10000);

            }
        });
        ibtnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                url =start+localip.toString()+mid+"uppreset=1";
                tvlog.setText(url);
                reqqueue(url);
                localip=localip.toString();
                getstatus(10000);
            }
        });
        ibtnvdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                url =start+localip.toString()+mid+"downvolume=2";
                reqqueue(url);
                //webView.loadUrl(url);
                localip=localip.toString();
                getstatus(10000);

            }
        });
        ibtnvup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url =start+localip.toString()+mid+"upvolume=2";
                reqqueue(url);
                localip=localip.toString();
                getstatus(10000);
            }
        });
        autoCompleteTextView.setOnKeyListener(new EditText.OnKeyListener(){
            public boolean onKey (View view,  int keyCode, KeyEvent event){
                if (event.getAction()==KeyEvent.ACTION_DOWN &&keyCode== KeyEvent.KEYCODE_ENTER){
                    url =start+localip.toString()+mid+autoCompleteTextView.getText().toString().toLowerCase();
                    if(autoCompleteTextView.getText().toString().substring(0,4).equals("reqp")){
                        listingpreset=true;
                    }
                    listh.add(autoCompleteTextView.getText().toString().toLowerCase());
                    reqqueue(url);
//                    autoCompleteTextView.setText((autoCompleteTextView.getText().toString().substring(0,4)));
                    autoCompleteTextView.setText("");
                    return true;
                }
                if(event.getAction()==KeyEvent.ACTION_DOWN){
                    autoCompleteTextView.setText(autoCompleteTextView.getText().toString().toLowerCase());
                }
                return false;
            }

        });
//
//        final EditText netpreset=findViewById(R.id.numedittextpreset);
//        netpreset.setOnKeyListener(new EditText.OnKeyListener(){
//            public boolean onKey (View view,  int keyCode, KeyEvent event){
//                if (event.getAction()==KeyEvent.ACTION_DOWN &&keyCode== KeyEvent.KEYCODE_ENTER){
//                    url =start+localip.toString()+mid+"preset="+netpreset.getText().toString();
//                    reqqueue(url);
//                    netpreset.setText("");
//                    return true;
//                }
//                return false;
//            }
//
//        });

        final EditText netvol=findViewById(R.id.numedittextvol);
        netvol.setOnKeyListener(new EditText.OnKeyListener(){
            public boolean onKey (View view,  int keyCode, KeyEvent event){
                if (event.getAction()==KeyEvent.ACTION_DOWN &&keyCode== KeyEvent.KEYCODE_ENTER){

                    url =start+localip.toString()+mid+"volume="+netvol.getText().toString();
                    reqqueue(url);
                    netvol.setText("");
                    return true;
                }
                return false;
            }

        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                localip=localip.toString();
                getstatus(5000);
                Snackbar.make(view, "Request status information from ESP32", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }
//    public boolean onEditorAction(TextView netpreset, int action,KeyEvent event){
//
//    }

    private void ceksdcard() {
        String state = Environment.getExternalStorageState();

        if (!(state.equals(Environment.MEDIA_MOUNTED))) {
            Toast.makeText(this, "There is no any sd card", Toast.LENGTH_LONG).show();


        } else {
            BufferedReader reader = null;
            try {
                Toast.makeText(this, "Sd card available", Toast.LENGTH_LONG).show();
                File file = Environment.getExternalStorageDirectory();
                File textFile = new File(file.getAbsolutePath() + File.separator + "chapter.xml");
                reader = new BufferedReader(new FileReader(textFile));
                StringBuilder textBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    textBuilder.append(line);
                    textBuilder.append("\n");

                }
                tvlog.setText(textBuilder);

            } catch (FileNotFoundException e) {
                // TODO: handle exception
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void reqqueue(final String urld){

        final RequestQueue queue = Volley.newRequestQueue(this);
        // Request a string response from the provided URL.

//        mltext.setText(urld);
         StringRequest stringRequest = new StringRequest(Request.Method.GET, urld,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        response=response.replaceAll("\\s+$", "");
                        Log.d("received", "received: "+response);

//                        Toast.makeText(MainActivity.this, "packetnum = "+ packetnum, Toast.LENGTH_LONG).show();

                        if(response.toString()!=""){
                            if(response.toString().length()>4){
                                if(response.toString().substring(0,4).equals("sync")){

//                                Toast.makeText(MainActivity.this, "not null and got list ", Toast.LENGTH_LONG).show();
                                    String [] text=response.split("\\n");
//                            String l2=text[5];
                                    int linenumber=text.length;

//                                    Toast.makeText(MainActivity.this, "total line number after sync = "+ linenumber, Toast.LENGTH_LONG).show();
//                            String l2;
                                    if (linenumber>1){

                                        if (text[2].toString() != null) {
                                            int presetnumb=Integer.parseInt(text[2]);
                                            volnumb=Integer.parseInt(text[3]);
//                                            Toast.makeText(MainActivity.this, "sync line2 = "+ presetnumb+"\n sync line3 = "+ volnumb, Toast.LENGTH_LONG).show();
                                            if(spinner2.getAdapter().getCount()>1){
                                                if(spinner2.getSelectedItemPosition()!=(presetnumb+1)){

                                                    spinner2.setSelection(presetnumb+1);
                                                    dontselectspinner=true;
                                                }
                                            }
                                            seekBarvol.setProgress(volnumb/2);
                                            String v=volnumb.toString();
                                            tvVoll.setText(v);

                                        }
                                    }
                                    b_getsync=false;
                                }
                                else if(response.toString().substring(0,4).equals("list")){

//                                    Toast.makeText(MainActivity.this, "not null and got list ", Toast.LENGTH_LONG).show();

                                    String [] text=response.split("\\n");
//                            String l2=text[5];
                                    int linenumber=text.length;

//                            Toast.makeText(MainActivity.this, "total line number = "+ linenumber, Toast.LENGTH_LONG).show();
                                    for (int i=1;i<linenumber;i++){

                                        try{

//                                    list2.add(text[i]);
                                            if(text[i].length()>2){

                                                list2.add(text[i].substring(2));
                                            }
                                        }catch (UnknownError e){
                                            e.printStackTrace();
                                        }
                                    }
//                                listingpreset=false;
                                    tvlog.setText("ESP radio now ready received command");
                                    packetnum=9;
                                    getstatus(6000);
                                }else {

                                    tvlog.setText(response);
                                }
                            }

                        }


                        if (response.toString().equals("ESP")){
                            if(packetnum==0){                                                                                         //ip corectly set
                                tvlog.setText("ESPradio ip already correctly set ");
//                                getstatus(6000);
                                firstping=true;
                                listingpreset=true;                                                                             // through listing station mode
                                packetnum=1;
                                reqqueue(start+localip+mid+"populated");                   // filling spinner2 with all station
                                if(!b_scannetwork){

                                    new ScanIpTask().execute();                                                             //filling spinner 1 with all available ip
                                }
                            }else{

                            }
                        }
//                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(!firstping&&!b_getsync){
                    tvlog.setText("Scanning and list reachable network...");
//                    if(!b_scannetwork){
//
//                        new ScanIpTask().execute();                                                             //filling spinner 1 with all available ip
//                    }
//                   firstping=true;

                }else {

//                    tvip.setText("0.0.0.0");
//                    toolbar.setSubtitle("0.0.0.0");
                    tvlog.setText("Respon timeout.\nProbability wrong IP or Radio is offline");

                }


                if(packetnum==0){ //if no respon after send ping
                    tvlog.setText("Scanning and list reachable network...");

                        new ScanIpTask().execute();                                                             //filling spinner 1 with all available ip

                }
            }
        });
        int socketTimeOut=5000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
// Add the request to the RequestQueue.
        queue.add(stringRequest);
        queue.getCache().clear();
    }

    @Override
    public void onStart(){
super.onStart();
//        loadPreference();
//        makeText(this, "aaa", Toast.LENGTH_LONG);
//        TextView tvip=findViewById(R.id.tv_ip);

        if (random_color_state==true){
            rColor= custom.getRandomColore();
        }else{
//            rColor=sharedPreferences.getInt(NUANSA,rColor);
        }
        if(!firstping){
            getstatus(5000);

        }
    }


//    @Override
    public void onClick(View v){

        switch (v.getId()){

            case R.id.tv_ip:
                spinner.setVisibility(View.VISIBLE);
                break;
//            case R.id.imageButton_presset:
//                EditText editText=findViewById(R.id.numedittext);
//                if(editText.getText().toString()!=""){
//
//                    url =start+localip.toString()+mid+"presset="+editText.getText().toString();
//                    TextView tvlog=(TextView)findViewById(R.id.tvlog);
//                    tvlog.setText(url);
//                    reqqueue(url);
//                    //webView.loadUrl(url);
//                    localip=localip.toString();
//                    getstatus();
//                }
//                break;
            default:

                break;
        }

    }

    public void loadPreference(){
        makeText(this, "load preference called",Toast.LENGTH_SHORT);

        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        myips= sharedPreferences.getString(MYIP, myips);
        if(rColor!=0){
            rColor=sharedPreferences.getInt(NUANSA,rColor);
        }
        random_color_state=sharedPreferences.getBoolean(RNDCOLSTATE,random_color_state);
        makeText(this, myips,Toast.LENGTH_SHORT);


    }


    private void save_pref(){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        //TextView tvip=findViewById(R.id.tv_ip);
        editor.putString(MYIP, localip.toString());
        editor.commit();
    }


    public void disableallbutton(boolean disable){
        if(disable){

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                //System.exit(0);
                return true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                 //tvip=findViewById(R.id.tv_ip);
                url =start+localip.toString()+mid+"upvolume=2";
                reqqueue(url);
                localip=localip.toString();
                getstatus(5000);
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:

                 //tvip=findViewById(R.id.tv_ip);
                url =start+localip.toString()+mid+"downvolume=2";
                reqqueue(url);
                localip=localip.toString();
                getstatus(6000);
                return true;
            case KeyEvent.KEYCODE_MENU:
//              return true;
            case KeyEvent.KEYCODE_HEADSETHOOK:

                return true;
            case KeyEvent.KEYCODE_MEDIA_PLAY:

                return true;
            default:
                return false;

        }
    }

private void getsync(int delay){


    final Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
        @Override
        public void run() {
            if (localip.toString()!=null){
                url=(start+localip.toString()+mid+"getsync");
                reqqueue(url);
            }
        }
    }, delay);
}
    private void getstatus(int delay){


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (localip.toString()!=null){
                    url=(start+localip.toString()+mid+"status");
                    reqqueue(url);
                    listingpreset=false;
                }

                b_getsync=true;
                packetnum=10;
                getsync(4000);
            }
        }, delay);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            startActivity(new Intent(MainActivity.this, AturActivity.class));
            //return true ;
            return true;
        }
        if (id == R.id.action_rescan) {
//            spinner.setAdapter(null);
//            spinner.setAdapter(csAdapter);
            list.clear();
            list.add("select here");
            new ScanIpTask().execute();
            return true;
        }
        if (id == R.id.action_exit) {
            System.exit(0);
            return true;
        }
        if (id == R.id.action_restart) {
            Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            finish();
            startActivity(i);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("SocketException", ex.toString());
        }
        return null;
    }



    private class ScanIpTask extends AsyncTask<Void, String, Void> {

        static final String subnet = "192.168.0.";
        final String getway=s_gateway.substring(0,10);
        static final String subnet2 = "192.168.1.";
        Boolean route_type1=true;
        Boolean route_type2=true;
        static final int lower = 100;
        static final int upper = 110;
        static final int timeout = 500;
        String wfaddr;
        String hostname;
        @Override
        protected void onPreExecute() {
            DhcpInfo d;
            WifiManager wifii;
//            wifii= (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//            WifiInfo wifiInfo=wifii.getConnectionInfo();
//            String subnett=

//            wfaddr=Formatter.formatIpAddress(wifii.getConnectionInfo().getIpAddress());
//            d=wifii.getDhcpInfo();
//            int gatewayip = d.gateway;
//            tvGateway.setText(Formatter.formatIpAddress(dhcpInfo.gateway));
//            Toast.makeText(MainActivity.this, "Scan IP of "+gatewayip , Toast.LENGTH_LONG).show();
            if(!firstping){
                //tvlog.setText("scanning network...");

            }
//            Toast.makeText(MainActivity.this, wfaddr , Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i <= 15; i++) {
                String host = getway + i;

                try {
                    InetAddress inetAddress = InetAddress.getByName(host);
                    if (inetAddress.isReachable(timeout)){
                        if(i==1){
                            if(inetAddress.toString().equals("/192.168.0.1")){
//                                    route_type1=true;
//                                    route_type2=false;
                            }else{
//                                    route_type1=false;
//                                    route_type2=true;
                            }
                        }

                        publishProgress(inetAddress.toString());
                    }

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            // blacklist own IP
            String s=values[0];
            if (values[0].equals("/192.168.0.1")){return; }                 //blacklist router type 1(repeater) from list
            if (values[0].equals("/192.168.1.1")){return;}                  //blacklist router type main from list

            if (values[0].equals(wfaddr)){return;}                              //blacklist own IP from list
            list.add(values[0]);
            gethostname(wfaddr);
//            Toast.makeText(MainActivity.this, values[0]+" - "+wfaddr, Toast.LENGTH_SHORT).show();
        }
        public  void gethostname(String ip ) {

//            InetAddress inetAddress= InetAddress.getByName(ip);
//            hostname=inetAddress.getHostName();
//            try {
//                InetAddress inetAddress = InetAddress.getByName(ip);
//                hostname = inetAddress.getHostName();
//
//
//            } catch (UnknownHostException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            b_scannetwork=true;
            indexipspinner=spinner.getAdapter().getCount()-1;
            if(!firstping){

                tvlog.setText("Listing complete\nFiinding ESP radio ip.....");

                cekradio(true);
            }else{

                listingpreset=true;
//                addpreset(true);
            }

        }


    }//end of ipscan class
int ipasc;
public void detectradioip(int ipcount){
//    Toast.makeText(MainActivity.this, "detectradioip executed", Toast.LENGTH_SHORT).show();

//    Toast.makeText(MainActivity.this, "total index "+ ipcount, Toast.LENGTH_SHORT).show();
    for( ipasc = 1; ipasc < ipcount; ipasc++){
        try {

            tempip=(spinner.getItemAtPosition(ipasc).toString());
//            Toast.makeText(MainActivity.this, "index no " + ipasc+ "of "+ ipcount + " | ip :" + tempip, Toast.LENGTH_SHORT).show();
//                    reqqueue("http:/"+tempip+"/?ping");
            Thread.sleep(2000);
        }catch (Exception e){
            e.fillInStackTrace();
        }
//        Handler handler=new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//
////                    TextView tvip=findViewById(R.id.tv_ip);
////                    tvip.setText(sp
//// inner.getItemAtPosition(ipasc).toString());
////                    tempip=(spinner.getItemAtPosition(ipasc).toString());
////                    Toast.makeText(MainActivity.this, "pinging > "+tempip, Toast.LENGTH_SHORT).show();
////
////                    reqqueue("http:/"+tempip+"/?ping");
//
//                }
//            },6000);
//
//                    tempip=(spinner.getItemAtPosition(ipasc).toString());
//                    Toast.makeText(MainActivity.this, "pinging > "+tempip, Toast.LENGTH_SHORT).show();
//                        tvlog.setText("http:/"+tempip+"/?ping");
//                    reqqueue("http:/"+tempip+"/?ping");


    }
}

public void cekradio(boolean ceklagi){
    if (ceklagi){
        if(indexipspinner==0){
            return;
        }else {

//            Toast.makeText(MainActivity.this, "indexofspinner="+indexipspinner, Toast.LENGTH_LONG).show();
            Log.d("index","indexofspinner= "+indexipspinner);
            if(indexipspinner>2){
                itempos++;
                if(itempos<=indexipspinner) {
                    try{

                        Log.d("spinner","position spinner "+itempos+"  contain = "+(spinner.getItemAtPosition(itempos).toString()));
                        tempip = (spinner.getItemAtPosition(itempos).toString());


                    }catch (UnknownError e){
                        e.printStackTrace();
                    }
//            Toast.makeText(MainActivity.this, "tempip="+tempip, Toast.LENGTH_LONG).show();
                    reqradiorespon("http:/" + tempip + "/?ping");
                }
            }else {
//            return;
                ceklagi=false;
                tvlog.setText("");

            }

        }



        }


}

    public void reqradiorespon(String urld){

        final RequestQueue queue = Volley.newRequestQueue(this);
//        queue.stop();
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urld,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        response=response.replaceAll("\\s+$", "");
                        if (response.toString().equals("ESP")){
                            //tvip.setText(tempip);
//                            Toast.makeText(MainActivity.this, "ESP responded in", Toast.LENGTH_LONG).show();
                            tvlog.setText("ESPradio detected on address " +tempip.substring(1) + " and set as your ip target" );
                            toolbar.setSubtitle(tempip.substring(1));
                            localip=tempip;
//                            getstatus(6000);


                            listingpreset=true;
                            reqqueue(start+localip+mid+"populated");
//                            addpreset(true);
                            save_pref();
                        }
                        else{
                            cekradio(true);
                            tvlog.setText(response);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (itempos==indexipspinner){
                tvlog.setText("looks like no ESPradio detected.");
                toolbar.setSubtitle("0.0.0.0");
                }
                cekradio(true);
                firstping=true;
            }
        });
        int socketTimeOut=5000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
// Add the request to the RequestQueue.
        queue.add(stringRequest);
        queue.getCache().clear();
    }

}