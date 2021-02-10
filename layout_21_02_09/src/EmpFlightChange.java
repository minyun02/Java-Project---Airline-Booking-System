import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


import dbAll.EmpFlightChangeDAO;
import dbAll.EmpFlightChangeVO;

public class EmpFlightChange extends JPanel implements ActionListener{
	Font fnt = new Font("굴림체", Font.BOLD, 14);
	
	ImageIcon icon = new ImageIcon("img/calendar.png");
	Image im = icon.getImage();
	Image im2 = im.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
	ImageIcon icon2 = new ImageIcon(im2);
	JButton calBtn = new JButton(icon2);
	JTextField dateTf = new JTextField(10);
	String dateSearchWord ="";
	
	JLabel acLbl = new JLabel("AC");
	JTextField acTf = new JTextField(10);
	
	JLabel depLbl = new JLabel("출발지");
	JTextField depTf = new JTextField(10);
	
	JLabel desLbl = new JLabel("도착지");
	JTextField desTf = new JTextField(10);
	JButton searchBtn = new JButton("검색");
	
	////table
	String info[] = {"항공편명","출발날짜", "출발지", "출발시간", "도착지", "도착시간", "상태"};
	JTable table;
	JScrollPane sp;
	DefaultTableModel model;
	
	//
	JLabel foodLbl = new JLabel("기내식");
		JLabel korFoodLbl = new JLabel("한식");
			JComboBox<String> korFoodBox;
			String[] korFoodStr = {"비빔밥", "떡갈비", "불고기"};
		JLabel chineseFoodLbl = new JLabel("중식");
			JComboBox<String> chineseFoodBox;
			String[] chineseFoodStr = {"베이징면","사천볶음밥", "광저우생선"};
		JLabel westFoodLbl = new JLabel("양식");
			JComboBox<String> westFoodBox;
			String[] westFoodStr = {"파스타", "찹스테이크", "토마토스튜"};
	
	JLabel delayLbl = new JLabel("지연");
	JCheckBox delayCheckBox = new JCheckBox("", false);
	JLabel depTimeLbl = new JLabel("출발시간");
	JTextField depTimeTf = new JTextField(10);
	JLabel arrTimeLbl = new JLabel("~   도착시간");
	JTextField arrTimeTf = new JTextField(10);
	JLabel cancelLbl = new JLabel("결항");
	JComboBox<String> cancelState;
	String[] state = {"기상악화", "기장실종", "기타사유"};
	JCheckBox cancelBox = new JCheckBox("", false);
	
	JButton btn2 = new JButton("설정완료");
	
	//이벤트용 변수
	int clickCheck = 0;
	int calendarWindowTest = 0;
	
	public EmpFlightChange() {
		setLayout(null);
		this.setBackground(Color.white);
		
		add(calBtn).setBounds(200,100, 35,35);
			calBtn.setBorderPainted(false);
			calBtn.setContentAreaFilled(false);
			calBtn.setFocusPainted(false);
		add(dateTf).setBounds(200,140, 200,25);dateTf.setText("검색시작날짜 : "+dateSearchWord); dateTf.setFont(fnt); dateTf.setBorder(new LineBorder(Color.white)); dateTf.setEnabled(false);
		add(acLbl).setBounds(260, 100, 80, 35);
			//acLbl.setFont(fnt);
		add(acTf).setBounds(285, 105, 100, 25);
			acTf.setFont(fnt);
		add(depLbl).setBounds(400, 100, 50, 35);
			depLbl.setFont(fnt);
		add(depTf).setBounds(445, 105, 100, 25);
			depTf.setFont(fnt);
		add(desLbl).setBounds(560, 100, 50, 35);
			desLbl.setFont(fnt);
		add(desTf).setBounds(605, 105, 100, 25);
			desTf.setFont(fnt);
		add(searchBtn).setBounds(720, 101, 70, 30);
			searchBtn.setFont(fnt);
			searchBtn.setForeground(Color.white);
			searchBtn.setBackground(new Color(255,128,128));
			searchBtn.setBorder(new LineBorder(Color.white, 1, true));
		
		//table
		model = new DefaultTableModel(info,0);
		table = new JTable(model);
		sp  = new JScrollPane(table);
		add(sp).setBounds(200, 165, 600, 300);
			sp.getViewport().setBackground(Color.white);
		
		//기내식
		add(foodLbl).setBounds(200, 480, 60,35);
			foodLbl.setFont(fnt);
		//한식
		add(korFoodLbl).setBounds(280, 480,60,35); korFoodLbl.setFont(fnt);
		korFoodBox = new JComboBox<String> (korFoodStr);
		add(korFoodBox).setBounds(320, 483, 100, 25);korFoodBox.setFont(fnt);korFoodBox.setBackground(Color.white);
			//cb1.setAlignmentX(CENTER_ALIGNMENT);
		//중식
		add(chineseFoodLbl).setBounds(440,480, 60,35); chineseFoodLbl.setFont(fnt);
		chineseFoodBox = new JComboBox<String>(chineseFoodStr);
		add(chineseFoodBox).setBounds(480,483, 100, 25); chineseFoodBox.setFont(fnt);chineseFoodBox.setBackground(Color.white);
		//양식
		add(westFoodLbl).setBounds(600,480, 60,35); westFoodLbl.setFont(fnt);
		westFoodBox = new JComboBox<String>(westFoodStr);
		add(westFoodBox).setBounds(640,483, 100,25); westFoodBox.setFont(fnt);westFoodBox.setBackground(Color.white);
		
		//지연 상태
		add(delayLbl).setBounds(200, 515, 50, 35);
			delayLbl.setFont(fnt);
		add(delayCheckBox).setBounds(280, 524, 17, 17);
			delayCheckBox.setBackground(Color.white);
		add(depTimeLbl).setBounds(320,517, 60, 35);
			depTimeLbl.setFont(fnt);
		add(depTimeTf).setBounds(400, 521, 80, 25);
		depTimeTf.setFont(fnt);
		add(arrTimeLbl).setBounds(500, 516, 100, 35);
			arrTimeLbl.setFont(fnt);
		add(arrTimeTf).setBounds(610, 521, 80, 25);
			arrTimeTf.setFont(fnt);
			
		//결항 상태
		add(cancelLbl).setBounds(200, 550, 50, 35);
			cancelLbl.setFont(fnt);
		add(cancelBox).setBounds(280, 559, 17, 17);
		cancelBox.setBackground(Color.white);
		cancelState = new JComboBox<String>(state);
		add(cancelState).setBounds(320,555, 150, 25);
			cancelState.setFont(fnt);
			cancelState.setBackground(Color.white);
			
		add(btn2).setBounds(440, 600, 100,30);
			btn2.setFont(fnt);
			btn2.setForeground(Color.white);
			btn2.setBackground(new Color(255,128,128));
			btn2.setBorder(new LineBorder(Color.white, 1, true));
		
		setSize(1000, 800);
		setVisible(true);
		
		//이벤트 등록
		calBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me) {
				me.getSource(); 
				if(calendarWindowTest==0) {
					 new CustomCalendar();
					 calendarWindowTest=1;
				 }
			}
		});
		searchBtn.addActionListener(this);
		
		//초기화면에 오늘날짜 이후 항공편 출력
		getAllFlight();
	}
	public void getAllFlight() {
		EmpFlightChangeDAO dao = new EmpFlightChangeDAO();
		List<EmpFlightChangeVO> lst = dao.flightAllSelect();
		
		setNewTableList(lst);
	}
	public void setNewTableList(List<EmpFlightChangeVO> lst) {
		model.setRowCount(0);
		for(int i=0; i<lst.size(); i++) {
			EmpFlightChangeVO vo = lst.get(i);
			Object[] data = {vo.getFlightno_r(), vo.getBrdDate_r(),
					vo.getDep(), vo.getDepTime(), vo.getDes(), vo.getDepTime(), vo.getFlight_state()};
			model.addRow(data);
		}
	}
	public void flightSearch() {
		String acSearchWord = acTf.getText();
		System.out.println(acSearchWord);
		String depSearchWord = depTf.getText().toUpperCase();
		System.out.println(depSearchWord);
		String desSearchWord = desTf.getText().toUpperCase();
		System.out.println(desSearchWord);
		//모든 tf에 검색어가 없을때 현재날짜 기준 모든 항공편 출력
		if(dateSearchWord.equals("")&& acSearchWord.equals("")&&depSearchWord.equals("")&&
				desSearchWord.equals("")) {
			JOptionPane.showMessageDialog(this, "검색조건 미입력으로 \n"
					+ "현재날짜기준 항공편이 출력됩니다");
			getAllFlight();
		}else {//검색어가 있을때
			EmpFlightChangeDAO dao = new EmpFlightChangeDAO();
			List<EmpFlightChangeVO> searchList = dao.getSearchRecord(dateSearchWord, acSearchWord, depSearchWord, desSearchWord);
			if (searchList.size()==0) {
				JOptionPane.showMessageDialog(this, "검색 결과가 없습니다.");
			}else {
				setNewTableList(searchList);
			}
			dateSearchWord = "";
			dateTf.setText("검색시작날짜 : "+dateSearchWord);
			acTf.setText("");
			depTf.setText("");
			desTf.setText("");
		}
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		String eventBtn = ae.getActionCommand();
		if(eventBtn.equals("검색")) {
			flightSearch();
		}
		
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	///////////////////////////////////////////////////////////////////////////////////////////
	
	//////////////////            달                   력      ////////////////////////////////
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
			super("검색날짜 선택");
			year = now.get(Calendar.YEAR);// 2021년
			month = now.get(Calendar.MONTH)+1; // 0월 == 1월
			date = now.get(Calendar.DATE);
			for(int i=year-5; i<=year+50; i++){yearModel.addElement(i);}
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
			setLocationRelativeTo(null);
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
					if(mm == 1) {
						yy--; mm = 12;
					}else {
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
							dateTf.setText("검색시작날짜 : "+y+m+str);
							dateSearchWord = y+m+str;
							dateTf.setEnabled(false);
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
