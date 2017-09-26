package com.yqh.test;

import java.io.Serializable;
import java.util.List;

/**
 * ��˵�����û���
 * 
 * @author ����: yinqqianhui
 * @version ����ʱ�䣺2015-4-18 ����12:38:24
 */
public class User implements Serializable {
	private static final long serialVersionUID = -4415990281535582814L;
	private Integer id;
	private String username;
	private String password;
	private String email;
	private String nickname;
	private String tel;
	private String birth;
	private String address;
	private List<Interest> interests;
	
	public List<Interest> getInterests() {
		return interests;
	}

	public void setInterests(List<Interest> interests) {
		this.interests = interests;
	}

	private  List<Habit> habit;
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public List<Habit> getHabit() {
		return habit;
	}

	public void setHabit(List<Habit> habit) {
		this.habit = habit;
	}

	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", email=" + email + ", nickname=" + nickname
				+ ", tel=" + tel + ", birth=" + birth + ", address=" + address
				+ ", interests=" + interests + ", habit=" + habit + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
