package com.evaluation.source.service.impl;

import org.springframework.stereotype.Service;

import com.evaluation.source.dto.user.UserDTO;
import com.evaluation.source.model.User;
import com.evaluation.source.repository.UserRepository;
import com.evaluation.source.service.i.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder encoder;
    
    @Override
    public User register(UserDTO registerRequest) {
        User user = userRepository.findByEmail(registerRequest.getEmail()).orElse(null);
        if (user == null) {
            user = mapper.map(registerRequest, User.class);
            user.setPassword(encoder.encode(registerRequest.getPassword()));
            return userRepository.save(user);
        } else {
            throw new RuntimeException("This email is being used by another user");
        }
    }

    @Override
    public void resetPassword(String email) {
        // User user = userRepository.findByEmail(email).orElseThrow(
        //         () -> new RuntimeException("Not found user")
        // );
        // sendEmailResetPassword(user, "00000000");
    }

    // private void sendEmailResetPassword(User user, String password) {
    //     String subject = "BIOCAR - Reset your password, " + user.getFullName();
    //     Map<String, Object> model = new HashMap<>();
    //     String link = domain + resetPasswordVerifyLink + token;
    //     model.put("link", link);
    //     model.put("name", user.getFullName());
    //     emailService.send(subject, user.getEmail(), "email/forgot-password.html", model);
    // }
    
    @Override
    public void changePassword(User currentUser, String oldPassword, String newPassword) {
        if (!encoder.matches(oldPassword, currentUser.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }
        currentUser.setPassword(encoder.encode(newPassword));
        userRepository.save(currentUser);
    }

}
