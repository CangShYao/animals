import java.sql.*;

public class DBconnect {
    private static String USERNAME = "CJD";
    private static String PASSWORD = "123";
    private static String DRIVER = "oracle.jdbc.OracleDriver";
    private static String URL = "jdbc:oracle:thin:@localhost:1521:orcl";

    // 创建一个数据库连接
    private Connection connection = null;
    // 创建预编译语句对象，一般都是用这个而不用Statement
    private PreparedStatement pstm = null;
    // 创建一个结果集对象
    private ResultSet rs = null;


    public Connection getConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            // System.out.println("成功连接数据库");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("class not find !", e);
        } catch (SQLException e) {
            throw new RuntimeException("get connection error!", e);
        }

        return connection;
    }

    private void ReleaseResource() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstm != null) {
            try {
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getPassword(String id) {
        connection = getConnection();
        String sql = "call GETADMINPASSWORD(?)";
        try {
            CallableStatement cstmt = null;
            cstmt = connection.prepareCall("{?=call GETADMINPASSWORD(?)}");
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.setString(2, id.trim());
            cstmt.execute();
            return cstmt.getString(1);
        } catch (SQLException e) {
            // e.printStackTrace();
            try {
                CallableStatement cstmt = null;
                cstmt = connection.prepareCall("{?=call GETPASSWORD(?)}");
                cstmt.registerOutParameter(1, Types.VARCHAR);
                cstmt.setString(2, id.trim());
                cstmt.execute();
                return cstmt.getString(1);
            } catch (SQLException e1) {
                ErrorMessage eM = new ErrorMessage();
                eM.show_error("无用户名");
                e1.printStackTrace();
            } finally {
                ReleaseResource();
            }
        } finally {
            ReleaseResource();
        }
        return "";
    }

    public void DeleteData(String id, String table) {
        connection = getConnection();
        String sqlStr = null;
        switch (table){
            case "ANIMAL":{
                sqlStr = "DELETE FROM CJD.ANIMAL WHERE ANIMALID = ?";
                break;
            }
            case "CENTER":{
                sqlStr = "DELETE FROM CJD.CENTER WHERE CENTERID = ?";
                break;
            }
            case "VACCINE":{
                sqlStr = "DELETE FROM CJD.VACCINE WHERE VACCINEID = ?";
                break;
            }
        }
        try {
            pstm = connection.prepareStatement(sqlStr);
            pstm.setString(1, id);
            int xx = pstm.executeUpdate();
            if (xx == 1){
                ErrorMessage em = new ErrorMessage();
                em.show_error("删除成功");
            }
            else {
                ErrorMessage em = new ErrorMessage();
                em.show_error("删除失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
    }

    public void SelectData(String id, String table) {
        connection = getConnection();
        String sqlStr = "SELECT * FROM CJD.? WHERE ?ID = ?";
        try {
            pstm = connection.prepareStatement(sqlStr);
            rs = pstm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
    }
}
