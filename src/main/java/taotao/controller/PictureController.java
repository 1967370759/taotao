package taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import taotao.Service.PictureService;
import taotao.pojo.PictureResult;

public class PictureController {
    @Autowired
    private PictureService pictureService;
    @RequestMapping("/pic/upload")
    @ResponseBody
    public PictureResult upload(MultipartFile uploadFile){
        PictureResult result=pictureService.uploadPicture(uploadFile);
        //为了保证功能的兼容性，需要把Result转换成json格式的字符串

        return result;
    }
}
