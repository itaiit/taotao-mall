package io.itaiit.mapper;

import io.itaiit.pojo.TbItem;

import java.util.List;

public interface TbItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item
     *
     * @mbg.generated Tue Nov 19 20:33:25 CST 2019
     */
    int insert(TbItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item
     *
     * @mbg.generated Tue Nov 19 20:33:25 CST 2019
     */
    List<TbItem> selectAll();
}