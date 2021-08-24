package vo;

public class MemberVo {
	private int mber_seq;
	private String id;
	private String pwd;
	private String nm;
	private String moblphon;
	private boolean del_fl;
	private String dttm;
	
	public int getMber_seq() {
		return mber_seq;
	}
	public void setMber_seq(int mber_seq) {
		this.mber_seq = mber_seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public String getMoblphon() {
		return moblphon;
	}
	public void setMoblphon(String moblphon) {
		this.moblphon = moblphon;
	}
	public boolean isDel_fl() {
		return del_fl;
	}
	public void setDel_fl(boolean del_fl) {
		this.del_fl = del_fl;
	}
	public String getDttm() {
		return dttm;
	}
	public void setDttm(String dttm) {
		this.dttm = dttm;
	}
	
}
