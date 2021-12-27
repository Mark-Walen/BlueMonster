package cn.blue.phoenix.controller.goods;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.entity.Result;
import cn.blue.phoenix.pojo.goods.Goods;
import cn.blue.phoenix.pojo.goods.Spu;
import cn.blue.phoenix.service.goods.SpuService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/spu")
public class SpuController {

    @DubboReference
    private SpuService spuService;

    @GetMapping("/findAll")
    public List<Spu> findAll() {
        return spuService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Spu> findPage(Integer page, Integer size) {
        return spuService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Spu> findList(@RequestBody Map<String, Object> searchMap) {
        return spuService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Spu> findPage(@RequestBody Map<String, Object> searchMap, int page, int size) {
        return spuService.findPage(searchMap, page, size);
    }

    @GetMapping("/findById")
    public Spu findById(String id) {
        return spuService.findById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody Spu spu) {
        spuService.add(spu);
        return ResponseEntity.ok(new Result(200, "添加成功"));
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody Spu spu) {
        spuService.update(spu);
        return ResponseEntity.ok(new Result(200, "更新成功"));
    }

    @GetMapping("/delete")
    public ResponseEntity<Result> delete(String id) {
        spuService.delete(id);
        return ResponseEntity.ok(new Result(200, "删除成功"));
    }

    @PostMapping("/saveGoods")
    public  ResponseEntity<Result> saveGoods(@RequestBody Goods goods) {
        spuService.saveGoods(goods);
        return ResponseEntity.ok(new Result(200, "添加成功"));
    }

    @GetMapping("/findGoodsById")
    public Goods findGoodsById(String id) {
        return spuService.findGoodsById(id);
    }

    @PostMapping("/findRecoveryGoods")
    public PageResult<Goods> findRecoveryGoods(Integer page, Integer size) {
        return spuService.findRecoveryGoods(page, size);
    }

    @GetMapping("/deleteGoodsById")
    public ResponseEntity<Result> deleteGoodsById(String id) {
        spuService.deleteGoods(id);
        return ResponseEntity.ok(new Result(200, "删除成功"));
    }

    @GetMapping("/audit")
    public ResponseEntity<Result> audit(String id, String status, String message) {
        spuService.audit(id, status, message);
        return ResponseEntity.ok(new Result(200, "操作成功"));
    }

    @GetMapping("/pull")
    public ResponseEntity<Result> pull(String id) {
        spuService.pull(id);
        return ResponseEntity.ok(new Result(200, "操作成功"));
    }

    // TODO 商品的批量下架
    // TODO 商品的删除与还原

    @GetMapping("/put")
    public ResponseEntity<Result> put(String id) {
        spuService.put(id);
        return ResponseEntity.ok(new Result(200, "操作成功"));
    }

    @GetMapping("/putMany")
    public ResponseEntity<Result> putMany(String[] ids) {
        int count = spuService.putMany(ids);
        return ResponseEntity.ok(new Result(200, "成功上架" + count + "个商品"));
    }
}
