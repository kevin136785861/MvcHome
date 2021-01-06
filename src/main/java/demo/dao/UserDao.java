package demo.dao;


import demo.domain.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {
    User selectUserById(int id);
    List<User> selectUserByAddrAndSexA(@Param("user") User user);
    List<User> selectUserBySomeAddr(@Param("addresses") String[] addresses);
    List<User>  selectUserLikeName(String name);
    void insertUser(@Param("user")User user);
    List<User> selectAllUser();

    @MapKey("id")
    Map<Integer,User> selectAllMapUser();
    void insertUserList(@Param("list")List<User> list);

}
