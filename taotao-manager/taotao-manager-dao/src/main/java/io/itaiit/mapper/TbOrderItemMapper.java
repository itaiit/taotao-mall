package io.itaiit.mapper;

import io.itaiit.pojo.TbOrderItem;

import java.util.List;

public interface TbOrderItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_order_item
     *
     * @mbg.generated Tue Nov 19 20:33:25 CST 2019
     */
    int insert(TbOrderItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_order_item
     *
     * @mbg.generated Tue Nov 19 20:33:25 CST 2019
     */
    List<TbOrderItem> selectAll();
}