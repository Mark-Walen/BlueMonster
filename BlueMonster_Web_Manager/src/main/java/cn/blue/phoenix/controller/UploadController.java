package cn.blue.phoenix.controller;

import cn.blue.phoenix.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private HttpServletRequest request;

    /**
     *
     * @param file 上传的图片
     * @return 返回上传文件的地址给前端
     */
    @PostMapping("/native")
    public ResponseEntity<Result> nativeUpload(@RequestParam("file")MultipartFile file) {
        String path = request.getSession().getServletContext().getRealPath("/static/img");
        String filePath = path + "/" + file.getOriginalFilename();
        File desFile = new File(filePath);
        if (!desFile.getParentFile().exists()) {
            boolean mkdirs = desFile.mkdirs();
            if (!mkdirs) {
                return new ResponseEntity<>(new Result(503, "文件上传失败，请稍后再试！"), HttpStatus.SERVICE_UNAVAILABLE);
            }
        }
        try {
            file.transferTo(desFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(new Result(200, "http://localhost:19101/static/img"));
    }
}
