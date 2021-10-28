package cn.blue.phoenix.controller;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.entity.Result;
import cn.blue.phoenix.pojo.goods.Album;
import cn.blue.phoenix.service.goods.AlbumService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @DubboReference
    private AlbumService albumService;

    @GetMapping("/findAll")
    public List<Album> findAll() {
        return albumService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Album> findPage(Integer page, Integer size) {
        return albumService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Album> findList(@RequestBody Map<String, Object> searchMap) {
        return albumService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Album> findPage(@RequestBody Map<String, Object> searchMap, int page, int size) {
        return albumService.findPage(searchMap, page, size);
    }

    @GetMapping("/findById")
    public Album findById(Integer id) {
        return albumService.findById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody Album template) {
        albumService.add(template);
        return ResponseEntity.ok(new Result(200, "添加成功"));
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody Album template) {
        albumService.update(template);
        return ResponseEntity.ok(new Result(200, "更新成功"));
    }

    @GetMapping("/delete")
    public ResponseEntity<Result> delete(Integer id) {
        albumService.delete(id);
        return ResponseEntity.ok(new Result(200, "删除成功"));
    }
}
