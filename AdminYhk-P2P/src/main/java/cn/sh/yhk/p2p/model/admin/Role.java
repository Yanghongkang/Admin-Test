package cn.sh.yhk.p2p.model.admin;

import java.util.Date;


public class Role {

	private long id;

	private String name;
	private String level;
	private String status;
/*
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "ROLE_RESOURCE", joinColumns = {
			@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "RESOURCE_ID", referencedColumnName = "ID") })
	private List<Resource> resources;*/

	private Date createDate;

	private Date updateDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
/*
	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
*/
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
