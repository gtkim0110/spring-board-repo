package com.example.demo.dto;

import com.example.demo.domain.Article;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.demo.domain.Article}
 */
//@Value
//public class ArticleDto implements Serializable {
//    LocalDateTime createdAt;
//    String createdBy;
//    LocalDateTime modifiedAt;
//    String modifiedBy;
//    String title;
//    String content;
//    String hashtag;
//}


//  생성 시 검증, 전처리, 변환 로직을 넣을 수 있는 구조입니다.

//3. 여러 오버로드 제공 가능
//필요하다면 다양한 파라미터를 받는 of() 메서드를 여러 개 정의할 수 있음

//public static ArticleDto of(String title, String content) {
//    return new ArticleDto(LocalDateTime.now(), "system", title, content, null);
//}
//→ 같은 타입의 DTO를 다양한 방식으로 만들 수 있는 유연함을 줍니다.

public record ArticleDto(
        LocalDateTime createdAt,
        String createdBy,
        String title,
        String content,
        String hashtag
) {
    public static ArticleDto of (LocalDateTime createdAt, String createdBy, String title, String content, String hashtag) {
        return new ArticleDto(createdAt, createdBy, title, content, hashtag);
    }

    public static ArticleDto from(Article entity) {
        return new ArticleDto(
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag()
        );
    }
}