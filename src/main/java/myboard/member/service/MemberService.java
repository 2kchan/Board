package myboard.member.service;

import myboard.member.dao.MemberDao;
import vo.MemberVo;

import java.sql.Connection;

import static common.jdbcUtil.*;

public class MemberService {
	
	public int getMemberCountById(String id) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.getMemberCountById(id);
		close(con);
		return count;
	}
	
	public boolean joinMember(MemberVo memberVo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.joinMember(memberVo);
		boolean isSuccess = false;
		
		if (count > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	
	public MemberVo getLoginInfo(String id) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		MemberVo memberVo = dao.getLoginInfo(id);
		close(con);
		return memberVo;
	}
	
	public MemberVo getMemberInfo(int mber_seq) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		MemberVo memberVo = dao.getMemberInfo(mber_seq);
		close(con);
		return memberVo;
	}
	
	public boolean getModifyMember(MemberVo memberVo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		int count = dao.ModifyMember(memberVo);
		boolean isSuccess = false;
		
		if (count > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	
	public String getMemberId(MemberVo memberVo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		String id = dao.getMemberId(memberVo);
		close(con);
		return id;
	}
	
	public int getMemberSequence(MemberVo memberVo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int mber_seq = dao.getMemberSequence(memberVo);
		close(con);
		return mber_seq;
	}
	
	public boolean getModifyPasswordResult(MemberVo memberVo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		int count = dao.ModifyPasswordResult(memberVo);
		boolean isSuccess = false;
		
		if (count > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	
	public boolean getLeaveResult(int mber_seq) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		int count = dao.LeaveResult(mber_seq);
		boolean isSuccess = false;
		
		if (count > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	
	public boolean getModifyPwd(MemberVo memberVo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		int count = dao.ModifyPwd(memberVo);
		boolean isSuccess = false;
		
		if (count > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	
	
	
}
