package com.somil.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
	@GeneratedValue
	@Column(name = "product_id")
	private Long productId;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "product_price")
	private Double productPrice;
	
	@Column(name = "description")
	private String description;
	
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
	@Column(name = "modifiedOn")
    private Calendar modifiedOn;
	
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
	@Column(name = "createdOn")
    private Calendar createdOn;
    
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