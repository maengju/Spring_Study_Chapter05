package user.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import user.bean.UserDTO;
import user.dao.UserDAO;

public class UserSearchService implements UserService {
	
	@Autowired
	private UserDTO userDTO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		
		
		int num;
		while(true) {
			System.out.println();
			System.out.println("*************");
			System.out.println("1. 이름 검색");
			System.out.println("2. 아이디 검색");
			System.out.println("*************");
			System.out.print("번호 입력 : ");
			num = scan.nextInt();
			
			if(num==1) {
				System.out.print("이름 입력 : ");
				String name = scan.next();
				
				
				userDTO.setName(name);
				

				List<UserDTO> list=userDAO.search(userDTO);
				System.out.println("이름\t\t아이디\t\t비밀번호");
				for(UserDTO data : list) {
					System.out.println(data.getName()+"\t\t"+data.getId()+"\t\t"+data.getPwd());
					
				}
				return;
			}else if(num==2) {
				System.out.print("아이디 입력 : ");
				String id = scan.next();
				
				UserDTO userDTO = new UserDTO();
				userDTO.setId(id);
				
				
				List<UserDTO> list=userDAO.search(userDTO);
				System.out.println("이름\t\t아이디\t\t비밀번호");
				for(UserDTO data : list) {
					System.out.println(data.getName()+"\t\t"+data.getId()+"\t\t"+data.getPwd());
					
				}
				return;
			}
			
		}//while

	}

}
