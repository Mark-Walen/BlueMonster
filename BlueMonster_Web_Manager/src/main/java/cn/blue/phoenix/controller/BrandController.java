package cn.blue.phoenix.controller;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.entity.Result;
import cn.blue.phoenix.pojo.goods.Brand;
import cn.blue.phoenix.service.goods.BrandService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @DubboReference
    private BrandService brandService;

    @RequestMapping("/findAll")
    public List<Brand> findAll() {
        return brandService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Brand> findPage(Integer page, Integer size) {
        return brandService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Brand> findList(@RequestBody Map<String, Object> searchMap) {
        return brandService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Brand> findPage(@RequestBody Map<String, Object> searchMap, Integer page, Integer size) {
        System.out.println("page:" + page);
        System.out.println("size:" + size);
        return brandService.findPage(searchMap, page, size);
    }

    @GetMapping("/findById")
    public Brand findById(Integer id) {
        return brandService.findById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody Brand brand) {
        brandService.add(brand);
        return ResponseEntity.ok(new Result(200, "添加成功"));
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody Brand brand) {
        brandService.update(brand);
        return ResponseEntity.ok(new Result(200, "更新成功"));
    }

    @GetMapping("/delete")
    public ResponseEntity<Result> delete(Integer id) {
        brandService.delete(id);
        return ResponseEntity.ok(new Result(200, "删除成功"));
    }
}
