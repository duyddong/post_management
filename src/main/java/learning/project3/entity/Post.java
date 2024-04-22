package learning.project3.entity;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    // Relationships with User and Comment entities
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
    // Other fields, getters and setters
}

