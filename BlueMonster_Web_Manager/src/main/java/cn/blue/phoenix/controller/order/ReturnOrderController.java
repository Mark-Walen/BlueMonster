package cn.blue.phoenix.controller.order;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.entity.Result;
import cn.blue.phoenix.pojo.order.ReturnOrder;
import cn.blue.phoenix.service.order.ReturnOrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/returnOrder")
public class ReturnOrderController {

    @DubboReference
    private ReturnOrderService returnOrderService;

    @GetMapping("/findAll")
    public List<ReturnOrder> findAll(){
        return returnOrderService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<ReturnOrder> findPage(int page, int size){
        return returnOrderService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<ReturnOrder> findList(@RequestBody Map<String,Object> searchMap){
        return returnOrderService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<ReturnOrder> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  returnOrderService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public ReturnOrder findById(Long id){
        return returnOrderService.findById(id);
    }


    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody ReturnOrder returnOrder){
        returnOrderService.add(returnOrder);
        return ResponseEntity.ok(new Result(200, "添加成功"));
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody ReturnOrder returnOrder){
        returnOrderService.update(returnOrder);
        return ResponseEntity.ok(new Result(200, "更新成功"));
    }

    @GetMapping("/delete")
    public ResponseEntity<Result> delete(Long id){
        returnOrderService.delete(id);
        return ResponseEntity.ok(new Result(200, "删除成功"));
    }

    public ResponseEntity<Result> rejectRefund(String id, String remark) {
        // TODO 由于队 spring security 不熟悉，所以获取 id 这部分不做了。
        Integer adminId = 0;
        returnOrderService.rejectRefund(id, remark, adminId);

        return ResponseEntity.ok(new Result(200, "删除成功"));
    }
}
