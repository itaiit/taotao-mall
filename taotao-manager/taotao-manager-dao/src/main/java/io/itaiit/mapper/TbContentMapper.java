package io.itaiit.mapper;

import io.itaiit.pojo.TbContent;

import java.util.List;

public interface TbContentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content
     *
     * @mbg.generated Tue Nov 19 20:33:25 CST 2019
     */
    int insert(TbContent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content
     *
     * @mbg.generated Tue Nov 19 20:33:25 CST 2019
     */
    List<TbContent> selectAll();
}