package info.idb.crud.service;

import info.idb.crud.dto.Response;
import info.idb.crud.entity.User;

public interface UserService extends BaseService<User, Long> {

    Response<String> login(User user);
}
