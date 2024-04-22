package learning.project3.service.impl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import learning.project3.dto.PostDto;
import learning.project3.dto.TaskDto;
import learning.project3.entity.Post;
import learning.project3.entity.Task;
import learning.project3.entity.User;
import learning.project3.repository.PostRepository;
import learning.project3.repository.UserRepository;
import learning.project3.service.PostService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    
    @Autowired private UserRepository userRepository ;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PostDto> getAllPosts() {
    	List<Post> posts = postRepository.findAll();
        return posts.stream()
                    .map(post -> modelMapper.map(post, PostDto.class))
                    .collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long id) {
    	Post post = postRepository.findById(id).orElse(null);
    	PostDto postDto = modelMapper.map(post, PostDto.class);
        return postDto;
    }

    @Override
    public PostDto createPost(PostDto postRequest) {
        Post post = new Post();
        User user = userRepository.findById(postRequest.getUserId()).orElse(null);
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        Date currentTime = new Date();
        post.setCreatedAt(currentTime);
        post.setUser(user);
        Post postCreated = postRepository.save(post);
        PostDto postDto = modelMapper.map(postCreated,PostDto.class);
        return postDto ;
    }

    @Override
    public PostDto updatePost(Long id, PostDto postRequest) {
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            post.setTitle(postRequest.getTitle());
            post.setContent(postRequest.getContent());
            post.setCreatedAt(postRequest.getCreatedAt());
            Post postCreated = postRepository.save(post);
            PostDto postDto = modelMapper.map(postCreated,PostDto.class);
            return postDto ;
        }
        return null;
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
    
    @Override
    public List<PostDto> findPostsByUserId(Long id) {
    	List<Post> posts = postRepository.findByUserId(id);
        return posts.stream()
                    .map(post -> modelMapper.map(post, PostDto.class))
                    .collect(Collectors.toList());
    }
}
