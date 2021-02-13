import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dbAll.CustomBookingChange2DAO;
import dbAll.CustomBookingChange2VO;

public class CustomBookingChange2 extends JPanel implements ActionListener,MouseListener{
	Font fnt = new Font("굴림체",Font.BOLD,14);
	JPanel changePane = new JPanel();
		JPanel centerPane = new JPanel();
			JPanel northPane = new JPanel();
				JPanel titlePane = new JPanel();
					JLabel titleLbl = new JLabel("항공편을 선택하세요");
				JPanel countryPane = new JPanel();
					JLabel startCountry = new JLabel("출발지");
					ImageIcon icon = new ImageIcon("img/arrow.png");
					Image im = icon.getImage();
					Image im2 = im.getScaledInstance(50, 30, Image.SCALE_DEFAULT);
					ImageIcon icon2 = new ImageIcon(im2);
					JLabel arriveCountry = new JLabel("도착지");
				JPanel datePane = new JPanel();
					JLabel startDate = new JLabel("2021/02/02");
					JLabel arriveDate = new JLabel("2021/02/08");
			JPanel tablePane = new JPanel();
				String modelTitle[] = {"출발시간","도착시간","총 비행시간","비행편명","예약 상태","운임"};
				DefaultTableModel model = new DefaultTableModel(modelTitle,0);
				JTable tbl = new JTable(model);
				JScrollPane sp = new JScrollPane(tbl);
				
		JPanel btnPane = new JPanel();
			JButton cancelBtn = new JButton("변경취소");
			JButton nextBtn = new JButton("다음단계");
		JPanel test = new JPanel();
	
	//이벤트용 변수
		int row = 0;
		
		String depFromCbc1 = "";//cbc1에서 출발지 받아와서 담아주기
		String desFromCbc1 = "";//cbc1에서 출발지 받아와서 담아주기
		
		List<CustomBookingChange2VO> lst = new ArrayList<CustomBookingChange2VO>();
		
	public CustomBookingChange2() {
		setLayout(new BorderLayout());
		
		// 전환되는 패널
		add("North",changePane);
		changePane.setLayout(new BorderLayout(200,30));
		changePane.setBackground(Color.white);
		changePane.add("North",new JLabel());
		changePane.add("East",new JLabel());
		changePane.add("West",new JLabel());
		changePane.add("South",new JLabel());
		changePane.add("Center",centerPane);
			centerPane.setLayout(new BorderLayout());
			centerPane.setBackground(Color.white);
			centerPane.add("North",northPane);
				northPane.setLayout(new GridLayout(3,1));
				northPane.setBackground(Color.white);
					// 타이틀 라벨
					northPane.add(titleLbl);
						titleLbl.setHorizontalAlignment(JLabel.CENTER);
						titleLbl.setFont(new Font("굴림체",Font.BOLD,24));
						titleLbl.setBackground(Color.white);
					// 도시표시 패널
					northPane.add(countryPane);
						countryPane.setLayout(new FlowLayout(FlowLayout.CENTER,30,10));
						//출발지
						countryPane.add(startCountry);
						countryPane.setBackground(Color.white);
							startCountry.setFont(fnt);
							startCountry.setBackground(Color.white);
						//화살표
						countryPane.add(new JLabel(icon2));
						//도착지
						countryPane.add(arriveCountry);
							arriveCountry.setFont(fnt);
							arriveCountry.setBackground(Color.white);
					//날짜패널
					northPane.add(datePane);
					datePane.setBackground(Color.white);
						datePane.setLayout(new FlowLayout(FlowLayout.CENTER,80,0));
						datePane.setBackground(Color.white);
						// 출발 날짜
						datePane.add(startDate);
							startDate.setFont(fnt);
							startDate.setBackground(Color.white);
							// 복귀 날짜
						datePane.add(arriveDate);
							arriveDate.setFont(fnt);
							arriveDate.setBackground(Color.white);
			//테이블 패널
			centerPane.add("Center",tablePane);
			tablePane.setBackground(Color.white);
				tablePane.add(sp);
				sp.setFont(fnt);
			
			centerPane.add("South",btnPane);
				btnPane.setLayout(new FlowLayout(FlowLayout.CENTER,50,10));
				btnPane.setBackground(Color.white);
				btnPane.add(cancelBtn);
					cancelBtn.setFont(fnt);
					cancelBtn.setBackground(new Color(0,130,255));
					cancelBtn.setForeground(Color.white);
					
				btnPane.add(nextBtn);
					nextBtn.setFont(fnt);
					nextBtn.setBackground(new Color(0,130,255));
					nextBtn.setForeground(Color.white);
					
		setBackground(Color.white);
		setSize(1000,800);
		setVisible(true);
		
		
		//
		tbl.addMouseListener(this);
		cancelBtn.addActionListener(this);
		nextBtn.addActionListener(this);
	}
	
	public void getDepFromCbc1(String dep, String des) {///cbc1에서 출발지 받아오기
		String dep2 = dep;
		String des2 = des;
		System.out.println("cbc2로 잘 도착dep->"+dep2);
		System.out.println("cbc2로 잘 도착des->"+des2);
		
		CustomBookingChange2DAO dao = new CustomBookingChange2DAO();
		lst = dao.getDepDes(dep2, des2);
		
		setNewTableList(lst);
	}
	public void setNewTableList(List<CustomBookingChange2VO> lst) {
		model.setRowCount(0);
		for(int i=0; i<lst.size(); i++) {
			CustomBookingChange2VO vo = lst.get(i);
			Object[] data = {vo.getDepTime(), vo.getDesTime(), vo.getFlightTime(),
					vo.getFlightNo(), vo.getFlightState(), vo.getFare()};
			model.addRow(data);
			
			System.out.println("1->"+vo.getDepTime()+"2->"+vo.getDesTime()+"3->"+vo.getFlightTime()
			+"4->"+vo.getFlightNo()+"5->"+vo.getFlightState()+"6->"+vo.getFare());
			}
		sp.getViewport().setBackground(Color.white);
	}
	public void actionPerformed(ActionEvent ae){
		Object obj = ae.getSource();
		if(obj instanceof JButton) {
			String str = ae.getActionCommand();
			if(str.equals("변경취소")) {
				this.setVisible(false);
				CustomFrame.plan.setVisible(true);
				CustomFrame.centerPane.add(CustomFrame.plan);
			} else if(str.equals("다음단계")) {
//				if(row==0) {
//					JOptionPane.showMessageDialog(this, "변경할 항공편을 선택하세요.");
//				}else {
					this.setVisible(false);
					CustomFrame.bookingChange3.setVisible(true);
					CustomFrame.centerPane.add(CustomFrame.bookingChange3);
				}
			}
		}
	//}

	@Override
	public void mouseClicked(MouseEvent me) {
		if(me.getButton()==1) {
			if(tbl.getSelectedRowCount() !=1) {
				JOptionPane.showMessageDialog(this, "1개의 비행편 선택 가능합니다.");
			}else {
				row = tbl.getSelectedRow()+1;
				System.out.println(row);
				
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

