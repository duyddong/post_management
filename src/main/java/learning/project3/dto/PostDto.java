package learning.project3.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class PostDto {
	private Long id;
    private String title;
    private String content;
    private Date createdAt;
    private Long userId;
    private List<CommentDto> comments;

}

