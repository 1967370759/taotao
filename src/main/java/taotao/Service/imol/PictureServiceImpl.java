package taotao.Service.imol;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import taotao.Service.PictureService;
import taotao.Util.ExceptionUtil;
import taotao.Util.FtpUtil;
import taotao.Util.IDUtils;
import taotao.pojo.PictureResult;

import java.io.IOException;

public class PictureServiceImpl implements PictureService {
    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USER_NAME}")
    private String FTP_USER_NAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;
    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;

    @Override
    public PictureResult uploadPicture(MultipartFile uploadFlie) {
        //判断上传图片是否为空
        if (null == uploadFlie || uploadFlie.isEmpty()) {
            return PictureResult.error("上传图片为空");

        }
        //取得文件扩展名
        String originalFilename=uploadFlie.getOriginalFilename();
        String ext=originalFilename.substring(originalFilename.lastIndexOf("."));
                //生成新文件名
        //可以使用uuid生成新文件名
        //UUID.randomUUID();
        //可以是时间+随机数生成的文件名
        String imageName= IDUtils.genlmageName();
        //把图片上传到ftp服务器（图片服务器）
        //需要把ftp的参数配置到配置文件
        //文件在服务器的存放路径，应该使用日期分割的目录结果
        DateTime dateTime=new DateTime();
        String filepath=dateTime.toString("/yyyy/mm/dd");
        try {
            FtpUtil.uploadFile(FTP_ADDRESS,FTP_PORT,FTP_USER_NAME,FTP_PASSWORD,FTP_BASE_PATH,
                    filepath,imageName+ext,uploadFlie.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return PictureResult.error(ExceptionUtil.getStackTrace(e));
        }
        //返回结果，生成一个可以访问到图片的url返回
        return PictureResult.ok(IMAGE_BASE_URL+filepath+"/"+imageName+ext);
    }
}
