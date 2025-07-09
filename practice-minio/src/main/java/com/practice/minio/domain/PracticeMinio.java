package com.practice.minio.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.practice.common.annotation.Excel;
import com.practice.common.core.domain.BaseEntity;

/**
 * minio信息存储路径对象 practice_minio
 * 
 * @author wyn
 * @date 2025-07-09
 */
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

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }

    public void setMinioPath(String minioPath) 
    {
        this.minioPath = minioPath;
    }

    public String getMinioPath() 
    {
        return minioPath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fileName", getFileName())
            .append("minioPath", getMinioPath())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
