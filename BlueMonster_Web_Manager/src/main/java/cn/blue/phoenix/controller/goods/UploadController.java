package cn.blue.phoenix.controller.goods;

import cn.blue.phoenix.entity.Result;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

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
    public ResponseEntity<Result> nativeUpload(@RequestPart MultipartFile file) {
        String path = request.getSession().getServletContext().getRealPath("/static/img");
        String fileName = file.getOriginalFilename();
        String filePath = path + "/" + fileName;
        File desFile = new File(filePath);
        if (!desFile.getParentFile().exists()) {
            boolean mkdirs = desFile.mkdirs();
            if (!mkdirs) {
                throw new RuntimeException("文件上传失败，请稍后再试！");
            }
        }
        try {
            // 解决文件存在 或 同名问题
            if (desFile.exists()) {
                String uuid = UUID.randomUUID().toString().replace("-", "");
                assert fileName != null;
                String extName = fileName.substring(fileName.lastIndexOf("."));
                fileName = uuid + RandomStringUtils.randomAlphanumeric(5) + extName;
                filePath = path + "/" + fileName;
                desFile = new File(filePath);
            }
            file.transferTo(desFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Result(200, "http://localhost:19101/static/img/" + fileName), HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestParam String fileName) {
        String path = request.getSession().getServletContext().getRealPath("/static/img");
        String filePath = path + "/" + fileName;
        File desFile = new File(filePath);
        if (desFile.exists() && desFile.isFile() && desFile.delete()) {
            return new ResponseEntity<>(new Result(200, "文件删除成功"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Result(503, "文件不存在或文件已被删除"), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
