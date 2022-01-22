package cn.blue.phoenix.controller.order;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.entity.Result;
import cn.blue.phoenix.pojo.order.ReturnOrderItem;
import cn.blue.phoenix.service.order.ReturnOrderItemService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/returnOrderItem")
public class ReturnOrderItemController {

    @Reference
    private ReturnOrderItemService returnOrderItemService;

    @GetMapping("/findAll")
    public List<ReturnOrderItem> findAll(){
        return returnOrderItemService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<ReturnOrderItem> findPage(int page, int size){
        return returnOrderItemService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<ReturnOrderItem> findList(@RequestBody Map<String,Object> searchMap){
        return returnOrderItemService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<ReturnOrderItem> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  returnOrderItemService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public ReturnOrderItem findById(Long id){
        return returnOrderItemService.findById(id);
    }


    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody ReturnOrderItem returnOrderItem){
        returnOrderItemService.add(returnOrderItem);
        return ResponseEntity.ok(new Result(200, "添加成功"));
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody ReturnOrderItem returnOrderItem){
        returnOrderItemService.update(returnOrderItem);
        return ResponseEntity.ok(new Result(200, "更新成功"));
    }

    @GetMapping("/delete")
    public ResponseEntity<Result> delete(Long id){
        returnOrderItemService.delete(id);
        return ResponseEntity.ok(new Result(200, "删除成功"));
    }

}
