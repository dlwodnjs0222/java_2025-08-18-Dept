package dept.test;

import java.util.List;

import dao.dept.du.DeptDao;
import dto.dept.du.Dept;

public class Testdept {
	public static void main(String[] args) {
		DeptDao dao = new DeptDao();
		Dept dept = dao.selectOne(10);
		System.out.println(dept);
		System.out.println("");
		
		List<Dept> list = dao.selectList();
		for(Dept dto : list) {
			System.out.println(dto);
		}
	}

}
