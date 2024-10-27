package com.evaluation.source.service.i;

import com.evaluation.source.dto.user.UserDTO;

public interface AuthService {
    String login(UserDTO authRequest);

    // String refresh(String refreshToken);

    // void logout(String refreshToken, User user);
}
