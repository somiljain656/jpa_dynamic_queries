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
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.somil.constants.QueryTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class Product 
	implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer productId;
	
	@NotNull
	@Column(name = "product_name")
	private String productName;

	@NotNull
	@Column(name = "product_price")
	private Double productPrice;

	@Column(name = "description")
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@Column(name = "modified_on")
	private Calendar modifiedOn;

	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "created_on")
	private Calendar createdOn;
	
	@Transient
	private QueryTypeEnum priceQueryType;

	/*
	 *  To be called before every new product insertion  
	 */
	@PrePersist
	public void setCreationTime() {
		Calendar currentTime = Calendar.getInstance();
		this.setCreatedOn(currentTime);
		this.setModifiedOn(currentTime);
	}

	/*
	 *  To be called before every product update
	 */
	@PreUpdate
	public void setUpdationTime() {
		this.setModifiedOn(Calendar.getInstance());
	}
}