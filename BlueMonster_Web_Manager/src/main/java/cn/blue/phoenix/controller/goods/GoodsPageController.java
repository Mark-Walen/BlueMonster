package cn.blue.phoenix.controller.goods;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 静态页面跳转控制处理
 */
@Controller
@RequestMapping("/goods")
public class GoodsPageController {

    @GetMapping("/")
    public String getGoodsList() {
        return "goods/goods";
    }

    @GetMapping("/recovery")
    public String getGoodsRecoveryBin() {
        return "goods/goods-recovery";
    }

    @GetMapping("/save-goods")
    public String saveGoods() {
        return "goods/save-goods";
    }

    @GetMapping("/brand")
    public String getBrand() {
        return "goods/brand";
    }

    @GetMapping("/template")
    public String getTemplate() {
        return "goods/template";
    }

    @GetMapping("/spec")
    public String getSpec() {
        return "goods/spec";
    }

    @GetMapping("/param")
    public String getParam() {
        return "goods/param";
    }

    @GetMapping("/category")
    public String getCategory() {
        return "goods/category";
    }

    @GetMapping("/album")
    public String getAlbum() {
        return "goods/album";
    }

    @GetMapping("/album-list")
    public String getAlbumList() {
        return "goods/album-list";
    }
}
