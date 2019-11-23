package io.itaiit.content.service.impl;

import io.itaiit.common.pojo.EasyUITreeNode;
import io.itaiit.common.pojo.TaotaoResult;
import io.itaiit.content.service.ContentCategoryService;
import io.itaiit.mapper.TbContentCategoryMapper;
import io.itaiit.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<EasyUITreeNode> getContentCategoryList(Long parentId) {

        List<TbContentCategory> contentCategories = tbContentCategoryMapper.getContentCategoryList(parentId);
        List<EasyUITreeNode> easyUITreeNodes = new ArrayList<>();
        for (TbContentCategory contentCategory:
                contentCategories) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(contentCategory.getId());
            node.setText(contentCategory.getName());
            node.setState(contentCategory.getIsParent() ? "closed":"open");
            easyUITreeNodes.add(node);
        }

        return easyUITreeNodes;
    }

    @Override
    public TaotaoResult saveContentCategory(Long parentId, String name) {
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);
        contentCategory.setIsParent(false);
        tbContentCategoryMapper.insertContentCategory(contentCategory);
        TbContentCategory parentNode = tbContentCategoryMapper.selectById(parentId);
        if (!parentNode.getIsParent()){
            parentNode.setIsParent(true);
            tbContentCategoryMapper.updateById(parentNode);
        }
        return TaotaoResult.ok(contentCategory);
    }
}
