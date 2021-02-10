import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Calendar;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import dbAll.EmpReservationDAO;
import dbAll.EmpReservationVO;



public class EmpAirlineReservation extends JPanel implements ActionListener{

	
	Font fnt = new Font("굴림체",Font.BOLD,32);// 탑승자 정보
	Font fnt2 = new Font("굴림체",Font.BOLD,24); //  	
	Font fnt3 = new Font("굴림체",Font.BOLD,14); //기본폰트 
	
	JPanel centerPane = new JPanel();
		JLabel reservationLbl = new JLabel("예약번호");
			JButton btn = new JButton("검색");
		JLabel startLbl = new JLabel("출 발 지");
			JButton btn1 = new JButton("검색");
		JLabel airLbl = new JLabel("항 공 편 ");
			JButton btn2 = new JButton("검색");
		JLabel dateLbl = new JLabel("출발날짜");
			ImageIcon icon = new ImageIcon("img/calendar.png");
				Image cal = icon.getImage();
				Image cal2 = cal.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
			     ImageIcon icon2 = new ImageIcon(cal2);
			     JLabel startCalendar = new JLabel(icon2);
			     
			     
		 JTextField tf [] = {new JTextField(10), new JTextField(10),new JTextField(10),new JTextField(10)};	     
			     

	JPanel tablePane = new JPanel(new BorderLayout());	
		JLabel passengerlbl = new JLabel("탑승자 정보");
		JTable table;
			String listLbl [] = {"예약번호", "성명 (한)", "생년월일", "연락처","출발지","항공편"};
//			Object test[][] = {
//					{"DEKL8","홍길동","00/11/11","010-0000-0000","ICN","AC832"},
//					{"2DWR7","이순신","06/08/31","010-7777-7777","ICN","AC614"},
//					{"D2W5G","세종대왕","92/05/20","010-8888-8888","ICN","AC682"},
//					{"21RES","장영실","80/10/31","010-3333-3333","ICN","AC516"},	};     	

			JScrollPane sp;
			DefaultTableModel sales;	
				
				
	JPanel SouthPane = new JPanel(new BorderLayout()); 
		JPanel btnPane = new JPanel();		
			JButton modibtn = new JButton("수정");
			JLabel empty = new JLabel("      ");
			JButton resetBtn = new JButton("초기화");
			JLabel empty2 = new JLabel("      ");
			JButton delBtn = new JButton("삭제");
		

	JPanel mainSouthPane = new JPanel();
		
	int x, x1;
	int calendarWindowTest = 0;
	int clickCheck = 0;

	
	public EmpAirlineReservation() {

		setLayout(new BorderLayout());
		
		add("North",centerPane);
			centerPane.setLayout(null);
			centerPane.setPreferredSize(new Dimension(1000,250));
			int x=320;
			int x1=400;
			
			centerPane.add(reservationLbl).setBounds(x,50,70,30);
				reservationLbl.setFont(fnt3);
			centerPane.add(tf[0]).setBounds(x1,50,150,30); 
			centerPane.add(btn).setBounds(570,50,70,30);
				btn.setFont(fnt3);btn.setForeground(Color.white);
				btn.setBackground(new Color(255,128,128));btn.setBorder(new LineBorder(Color.white, 1, true));

			centerPane.add(startLbl).setBounds(x,100,70,30);
				startLbl.setFont(fnt3);
			centerPane.add(tf[1]).setBounds(x1,100,150,30); 
			centerPane.add(btn1).setBounds(570,100,70,30);
				btn1.setFont(fnt3);	btn1.setForeground(Color.white);
				btn1.setBackground(new Color(255,128,128));	btn1.setBorder(new LineBorder(Color.white, 1, true));

			centerPane.add(airLbl).setBounds(x,150,70,30);
				airLbl.setFont(fnt3);

			centerPane.add(tf[2]).setBounds(x1,150,150,30); 
			centerPane.add(btn2).setBounds(570,150,70,30);	
				btn2.setFont(fnt3);	btn2.setForeground(Color.white);
				btn2.setBackground(new Color(255,128,128));	btn2.setBorder(new LineBorder(Color.white, 1, true));
		
			
			centerPane.add(dateLbl).setBounds(x,200,70,30);
			dateLbl.setFont(fnt3);
			centerPane.add(tf[3]).setBounds(x1,200,150,30); 
			centerPane.add(startCalendar).setBounds(545,200,70,30);
	
			centerPane.setBackground(Color.white);
	
		add("Center",tablePane);
		
		
			tablePane.setLayout(null);
			
				tablePane.add(passengerlbl).setBounds(430,-130,750,300);
				passengerlbl.setFont(fnt2);
				
				sales = new DefaultTableModel(listLbl,0);
				table = new JTable(sales);
				sp = new JScrollPane(table);
			
				table.getParent().setBackground(Color.white);
				tablePane.add(sp).setBounds(116,50,750,300); 

				// 테이블 셀 편집 가능 여부
				table.setEnabled(true);
				// DefaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)
				DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();

				// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
				tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

				// 정렬할 테이블의 ColumnModel을 가져옴
				TableColumnModel tcmSchedule = table.getColumnModel();

				// 반복문을 이용하여 테이블을 가운데 정렬로 지정
				for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
				tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
				}
				//
				
		
				
				tablePane.setBackground(Color.white);
				
				
		add("South",SouthPane);
			SouthPane.add(btnPane);
				btnPane.add(modibtn);
					modibtn.setFont(fnt3);
					modibtn.setForeground(Color.white);
					modibtn.setBackground(new Color(255,128,128));
				
				btnPane.add(empty);
				btnPane.add(resetBtn);
					resetBtn.setFont(fnt3);
					resetBtn.setForeground(Color.white);
					resetBtn.setBackground(new Color(255,128,128));
					
				btnPane.add(empty2);
				btnPane.add(delBtn);
					delBtn.setFont(fnt3);
					delBtn.setForeground(Color.white);
					delBtn.setBackground(new Color(255,128,128));
			
				btnPane.setLayout(new FlowLayout(FlowLayout.CENTER));		
			
				btnPane.setBackground(Color.white);
			
		
		
		setSize(1000,800);
		setVisible(true);
		
		getReservationAll();
		
		btn.addActionListener(this);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		
		
		modibtn.addActionListener(this);
		delBtn.addActionListener(this);
		resetBtn.addActionListener(this);
		
		
		
		
//		table.addMouseListener(new MouseAdapter() {
//			public void mouseReleased(MouseEvent me) { //JTable에서 클릭하면 이벤트생김
//				//이벤트 발생버튼이마우스 인쪽 버튼이면
//				if(me.getButton()==1) {
//					// 선택된 행번호 가져오기
//					int row = table.getSelectedRow(); // 행번호가져오기
//					int col =table.getColumnCount(); //칸수 구하기
//
//						tf[0].setText((String)sales.getValueAt(row,1));
//						tf[1].setText((String)sales.getValueAt(row,4));
//						tf[2].setText((String)sales.getValueAt(row,5));
//							
//				}
//			}
//		});

		startCalendar.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me) {
				me.getSource(); 
				if(calendarWindowTest==0) {
					 new CustomCalendar();
					 calendarWindowTest=1;
				 }
			}
		});
	}
	
	public void getReservationAll() {
		//데이터 베이스의 모든 회원을 선택해서 JTabel 에 표시한다
		EmpReservationDAO dao=new EmpReservationDAO();
		List<EmpReservationVO> lst = dao.EmpReservationAllselect();
		
		setNewTableList(lst);
	}

	public void setNewTableList(List<EmpReservationVO> lst) {
		sales.setRowCount(0); //JTable의 모든 레코드 지우기
		for(int i = 0 ; i<lst.size() ; i++) {
			EmpReservationVO vo =lst.get(i);  // i씩 꺼낼때 vo에 들어가있는데
//			Object[] data = {vo.getResno(),vo.getUser_name(),vo.getUser_birth(),
//							vo.getUser_tel(),vo.getDep(),vo.getFlightno(),
//							vo.getResno_res(),vo.getCom_name(),vo.getCom_birth(),
//							vo.getCom_tel(),vo.getDep2(),vo.getFlightno_res()
//							};
			Object[] data1 = {vo.getResno(),vo.getUser_name(),vo.getUser_birth(),
					vo.getUser_tel(),vo.getDep(),vo.getFlightno(),
			};

	
			sales.addRow(data1);

		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton) {
			String btn = e.getActionCommand();
			if(btn.equals("삭제")) {
			
			} else if(btn.equals("초기화")) { // 다지우기
				setFormClear();
			}else if(btn.equals("수정")) {
				this.setVisible(false);
				EmpMainFrame.reservation1.setVisible(true);
				EmpMainFrame.centerPane.add(EmpMainFrame.reservation1);
			}
		}
		
	}
	
	public void setFormClear() {	
		for (int i = 0 ; i <tf.length ; i++) {
			tf[i].setText("");
		}
	}
	
	class CustomCalendar extends JFrame implements ActionListener, WindowListener{
		// 상단 지역
		JPanel bar = new JPanel();
			JButton lastMonth = new JButton("◀");
		
			JComboBox<Integer> yearCombo = new JComboBox<Integer>(); 
				DefaultComboBoxModel<Integer> yearModel = new DefaultComboBoxModel<Integer>();
			
			JLabel yLbl = new JLabel("년 ");
			
			JComboBox<Integer> monthCombo = new JComboBox<Integer>(); 
				DefaultComboBoxModel<Integer> monthModel = new DefaultComboBoxModel<Integer>();
			JLabel mLbl = new JLabel("월");
			JButton nextMonth = new JButton("▶");
		// 중앙 지역
		JPanel center = new JPanel(new BorderLayout());
			// 중앙 상단 지역
			JPanel cntNorth = new JPanel(new GridLayout(0,7));
			// 중앙 중앙 지역
			JPanel cntCenter = new JPanel(new GridLayout(0,7));
		
		// 요일 입력
		String dw[] = {"일","월","화","수","목","금","토"};
		
		
		Calendar now = Calendar.getInstance();
		int year, month, date;
		
		public CustomCalendar() {
			year = now.get(Calendar.YEAR);// 2021년
			month = now.get(Calendar.MONTH)+1; // 0월 == 1월
			date = now.get(Calendar.DATE);
			for(int i=year; i<=year+50; i++){yearModel.addElement(i);}
			for(int i=1; i<=12; i++) { monthModel.addElement(i); }
			//////////////////////////프레임///////////////////////////////////////////
			// 상단 지역
			add("North", bar);
				bar.setLayout(new FlowLayout());
				bar.setSize(300,400);
				bar.add(lastMonth);
				//////////////////////////달력/////////////////////////////////////////////
				bar.add(yearCombo);
					yearCombo.setModel(yearModel);
					yearCombo.setSelectedItem(year);

				bar.add(yLbl);
				bar.add(monthCombo);
					monthCombo.setModel(monthModel);
					monthCombo.setSelectedItem(month);

				bar.add(mLbl);
				bar.add(nextMonth);
				bar.setBackground(new Color(0,210,180));

			// 중앙 지역
			add("Center", center);
				// 중앙 상단 지역
				center.add("North",cntNorth);
				for(int i=0; i<dw.length; i++) {
					JLabel dayOfWeek = new JLabel(dw[i],JLabel.CENTER);
					if(i==0) dayOfWeek.setForeground(Color.red);
					else if(i==6) dayOfWeek.setForeground(Color.blue);
					cntNorth.add(dayOfWeek);
				}

				// 중앙 중앙 지역
				center.add("Center",cntCenter);
				dayPrint(year,month);
				
				
			// 이벤트
			yearCombo.addActionListener(this);
			monthCombo.addActionListener(this);
			lastMonth.addActionListener(this);
			nextMonth.addActionListener(this);
			addWindowListener(this);
			
			// frame 기본 셋팅
			setSize(400,300);
			setVisible(true);
			setResizable(false);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			
		}	

		
	
		
		// 이벤트 처리
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(obj instanceof JButton){
				JButton eventBtn = (JButton)obj;
				int yy = (Integer)yearCombo.getSelectedItem();
				int mm = (Integer)monthCombo.getSelectedItem();
				if(eventBtn.equals(lastMonth)){	//전달
					if(mm == 1 && yy == year ) {
					} else if(mm == 1){
						yy--; mm = 12;
					} else {
						mm--;
					}
				}else if(eventBtn.equals(nextMonth)){	//다음달
					if(mm == 12){
						yy++; mm = 1;
					}else{
						mm++;
					}
				}
				yearCombo.setSelectedItem(yy);
				monthCombo.setSelectedItem(mm);
			}else if(obj instanceof JComboBox){	//콤보박스 이벤트 발생시
				createDayStart();
			}
		}


		private void createDayStart() {
			cntCenter.setVisible(false);	//패널 숨기기
			cntCenter.removeAll();	//날짜 출력한 라벨 지우기
			dayPrint((Integer)yearCombo.getSelectedItem(), (Integer)monthCombo.getSelectedItem());
			cntCenter.setVisible(true);	//패널 재출력	
		}


		// 날짜 출력
		public void dayPrint(int y,int m) {
			Calendar cal = Calendar.getInstance();
			cal.set(y, m-1, 1);
			int week = cal.get(Calendar.DAY_OF_WEEK); // 1일에 대한 요일
			int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 1월에 대한 마지막 요일
			for(int i =1; i<week; i++) { // 1월 1일 전까지 공백을 표시해라
				cntCenter.add(new JLabel(""));
			}
			
			for(int i =0;i<=lastDate-1;i++) { // 1월 마지막 날까지 숫자를 적어라, 그리고 토요일 일요일은 색깔을 입혀라
				JLabel day = new JLabel();
				day.setHorizontalAlignment(JLabel.CENTER);
				if((week+i)%7==0) {
					cntCenter.add(day).setForeground(Color.blue);
					day.setText(1+i+"");
				} else if((week+i)%7==1) {
					cntCenter.add(day).setForeground(Color.red);
					day.setText(1+i+"");
				} else {
					cntCenter.add(day);
					day.setText(1+i+"");
				}
				day.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent me) {
						JLabel mouseClick = (JLabel)me.getSource();
						String str= mouseClick.getText();
						String y = ""+yearCombo.getSelectedItem();
						String m = ""+monthCombo.getSelectedItem();
						
						// 받은 "요일"이 1자리면 0을 붙여라
						if(str.equals("")) ;
						else if(str.length()==1) str = "0"+str; 
						
						// 받은 "월"이 1자리면 0을 붙여라
						if(m.length()==1) m = "0"+m;
						
						if(clickCheck==0) {
							tf[3].setText(y+"/"+m+"/"+str);
							tf[3].setEnabled(false);
							clickCheck++;
						} else if(me.getClickCount()==2) {
							dispose(); // 더블클릭 두번하면 창  //? 근데 다시 열리지않음..음..? ?반복?
							calendarWindowTest = 0;
							clickCheck=0;
						
				
						}
					}
				});
			}

			
		}

		public void windowOpened(WindowEvent e) {
			calendarWindowTest = 1;
		}
		public void windowClosing(WindowEvent e) {
			calendarWindowTest = 0;
		}	
		public void windowClosed(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowActivated(WindowEvent e) {}
		public void windowDeactivated(WindowEvent e) {}
	}


}
