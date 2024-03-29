package com.somil.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.somil.constants.QueryTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Customer entity class 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer", uniqueConstraints = {@UniqueConstraint(columnNames = { "mobile_number"},name = "uk_mobile_number")})
@EntityListeners(AuditingEntityListener.class)
public class Customer 
	implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Integer customerId;
	
	@NotNull
	@Column(name = "customer_name")
	private String customerName;

	@NotNull
	@Column(name = "mobile_number")
	private String mobileNumber;

	@Column(name = "address")
	private String Address;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "country")
	private String country;

	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@Column(name = "modified_on")
	private Calendar modifiedOn;

	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "created_on")
	private Calendar createdOn;
	
	@Transient
	private QueryTypeEnum customerNameQueryType;

	/*
	 *  To be called before every new customer insertion  
	 */
	@PrePersist
	public void setCreationTime() {
		Calendar currentTime = Calendar.getInstance();
		this.setCreatedOn(currentTime);
		this.setModifiedOn(currentTime);
	}

	/*
	 *  To be called before every customer update  
	 */
	@PreUpdate
	public void setUpdationTime() {
		this.setModifiedOn(Calendar.getInstance());
	}

}