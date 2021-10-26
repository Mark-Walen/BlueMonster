package cn.blue.phoenix.controller;


import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.entity.Result;
import cn.blue.phoenix.pojo.goods.Spec;
import cn.blue.phoenix.service.goods.SpecService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/spec")
public class SpecController {
    @DubboReference
    private SpecService specService;

    @GetMapping("/findAll")
    public List<Spec> findAll() {
        return specService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Spec> findPage(Integer page, Integer size) {
        return specService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Spec> findList(@RequestBody Map<String, Object> searchMap) {
        return specService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Spec> findPage(@RequestBody Map<String, Object> searchMap, int page, int size) {
        return specService.findPage(searchMap, page, size);
    }

    @GetMapping("/findById")
    public Spec findById(Integer id) {
        return specService.findById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody Spec spec) {
        specService.add(spec);
        return ResponseEntity.ok(new Result(200, "添加成功"));
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody Spec spec) {
        specService.update(spec);
        return ResponseEntity.ok(new Result(200, "更新成功"));
    }

    @GetMapping("/delete")
    public ResponseEntity<Result> delete(Integer id) {
        specService.delete(id);
        return ResponseEntity.ok(new Result(200, "删除成功"));
    }
}
