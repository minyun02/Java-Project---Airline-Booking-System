package dbAll;

import java.util.ArrayList;
import java.util.List;

public class EmpReservationDAO extends DBConn{

	public EmpReservationDAO() {
		
	}
	
	public List<EmpReservationVO> EmpReservationAllselect(){
		List<EmpReservationVO> lst = new ArrayList<EmpReservationVO>();
		try {
			getConn();
			sql = "select resno, user_name,  to_char(user_birth, 'YYYY/MM/DD'), user_tel, dep, r.flightno "
					+ "from ac_reservation r join ac_user u on r.user_passNo = u.user_passNo "
					+ "join ac_flight f on r.flightNO= f.flightNO "
					+ "UNION all "
					+ "select r.resno, com_name, to_char(com_birth, 'YYYY/MM/DD'), com_tel,  dep, r.flightno "
					+ "from ac_reservation r join ac_company c on r.resNo = c.resno "
					+ "join ac_flight f on r.flightNO= f.flightNO order by resno" ; 
			pstmt = conn.prepareStatement(sql);
			
			rs =pstmt.executeQuery();
			
			while(rs.next()) {
				EmpReservationVO vo = new EmpReservationVO();
				vo.setResno(rs.getString(1));
				vo.setUser_name(rs.getString(2));
				vo.setUser_birth(rs.getString(3));
				vo.setUser_tel(rs.getString(4));
				vo.setDep(rs.getString(5));
				vo.setFlightno(rs.getString(6));
				lst.add(vo);
				
			}
			

		}catch(Exception ae) {
			ae.printStackTrace();
		}finally {
			
			dbClose();
		}
		return lst;
	}
	
	
	// 예약번호 삭제
	public int empReservationDelete(String resNo) {
		int result = 0 ;
		
		try {
			getConn();
			sql = "delete from ac_reservation where resNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, resNo);
			
			result = pstmt.executeUpdate();
		}catch(Exception ae) {
			ae.printStackTrace();
		}finally {
			dbClose();
		}
 		
		return result;
	}

}
