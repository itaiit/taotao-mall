package io.itaiit.content.service;

import io.itaiit.common.pojo.EasyUIDataGridResult;
import io.itaiit.common.pojo.TaotaoResult;
import io.itaiit.pojo.TbContent;

public interface ContentService {

    EasyUIDataGridResult getContentList(Integer page, Integer rows, Long categoryId);

    TaotaoResult saveContent(TbContent content);

}
