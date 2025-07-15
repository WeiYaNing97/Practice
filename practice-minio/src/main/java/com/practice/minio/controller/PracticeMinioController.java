package com.practice.minio.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.practice.common.utils.StringUtils;
import com.practice.minio.config.MinioConfig;
import com.practice.minio.mapper.PracticeMinioMapper;
import com.practice.minio.utils.MinioUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.practice.common.annotation.Log;
import com.practice.common.core.controller.BaseController;
import com.practice.common.core.domain.AjaxResult;
import com.practice.common.enums.BusinessType;
import com.practice.minio.domain.PracticeMinio;
import com.practice.minio.service.IPracticeMinioService;
import com.practice.common.utils.poi.ExcelUtil;
import com.practice.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * minio信息存储路径Controller
 * 
 * @author wyn
 * @date 2025-07-09
 */
@RestController
@RequestMapping("/minio/minio")
public class PracticeMinioController extends BaseController
{

    @Autowired
    private IPracticeMinioService practiceMinioService;


    /**
     * 查询minio信息存储路径列表
     */
    @PreAuthorize("@ss.hasPermi('minio:minio:list')")
    @GetMapping("/list")
    public TableDataInfo list(PracticeMinio practiceMinio)
    {
        startPage();
        List<PracticeMinio> list = practiceMinioService.selectPracticeMinioList(practiceMinio);
        return getDataTable(list);
    }

    /**
     * 导出minio信息存储路径列表
     */
    @PreAuthorize("@ss.hasPermi('minio:minio:export')")
    @Log(title = "minio信息存储路径", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PracticeMinio practiceMinio)
    {
        List<PracticeMinio> list = practiceMinioService.selectPracticeMinioList(practiceMinio);
        ExcelUtil<PracticeMinio> util = new ExcelUtil<PracticeMinio>(PracticeMinio.class);
        util.exportExcel(response, list, "minio信息存储路径数据");
    }

    /**
     * 获取minio信息存储路径详细信息
     */
    @PreAuthorize("@ss.hasPermi('minio:minio:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(practiceMinioService.selectPracticeMinioById(id));
    }

    /**
     * 新增minio信息存储路径
     */
    @PreAuthorize("@ss.hasPermi('minio:minio:add')")
    @Log(title = "minio信息存储路径", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PracticeMinio practiceMinio)
    {
        return toAjax(practiceMinioService.insertPracticeMinio(practiceMinio));
    }

    /**
     * 修改minio信息存储路径
     */
    @PreAuthorize("@ss.hasPermi('minio:minio:edit')")
    @Log(title = "minio信息存储路径", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PracticeMinio practiceMinio)
    {
        return toAjax(practiceMinioService.updatePracticeMinio(practiceMinio));
    }

    /**
     * 删除minio信息存储路径
     */
    @PreAuthorize("@ss.hasPermi('minio:minio:remove')")
    @Log(title = "minio信息存储路径", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(practiceMinioService.deletePracticeMinioByIds(ids));
    }
}
