package com.javaex.ex02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.javaex.ex01.Person;

public class PhoneManager {

	private List<Person> pList;
	private Scanner sc;

	public PhoneManager() {
		sc = new Scanner(System.in);
		pList = new ArrayList<Person>();
	}

	// 시작준비 (시작화면 출려과 리스트 가져온다)
	public void showTitle() throws IOException {
		System.out.println("**********************************************");
    	System.out.println("*           전화번호 관리 프로그램           *");
    	System.out.println("**********************************************");
    	
    	pList = getList();
	}

	// 메뉴 출력과 입력을 받는다.
	public int showMenu() {
		System.out.println();
		System.out.println("1.리스트   2.등록   3.삭제   4.검색   5.종료");
		System.out.println("----------------------------------------------");
		System.out.print(">메뉴번호: ");
		return sc.nextInt();
	}

	// 1.리스트선택시
	public void showList() throws IOException {
    	System.out.println("<1.리스트>");
		for(Person p: pList) {
			System.out.println(pList.indexOf(p)+1+p.showList());
		}
	}

	// 2.등록선택시
	public void showAdd() throws IOException{
		OutputStream out = new FileOutputStream("./PhoneDB.txt", true);
		OutputStreamWriter osw = new OutputStreamWriter(out);
		BufferedWriter bw = new BufferedWriter(osw); 
		
		System.out.println("<2.등록>");
		//정보입력받기
		System.out.print(">이름: ");
		String name = sc.next();
		
		
		System.out.print(">휴대전화: ");
		String hp = sc.next();
		
		System.out.print(">회사전화: ");
		String company = sc.next();

		//리스트에 추가
		Person newP = new Person(name, hp, company);
		pList.add(newP);
		//파일에 쓰기
		bw.write(name+","+hp+","+company);
		bw.newLine();
		
		System.out.println("[등록되었습니다.]");
		
		bw.close();

	}

	// 3.삭제선택시
	public void showRemove() throws IOException{
    	OutputStream outOver = new FileOutputStream("./PhoneDB.txt");
		OutputStreamWriter oswOver = new OutputStreamWriter(outOver);
		BufferedWriter bwOver = new BufferedWriter(oswOver); 
		
		
			System.out.println("<3.삭제>");
			System.out.print(">번호 :");
			int num = sc.nextInt()-1;
			pList.remove(num);
			
			for(Person p: pList) {
				bwOver.write(p.getName()+","+p.getHp()+","+p.getCompany());
				bwOver.newLine();
			}//for
			
			System.out.println("[삭제되었습니다.]");
			
			bwOver.close();
	}

	// 4.검색선택시
	public void showSearch() {
		System.out.println("<4.검색>");
		System.out.print(">이름: ");
		String str = sc.next();
	
		for(Person pp: pList) {
			String pPart = pp.getName();
			if(pPart.contains(str)){
				int idnum = pList.indexOf(pp)+1;
				System.out.println(idnum+pp.showList());
			}//if
		
		}//for
	}

	// 5.종료시
	public void showEnd() {
    	System.out.println("**********************************************");
    	System.out.println("*                 감사합니다.                *");
    	System.out.println("**********************************************");
		sc.close();
	}
	
	
	// 메뉴번호를 잘못 입력시 안내문구를 출력하는 메소드
	public void showEtc() {
		System.out.println("[다시 입력해 주세요.]");
	}
	
	
	// 파일을 읽어 리스트에 담아 전달한다.
	private List<Person> getList() throws IOException {
		InputStream in = new FileInputStream("./PhoneDB.txt");
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(isr);
		
		List<Person> pList = new ArrayList<>();
		
		while(true) {
			String str = br.readLine();
			if(str==null) {
				break;
			}
			String[] splitInfo = str.split(",");
			Person person = new Person(splitInfo[0], splitInfo[1], splitInfo[2]);
			pList.add(person);
		}
		br.close();
		return pList;
	}

	
}
