package com.practice.minio.service.impl;

import java.util.List;
import com.practice.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.practice.minio.mapper.PracticeMinioMapper;
import com.practice.minio.domain.PracticeMinio;
import com.practice.minio.service.IPracticeMinioService;

/**
 * minio信息存储路径Service业务层处理
 * 
 * @author wyn
 * @date 2025-07-09
 */
@Service
public class PracticeMinioServiceImpl implements IPracticeMinioService 
{
    @Autowired
    private PracticeMinioMapper practiceMinioMapper;

    /**
     * 查询minio信息存储路径
     * 
     * @param id minio信息存储路径主键
     * @return minio信息存储路径
     */
    @Override
    public PracticeMinio selectPracticeMinioById(Long id)
    {
        return practiceMinioMapper.selectPracticeMinioById(id);
    }

    /**
     * 查询minio信息存储路径列表
     * 
     * @param practiceMinio minio信息存储路径
     * @return minio信息存储路径
     */
    @Override
    public List<PracticeMinio> selectPracticeMinioList(PracticeMinio practiceMinio)
    {
        return practiceMinioMapper.selectPracticeMinioList(practiceMinio);
    }

    /**
     * 新增minio信息存储路径
     * 
     * @param practiceMinio minio信息存储路径
     * @return 结果
     */
    @Override
    public int insertPracticeMinio(PracticeMinio practiceMinio)
    {
        practiceMinio.setCreateTime(DateUtils.getNowDate());
        return practiceMinioMapper.insertPracticeMinio(practiceMinio);
    }

    /**
     * 修改minio信息存储路径
     * 
     * @param practiceMinio minio信息存储路径
     * @return 结果
     */
    @Override
    public int updatePracticeMinio(PracticeMinio practiceMinio)
    {
        practiceMinio.setUpdateTime(DateUtils.getNowDate());
        return practiceMinioMapper.updatePracticeMinio(practiceMinio);
    }

    /**
     * 批量删除minio信息存储路径
     * 
     * @param ids 需要删除的minio信息存储路径主键
     * @return 结果
     */
    @Override
    public int deletePracticeMinioByIds(Long[] ids)
    {
        return practiceMinioMapper.deletePracticeMinioByIds(ids);
    }

    /**
     * 删除minio信息存储路径信息
     * 
     * @param id minio信息存储路径主键
     * @return 结果
     */
    @Override
    public int deletePracticeMinioById(Long id)
    {
        return practiceMinioMapper.deletePracticeMinioById(id);
    }
}
