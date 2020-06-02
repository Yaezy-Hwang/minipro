package com.javaex.ex01;

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

public class PhoneApp {

    public static void main(String[] args) throws IOException{

    	//1번문제
    	
		//Person Arraylist 생성
		ArrayList<Person> pList = new ArrayList<Person>();
    	
		//파일 읽어오기
    	readFile(pList);

    	Scanner sc = new Scanner(System.in);
    	
    	System.out.println("**********************************************");
    	System.out.println("*           전화번호 관리 프로그램           *");
    	System.out.println("**********************************************");
    	
    	boolean run = true;
    	while(run) {
    		System.out.println();
    		System.out.println("1.리스트   2.등록   3.삭제   4.검색   5.종료");
    		System.out.println("----------------------------------------------");
    		System.out.print(">메뉴번호: ");
    		int menu = sc.nextInt();
    		
    		switch(menu) {
    		case 1:
    			showList(pList);
    			break;
    		
    		case 2:
    			addList(pList);
    			break;
    			
    		case 3:
    			deleteList(pList);
    			break;
    			
    		case 4:
    			search(pList);
    			break;
    			
    		case 5:
    			endMsg(pList);
    	    	run = false;
    			break;
    			
    		default:
    			System.out.println("[다시 입력해 주세요.]");
    			break;
    		}//switch
    	
    	}//while

    	sc.close();
    }
    
    static Scanner sc = new Scanner(System.in);
    
    //메소드 모음
    
    //인풋스트림+","로 잘라서 리스트에 넣기
    public static void readFile(List<Person> pList) throws IOException {
    	InputStream in = new FileInputStream("./PhoneDB.txt");
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(isr);

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
    }//readFile
    
    
    // 리스트 출력
    public static void showList(List<Person> pList) throws IOException {
    	System.out.println("<1.리스트>");
		for(Person p: pList) {
			System.out.println(pList.indexOf(p)+1+p.showList());
		}
    }//showList
    
    
    // 리스트에 추가하기
    public static void addList(List<Person> pList) throws IOException {
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
    }//addList
    
    
    //삭제하기
    public static void deleteList(List<Person> pList) throws IOException {
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
			}
			
			System.out.println("[삭제되었습니다.]");
			
			bwOver.close();
    }//delete
    
    
    //검색하기
    public static void search(List<Person> pList) throws IOException {
			System.out.println("<4.검색>");
			System.out.print(">이름: ");
			String str = sc.next();
		
			for(Person pp: pList) {
			String pPart = pp.getName();
			if(pPart.contains(str)){
				int idnum = pList.indexOf(pp)+1;
				System.out.println(idnum+pp.showList());
			}
			
		}
    } //search
    
    public static void endMsg(List<Person> pList) throws IOException {
    	System.out.println("**********************************************");
    	System.out.println("*                 감사합니다.                *");
    	System.out.println("**********************************************");
    }
    
    

}