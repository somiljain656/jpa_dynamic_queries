package com.somil.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order")
@EntityListeners(AuditingEntityListener.class)
public class Order 	
	implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "order_id")
	private Long orderId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "createdOn")
	private Calendar createdOn;

	/*
	 *  To be called before every new product insertion  
	 */
	@PrePersist
	public void setCreationTime() {
		this.setCreatedOn(Calendar.getInstance());
	}

}
