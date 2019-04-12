package taotao.Service;


import taotao.pojo.EUDataGridResult;
import taotao.pojo.TbItem;

public interface ItemService {
    TbItem getItemById(long  itemId);
    //商品列表查询
    EUDataGridResult getItemList(int page,int rows);
}
