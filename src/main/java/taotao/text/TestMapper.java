package taotao.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import taotao.mapper.TbItemMapper;

@Controller
public class TestMapper {

    @Autowired
    private TbItemMapper itemMapper;


}
