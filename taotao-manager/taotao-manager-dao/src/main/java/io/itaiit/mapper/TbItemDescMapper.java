package io.itaiit.mapper;

import io.itaiit.pojo.TbItemDesc;

import java.util.List;

public interface TbItemDescMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_desc
     *
     * @mbg.generated Tue Nov 19 20:33:25 CST 2019
     */
    int insert(TbItemDesc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_desc
     *
     * @mbg.generated Tue Nov 19 20:33:25 CST 2019
     */
    List<TbItemDesc> selectAll();
}