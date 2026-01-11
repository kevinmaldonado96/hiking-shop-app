package org.example.hickingshop.services.iservices;

import org.example.hickingshop.dto.UserDto;
import org.example.hickingshop.entities.User;

public interface IUserService {

    User createUser(UserDto user);
}
