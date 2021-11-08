package cn.blue.phoenix.controller;

import cn.blue.phoenix.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private HttpServletRequest request;

//    // 在开发过程中要保留，因为 git push 前会对war包进行清除后，上传至github
    private final String local = "D:/MarkWalen/BlueMonster/BlueMonster_Web_Manager/src/main/webapp/static/img";

    /**
     *
     * @param file 上传的图片
     * @return 返回上传文件的地址给前端
     */
    @PostMapping("/native")
    public ResponseEntity<Result> nativeUpload(@RequestPart MultipartFile file) {
        String path = request.getSession().getServletContext().getRealPath("/static/img");
        String filePath = path + "/" + file.getOriginalFilename();
//        String localPath = local + "/" + file.getOriginalFilename();
        File desFile = new File(filePath);
//        File localFile = new File(localPath);
        if (!desFile.getParentFile().exists()) {
            boolean mkdirs = desFile.mkdirs();
            if (!mkdirs) {
                throw new RuntimeException("文件上传失败，请稍后再试！");
            }
        }
        try {
            boolean fileExist = true;
            if (!desFile.exists()) {
                file.transferTo(desFile);
                fileExist = false;
            }
//            if (!localFile.exists()) {
//                file.transferTo(localFile);
//                fileExist = false;
//            }
            if (fileExist) return new ResponseEntity<>(new Result(503, "文件已存在或已有同名文件存在"), HttpStatus.SERVICE_UNAVAILABLE);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Result(503, "http://localhost:19101/static/img/" + file.getOriginalFilename()), HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestParam String fileName) {
        String path = request.getSession().getServletContext().getRealPath("/static/img");
        String filePath = path + "/" + fileName;
//        String localPath = this.local + "/" + fileName;
        System.out.println(filePath);
//        System.out.println(localPath);
        File desFile = new File(filePath);
//        File localFile = new File(localPath);
        if (!desFile.exists() && !desFile.isFile() && !desFile.delete()) {
            return new ResponseEntity<>(new Result(503, "文件不存在或文件已被删除"), HttpStatus.SERVICE_UNAVAILABLE);
        }
        /*if (!localFile.exists() && !localFile.isFile() && !localFile.delete()) {
            return new ResponseEntity<>(new Result(503, "文件不存在或文件已被删除"), HttpStatus.SERVICE_UNAVAILABLE);
        }*/
        return new ResponseEntity<>(new Result(200, "文件删除成功"), HttpStatus.OK);
    }
}
