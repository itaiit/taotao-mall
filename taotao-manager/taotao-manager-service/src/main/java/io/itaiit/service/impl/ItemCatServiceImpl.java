package io.itaiit.service.impl;

import io.itaiit.common.pojo.EasyUITreeNode;
import io.itaiit.mapper.TbItemCatMapper;
import io.itaiit.pojo.TbItemCat;
import io.itaiit.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    public List<EasyUITreeNode> getItemCatList(Long parentId) {
        List<TbItemCat> tbItemCats = tbItemCatMapper.selectById(parentId);
        List<EasyUITreeNode> easyUITreeNodes = new ArrayList<>();
        for (TbItemCat cat :
                tbItemCats) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(cat.getId());
            node.setText(cat.getName());
            node.setState(cat.getIsParent()?"closed":"open");
            easyUITreeNodes.add(node);
        }
        return easyUITreeNodes;
    }

}
