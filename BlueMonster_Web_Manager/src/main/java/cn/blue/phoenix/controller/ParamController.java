package cn.blue.phoenix.controller;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.entity.Result;
import cn.blue.phoenix.pojo.goods.Param;
import cn.blue.phoenix.service.goods.ParamService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/param")
public class ParamController {

    @DubboReference
    private ParamService paramService;

    @GetMapping("/findAll")
    public List<Param> findAll() {
        return paramService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Param> findPage(Integer page, Integer size) {
        return paramService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Param> findList(@RequestBody Map<String, Object> searchMap) {
        return paramService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Param> findPage(@RequestBody Map<String, Object> searchMap, int page, int size) {
        return paramService.findPage(searchMap, page, size);
    }

    @GetMapping("/findById")
    public Param findById(Integer id) {
        return paramService.findById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody Param param) {
        paramService.add(param);
        return ResponseEntity.ok(new Result(200, "添加成功"));
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody Param param) {
        paramService.update(param);
        return ResponseEntity.ok(new Result(200, "更新成功"));
    }

    @GetMapping("/delete")
    public ResponseEntity<Result> delete(Integer id) {
        paramService.delete(id);
        return ResponseEntity.ok(new Result(200, "删除成功"));
    }
}
