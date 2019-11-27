package com.imooc.controller;

import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.AddressBO;
import com.imooc.service.AddressService;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.MobileEmailUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@ApiIgnore
@Api(value = "地址相关",tags={"地址相关接口"})
@RestController
@RequestMapping("address")
public class AddressController {

    private final static Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    /**
     * 查询用户收货地址列表
     */
    @ApiOperation(value = "查询用户收货地址列表",notes = "查询用户收货地址列表",httpMethod = "POST")
    @PostMapping("/list")
    public IMOOCJSONResult list(
            @ApiParam(name = "userId",value = "用户id",required = true)
            @RequestParam String userId){

        if (StringUtils.isBlank(userId)){
            return IMOOCJSONResult.errorMsg("");
        }

        List<UserAddress> list = addressService.queryAll(userId);

        return IMOOCJSONResult.ok(list);
    }

    /**
     * 增加收货地址
     */
    @ApiOperation(value = "增加收货地址",notes = "增加收货地址",httpMethod = "POST")
    @PostMapping("/add")
    public IMOOCJSONResult add(

            @RequestBody AddressBO addressBO){

        if (checkAddress(addressBO).getStatus() != 200){
            return IMOOCJSONResult.errorMsg("");
        }

        addressService.addNewUserAddress(addressBO);

        return IMOOCJSONResult.ok();
    }

    /**
     * 更新收货地址
     */
    @ApiOperation(value = "更新收货地址",notes = "更新收货地址",httpMethod = "POST")
    @PostMapping("/update")
    public IMOOCJSONResult update(
            @RequestBody AddressBO addressBO){

        if (checkAddress(addressBO).getStatus() != 200){
            return IMOOCJSONResult.errorMsg("");
        }

        addressService.updateUserAddress(addressBO);

        return IMOOCJSONResult.ok();
    }

    /**
     * 删除收货地址
     */
    @ApiOperation(value = "删除收货地址",notes = "删除收货地址",httpMethod = "POST")
    @PostMapping("/delete")
    public IMOOCJSONResult delete(
            @ApiParam(name = "userId",value = "用户id",required = true)
            @RequestParam String userId,
            @ApiParam(name = "addressId",value = "用户地址id",required = true)
            @RequestParam String addressId){

        if (StringUtils.isBlank(userId)||StringUtils.isBlank(addressId)){
            return IMOOCJSONResult.errorMsg("");
        }

        addressService.deleteUserAddress(userId,addressId);

        return IMOOCJSONResult.ok();
    }

    /**
     * 设置默认收货地址
     */
    @ApiOperation(value = "设置默认收货地址",notes = "设置默认收货地址",httpMethod = "POST")
    @PostMapping("/setDefault")
    public IMOOCJSONResult setDefault(
            @ApiParam(name = "userId",value = "用户id",required = true)
            @RequestParam String userId,
            @ApiParam(name = "addressId",value = "用户地址id",required = true)
            @RequestParam String addressId){

        if (StringUtils.isBlank(userId)||StringUtils.isBlank(addressId)){
            return IMOOCJSONResult.errorMsg("");
        }

        addressService.updateUserAddressToBeDefault(userId,addressId);

        return IMOOCJSONResult.ok();
    }



    private IMOOCJSONResult checkAddress(AddressBO addressBO) {
        String receiver = addressBO.getReceiver();
        if (StringUtils.isBlank(receiver)) {
            return IMOOCJSONResult.errorMsg("收货人不能为空");
        }
        if (receiver.length() > 12) {
            return IMOOCJSONResult.errorMsg("收货人姓名不能太长");
        }

        String mobile = addressBO.getMobile();
        if (StringUtils.isBlank(mobile)) {
            return IMOOCJSONResult.errorMsg("收货人手机号不能为空");
        }
        if (mobile.length() != 11) {
            return IMOOCJSONResult.errorMsg("收货人手机号长度不正确");
        }
        boolean isMobileOk = MobileEmailUtils.checkMobileIsOk(mobile);
        if (!isMobileOk) {
            return IMOOCJSONResult.errorMsg("收货人手机号格式不正确");
        }

        String province = addressBO.getProvince();
        String city = addressBO.getCity();
        String district = addressBO.getDistrict();
        String detail = addressBO.getDetail();
        if (StringUtils.isBlank(province) ||
                StringUtils.isBlank(city) ||
                StringUtils.isBlank(district) ||
                StringUtils.isBlank(detail)) {
            return IMOOCJSONResult.errorMsg("收货地址信息不能为空");
        }

        return IMOOCJSONResult.ok();
    }


}
