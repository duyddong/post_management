package learning.project3.dto;

import java.util.Date;

import learning.project3.entity.Task;
import lombok.Data;

@Data
public class TaskDto {
	private Long id ;
    private String title;
    private String description;
    private Date deadline;
    private String status;
    private Long userId ;

    
}

