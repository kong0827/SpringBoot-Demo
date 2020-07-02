package com.kxj.dao.primary;

import com.kxj.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiangjin.kong
 * @date 2020/7/2 16:14
 * @desc
 */
@Repository
public interface MasterUserDao {

    @Select("select * from user")
    List<User> selectUsers();
}
