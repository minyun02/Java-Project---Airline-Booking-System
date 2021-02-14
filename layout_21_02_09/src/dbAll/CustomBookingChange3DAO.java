package dbAll;

import java.util.ArrayList;
import java.util.List;

public class CustomBookingChange3DAO extends DBConn {

	public CustomBookingChange3DAO() {
		
	}
	public List<CustomBookingChange3VO> getTable1(String newFlight, String resNo){
		List<CustomBookingChange3VO> lst = new ArrayList<CustomBookingChange3VO>();
		try {
			getConn();
			sql = "select dep, des, brddate, deptime, destime, f.flightno, seatno, fare from ac_flight f, ac_reservation r, ac_seat s "
					+ "where f.flightno=? AND r.resno=? AND s.resno=? order by seatno";
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setString(1, newFlight);
			pstmt.setString(2, resNo);
			pstmt.setString(3, resNo);
		
			rs = pstmt.executeQuery();
			System.out.println("쿼리문"+newFlight);
			
			while(rs.next()) {
				//레코드를 VO에 담고 VO를 List에 담는 작업
				CustomBookingChange3VO vo = new CustomBookingChange3VO();
				vo.setDep(rs.getString(1));
				vo.setDes(rs.getString(2));
				vo.setBrdDate(rs.getString(3));
				vo.setDepTime(rs.getString(4));
				vo.setDesTime(rs.getString(5));
				vo.setFlightNo(rs.getString(6));
				vo.setSeatNo(rs.getString(7));
				vo.setFare(rs.getString(8));
				
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	
	public List<CustomBookingChange3VO> getTable2(String resNo){
		List<CustomBookingChange3VO> lst = new ArrayList<CustomBookingChange3VO>();
		try {
			getConn();
			sql = "select user_gender, user_name, user_ename, r.user_passno, r.user_exdate, user_nation, user_birth, user_tel, user_email "
					+ "from ac_user u join ac_reservation r on u.user_passno=r.user_passno where r.resno=? "
					+ "union all "
					+ "select com_gender, com_name, com_ename, com_passno, com_exdate, com_nation, com_birth, com_tel, com_email "
					+ "from ac_company where resno=?"; 
					
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setString(1, resNo);
			pstmt.setString(2, resNo);
		
			rs = pstmt.executeQuery();
			System.out.println("쿼리문2"+resNo);
			
			while(rs.next()) {
				//레코드를 VO에 담고 VO를 List에 담는 작업
				CustomBookingChange3VO vo = new CustomBookingChange3VO();
				vo.setGender(rs.getString(1));
				vo.setUserName(rs.getString(2));
				vo.setUserEname(rs.getString(3));
				vo.setUserPassNo(rs.getString(4));
				vo.setUserExdate(rs.getString(5));
				vo.setUserNation(rs.getString(6));
				vo.setUserBirth(rs.getString(7));
				vo.setUserTel(rs.getString(8));
				vo.setUserEmail(rs.getString(9));
				
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
}
