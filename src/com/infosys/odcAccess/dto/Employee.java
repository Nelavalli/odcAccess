package com.infosys.odcAccess.dto;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.infosys.odcAccess.controller.EmployeeController;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="EMPLOYEE")
@Audited
@JsonIgnoreProperties(ignoreUnknown=true)
public class Employee implements Serializable{

	private static final long serialVersionUID = 453693552059515150L;
	private static final Logger log = LoggerFactory.getLogger(Employee.class);
	
	@Id
	@Getter @Setter @JsonProperty @Column(name="EMPLOYEE_ID")
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_SEQ")
	//@SequenceGenerator(name = "EMPLOYEE_SEQ", sequenceName = "EMPLOYEE_SEQ", allocationSize = 1, initialValue = 1)	
	private Long id;
	
	@Getter @Setter @JsonProperty @Column (name="FIRSTNAME")
	private String firstName;
	
	@Getter @Setter @JsonProperty @Column (name="MIDDLENAME")
	private String middleName;
	
	@Getter @Setter @JsonProperty @Column (name="LASTNAME", nullable = false, length = 100)
	private String lastName;
	
	@Getter @Setter @JsonProperty @Column (name="SALUTATION")
	private String salutation;	
	
	@Getter @Setter @JsonProperty @Column (name="LOGINNAME")
	private String loginName;
	
	@Getter @Setter @JsonProperty @Column (name="LOGINPASSWORD")
	private String loginPassword;
	
	@Getter @Setter @JsonProperty @Column (name="PHONENUMBER")
	private String phoneNumber;	
	
	@Getter @Setter @JsonProperty @Column (name="ADMINFLAG")
	private Boolean adminFlag;
	
	/*
	@OneToMany(cascade = CascadeType.ALL)
	 @JoinTable(
			 name = "EMPLOYEE_DC",
	         joinColumns = @JoinColumn(table="EMPLOYEE",name = "EMPLOYEE_ID", referencedColumnName="ID"),
	         inverseJoinColumns = {
	        		 @JoinColumn(table="EMPLOYEE_DC_PRIVILEGE", name = "DC_ID", referencedColumnName="DC_ID"),
	         }
	 )
	@Getter @Setter @JsonProperty
	private List<EmployeeDcPrivilege> employeeDcPrivileges;
	*/
	
	/* @JoinTable(
			 name = "EMPLOYEE_DC_X",
	         joinColumns = @JoinColumn(table="EMPLOYEE",name = "EMPLOYEE_ID", referencedColumnName="EMPLOYEE_ID"),
	         inverseJoinColumns = {
	        		 @JoinColumn(table="EMPLOYE_DC", name = "EMPLOYEE_DC_ID", referencedColumnName="EMPLOYEE_DC_ID"),
	         }
	 )
	 */
	@OneToMany(cascade=CascadeType.ALL, mappedBy="employeeId") 
	@Getter @Setter @JsonProperty
	private List<EmployeeDevCenter> dcs;
	
	
	/*
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	 @JoinTable(
			 name = "EMPLOYEE_DC_PRIVILEGE",
	         joinColumns = @JoinColumn(table="EMPLOYEE",name = "EMPLOYEE_ID", referencedColumnName="ID"),	        		 
	         inverseJoinColumns = @JoinColumn(table="EMPLOYEE",name = "DC_ID")
	 )
	@Getter @Setter 
	private Set<EmployeeDcPrivilege> employeeDcPrivileges;
	*/
	
	@Getter @Setter @Column (name="DELETEFLAG")
	private Boolean deleteFlag;
	
	@Getter @Setter @Column (name="LASTMODIFIEDBY")
	private String lastModifiedBy;
	
	@Temporal(TemporalType.DATE)
	@Getter @Setter @Column (name="LASTMODIFIED")
	private Date lastModified;
	
	@Override
	public boolean equals(Object employee){
		try { 
			if (null == employee) return false;
			if (this == employee) return true;
			if (this.id == ((Employee)employee).id){
				return true;
			} else return false;
			
		} catch(Throwable t) {
			t.printStackTrace();
			return false;
		}
	}
	
	@Override
	public String toString(){
		try { 
			StringBuffer sb = new StringBuffer();
			sb.append("  id:"+id+"; firstName:"+firstName+";  middleName:"+middleName+";  lastName:"+lastName);
			sb.append(";  salutation:"+salutation+";  loginName:"+loginName+";  loginPassword:"+loginPassword);
			sb.append(";  phoneNumber:"+phoneNumber+";  adminFlag:"+adminFlag);
			log.debug(sb.toString());
			return sb.toString();
		} catch(Throwable t) {
			t.printStackTrace();
			return "";
		}
	}
			 
}
