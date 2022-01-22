package cn.blue.phoenix.controller.order;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.entity.Result;
import cn.blue.phoenix.pojo.order.ReturnCause;
import cn.blue.phoenix.service.order.ReturnCauseService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/returnCause")
public class ReturnCauseController {

    @DubboReference
    private ReturnCauseService returnCauseService;

    @GetMapping("/findAll")
    public List<ReturnCause> findAll(){
        return returnCauseService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<ReturnCause> findPage(int page, int size){
        return returnCauseService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<ReturnCause> findList(@RequestBody Map<String,Object> searchMap){
        return returnCauseService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<ReturnCause> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  returnCauseService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public ReturnCause findById(Integer id){
        return returnCauseService.findById(id);
    }


    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody ReturnCause returnCause){
        returnCauseService.add(returnCause);
        return ResponseEntity.ok(new Result(200, "添加成功"));
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody ReturnCause returnCause){
        returnCauseService.update(returnCause);
        return ResponseEntity.ok(new Result(200, "更新成功"));
    }

    @GetMapping("/delete")
    public ResponseEntity<Result> delete(Integer id){
        returnCauseService.delete(id);
        return ResponseEntity.ok(new Result(200, "删除成功"));
    }

}
