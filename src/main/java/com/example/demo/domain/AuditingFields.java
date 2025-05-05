package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditingFields {
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @CreatedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @CreatedBy
  @Column(nullable = false, updatable = false, length = 100)
  private String createdBy;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @LastModifiedDate
  @Column(nullable = false)
  private LocalDateTime modifiedAt;

  @LastModifiedBy
  @Column(nullable = false, length = 100)
  private String modifiedBy;
}

//@EntityListeners(AuditingEntityListener.class)
//@MappedSuperclass

// 얘네가 필요한 이유는 Spring Data JPA 의 감사 ( Auditing ) 기능을 위해 필요한 어노테이션

// @EntityListeners(AuditingEntityListener.class)
// jpa 의 생명주기 이벤트 ( 예 persist, update 등 ) 가 발생할때 AuditingEntityListener 가 자동으로
// @CreatedDate, @LastModifiedDate, @CreatedBy, @LastModifiedBy 등의 값을 채워주게 해줍니다.
// 필요 이유: 이 어노테이션이 없으면, createdAt, modifiedAt 등에 자동으로 값이 들어가지 않습니다

// @MappedSuperclass

// 목적: 이 클래스를 공통 필드를 가진 부모 클래스로 사용하되, 실제로는 테이블을 만들지 않고 자식엔티티에 필드를 상속만함.
// 이 클래스 자체를 @Entity 로 만들면 별도의 테이블이 생성되어버리므로, 상속용으로만 사용할때 @MappedSuperclass 를 사용함