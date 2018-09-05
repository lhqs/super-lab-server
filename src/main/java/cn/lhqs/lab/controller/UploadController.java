package cn.lhqs.lab.controller;

import cn.lhqs.lab.entity.ReturnResult;
import cn.lhqs.lab.utils.QiniuStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-09-02 16:10
 * description : UploadController.class
 * version : 1.0
 */

@RestController
@RequestMapping("api")
public class UploadController {

    private static Logger logger = LoggerFactory.getLogger(UploadController.class);

    /**
     * 上传文件
     * @param file
     * @return
     */
    @PostMapping(value = "/upload/uploadHandle")
    public ReturnResult uploadHandle(@RequestParam("file") MultipartFile file){
        String key = null;
        try {
            if (null != file && file.getBytes().length > 0) {
                String contentType = file.getContentType();
                String fileName = file.getOriginalFilename();
                // System.out.println("contentType: "+ contentType);
                key = QiniuStorage.uploadFile(file.getBytes(),fileName);
                logger.info("the file key --->"+key);
            }
        } catch (IOException e) {
            logger.warn("upload orgLogo fail",e);
            return ReturnResult.fail;
        }
        return new ReturnResult(0,"success", key);
    }
}
