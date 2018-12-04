//package chicken.head.espodeng;
//
//public class iphistory {
//    public static final String TABLE_NAME = "ipaddress";
//
//    public static final String COLUMN_ID = "id";
//    public static final String COLUMN_IP = "ip";
//    public static final String COLUMN_TIMESTAMP = "timestamp";
//
//    private int id;
//    private String ip;
//    private String timestamp;
//
//
//    // Create table SQL query
//    public static final String CREATE_TABLE =
//            "CREATE TABLE " + TABLE_NAME + "("
//                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                    + COLUMN_IP+ " TEXT,"
//                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
//                    + ")";
//
//    public Ip() {
//    }
//
//    public Ip(int id, String ip, String timestamp) {
//        this.id = id;
//        this.ip = ip;
//        this.timestamp = timestamp;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public String getIp() {
//        return ip;
//    }
//
//    public void setIp(String ip) {
//        this.ip = ip;
//    }
//
//    public String getTimestamp() {
//        return timestamp;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public void setTimestamp(String timestamp) {
//        this.timestamp = timestamp;
//
//
//    }
//}
