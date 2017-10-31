package com.tuberculosis.web.rest;

import com.tuberculosis.auth.Authentication;
import com.tuberculosis.service.common.PropertyService;
import com.tuberculosis.util.DateFormatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping(value = "/rest/upload")
public class ImageUploadController {

    private static Logger logger = LoggerFactory.getLogger(ImageUploadController.class);

    @Autowired
    private PropertyService propertyService;

    @RequestMapping(value="chestImg")
    @Authentication
    @ResponseBody
    public String uploadPluploadFile(@RequestParam("file") MultipartFile multipartFile,HttpServletRequest request,HttpServletResponse response){

        StringBuilder destDir = new StringBuilder();
        StringBuilder imgUrl = new StringBuilder();

        try {
            this.generateDestDirAndImgUrl(destDir, imgUrl, request.getParameter("path"), multipartFile.getOriginalFilename());

            File newFile=new File(destDir.toString());
            multipartFile.transferTo(newFile);
        } catch (IOException e) {
            logger.error("上传胸片错误", e);
        }

        return imgUrl.toString();
    }

    private void generateDestDirAndImgUrl(StringBuilder destDir, StringBuilder imgUrl, String path, String originalFileName) throws IOException{
        String baseDir = propertyService.imageAddress;
        String year = DateFormatUtil.currentDateFormat("yyyy");
        String monthDay = DateFormatUtil.currentDateFormat("MMdd");
        String timestamp = Long.toString(System.currentTimeMillis());
        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
        destDir.append(baseDir).append(File.separator).append(year).append(File.separator)
                .append(monthDay).append(File.separator);

        File destDirFile = new File(destDir.toString());
        if(!destDirFile.exists()){
            if(!destDirFile.mkdirs()) throw new IOException("上传图片时创建目录失败");
        }
        destDir.append(timestamp).append(suffix);

        imgUrl.append(propertyService.imageUrl).append("/").append(year).append("/").append(monthDay).append("/").append(timestamp).append(suffix).toString();
    }
}
