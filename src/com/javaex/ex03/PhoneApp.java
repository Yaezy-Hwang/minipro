package com.javaex.ex03;

import java.io.IOException;
import java.util.List;

public class PhoneApp {

	public static void main(String[] args) throws IOException {

		/*
		PhoneView 생성
        화면의 출력, 데이터 입력의 기능을 담당하고 있는 클래스
		*/
		PhoneView phoneView = new PhoneView();
		
		/*
		PhoneRepository 생성
        PhoneDB.txt로 부터 데이터를 가져오고, 저장하고, 삭제하는 등의 기능을
		담당하는 클래스
		*/
		PhoneRepository phoneRepo =  new PhoneRepository();

		
		boolean run = true;
		List<Person> phoneList;

		phoneView.showStart();

		while (run) {
			int menuNum = phoneView.showMenu();

			switch (menuNum) {

			case 1:
				phoneList = phoneRepo.getList();
				phoneView.showList(phoneList);
				break;

			case 2:
				Person phoneVO = phoneView.showAdd();
				phoneRepo.addInfo(phoneVO);
				phoneView.showAddResult();
				break;

			case 3:
				int delNo = phoneView.showDel();
				phoneRepo.delInfo(delNo);
				phoneView.showDelResult();
				break;

			case 4:
				String keyword = phoneView.showSearch();
				phoneList = phoneRepo.getList();
				phoneView.showSearchResult(phoneList, keyword);
				break;

			case 5:
				phoneView.showEnd();
				run = false;
				break;

			default:
				phoneView.showEtc();
				break;
			}// switch

		} // while

	}

}
