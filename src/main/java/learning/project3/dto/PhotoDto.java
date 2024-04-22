package learning.project3.dto;

import java.util.Date;
import lombok.Data;

@Data
public class PhotoDto {
    private String url;
    private String description;
    private Date createdAt;
    private Long postId;

}
