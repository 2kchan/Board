package common;

public class Constants {

	public final static String MEMBER_REGEXP_ID = "^[a-z0-9]{4,20}$";
	public final static String MEMBER_REGEXP_PWD = "^[a-z0-9~!@#$%^&*]{4,20}$";
	public final static String MEMBER_REGEXP_NAME = "^[가-힣]{2,20}$";
	public final static String MEMBER_REGEXP_MOBILE = "^[0-9]{11,11}$";
	public final static String MEMBER_REGEXP_NUMBER = "^[0-9]{1,}$";
	public final static String MEMBER_REGEXP_SK = "^[가-힣a-zA-Z0-9 ]{2,20}$";
	public final static String BOARD_REGEXP_SJ = "^.{1,50}$";
	

	public final static String WRONG_ACCESS = "잘못된 접근";
	public final static String ID_USABLE = "사용할 수 있는 아이디 입니다.";
	public final static String ID_DUPLICATED = "중복된 아이디 입니다.";
	

	public final static String FAIL_JOIN = "회원가입에 실패하였습니다.";
	public final static String CHECK_LOGIN_INFO = "아이디 비밀번호를 확인해 주세요";
	public final static String LOGIN_NEEDED_SERVICE = "로그인이 필요한 서비스 입니다.";
	public final static String CHECK_PASSWORD = "비밀번호를 확인해 주세요.";
	public final static String FAIL_MODIFY_MEMBER = "회원 정보를 수정하는데 실패하였습니다";
	public final static String CANNOT_FIND_ID = "입력하신 정보의 해당하는 아이디를 찾을수 없습니다.";
	public final static String CANNOT_FIND_MEMBER = "입력하신 정보의 해당하는 회원정보를 찾을수 없습니다.";
	public final static String FAIL_MODIFY_PASSWORD = "비밀번호 정보를 수정하는데 실패하였습니다";
	public final static String CANNOT_MODIFY_PASSWORD = "비밀번호를 변경하는데 실패하였습니다";
	public final static String CANNOT_LEAVE = "회원탈퇴에 실패하였습니다";
	public final static String CANNOT_WRITE_ARTICLE = "글을 저장하는데 실패했습니다";
	public final static String CANNOT_MODIFY_ARTICLE = "글을 수정하는데 실패했습니다";
	public final static String CANNOT_DELETE_ARTICLE = "글을 삭제하는데 실패했습니다";
	public final static String CANNOT_GET_ARTICLE = "글 정보가 존재하지 않습니다";
	
	public final static String MEMBER_ALERT_ID = "^[a-z0-9]{4,20}$";
	public final static String MEMBER_ALERT_PWD = "^[a-z0-9]{4,20}$";
	public final static String MEMBER_ALERT_NAME = "^[a-z0-9]{4,20}$";
	public final static String MEMBER_ALERT_MOBILE = "^[a-z0-9]{4,20}$";

}
