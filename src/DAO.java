import java.sql.*;

public class DAO {
    private static String server = "localhost:3306"; // 서버 주소
    private static String user_name = "root"; //  접속자 id
    private static String password = "1234"; // 접속자 pw
    private Connection con = null;
    Statement stmt;
    PreparedStatement pstmt = null;
    ResultSet rs;


    static{
        // JDBC 드라이버 로드
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("JDBC 드라이버 로드완료");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC 드라이버를 로드하는데에 문제 발생" + e.getMessage());
            e.printStackTrace();
        }
    }

    void get(){
        // db에 있는 데이터를 받아 dto에 저장하는 단계
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/note" + "?useSSL=false", user_name, password);
            //서버명 다음 db명
            System.out.println("연결 완료!");
        } catch(SQLException e) {
            System.err.println("연결 오류" + e.getMessage());
            e.printStackTrace();
        }

        DTO dto = DTO.getInstance();
        String query = "select * from notee";
    //    String query1 = "insert into notee(Sequence,record) values(2,'흐에에에에에!')";

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                System.out.println("카운터 :" + rs.getInt(1));
                dto.getSequence().add(rs.getInt(1));
                dto.getRecord().add(rs.getString(2));
            }
            //while(rs.next) 영역에서 값들이 대입되는 건 아닌거같다. i++도 안되고 아래 코드도 적용이 안되었다. 오류 표시가 없기에 더 문제다.
    //        dto.getSequence().add(rs.getInt("Sequence"));
    //        dto.getRecord().add(rs.getString("record"));
    //        stmt.executeUpdate(query1);

            if(con != null)
                rs.close();
                stmt.close();
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    //데이터 저장 insert
    public void save(String msg){
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/note" + "?useSSL=false", user_name, password);
            System.out.println("연결 완료!");
        } catch(SQLException e) {
            System.err.println("연결 오류" + e.getMessage());
            e.printStackTrace();
        }

        int n=0;
        DTO dto = DTO.getInstance();
    //    String query = "select * from notee where Sequence=2";
        String query = "select * from notee";
    //    String query1 = "insert into notee(Sequence,record) values(" + dto.getSequence() + "," + dto.getRecord() + ")";
    //    System.out.println("쿼리 : " + query1);

        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                dto.setCount(rs.getInt("Sequence"));
            }
            n = dto.getCount() + 1; //가장 마지막 시퀸스 값을 구한 이후 1을 더함.

            String query0 = "insert into notee(Sequence,record) values(" + n + "," + "\'"+msg+"\'" + ")";
// 문제점. msg에 코드를 넣으면 오류가 남

            stmt.executeUpdate(query0);
            System.out.println("입력된 값 : " + n + " " + msg);

            dto.getSequence().add(n);
            dto.getRecord().add(msg);

            if(con != null) {
                rs.close();
                stmt.close();
                con.close();
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    //데이터 삭제 delete
    public void delete(int x){
        try{
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/note" + "?useSSL=false", user_name, password);
        } catch(SQLException e){
            e.printStackTrace();
        }
        String query0 = "delete from notee where Sequence = ?";

        try{
            pstmt = con.prepareStatement(query0);
            pstmt.setInt(1,x);//x는 db의 인덱스 값 ( 2를 넣었는데 sequence=3인 값은 삭제함. 0부터 시작하는 db인덱스)
            pstmt.executeUpdate();
            System.out.println(x+"번쨰 값 삭제");

            if(con != null){
                pstmt.close();
                con.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
