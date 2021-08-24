package myboard.member.dao;

import static common.jdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.MemberVo;

public class MemberDao {
	private Connection con;

	private MemberDao() {

	}

	private static class LazyHolder {
		private static final MemberDao INSTANCE = new MemberDao();
	}

	public static MemberDao getInstance() {
		return MemberDao.LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public int getMemberCountById(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			pstmt = con.prepareStatement("select count(mber_seq) from inf_mber_tb where binary(id)=? and del_fl=?");
			pstmt.setString(1, id);
			pstmt.setBoolean(2, false);
			rs = pstmt.executeQuery();	//select
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return count;
	}
	
	public int joinMember(MemberVo memberVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("insert into inf_mber_tb(id,pwd,nm,moblphon) values(?,?,?,?)");
			pstmt.setString(1, memberVo.getId());
			pstmt.setString(2, memberVo.getPwd());
			pstmt.setString(3, memberVo.getNm());
			pstmt.setString(4, memberVo.getMoblphon());
			count = pstmt.executeUpdate();	// insert delete update
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);

		}
		return count;
	}
	
	public MemberVo getLoginInfo(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo memberVo = null;

		try {
			pstmt = con.prepareStatement("select mber_seq, id, pwd from inf_mber_tb where binary(id)=? and del_fl=?"); //binary = 대소문자 확인
			pstmt.setString(1, id);
			pstmt.setBoolean(2, false);
			rs = pstmt.executeQuery();	//select
			while(rs.next()) {
				memberVo = new MemberVo();
				memberVo.setMber_seq(rs.getInt("mber_seq"));
				memberVo.setId(rs.getString("id"));
				memberVo.setPwd(rs.getString("pwd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return memberVo;
	}
	
	public MemberVo getMemberInfo(int mber_seq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo memberVo = null;

		try {
			pstmt = con.prepareStatement("select mber_seq, id, pwd, nm, moblphon, dttm from inf_mber_tb where mber_seq=? and del_fl=?");
			pstmt.setInt(1, mber_seq);
			pstmt.setBoolean(2, false);
			rs = pstmt.executeQuery();	//select
			while(rs.next()) {
				memberVo = new MemberVo();
				memberVo.setMber_seq(rs.getInt("mber_seq"));
				memberVo.setId(rs.getString("id"));
				memberVo.setPwd(rs.getString("pwd"));
				memberVo.setNm(rs.getString("nm"));
				memberVo.setMoblphon(rs.getString("moblphon"));
				memberVo.setDttm(rs.getString("dttm"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return memberVo;
	}
	
	public int ModifyMember(MemberVo memberVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update inf_mber_tb set nm=?, moblphon=? where mber_seq=?");
			pstmt.setString(1, memberVo.getNm());
			pstmt.setString(2, memberVo.getMoblphon());
			pstmt.setInt(3, memberVo.getMber_seq());
			count = pstmt.executeUpdate();	// insert delete update
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);

		}
		return count;
	}
	
	public String getMemberId(MemberVo memberVo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String id = null;

		try {
			pstmt = con.prepareStatement("select id from inf_mber_tb where nm=? and moblphon=?"); //binary = 대소문자 확인
			pstmt.setString(1, memberVo.getNm());
			pstmt.setString(2, memberVo.getMoblphon());
			rs = pstmt.executeQuery();	//select
			while(rs.next()) {
				id = rs.getString("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return id;
	}
	
	public int getMemberSequence(MemberVo memberVo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int mber_seq = 0;

		try {
			pstmt = con.prepareStatement("select mber_seq from inf_mber_tb where binary(id)=? and nm=? and moblphon=? and del_fl=?");
			pstmt.setString(1, memberVo.getId());
			pstmt.setString(2, memberVo.getNm());
			pstmt.setString(3, memberVo.getMoblphon());
			pstmt.setBoolean(4, false);
			rs = pstmt.executeQuery();	//select
			while(rs.next()) {
				mber_seq = rs.getInt("mber_seq");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return mber_seq;
	}
	
	public int ModifyPasswordResult(MemberVo memberVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update inf_mber_tb set pwd=? where mber_seq=?");
			pstmt.setString(1, memberVo.getPwd());
			pstmt.setInt(2, memberVo.getMber_seq());
			count = pstmt.executeUpdate();	// insert delete update
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);

		}
		return count;
	}
	
	public int LeaveResult(int mber_seq) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update inf_mber_tb set del_fl=? where mber_seq=?");
			pstmt.setBoolean(1, true);
			pstmt.setInt(2, mber_seq);
			count = pstmt.executeUpdate();	// insert delete update
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);

		}
		return count;
	}
	
	public int ModifyPwd(MemberVo memberVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update inf_mber_tb set pwd=? where mber_seq=?");
			pstmt.setString(1, memberVo.getPwd());
			pstmt.setInt(2, memberVo.getMber_seq());
			count = pstmt.executeUpdate();	// insert delete update
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);

		}
		return count;
	}	
}
