package net.xdclass.xdvideo.mapper;

import net.xdclass.xdvideo.domain.VideoOrder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单dao层
 */
@Repository
public interface VideoOrderMapper {


    /**
     * save order, return value include primary key
     * @param videoOrder
     * @return
     */
    @Insert("INSERT INTO `video_order` (`openid`, `out_trade_no`, `state`, `create_time`," +
            " `notify_time`, `total_fee`, `nickname`, `head_img`, `video_id`, `video_title`," +
            " `video_img`, `user_id`, `ip`, `del`)" +
            "VALUES" +
            "(#{openid},#{outTradeNo},#{state},#{createTime},#{notifyTime},#{totalFee}," +
            "#{nickname},#{headImg},#{videoId},#{videoTitle},#{videoImg},#{userId},#{ip},#{del});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insert(VideoOrder videoOrder);


    /**
     * search order by primary key
     * @param id
     * @return
     */
    @Select("select * from video_order where id=#{order_id} and del=0")
    VideoOrder findById(@Param("order_id") int id);


    /**
     * 根retrive order object by order number
     * @param outTradeNo
     * @return
     */
    @Select("select * from video_order where out_trade_no=#{out_trade_no} and del=0")
    VideoOrder findByOutTradeNo(@Param("out_trade_no") String outTradeNo);


    /**
     * logically delete order
     * @param id
     * @param userId
     * @return
     */
    @Update("update video_order set del=0 where id=#{id} and user_id =#{userId}")
    int del(@Param("id") int id, @Param("userId") int userId);


    /**
     * search user's all orders
     * @param userId
     * @return
     */
    @Select("select * from video_order where user_id =#{userId}")
    List<VideoOrder> findMyOrderList(int userId);


    /**
     * update by order number
     * @param videoOrder
     * @return
     */
    @Update("update video_order set state=#{state}, notify_time=#{notifyTime}, openid=#{openid}" +
            " where out_trade_no=#{outTradeNo} and state=0 and del=0")
    int updateVideoOderByOutTradeNo(VideoOrder videoOrder);


}
