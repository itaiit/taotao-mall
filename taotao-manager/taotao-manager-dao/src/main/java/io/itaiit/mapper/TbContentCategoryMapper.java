package io.itaiit.mapper;

import io.itaiit.pojo.TbContentCategory;

import java.util.List;

public interface TbContentCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbg.generated Tue Nov 19 20:33:25 CST 2019
     */
    int insert(TbContentCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbg.generated Tue Nov 19 20:33:25 CST 2019
     */
    List<TbContentCategory> selectAll();
}