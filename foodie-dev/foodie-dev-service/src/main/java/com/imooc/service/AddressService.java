package com.imooc.service;

import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.AddressBO;

import java.util.List;

public interface AddressService {

    /**
     * 查询用户收货地址
     * @param userId
     * @return
     */
    public List<UserAddress> queryAll(String userId);

    /**
     * 新增地址
     * @param addressBO
     */
    public void addNewUserAddress(AddressBO addressBO);

    /**
     * 更新地址
     * @param addressBO
     */
    public void updateUserAddress(AddressBO addressBO);

    /**
     * 删除地址
     * @param userId,addressId
     */
    public void deleteUserAddress(String userId, String addressId);

    /**
     * 设置默认地址
     * @param userId,addressId
     */
    public void updateUserAddressToBeDefault(String userId, String addressId);

    /**
     * 查询用户地址
     * @param userId,addressId
     */
    public UserAddress queryUserAddress(String userId, String addressId);

}
