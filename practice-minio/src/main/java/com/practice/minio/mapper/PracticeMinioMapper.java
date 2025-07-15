package com.practice.minio.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.practice.minio.domain.PracticeMinio;

/**
 * minio信息存储路径Mapper接口
 * 
 * @author wyn
 * @date 2025-07-09
 */
public interface PracticeMinioMapper extends BaseMapper<PracticeMinio>
{
    /**
     * 查询minio信息存储路径
     * 
     * @param id minio信息存储路径主键
     * @return minio信息存储路径
     */
    public PracticeMinio selectPracticeMinioById(Long id);

    /**
     * 查询minio信息存储路径列表
     * 
     * @param practiceMinio minio信息存储路径
     * @return minio信息存储路径集合
     */
    public List<PracticeMinio> selectPracticeMinioList(PracticeMinio practiceMinio);

    /**
     * 新增minio信息存储路径
     * 
     * @param practiceMinio minio信息存储路径
     * @return 结果
     */
    public int insertPracticeMinio(PracticeMinio practiceMinio);

    /**
     * 修改minio信息存储路径
     * 
     * @param practiceMinio minio信息存储路径
     * @return 结果
     */
    public int updatePracticeMinio(PracticeMinio practiceMinio);

    /**
     * 删除minio信息存储路径
     * 
     * @param id minio信息存储路径主键
     * @return 结果
     */
    public int deletePracticeMinioById(Long id);

    /**
     * 批量删除minio信息存储路径
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePracticeMinioByIds(Long[] ids);
}
