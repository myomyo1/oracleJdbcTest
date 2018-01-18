package pro04;

import java.util.List;

public class MemberApp {

	public static List<BookVo> mList;
	public static MemberDao dao = new MemberDao();

	public static void main(String[] args) {

		// 김상명추가
		BookVo vo = new BookVo();
		vo.setEmail("kim@bit.ac.kr");
		vo.setName("김비트");
		vo.setGender("남");
		vo.setPassword("12345");

		dao.insertMember(vo);

		printAllMemebrList();

		// 이상명추가
		vo.setEmail("lee@bit.ac.kr");
		vo.setName("이비트");
		vo.setGender("여");
		vo.setPassword("12345");

		dao.insertMember(vo);

		// 현재 멤버 리스트 출력
		printAllMemebrList();

		// 이상명 비밀번호 변경(이메일 주소 찾아서 비밀번호만 변경되어야함
		vo.setEmail("lee@bit.ac.kr");
		vo.setName("");
		vo.setGender("");
		vo.setPassword("54321");

		dao.updatePassword(vo);
		// 현재 멤버 리스트 출력
		printAllMemebrList();

		// 김상명 삭제
		dao.deleteMember("kim@bit.ac.kr");
		// 김상명 삭제
		printAllMemebrList();

	}

	public static void printAllMemebrList() {
		System.out.println("*****현재  멤버 리스트  *****");

		// 출력코드작성
		List<BookVo> m = dao.getListAll();
		for (BookVo mvo : m) {
			System.out.println(mvo);
		}

	}

}
