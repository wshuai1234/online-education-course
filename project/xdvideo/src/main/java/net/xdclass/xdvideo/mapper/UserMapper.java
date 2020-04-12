package net.xdclass.xdvideo.mapper;

import net.xdclass.xdvideo.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {


    /**
     * search by primary key id
     * @param userId
     * @return
     */
    @Select("select * from user where id = #{id}")
    User findByid(@Param("id") int userId);

    /**
     * search user by openid
     * @param openid
     * @return
     */
    @Select("select * from user where openid = #{openid}")
    User findByopenid(@Param("openid") String openid);


    /**
     * save new user
     * @param user
     * @return
     */
    @Insert("INSERT INTO `user` ( `openid`, `name`, `head_img`, `phone`, `sign`, `sex`, `city`, `create_time`)" +
            "VALUES" +
            "(#{openid},#{name},#{headImg},#{phone},#{sign},#{sex},#{city},#{createTime});")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int save(User user);


}
