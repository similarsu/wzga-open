package cn.wzga.open.entity.sys;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "sys_dic")
public class SysDic implements Serializable {
	private Long id;
	private String dicType;// 数据类型
	private String typeDesc;// 数据类型描述
	private String dicKey;// 数据键
	private String dicValue;// 数据值
	private Integer sort;
	private SysUser creator;// 创建人
	private Date createDate;// 创建时间
	private SysUser modifyMan;// 修改人
	private Date modifyDate;// 修改时间
	private Integer state = 1;

	@Id
	@SequenceGenerator(name = "s_sys_dic", sequenceName = "s_sys_dic", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_sys_dic")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "dic_key")
	public String getDicKey() {
		return dicKey;
	}

	public void setDicKey(String dicKey) {
		this.dicKey = dicKey;
	}

	@Column(name = "dic_value")
	public String getDicValue() {
		return dicValue;
	}

	public void setDicValue(String dicValue) {
		this.dicValue = dicValue;
	}

	@Column(name = "dic_type")
	public String getDicType() {
		return dicType;
	}

	public void setDicType(String dicType) {
		this.dicType = dicType;
	}

	@Column(name = "type_desc")
	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	@Column(name = "sort")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creator")
	public SysUser getCreator() {
		return creator;
	}

	public void setCreator(SysUser creator) {
		this.creator = creator;
	}

	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "modify_man")
	public SysUser getModifyMan() {
		return modifyMan;
	}

	public void setModifyMan(SysUser modifyMan) {
		this.modifyMan = modifyMan;
	}

	@Column(name = "modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Column(name = "state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
