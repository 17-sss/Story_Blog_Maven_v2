package story.db;

import java.util.Date;

public class UserDataBean {
	private int num;
	private String email;
	private String pwd;
	private String name;
	private String tel;
	private String birth;
	private String filename; //프로필 사진
	private int filesize;	// 프로필 용량
	private Date cdate;	// 생성 날짜
	private int p_level; // 회원권한레벨
	private String sort_option; // 갤러리, 메인 사진 정렬 옵션
	private String ip;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getFilesize() {
		return filesize;
	}
	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public int getP_level() {
		return p_level;
	}
	public void setP_level(int p_level) {
		this.p_level = p_level;
	}
	public String getSort_option() {
		return sort_option;
	}
	public void setSort_option(String sort_option) {
		this.sort_option = sort_option;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	@Override
	public String toString() {
		return "UserDataBean [num=" + num + ", email=" + email + ", pwd=" + pwd + ", name=" + name + ", tel=" + tel
				+ ", birth=" + birth + ", filename=" + filename + ", filesize=" + filesize + ", cdate=" + cdate
				+ ", p_level=" + p_level + ", sort_option=" + sort_option + ", ip=" + ip + "]";
	}
	

	
}
