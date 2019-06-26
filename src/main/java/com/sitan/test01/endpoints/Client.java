/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sitan.test01.endpoints;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author sidaty
 */
@Entity
@Data
@Builder
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "etat <> 'DELETED'")
@SQLDelete(sql = "UPDATE clients set etat = 'DELETED' where id = ?", check = ResultCheckStyle.COUNT)
@EntityListeners(AuditingEntityListener.class)
public class Client implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private String prenom;
    @Transient
    private Double ca;
    private String etat;
    @Embedded
    private Adresse adresse;
    
    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    
    
    @PrePersist
    public void beforePersist() {
        this.etat = "ACTIVE";
    }

    @PreRemove
    public void beforeDelete() {
        this.etat = "DELETED";
    }

}
