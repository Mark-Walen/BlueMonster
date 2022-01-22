package cn.blue.phoenix.controller.order;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.entity.Result;
import cn.blue.phoenix.pojo.order.OrderConfig;
import cn.blue.phoenix.service.order.OrderConfigService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orderConfig")
public class OrderConfigController {

    @DubboReference
    private OrderConfigService orderConfigService;

    @GetMapping("/findAll")
    public List<OrderConfig> findAll(){
        return orderConfigService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<OrderConfig> findPage(int page, int size){
        return orderConfigService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<OrderConfig> findList(@RequestBody Map<String,Object> searchMap){
        return orderConfigService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<OrderConfig> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  orderConfigService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public OrderConfig findById(Integer id){
        return orderConfigService.findById(id);
    }


    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody OrderConfig orderConfig){
        orderConfigService.add(orderConfig);
        return ResponseEntity.ok(new Result(200, "添加成功"));
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody OrderConfig orderConfig){
        orderConfigService.update(orderConfig);
        return ResponseEntity.ok(new Result(200, "更新成功"));
    }

    @GetMapping("/delete")
    public ResponseEntity<Result> delete(Integer id){
        orderConfigService.delete(id);
        return ResponseEntity.ok(new Result(200, "删除成功"));
    }

}
