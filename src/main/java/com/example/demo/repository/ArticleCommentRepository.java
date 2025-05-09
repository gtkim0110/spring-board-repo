package com.example.demo.repository;

import com.example.demo.domain.Article;
import com.example.demo.domain.ArticleComment;
import com.example.demo.domain.QArticle;
import com.example.demo.domain.QArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>,
        QuerydslBinderCustomizer<QArticleComment>
{
    @Override
    default void customize(QuerydslBindings bindings, QArticleComment articleComment) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(articleComment.article, articleComment.createdBy, articleComment.createdAt);
        bindings.including(articleComment.content);

    }
}
