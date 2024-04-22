package learning.project3.service;
//import com.ey.springboot3security.entity.UserInfo;
//import com.ey.springboot3security.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import learning.project3.repository.UserRepository;
import learning.project3.dto.UserRequest;
import learning.project3.entity.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetail = repository.findByUsername(username);
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String addUser(UserRequest userInfo) {
    	User user = new User();
    	user.setUsername(userInfo.getUsername());
    	user.setPassword(encoder.encode(userInfo.getPassword()));
        user.setEmail(userInfo.getEmail());
        user.setRole(userInfo.getRole());
        repository.save(user);
        return "User Added Successfully";
    }
    
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    
    public User getUserById(Long id) {
        return repository.findById(id).orElse(null);
    }

    
    public User createUser(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setEmail(userRequest.getEmail());
        user.setRole(userRequest.getRole());
        return repository.save(user);
    }

    
    public User updateUser(Long id, UserRequest userRequest) {
        User user = repository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(userRequest.getUsername());
            user.setPassword(userRequest.getPassword());
            user.setEmail(userRequest.getEmail());
            user.setRole(userRequest.getRole());
            return repository.save(user);
        }
        return null;
    }

    
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
