package com.java.user.service;

import com.java.common.AppService;
import com.java.user.domain.User;
import com.java.user.repository.UserRepository;

import static com.java.view.AppUI.*;

import java.util.List;

public class UserService implements AppService{

	private final UserRepository userRepository = new UserRepository();
	
	@Override
	public void start() {
		while(true) {
			userManagementScreen();
			int selection = inputInteger();
			
			switch (selection) {
			case 1:
				join();
				break;
			case 2:
				showSearchResult();		
				break;
			case 3:
				deleteuser();
				break;
			case 4:
				
				return;// 메인으로 돌아가기

			default:
				System.out.println("메뉴를 다시입력하세요.");
			}
			System.out.println("\n====== 계속 진행하시려면 ENTER를 누르세요. ======");
			inputString();
		}
	}
	
	//회원 추가 비즈니스 로직
	private void join() {
		
		System.out.println("\n====== 회원가입을 진행합니다. ======");
		System.out.print("#회원명: ");
		String name = inputString();
		System.out.print("#전화번호: ");
		String phone = inputString();
		
		User user = new User();
		user.setUserName(name);
		user.setPhoneNumber(phone);
		
		userRepository.addUser(user);
		
	}
	
	//회원 이름으로 검색 비즈니스 로직
	private List<User> searchUser() {
		System.out.println("\n### 조회할 회원의 이름을 입력하세요.");
		System.out.print(">>>");
		String name = inputString();
		return userRepository.findByUserName(name);
	}
	private int showSearchResult() {
		List<User> users = searchUser();
		if(users.size()!=0) {
			System.out.println("\n=================== 회원 조회 결과 ===================");
			for(User user :users) {
				System.out.println(user);
			}
		}
		else {
			System.out.println("\n### 조회결과가 없습니다.");
		}
		return users.size();
	}
	
	//회원탈퇴 비즈니스 로직
	private void deleteuser() {
		//삭제할 회원의 이름을 입력받아서 삭제 대상 회원만 가지고 오자.
		
		if(showSearchResult() > 0) {
			System.out.println("\n### 탈퇴할 회원의 번호를 입력하세요.");
			System.out.println(">>>");
			int delUserNum = inputInteger();
					
			userRepository.deleteUser(delUserNum);
		}
	}
			
	
	
}
