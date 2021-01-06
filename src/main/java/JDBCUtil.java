import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {

    private static String driver;   //JDBC Driver类的全类名。jdbc 5.1和jdbc 8.0不一样
    private static String url;      //获取连接需要的url。通常为 "jdbc:mysql://localhost:3306/数据库名"
    private static String username; //连接数据库的用户名
    private static String password; //连接数据库的密码

    /**
     * 从<tt>jdbc.properties</tt>配置文件中获取连接用到的参数 。
     * 文件存放在和<tt>JDBCUtil</tt>类同级的目录下。
     */
    static {
        InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pro = new Properties();
        try {
            pro.load(is);
            driver = pro.getProperty("driver");
            url = pro.getProperty("url");
            username = pro.getProperty("username");
            password = pro.getProperty("password");

            //连接数据库第一步：加载驱动Drive
            //只执行一步，所以放在静态块，之后再次获取Connection时就不再需要加载驱动了
            Class.forName(driver); //通过反射创建Driver对象，并加载驱动。
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 每次进行数据库的操作时，需要获取和数据库的一个连接。当操作完成时，需要关闭数据库连接。
     * 获取连接用到的参数<tt>(url, username, password)</tt>由<tt>jdbc.properties</tt>指定
     * @return  和数据库的连接<tt>Connection</tt>
     * @see     Connection
     */
    public static Connection getConn(){
        //连接数据库第二步：通过驱动管理器(DriverManager)获取数据库连接Connection
        Connection conn = null;
        try {
            conn =  DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 依次关闭ResultSet，Statement和Connection。如果没有结果集，传入<tt>null</tt>
     * @param resultSet 连接的结果集
     * @param statement 连接的Statement
     * @param conn      数据库连接
     */
    public static void close(ResultSet resultSet, Statement statement, Connection conn){
        try {
            if(resultSet != null)
                resultSet.close();
            if(statement != null)
                statement.close();
            if(conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
