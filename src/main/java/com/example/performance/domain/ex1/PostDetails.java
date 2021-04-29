package com.example.performance.domain.ex1;

import lombok.Data;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "post_details")
public class PostDetails {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "created_on")
    private Date createdOn = new Date();

    @Column(name = "created_by")
    private String createdBy;

    @OneToOne(fetch = FetchType.LAZY)
//    @LazyToOne(LazyToOneOption.FALSE)
//    @MapsId
    @JoinColumn(name = "id")
    private Post post;
}
