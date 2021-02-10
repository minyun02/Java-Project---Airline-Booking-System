package dbAll;

import java.util.ArrayList;
import java.util.List;

public class EmpSalesDAO extends DBConn {

	public EmpSalesDAO() {
		
	}
	
	public List<EmpSalesVO>EmpSalesAllselect(){
		List<EmpSalesVO> lst = new ArrayList<EmpSalesVO>();
		try {
			getConn();
			sql = "select to_char(brdDate, 'YYYY/MM/DD'), f.flightno, dep, des , TO_CHAR(fare*0.4, 'L9,999,999'), TO_CHAR(fare, 'L9,999,999')  ,TO_CHAR(fare-(fare*0.4), 'L9,999,999') 영업이익 "
					+ "from ac_flight f join ac_reservation r on f.flightno=r.flightno";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmpSalesVO vo = new EmpSalesVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7));
				
				lst.add(vo);
			}
		}catch(Exception ae) {
			ae.printStackTrace();
		}finally {
			dbClose();
		}
		
		
		return lst;
	}

}
