package taotao.Service;

import org.springframework.web.multipart.MultipartFile;
import taotao.pojo.PictureResult;

public interface PictureService {
    PictureResult uploadPicture(MultipartFile uploadFlie);
}
