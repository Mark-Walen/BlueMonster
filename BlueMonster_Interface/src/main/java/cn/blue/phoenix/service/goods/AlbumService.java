package cn.blue.phoenix.service.goods;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.goods.Album;

import java.util.List;
import java.util.Map;

public interface AlbumService {
    List<Album> findAll();

    PageResult<Album> findPage(Integer page, Integer size);

    List<Album> findList(Map<String, Object> searchMap);

    PageResult<Album> findPage(Map<String, Object> searchMap, Integer page, Integer size);

    Album findById(Integer id);

    void add(Album album);

    void update(Album album);

    void delete(Integer id);
}
