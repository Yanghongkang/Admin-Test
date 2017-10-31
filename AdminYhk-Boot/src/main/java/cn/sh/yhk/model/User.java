package cn.sh.yhk.model;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1462828219830405217L;

	private long id;
	private String name;
	private String password;
	private long age;

	public User() {
		super();
	}

	public User(long id, String name, String password, long age) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.age = age;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public long getAge() {
		return age;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAge(long age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", age=" + age + "]";
	}

}
