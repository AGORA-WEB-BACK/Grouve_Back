package agora.webproject.service;

import agora.webproject.domain.User;
import agora.webproject.dto.UserDTO;

public interface UserRegisterService {
    User saveEntity(User user);

    User saveDTO(UserDTO userDTO);

    boolean isEmailExist(String email);

    boolean isUsernameExist(String username);
}
