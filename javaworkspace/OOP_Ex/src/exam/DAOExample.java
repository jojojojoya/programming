package exam;

import java.util.Scanner;

interface DataAccessObject {
	String select(); // 조회 R
	String insert(); // 생성 C
	String update(); // 수정 U
	String delete(); // 삭제 D
	
}


class OracleDao implements DataAccessObject {
@Override
public String insert() {
	return "oracle로 진현 회원가입";
}@Override
	public String select() {
		return "oracle로 진현 정보조회";
	}@Override
		public String update() {
			return "oracle로 진현 정보수정";
		}@Override
			public String delete() {
				return "oracle로 진현 정보삭제";
			}

}
class MySqlDao implements DataAccessObject{
	@Override
	public String insert() {
		return "mysql로 진현 회원가입";
	}@Override
	public String select() {
		return "mysql로 진현 정보조회";
	}@Override
	public String update() {
		return "mysql로 진현 정보수정";
	}@Override
	public String delete() {
		return "mysql로 진현 정보삭제";
	}
	
	
	
}

public class DAOExample {

	public static void dbwork(DataAccessObject oracleDao) {
		System.out.println(oracleDao.select());
		System.out.println(oracleDao.insert());
		System.out.println(oracleDao.update());
		System.out.println(oracleDao.delete());
	}
	
	public static void dbwork(MySqlDao oracleDao) {
		System.out.println(oracleDao.select());
		System.out.println(oracleDao.insert());
		System.out.println(oracleDao.update());
		System.out.println(oracleDao.delete());
	}

	
	public static void main(String[] args) {
		dbwork(new OracleDao());
		dbwork(new MySqlDao());
		// 이해안됨
		
		
		
	}
}
