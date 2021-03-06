package com.platform.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.platform.common.annotation.AutoDictFieldValue;
import com.platform.common.exception.SystemErrorCode;
import com.platform.common.utils.SpringBeanUtils;
import com.platform.common.utils.ValidateUtils;
import com.platform.model.dto.user.AccountAssociateDto;
import com.platform.model.dto.user.SysAccountDto;
import com.platform.model.dto.user.SysUserRoleDto;
import com.platform.model.entity.user.*;
import com.platform.model.vo.OauthUserVo;
import com.platform.model.vo.user.SysAccountVo;
import com.platform.user.mapper.SysAccountMapper;
import com.platform.user.service.*;
import com.platform.mybatis.service.BaseServiceImpl;
import com.platform.mybatis.utils.PageVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 账户信息 服务实现类
 * @author lin512100
 * @since 2021-07-22
 */
@Service
public class SysAccountServiceImpl extends BaseServiceImpl<SysAccountMapper, SysAccount> implements SysAccountService {

    @Resource
    private SysUserService userService;
    @Resource
    private SysUserRoleService userRoleService;
    @Resource
    private SysRolePermissionService rolePermissionService;
    @Resource
    private SysPermissionOperationService permissionOperationService;
    @Resource
    private SysOperationService operationService;

    @Override
    public Long register(SysAccountDto dto) {
        SysAccount entity = toEntity(dto);
        ValidateUtils.isTrue(entity.getId() != null, SystemErrorCode.PARAM_ERROR, SysAccount.ID);
        this.getBaseMapper().insert(entity);
        return entity.getId();
    }

    @Override
    public void associate(AccountAssociateDto dto) {
        ValidateUtils.isTrue(dto.getAssociatedAccountId() == null, "被关联账户不能为空");
        ValidateUtils.isTrue(dto.getAccountId() == null, "关联账户不能为空");

        SysAccount associatedAccount = baseMapper.selectById(dto.getAssociatedAccountId());
        ValidateUtils.isTrue(associatedAccount == null, "被关联账户信息不存在");

        if (associatedAccount.getUserId() != null) {
            ValidateUtils.isTrue(userService.getById(associatedAccount.getUserId()) != null, "被关联账户已实名,请联系管理员进行处理");
        }

        SysAccount account = baseMapper.selectById(dto.getAccountId());
        ValidateUtils.isTrue(account == null, "关联账户信息不存在");
        ValidateUtils.isTrue(account.getUserId() == null, "请先进行实名认证");

        associatedAccount.setUserId(account.getUserId());
        baseMapper.updateById(associatedAccount);

    }

    @Override
    public void del(SysAccountDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysAccount.ID);
        SysAccount one = baseMapper.selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "账户信息");
        this.baseMapper.deleteById(one.getId());
    }

    @Override
    public void modify(SysAccountDto dto) {
        ValidateUtils.noEmpty(dto.getId(), SysAccount.ID);
        SysAccount entity = toEntity(dto);
        // 判断信息是否存在
        SysAccount one = this.getBaseMapper().selectById(dto.getId());
        ValidateUtils.noEmpty(one, SystemErrorCode.DATA_ERROR_NONE, "账户信息");
        BeanUtils.copyProperties(entity, one, SysAccount.ID, SysAccount.USER_ID, SysAccount.ACC_NAME);
        this.getBaseMapper().updateById(one);
    }

    @Override
    public PageVo<SysAccountVo> list(SysAccountDto dto) {
        ValidateUtils.isTrue(dto.getPageNo() == null || dto.getPageSize() == null, "分页参数");
        Page<SysAccount> page = PageMethod.startPage(dto.getPageNo(), dto.getPageSize()).doSelectPage(
                () -> this.queryByParams(toEntity(dto)));
        return new PageVo<>(page.getPageSize(), page.getPageNum(), page.getTotal(), assembleDataList(page.getResult()));
    }

    @Override
    public List<SysAccountVo> assembleDataList(List<SysAccount> dataList) {
        if (CollectionUtils.isEmpty(dataList)) {
            return new ArrayList<>();
        }
        return dataList.stream().map(item -> SpringBeanUtils.getBean(SysAccountService.class).toVo(item)).collect(Collectors.toList());
    }

    @Override
    public SysAccount toEntity(SysAccountDto dto) {
        SysAccount entity = new SysAccount();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    @AutoDictFieldValue
    public SysAccountVo toVo(SysAccount entity) {
        SysAccountVo vo = new SysAccountVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    @Override
    public OauthUserVo getOauthUserVo(String username) {
        OauthUserVo oauthUserVo = new OauthUserVo();
        // 默认权限为空
        oauthUserVo.setGrantedAuthorityList(new ArrayList<>());

        ValidateUtils.noEmpty(username, SysAccount.ACC_NAME);
        LambdaQueryWrapper<SysAccount> accountQuery = new LambdaQueryWrapper<>();
        accountQuery.eq(SysAccount::getAccName, username);
        // 查询账户信息
        SysAccount account = baseMapper.selectOne(accountQuery);
        ValidateUtils.isTrue(account == null, "账户信息不存在");
        oauthUserVo.setUsername(username);
        oauthUserVo.setPassword(account.getAccPwd());
        oauthUserVo.setChannel(account.getChannel());

        // 查询用户信息
        SysUser user = userService.getBaseMapper().selectById(account.getUserId());
        ValidateUtils.noEmpty(user , "用户信息");

        // 查询角色信息
        SysUserRoleDto userRoleDto = new SysUserRoleDto();
        userRoleDto.setUserId(user.getId());
        List<SysUserRole> userRoles = userRoleService.queryByParams(new SysUserRole());
        List<Long> roleIds = userRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(roleIds)) {
            return oauthUserVo;
        }

        // 查询权限信息
        LambdaQueryWrapper<SysRolePermission> roleQuery = new LambdaQueryWrapper<>();
        roleQuery.in(SysRolePermission::getRoleId, roleIds);
        List<SysRolePermission> rolePermissions = rolePermissionService.getBaseMapper().selectList(roleQuery);
        List<Long> permissionIds = rolePermissions.stream().map(SysRolePermission::getPermissionId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(permissionIds)) {
            return oauthUserVo;
        }

        // 查询操作权限
        LambdaQueryWrapper<SysPermissionOperation> permissionQuery = new LambdaQueryWrapper<>();
        permissionQuery.in(SysPermissionOperation::getPermissionId, permissionIds);
        List<SysPermissionOperation> permissionOperations = permissionOperationService.getBaseMapper().selectList(permissionQuery);
        List<Long> operationIds = permissionOperations.stream().map(SysPermissionOperation::getOperationId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(operationIds)) {
            return oauthUserVo;
        }

        // 查询权限信息
        LambdaQueryWrapper<SysOperation> operationQuery = new LambdaQueryWrapper<>();
        operationQuery.in(SysOperation::getId, operationIds);
        List<SysOperation> operations = operationService.getBaseMapper().selectList(operationQuery);
        List<String> operationFunc = operations.stream().map(SysOperation::getOperationFunc).collect(Collectors.toList());
        oauthUserVo.getGrantedAuthorityList().addAll(operationFunc);

        return oauthUserVo;
    }

}
