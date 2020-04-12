package net.xdclass.xdvideo.service;

import net.xdclass.xdvideo.domain.Video;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface VideoService {
    List<Video> findAll();
    Video findById(int id);
    int update(Video Video);
    int delete(int id);
    int save(Video video);
}
