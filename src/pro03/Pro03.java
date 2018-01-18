package pro03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pro03 {

	public static void main(String[] args) {

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				conn = DriverManager.getConnection(url, "hr", "hr"); 

				String query = "select employee_id, last_name, email, job_title, department_name, city" + 
							  " from employees e, departments d, locations l, jobs j" +
						      " where e.department_id=d.department_id and d.location_id=l.location_id and e.job_id=j.job_id" +
							  "       and l.city='Seattle' and e.job_id='PU_CLERK'" + 
							  " order by employee_id desc";
				
				pstmt=conn.prepareStatement(query);
				rs = pstmt.executeQuery(); 
			
				while(rs.next()) {
					int employeeId = rs.getInt("employee_id");
					String lastName = rs.getString("last_name");
					String email = rs.getString("email");
					String jobTitle = rs.getString("job_title");
					String departmentName = rs.getString("department_name");
					String city = rs.getString("city");
					System.out.println(employeeId +"\t"+ lastName +"\t"+ email+"\t"+ jobTitle +"\t"+ departmentName + "\t" + city);
				}
				
				
			} catch (ClassNotFoundException e) {
				System.out.println("error: 드라이버 로딩 실패 - " + e);
			} catch (SQLException e) {
				System.out.println("error:" + e);
			} finally {
				// 5. 자원정리
				try {
					if(rs != null) {
						rs.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					System.out.println("error:" + e);
				}

			}
		}

    }
