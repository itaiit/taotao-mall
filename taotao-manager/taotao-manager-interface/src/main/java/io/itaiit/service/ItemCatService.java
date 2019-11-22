package io.itaiit.service;

import io.itaiit.common.pojo.EasyUITreeNode;

import java.util.List;

public interface ItemCatService {

    /**
     * 根据parent_id查询结果集
     * @param parentId 父节点的id值
     * @return
     */
    List<EasyUITreeNode> getItemCatList(Long parentId);

}
