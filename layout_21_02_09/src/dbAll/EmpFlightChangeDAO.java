package dbAll;

import java.util.ArrayList;
import java.util.List;

public class EmpFlightChangeDAO extends DBConn{

	public EmpFlightChangeDAO() {
		
	}
	public int delayUpdate(EmpFlightChangeVO vo) {
		int result = 0;
		try {
			getConn();
			sql = "update ac_flight set depTime=?, desTime=?, flight_state='DELAY' where flightNo=? AND brdDate=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getDepTime());
			pstmt.setString(2, vo.getDesTime());
			pstmt.setString(3, vo.getFlightno_r());
			pstmt.setString(4, vo.getBrdDate_r());
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	public List<EmpFlightChangeVO> flightAllSelect(){
		List<EmpFlightChangeVO> lst = new ArrayList<EmpFlightChangeVO>();
		try {
			getConn();
			sql = "select distinct f.flightno, r.brdDate, dep, depTime, des, desTime, flight_state from ac_flight f join ac_reservation r "
			+ "on r.flightno=f.flightno where r.brdDate > to_char(sysdate,'YYYYMMDD') order by r.brddate";
			
			pstmt = conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				EmpFlightChangeVO vo = new EmpFlightChangeVO(rs.getString(1),
						rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	public List<EmpFlightChangeVO> getSearchRecord(String dateSearchWord, String acSearchWord, String depSearchWord, String desSearchWord){
		List<EmpFlightChangeVO> lst = new ArrayList<EmpFlightChangeVO> ();
		try {
			getConn();
			sql = "select f.flightno, r.brdDate, dep, depTime, des, desTime, flight_state from ac_flight f, ac_reservation r "
					+ "where f.flightno=? OR dep=? OR des=? OR r.brddate > to_date(?, 'YYYYMMDD') order by r.brddate";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "AC"+acSearchWord);
			pstmt.setString(2, depSearchWord);
			pstmt.setString(3, desSearchWord);
			pstmt.setString(4, dateSearchWord);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpFlightChangeVO vo = new EmpFlightChangeVO(rs.getString(1),
						rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7));
				
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
