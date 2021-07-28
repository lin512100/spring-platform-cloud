package com.platform.model.entity.basic;

import com.platform.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件信息
 * @author lin512100
 * @since 2021-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysFile extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 业务类型
     */
    private String bizCode;

    /**
     * 存放地址
     */
    private String fileUrl;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件格式后缀
     */
    private String fileSuffix;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 文件Hash值
     */
    private String fileMd5;


    public static final String BIZ_CODE = "biz_code";

    public static final String FILE_URL = "file_url";

    public static final String FILE_NAME = "file_name";

    public static final String FILE_SUFFIX = "file_suffix";

    public static final String FILE_SIZE = "file_size";

    public static final String FILE_HASH = "file_md5";

}
