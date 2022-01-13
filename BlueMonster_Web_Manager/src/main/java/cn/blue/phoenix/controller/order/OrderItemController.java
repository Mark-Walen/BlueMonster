package cn.blue.phoenix.controller.order;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.entity.Result;
import cn.blue.phoenix.pojo.order.OrderItem;
import cn.blue.phoenix.service.order.OrderItemService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>Date : 2022年01月13日 22:55</p>
 * <p>Project: BlueMonster</p>
 * <p>Package cn.blue.phoenix.controller.system</p>
 *
 * @author BlueVincent
 * @version V1.0
 */
@RestController
@RequestMapping("/orderItem")
public class OrderItemController {

    @DubboReference
    private OrderItemService orderItemService;

    @GetMapping("/findAll")
    public List<OrderItem> findAll(){
        return orderItemService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<OrderItem> findPage(int page, int size){
        return orderItemService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<OrderItem> findList(@RequestBody Map<String,Object> searchMap){
        return orderItemService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<OrderItem> findPage(@RequestBody Map<String,Object> searchMap, int page, int size){
        return  orderItemService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public OrderItem findById(String id){
        return orderItemService.findById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody OrderItem orderItem){
        orderItemService.add(orderItem);
        return ResponseEntity.ok(new Result(200, "添加成功"));
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody OrderItem orderItem){
        orderItemService.update(orderItem);
        return ResponseEntity.ok(new Result(200, "更新成功"));
    }

    @GetMapping("/delete")
    public ResponseEntity<Result> delete(String id){
        orderItemService.delete(id);
        return ResponseEntity.ok(new Result(200, "删除成功"));
    }
}
