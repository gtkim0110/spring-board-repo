package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString

@Table(indexes = {
    @Index(columnList = "content"),
    @Index(columnList = "createdAt"),
    @Index(columnList = "createdBy")
})

@Entity
public class ArticleComment extends AuditingFields{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter @ManyToOne(optional = false) private Article article;
  @Setter @Column(nullable = false, length = 255) private String content;

  protected ArticleComment() {}

  private ArticleComment(Article article, String content) {
    this.article = article;
    this.content = content;
  }

  public static ArticleComment of(Article article, String content) {
    return new ArticleComment(article, content);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    ArticleComment that = (ArticleComment) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}

// protected ArticleComment() 생성자
//jpa 가 객체를 생성할 수 있도록 기본생성자가 필요
//하지만 외부에서 직접 호출하지 못하게 protected 로 제한하여 의도치 않은 사용을막음.
//즉 jpa 내부에서는 사용 가능하지만, 개발자가 직접 new ArticleComment() 는 못하게 막음

// private ArticleComment(Article article, String content) 생성자
// 이 생성자는 내부 로직에서만 사용되도록 private 로 설정
// 생성 로직을 캡슐화하고, 외부에서 이 생성자를 직접 사용하는 대신 정적인 of() 메서드를 사용하게 유도

// public static ArticleComment of(Article article, String content)
// 이건 정적 팩토리 메서드
// 외부에서는 new ArticleComment() 대신 ArticleComment.of() 로 객체 생성

// 이 방식 사용하는 이뉴는 이름을 통해 의미 부여 가능
// 생성 방식 변경이 쉽고 코드 가독성이 올라감.