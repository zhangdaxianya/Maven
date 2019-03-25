package net.dgsr.service;

import net.dgsr.comment.ServiceResponse;
import net.dgsr.model.Userinfo;

public interface UserService {

	ServiceResponse<?> addUser(Userinfo userinfo);

}
