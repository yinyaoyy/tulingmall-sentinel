package com.tuling.tulingmall.feignapi.factory;

import com.tuling.tulingmall.common.api.CommonResult;
import com.tuling.tulingmall.domain.PortalMemberInfo;
import com.tuling.tulingmall.feignapi.ums.UmsMemberFeignApi;
import com.tuling.tulingmall.model.UmsMember;
import com.tuling.tulingmall.model.UmsMemberReceiveAddress;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Fox
 */
@Component
public class UmsMemberFeginFallbackFactory implements FallbackFactory<UmsMemberFeignApi> {
    
    @Override
    public UmsMemberFeignApi create(Throwable throwable) {
        
        return new UmsMemberFeignApi() {
            @Override
            public CommonResult<UmsMemberReceiveAddress> getItem(Long id) {
                //TODO 业务降级
                UmsMemberReceiveAddress defaultAddress = new UmsMemberReceiveAddress();
                defaultAddress.setName("默认地址");
                defaultAddress.setId(-1L);
                defaultAddress.setDefaultStatus(0);
                defaultAddress.setPostCode("-1");
                defaultAddress.setProvince("默认省份");
                defaultAddress.setCity("默认city");
                defaultAddress.setRegion("默认region");
                defaultAddress.setDetailAddress("默认详情地址");
                defaultAddress.setMemberId(-1L);
                defaultAddress.setPhoneNumber("199xxxxxx");
                return CommonResult.success(defaultAddress);
            }
            
            @Override
            public CommonResult<String> updateUmsMember(UmsMember umsMember) {
                return null;
            }
            
            @Override
            public CommonResult<PortalMemberInfo> getMemberById() {
                return null;
            }
            
            @Override
            public CommonResult<List<UmsMemberReceiveAddress>> list() {
                return null;
            }
        };
    }
    
    
}
