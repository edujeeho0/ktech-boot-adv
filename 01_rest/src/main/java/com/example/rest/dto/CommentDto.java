package com.example.rest.dto;

import com.example.rest.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class CommentDto {
    private Long id;
    @Setter
    private String content;
    @Setter
    private String writer;

    public static CommentDto fromEntity(Comment entity) {
        CommentDto dto = new CommentDto();
        dto.id = entity.getId();
        dto.content = entity.getContent();
        dto.writer = entity.getWriter();
        return dto;
    }
}
