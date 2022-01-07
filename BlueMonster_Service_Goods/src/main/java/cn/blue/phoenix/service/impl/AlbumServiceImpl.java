package cn.blue.phoenix.service.impl;

import cn.blue.phoenix.dao.AlbumMapper;
import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.pojo.goods.Album;
import cn.blue.phoenix.service.goods.AlbumService;
import cn.blue.phoenix.utils.PageHelperUtils;
import com.github.pagehelper.PageHelper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@DubboService(interfaceClass = AlbumService.class)
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumMapper albumMapper;

    private final PageHelperUtils<Album> pageUtils = new PageHelperUtils<>();

    @Override
    public List<Album> findAll() {
        return albumMapper.selectAll();
    }

    @Override
    public PageResult<Album> findPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Album> list = albumMapper.selectAll();

        return new PageResult<>(pageUtils.getTotal(), pageUtils.pageHelperUtils(list, page, size));
    }

    @Override
    public List<Album> findList(Map<String, Object> searchMap) {
        Example example = pageUtils.createExample(searchMap, Album.class);
        return albumMapper.selectByExample(example);
    }

    @Override
    public PageResult<Album> findPage(Map<String, Object> searchMap, Integer page, Integer size) {
        Example example = pageUtils.createExample(searchMap, Album.class);
        PageHelper.startPage(page, size);
        List<Album> list = albumMapper.selectByExample(example);

        return new PageResult<>(pageUtils.getTotal(), pageUtils.pageHelperUtils(list, page, size));
    }

    @Override
    public Album findById(Integer id) {
        return albumMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public void add(Album album) {
        albumMapper.insert(album);
    }

    @Override
    public void update(Album album) {
        albumMapper.updateByPrimaryKeySelective(album);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        albumMapper.deleteByPrimaryKey(id);
    }
}
