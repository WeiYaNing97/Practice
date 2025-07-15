package com.practice.minio.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.practice.common.annotation.Excel;
import com.practice.common.core.domain.BaseEntity;

import java.util.Map;

/**
 * minio信息存储路径对象 practice_minio
 * 
 * @author wyn
 * @date 2025-07-09
 */
@Data
@TableName("practice_minio")
public class PracticeMinio extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 文件名 */
    @Excel(name = "文件名")
    private String fileName;

    /** minio路径 */
    @Excel(name = "minio路径")
    private String minioPath;




    @TableField(exist = false)
    private String searchValue;
    /** 请求参数 */
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> params;
}
