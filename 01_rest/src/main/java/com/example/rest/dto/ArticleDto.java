package com.example.rest.dto;

import com.example.rest.entity.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class ArticleDto {
    private Long id;
    @Setter
    private String title;
    @Setter
    private String content;
    @Setter
    private String writer;

    // Static Factory Method Pattern
    // 어떤 특정 데이터 (지금은 Entity)를 기준으로
    // 이 클래스의 인스턴스를 만드는 방법을 기록하여
    // 코드의 중복을 줄이는 디자인 패턴
    public static ArticleDto fromEntity(Article entity) {
        ArticleDto dto = new ArticleDto();
        dto.id = entity.getId();
        dto.title = entity.getTitle();
        dto.content = entity.getContent();
        dto.writer = entity.getWriter();
        return dto;
    }
}
