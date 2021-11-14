package cn.blue.phoenix.controller;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.entity.Result;
import cn.blue.phoenix.pojo.goods.Sku;
import cn.blue.phoenix.service.goods.SkuService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sku")
public class SkuController {
    @DubboReference
    private SkuService skuService;

    @GetMapping("/findAll")
    public List<Sku> findAll() {
        return skuService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Sku> findPage(Integer page, Integer size) {
        return skuService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Sku> findList(@RequestBody Map<String, Object> searchMap) {
        return skuService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Sku> findPage(@RequestBody Map<String, Object> searchMap, int page, int size) {
        return skuService.findPage(searchMap, page, size);
    }

    @GetMapping("/findById")
    public Sku findById(Integer id) {
        return skuService.findById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody Sku sku) {
        skuService.add(sku);
        return ResponseEntity.ok(new Result(200, "添加成功"));
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody Sku sku) {
        skuService.update(sku);
        return ResponseEntity.ok(new Result(200, "更新成功"));
    }

    @GetMapping("/delete")
    public ResponseEntity<Result> delete(Integer id) {
        skuService.delete(id);
        return ResponseEntity.ok(new Result(200, "删除成功"));
    }
}
