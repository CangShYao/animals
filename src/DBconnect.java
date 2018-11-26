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

    /*
    public void AddData(String stuName, int gender, int age, String address) {
        connection = getConnection();
        // String sql =
        String sql = "select count(*) from student where 1 = 1";
        String sqlStr = "insert into student values(?,?,?,?,?)";
        int count = 0;

        try {
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1) + 1;
                System.out.println(rs.getInt(1));
            }
            // 执行插入数据操作
            pstm = connection.prepareStatement(sqlStr);
            pstm.setInt(1, count);
            pstm.setString(2, stuName);
            pstm.setInt(3, gender);
            pstm.setInt(4, age);
            pstm.setString(5, address);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
    }

    public void DeleteData(String stuName) {
        connection = getConnection();
        String sqlStr = "delete from student where stu_name=?";
        System.out.println(stuName);
        try {
            // 执行删除数据操作
            pstm = connection.prepareStatement(sqlStr);
            pstm.setString(1, stuName);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
    }

    public void SelectData() {
        connection = getConnection();
        String sql = "select * from student where 1 = 1";
        try {
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("stu_name");
                String gender = rs.getString("gender");
                String age = rs.getString("age");
                String address = rs.getString("address");
                System.out.println(id + "\t" + name + "\t" + gender + "\t"
                        + age + "\t" + address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
    } */

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

    public String getPassword(String id){
        connection = getConnection();
        String sql = "call GETADMINPASSWORD(?)";
        try {
            CallableStatement cstmt = null;
            cstmt = connection.prepareCall("{?=call GETADMINPASSWORD(?)}");
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.setString(2, id.trim());
            cstmt.execute();
            return cstmt.getString(1);
        } catch (SQLException e){
            // e.printStackTrace();
            try {
                CallableStatement cstmt = null;
                cstmt = connection.prepareCall("{?=call GETPASSWORD(?)}");
                cstmt.registerOutParameter(1, Types.VARCHAR);
                cstmt.setString(2, id.trim());
                cstmt.execute();
                return cstmt.getString(1);
            } catch (SQLException e1){
                ErrorMessage eM = new ErrorMessage();
                eM.show_error("无用户名");
                e1.printStackTrace();
            } finally {
                ReleaseResource();
            }
        }
        finally {
            ReleaseResource();
        }
        return "";
    }
}
