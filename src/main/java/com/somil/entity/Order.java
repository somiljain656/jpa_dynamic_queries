package com.somil.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
public class Order 	
	implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "order_id")
	private Integer orderId;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "order_customer_fk"))
	private Customer customer;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "order_product_fk"))
	private Product product;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "created_on")
	private Calendar createdOn;

	/*
	 *  To be called before every new product insertion  
	 */
	@PrePersist
	public void setCreationTime() {
		this.setCreatedOn(Calendar.getInstance());
	}

}
