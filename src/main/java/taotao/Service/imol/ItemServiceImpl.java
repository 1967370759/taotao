package taotao.Service.imol;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taotao.Service.ItemService;

import taotao.mapper.TbItemMapper;
import taotao.pojo.EUDataGridResult;
import taotao.pojo.TbItem;
import taotao.pojo.TbItemExample;
import taotao.pojo.TbItemExample.Criteria;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(long itemId) {
        //TbItem item =itemMaer.selectByPrimaryKey(itemId);
        //添加查询条件
        TbItemExample example = new TbItemExample();

        Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        System.out.println(example);
        List<TbItem> list = itemMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            TbItem item = list.get(0);
            return item;
        }
        return null;
    }

    /**
     * 商品列表查询
     *
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        //查询商品列表
        TbItemExample example = new TbItemExample();
        //分页处理
        PageHelper.startPage(page, rows);
        List<TbItem> list = itemMapper.selectByExample(example);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }


}
