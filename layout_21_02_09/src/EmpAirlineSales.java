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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import dbAll.EmpSalesDAO;
import dbAll.EmpSalesVO;

//import CustomReservation.CustomCalendar;




public class EmpAirlineSales extends JPanel implements ActionListener{
	
	// 폰트
	Font fnt = new Font("굴림체",Font.BOLD,24);//매출관리
	Font fnt2 = new Font("굴림체",Font.BOLD,14); // 여남 버튼 	




	//중간 매츌관리 부분 		
	JPanel centerPane = new JPanel(new BorderLayout());
				JLabel salesLbl= new JLabel("매출 관리");
	JPanel tablePane = new JPanel(new BorderLayout());			
			JPanel buttonPane = new JPanel(new GridLayout(1,0));
				String listLbl [] = {"출발일", "항공편", "출발지","도착지","비용","매출","영업이익"};
		
			JTable table;
			JScrollPane sp;
			DefaultTableModel sales;
		
		JPanel searchPane = new JPanel(new GridLayout(0,1));


			JPanel datePane = new JPanel();
				JTextField tfTest [] = {new JTextField(30), new JTextField(30)};
			
				JLabel startDateLbl = new JLabel(" 시 작 일 ");
					JTextField tf = new JTextField(30);
//				ImageIcon icon = new ImageIcon("img/calendar.png");
//					Image cal = icon.getImage();
//					Image cal2 = cal.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
//				     ImageIcon icon2 = new ImageIcon(cal2);
//				     JLabel startCalendar = new JLabel(icon2);
					
				JLabel endDateLbl = new JLabel(" 종 료 일 ");
					JTextField tf2 = new JTextField(30);
					ImageIcon icon = new ImageIcon("img/calendar.png");
					Image cal2 = icon.getImage();
					Image cal3 = cal2.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
				     ImageIcon icon3 = new ImageIcon(cal3);
				     JLabel endCalendar = new JLabel(icon3);	

				JLabel startLbl = new JLabel(" 출 발 지 ");
					JComboBox<String> cb1;
					String[] startName = {"ICN", "GMP","CJU","PUS",	"HNL", "SYD","LHR","CDG","BCN","BKK","DPS","SIN",};

				JLabel endLbl = new JLabel(" 목 적 지 ");
					JComboBox<String> cb2;
					String[] endName = {"ICN", "GMP","CJU","PUS",	"HNL", "SYD","LHR","CDG","BCN","BKK","DPS","SIN",};
		
				JLabel ageLbl = new JLabel("고객연령대");
					JComboBox<Integer> cb3;
					Integer [] age = {10,20,30,40,50,60};
					JComboBox<Integer> cb4;
					Integer [] age2 = {10,20,30,40,50,60};
		
				JLabel genderLbl = new JLabel(" 성    별 ");
					JCheckBox  female = new JCheckBox (" 여 ");
					JCheckBox  male = new JCheckBox (" 남 ");
			


		// 매출내역 조회버튼	
		JPanel SouthPane = new JPanel(new BorderLayout()); 
			JPanel btnPane = new JPanel();		
				JButton enterBtn = new JButton("조회");
				JLabel empty = new JLabel("    ");
					JButton resetBtn = new JButton("초기화");
				JLabel empty2 = new JLabel("    ");
					JButton allBtn = new JButton("전체보기");
					
		int x, x1;
		int calendarWindowTest = 0;
		int clickCheck = 0;
			

	public EmpAirlineSales() {
		
		setLayout(new BorderLayout());
		
		add("North",centerPane);
			centerPane.setLayout(null);
			centerPane.setPreferredSize(new Dimension(1000,100));
		
			centerPane.add(salesLbl).setBounds(430,-80,750,300);
			salesLbl.setFont(fnt);
			centerPane.setBackground(Color.white);
			
			
			
		add("Center",tablePane);
			tablePane.setLayout(null);
			
			int x=250;
			int y= 340;
	
			
			sales = new DefaultTableModel(listLbl,0);
			table = new JTable(sales);
			
			
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
			
			
			sp = new JScrollPane(table);
			table.getParent().setBackground(Color.white);
			
			
			
			tablePane.add(sp).setBounds(116,0,750,300);
			tablePane.setBackground(Color.white);


			tablePane.add(startDateLbl).setBounds(x, 320 ,100,30);	startDateLbl.setFont(fnt2); // 시작일
//			tablePane.add(tf).setBounds(y, 320 ,100,30);	startDateLbl.setFont(fnt2); // 시작일 텍필
			tablePane.add(tfTest[0]).setBounds(y, 320 ,100,30);	startDateLbl.setFont(fnt2); // 테스트\
//			tablePane.add(startCalendar).setBounds(450,320,30,30);
			
			

			
			tablePane.add(endDateLbl).setBounds(x+250, 320 ,100,30);	endDateLbl.setFont(fnt2); // 종료일
//			tablePane.add(tf2).setBounds(y+250, 320 ,100,30);	endDateLbl.setFont(fnt2); // 종료일 텍필
			tablePane.add(tfTest[1]).setBounds(y+250, 320 ,100,30);	startDateLbl.setFont(fnt2); // 테스트
			tablePane.add(endCalendar).setBounds(450+250,320,30,30);
			
			
			tablePane.add(startLbl).setBounds(x, 360 ,100,30);	startLbl.setFont(fnt2); // 출발지
			cb1 = new JComboBox<String>(startName); cb1.setBackground(Color.white);
			tablePane.add(cb1).setBounds(y, 360 ,200,30);cb1.setFont(fnt2); // 출발지 콤보박스
			
			tablePane.add(endLbl).setBounds(x, 400 ,100,30);endLbl.setFont(fnt2); // 목적지
			cb2 = new JComboBox<String>(endName);cb2.setBackground(Color.white);
			tablePane.add(cb2).setBounds(y, 400 ,200,30); cb2.setFont(fnt2);// 목적지 콤보박스
			cb2.setSelectedIndex(11);
			
			tablePane.add(ageLbl).setBounds(x, 440 ,100,30); ageLbl.setFont(fnt2); // 고객연령대
			cb3 = new JComboBox<Integer>(age); cb3.setBackground(Color.white);
			tablePane.add(cb3).setBounds(y, 440 ,150,30); cb3.setFont(fnt2);// 고객연령대 콤보
			cb4 = new JComboBox<Integer>(age2); cb4.setBackground(Color.white);
			tablePane.add(cb4).setBounds(y+150+20, 440 ,150,30); cb4.setFont(fnt2);//고객연령대 콤보
			cb4.setSelectedIndex(5);

			

			
			tablePane.add(genderLbl).setBounds(x, 480 ,100,30);	genderLbl.setFont(fnt2); // 성별
			tablePane.add(female).setBounds(y,480,100,30); female.setBackground(Color.white); female.setFont(fnt2); // 여  체크박스
			tablePane.add(male).setBounds(y+100,480,100,30); male.setBackground(Color.white); male.setFont(fnt2);// 남  체크박스
			
			ButtonGroup bg = new ButtonGroup(); // 체크박스 여러개 중 하나만 선택되도록 설정
			bg.add(female); bg.add(male); 
			
	
					
		add("South",SouthPane);
			SouthPane.add(btnPane);
				btnPane.add(enterBtn);
					enterBtn.setFont(fnt2);
					enterBtn.setBackground(new Color(255,128,128));
					enterBtn.setForeground(Color.white);
//					enterBtn.setBorder(new LineBorder(Color.white, 1, true));
				btnPane.add(empty);
				btnPane.add(resetBtn);
					resetBtn.setFont(fnt2);
					resetBtn.setBackground(new Color(255,128,128));
					resetBtn.setForeground(Color.white);
//					resetBtn.setBorder(new LineBorder(Color.white, 1, true));
				btnPane.add(empty2);
				btnPane.add(allBtn);
					allBtn.setFont(fnt2);
					allBtn.setBackground(new Color(255,128,128));
					allBtn.setForeground(Color.white);
//					allBtn.setBorder(new LineBorder(Color.white, 1, true));
				btnPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			
				
				btnPane.setBackground(Color.white);	
//				enterBtn.setPreferredSize(new Dimension(100,40));	
//				resetBtn.setPreferredSize(new Dimension(100,40));	
//				allBtn.setPreferredSize(new Dimension(120,40));	
				
		setBackground(Color.white);	
		setSize(1000,800);
		setVisible(true);
		
		getSalesAll();
		
		enterBtn.addActionListener(this);
		resetBtn.addActionListener(this);
		allBtn.addActionListener(this);
		
		endCalendar.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me) {
				me.getSource(); 
				if(calendarWindowTest==0) {
					 new CustomCalendar();
					 calendarWindowTest=1;
				 }
			}
		});
		

	}
	/////////////////////////////////////////////레이아웃//////////////////////////////////////////////
	
	public void getSalesAll() {
		EmpSalesDAO dao = new EmpSalesDAO();
		List<EmpSalesVO> lst = dao.EmpSalesAllselect();
		
		setNewTableList(lst);
	}
	
	public void setNewTableList(List<EmpSalesVO>lst) {
		sales.setRowCount(0); 
		for(int i = 0 ; i <lst.size(); i++) {
			EmpSalesVO vo = lst.get(i);
			
		Object[] data1 = {vo.getBrdDate(),vo.getFlightNo(),vo.getDep(),
					vo.getDes(),vo.getFare(),vo.getSales(),vo.getAvail()
			};
			sales.addRow(data1);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton) {
			String btn = e.getActionCommand();
			if(btn.equals("조회")) {	// 검색
				if(cb1.getSelectedItem().equals(cb2.getSelectedItem())) {
					JOptionPane.showMessageDialog(this, "동일 지역이 선택 될 수 없습니다");
				} else if(tfTest[0].getText().equals("") || tfTest[0].getText().equals("")) {
					JOptionPane.showMessageDialog(this, "날짜를 선택해 주시기바랍니다");
				} else if(tfTest[0].getText().equals(tfTest[1].getText())) {
					JOptionPane.showMessageDialog(this, "같은 날짜 선택은 불가능합니다.");
				} else if(errorCheck() ==1) {
					JOptionPane.showMessageDialog(this, "당일보다 이전일은 항공일정이 없습니다");
				} else if(dayMinusCheck()==1) {
					JOptionPane.showMessageDialog(this, "출발 날짜보다 도착 날짜가 이전일 수는 없습니다");
				} 
				
			} else if(btn.equals("초기화")) { // 다지우기
				setFormClear();
			
			}	else if(btn.equals("전체보기")) {	// 모두검색
//				getMemberAll();
		}
		
	}

	}
	/// 출발 날짜가 도착 날짜보다 뒤일 경우
		public int dayMinusCheck() {
			int result = 0;
			int start = Integer.valueOf(tfTest[0].getText().replace("/", ""));
			int arrive = Integer.valueOf(tfTest[1].getText().replace("/", ""));
			int minusCheck = arrive-start;
			if(minusCheck<0) {
				result = 1;
			}
			return result;
		}
		// 출발날짜 선택을 당일보다 전일로 설정 할 경우 
		public int errorCheck() {
			int result = 0;
			int start = Integer.valueOf(tfTest[0].getText().replace("/",""));
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			int fmt = Integer.valueOf(format.format(date));
			int dateCheck = fmt - start;
			if(dateCheck > 0){
				result = 1;
			}
			
			return result;
		}
		
	
	public void setFormClear() {	
		for (int i = 0 ; i <tfTest.length ; i++) {
			tfTest[i].setText("");
		}
		
	}
	
	
	//달력
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
							tfTest[0].setText(y+"/"+m+"/"+str);
							tfTest[0].setEnabled(false);
							clickCheck++;
						} else if(clickCheck==1) {
							tfTest[1].setText(y+"/"+m+"/"+str);
							tfTest[1].setEnabled(false);
							clickCheck--;
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
