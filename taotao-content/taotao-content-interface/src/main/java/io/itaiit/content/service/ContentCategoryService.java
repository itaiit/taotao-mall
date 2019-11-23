package io.itaiit.content.service;

import io.itaiit.common.pojo.EasyUITreeNode;
import io.itaiit.common.pojo.TaotaoResult;

import java.util.List;

public interface ContentCategoryService {

    List<EasyUITreeNode> getContentCategoryList(Long parentId);

    TaotaoResult saveContentCategory(Long parentId, String name);
}
