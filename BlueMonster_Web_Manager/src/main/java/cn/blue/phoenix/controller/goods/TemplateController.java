package cn.blue.phoenix.controller.goods;

import cn.blue.phoenix.entity.PageResult;
import cn.blue.phoenix.entity.Result;
import cn.blue.phoenix.pojo.goods.Template;
import cn.blue.phoenix.service.goods.TemplateService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/template")
public class TemplateController {
    @DubboReference
    private TemplateService templateService;

    @GetMapping("/findAll")
    public List<Template> findAll() {
        return templateService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Template> findPage(Integer page, Integer size) {
        return templateService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Template> findList(@RequestBody Map<String, Object> searchMap) {
        return templateService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Template> findPage(@RequestBody Map<String, Object> searchMap, int page, int size) {
        return templateService.findPage(searchMap, page, size);
    }

    @GetMapping("/findById")
    public Template findById(Integer id) {
        return templateService.findById(id);
    }


    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody Template template) {
        templateService.add(template);
        return ResponseEntity.ok(new Result(200, "添加成功"));
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody Template template) {
        templateService.update(template);
        return ResponseEntity.ok(new Result(200, "更新成功"));
    }

    @GetMapping("/delete")
    public ResponseEntity<Result> delete(Integer id) {
        templateService.delete(id);
        return ResponseEntity.ok(new Result(200, "删除成功"));
    }
}
