package learning.project3.service;
import java.util.List;

import learning.project3.dto.PostDto;
import learning.project3.entity.Post;

public interface PostService {
    List<PostDto> getAllPosts();
    PostDto getPostById(Long id);
    PostDto createPost(PostDto postRequest);
    PostDto updatePost(Long id, PostDto postRequest);
    void deletePost(Long id);
    List<PostDto> findPostsByUserId(Long id);
}
