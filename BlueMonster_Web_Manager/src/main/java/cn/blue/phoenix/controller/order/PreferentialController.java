package cn.blue.phoenix.controller.order;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.entity.Result;
import cn.blue.phoenix.pojo.order.Preferential;
import cn.blue.phoenix.service.order.PreferentialService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/preferential")
public class PreferentialController {

    @Reference
    private PreferentialService preferentialService;

    @GetMapping("/findAll")
    public List<Preferential> findAll(){
        return preferentialService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Preferential> findPage(int page, int size){
        return preferentialService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Preferential> findList(@RequestBody Map<String,Object> searchMap){
        return preferentialService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Preferential> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  preferentialService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Preferential findById(Integer id){
        return preferentialService.findById(id);
    }


    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody Preferential preferential){
        preferentialService.add(preferential);
        return ResponseEntity.ok(new Result(200, "添加成功"));
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody Preferential preferential){
        preferentialService.update(preferential);
        return ResponseEntity.ok(new Result(200, "更新成功"));
    }

    @GetMapping("/delete")
    public ResponseEntity<Result> delete(Integer id){
        preferentialService.delete(id);
        return ResponseEntity.ok(new Result(200, "删除成功"));
    }

}
