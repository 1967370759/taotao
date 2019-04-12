package taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import taotao.Service.ItemCatService;
import taotao.pojo.TreeNode;

import java.util.List;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/list")
    @ResponseBody
    public List<TreeNode> getItemCatList(
            @RequestParam(value = "id",defaultValue = "0")Long parentId){
        List<TreeNode> list=itemCatService.getItemCatList(parentId);
        System.out.println("11111");
        return list;
    }
}
