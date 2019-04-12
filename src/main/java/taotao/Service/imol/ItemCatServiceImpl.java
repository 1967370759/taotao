package taotao.Service.imol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taotao.Service.ItemCatService;
import taotao.mapper.TbItemCatMapper;
import taotao.pojo.TbItemCat;
import taotao.pojo.TbItemCatExample;
import taotao.pojo.TbItemExample;
import taotao.pojo.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类管理
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;
    @Override
    public List<TreeNode> getItemCatList(long parentId) {

       //根据parentId查询分类列表
        TbItemCatExample  example=new TbItemCatExample();
        //创建查询条件
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //根据条件查询
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        List<TreeNode> resultList=new ArrayList<>();
        //把这个列表转换成treeNodelist
        for (TbItemCat tbItemCat:list){
            TreeNode node=new TreeNode(tbItemCat.getId(),tbItemCat.getName(),tbItemCat.getIsParent()?"closed":"open");

            resultList.add(node);

        }
        //返回结果
        return resultList;
    }
}
