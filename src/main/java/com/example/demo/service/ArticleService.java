package com.example.demo.service;

import com.example.demo.domain.Article;
import com.example.demo.domain.type.SearchType;
import com.example.demo.dto.ArticleDto;
import com.example.demo.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

//@RequiredArgsConstructor
@Service
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;

    // lombok 없이 하면 생성자 만들어야함.
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    // 굳이 여기에 Transactional 을 거는 이유
    // 트랜잭션은 단순히 쓰기 일때만 필요한게 아님.
    // 여러 select 가 연속적으로 실행될때, 누군가 중간에 데이터를 수정하면 데이터가 엉킬수있으므로,
    // 트랜잭션을 걸면, 조회되는 동안에는 일관된 데이터를 조회할수있음.
    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String keyword, Pageable pageable) {

        if(keyword == null || keyword.isBlank()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }

        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContaining(keyword,pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(keyword,pageable).map(ArticleDto::from);
        };
    }

    // articleRepository.findAll(pageable).map(ArticleDto::from)
    // articleRepository.findAll(pageable) 이건 Article 객체를 리턴함.
    // .map(ArticleDto::from) 이 부분에서 Article 객체를 ArticleDto 로 변환한다.

}

// Spring은 @Service 빈을 생성할 때 생성자 주입(Constructor Injection) 을 사용
// @RequiredArgsConstructor가 없으면 생성자가 존재하지 않아서 의존성 주입에 실패합니다.
// 그 결과, 애플리케이션이 시작할 때 NoSuchMethodException, UnsatisfiedDependencyException 같은 에러가 발생합니다.

// Lombok 이 없으면 직접 생성자 주입해야함
