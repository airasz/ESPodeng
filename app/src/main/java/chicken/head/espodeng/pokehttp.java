//package chicken.head.espodeng;
//
//import android.os.AsyncTask;
//import android.util.Log;
//
//import org.apache.http.params.HttpConnectionParams;
//import org.apache.http.params.HttpParams;
//import org.apache.http.client.HttpClient;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//
//
//class pokehttp extends AsyncTask<String, String, String> {
//    @Override
//// username, password, message, mobile
//    protected String doInBackground(String... url) {
//        // constants
//        int timeoutSocket = 5000;
//        int timeoutConnection = 5000;
//
//        HttpParams httpParameters = new BasicHttpParams();
//        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
//        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
//        HttpClient client = new DefaultHttpClient(httpParameters);
//
//        HttpGet httpget = new HttpGet(url[0]);
//
//        try {
//
//
//            HttpResponse getResponse = client.execute(httpget);
//            final int statusCode = getResponse.getStatusLine().getStatusCode();
//
//            if(statusCode != HttpStatus.SC_OK) {
//                Log.w("MyApp", "Download Error: " + statusCode + "| for URL: " + url);
//                return null;
//            }
//
//            String line = "";
//            StringBuilder total = new StringBuilder();
//
//            HttpEntity getResponseEntity = getResponse.getEntity();
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(getResponseEntity.getContent()));
//
//            while((line = reader.readLine()) != null) {
//                total.append(line);
//            }
//
//            line = total.toString();
//            return line;
//        } catch (Exception e) {
//            Log.w("MyApp", "Download Exception : " + e.toString());
//        }
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(String result) {
//        // do something with result
//    }
//}