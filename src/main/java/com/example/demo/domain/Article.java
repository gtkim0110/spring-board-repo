package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter // 접근 가능하게 ? 왜 lombok 에 Getter 써야하는지 확인해보기.
@ToString
@Table(indexes = {
    @Index(columnList = "title"),
    @Index(columnList = "hashtag"),
    @Index(columnList = "createdAt"),
    @Index(columnList = "createdBy"),
})
@Entity
//@RequiredArgsConstructor
public class Article extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter @Column(nullable = false) private String title;
  @Setter @Column(nullable = false, length = 10000) private String content;
  @Setter @Column private String hashtag;

  @ToString.Exclude
  @OrderBy("id")
  @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
  private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

  protected Article() {}

  private Article(String title, String content, String hashtag) {
    this.title = title;
    this.content = content;
    this.hashtag = hashtag;
  }

  public static Article of(String title, String content, String hashtag) {
    return new Article(title,content,hashtag);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Article article = (Article) o;
    return Objects.equals(id, article.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}

//Setter 를 특정 필드에만 주는이유는 임의로 변경 불가능하게 하기위함. jpa 영속성 지키기위함?? 이건 좀더확인