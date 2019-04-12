package taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import taotao.Service.ItemService;
import taotao.pojo.EUDataGridResult;
import taotao.pojo.TbItem;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;


    @RequestMapping("/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId) {
        TbItem tbItem = itemService.getItemById(itemId);
        System.out.println(tbItem);
        return tbItem;
    }

    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getItemList( Integer page,  Integer rows) {
        System.out.println("111111111");
        EUDataGridResult itemById = itemService.getItemList(page, rows);
        return itemById;
    }


}
