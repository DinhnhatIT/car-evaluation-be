package com.evaluation.source.service.i;

import com.evaluation.source.dto.user.UserDTO;
import com.evaluation.source.model.User;

public interface UserService {

    User register(UserDTO registerRequest);

    void resetPassword(String email);

    // void updateProfile(User currentUser, String password);

    // void sendVerificationEmail(User user, String confirmToken);

    void changePassword(User currentUser, String oldPassword, String newPassword);
}
