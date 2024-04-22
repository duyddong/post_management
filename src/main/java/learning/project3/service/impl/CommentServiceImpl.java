package learning.project3.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import learning.project3.dto.CommentDto;
import learning.project3.entity.Comment;
import learning.project3.entity.Post;
import learning.project3.entity.User;
import learning.project3.repository.CommentRepository;
import learning.project3.repository.PostRepository;
import learning.project3.repository.UserRepository;
import learning.project3.service.CommentService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        return modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public CommentDto createComment(CommentDto commentRequest) {
        Comment comment = new Comment();
        User user = userRepository.findById(commentRequest.getUserId()).orElse(null);
        Post post = postRepository.findById(commentRequest.getPostId()).orElse(null);
        comment.setContent(commentRequest.getContent());
        comment.setUser(user);
        comment.setDate(new Date());
        comment.setPost(post);
        Comment createdComment = commentRepository.save(comment);
        return modelMapper.map(createdComment, CommentDto.class);
    }

    @Override
    public CommentDto updateComment(Long id, CommentDto commentRequest) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment != null) {
            comment.setContent(commentRequest.getContent());
            Comment updatedComment = commentRepository.save(comment);
            return modelMapper.map(updatedComment, CommentDto.class);
        }
        return null;
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
    
    @Override
    public List<CommentDto> findCommentsByUserId(Long id) {
        List<Comment> comments = commentRepository.findByUserId(id);
        return comments.stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<CommentDto> findCommentsByPostId(Long id) {
        List<Comment> comments = commentRepository.findByPostId(id);
        return comments.stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
    }
}
