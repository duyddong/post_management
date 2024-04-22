package learning.project3.service;

import learning.project3.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getAllComments();
    CommentDto getCommentById(Long id);
    CommentDto createComment(CommentDto commentRequest);
    CommentDto updateComment(Long id, CommentDto commentRequest);
    void deleteComment(Long id);
    List<CommentDto> findCommentsByUserId(Long id);
    List<CommentDto> findCommentsByPostId(Long id);
}
