package cn.blue.phoenix.controller.order;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.entity.Result;
import cn.blue.phoenix.pojo.order.Order;
import cn.blue.phoenix.pojo.order.OrderItemDetails;
import cn.blue.phoenix.service.order.OrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>Date : 2022年01月13日 22:44</p>
 * <p>Project: BlueMonster</p>
 * <p>Package cn.blue.phoenix.controller</p>
 *
 * @author BlueVincent
 * @version V1.0
 */

@RestController
@RequestMapping("/order")
public class OrderController {

    @DubboReference
    private OrderService orderService;

    @GetMapping("/findAll")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Order> findPage(int page, int size) {
        return orderService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Order> findList(@RequestBody Map<String, Object> searchMap) {
        return orderService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Order> findPage(@RequestBody Map<String, Object> searchMap, Integer page, Integer size) {
        return orderService.findPage(searchMap, page, size);
    }

    @GetMapping("/findById")
    public Order findById(String id) {
        return orderService.findById(id);
    }

    @GetMapping("/findOrderDetailsById")
    public OrderItemDetails findOrderDetailsById(String id) {
        return orderService.findOrderDetailsById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody Order order) {
        orderService.add(order);
        return ResponseEntity.ok(new Result(200, "添加成功"));
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody Order order) {
        orderService.update(order);
        return ResponseEntity.ok(new Result(200, "更新成功"));
    }

    @GetMapping("/delete")
    public ResponseEntity<Result> delete(String id) {
        orderService.delete(id);
        return ResponseEntity.ok(new Result(200, "删除成功"));
    }

    @PostMapping("/batchSend")
    public ResponseEntity<Result> batchSend(@RequestBody List<Order> orders) {
        orderService.batchSend(orders);
        return ResponseEntity.ok(new Result(200, "批量发货成功"));
    }
}
