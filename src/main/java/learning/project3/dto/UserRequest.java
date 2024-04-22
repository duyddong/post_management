package learning.project3.dto;

import lombok.Data;

@Data
public class UserRequest {
//    private Long id;
    private String username;
    private String password ;
    private String email;
    private String role;

}

