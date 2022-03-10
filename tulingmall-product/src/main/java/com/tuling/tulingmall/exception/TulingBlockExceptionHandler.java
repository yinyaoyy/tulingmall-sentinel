package com.tuling.tulingmall.exception;


import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.DefaultBlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuling.tulingmall.common.api.CommonResult;
import com.tuling.tulingmall.common.api.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import org.springframework.web.server.ServerWebExchange;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @author Fox
 *
 */
@Component
public class TulingBlockExceptionHandler extends DefaultBlockExceptionHandler {

    private static final String DEFAULT_BLOCK_MSG_PREFIX = "Blocked by Sentinel: ";
    
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        CommonResult result = null;
        //返回json数据
        response.setStatus(500);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getWriter(), buildErrorResult(e));
    }
    
    
    private CommonResult buildErrorResult(Throwable ex) {
        if (ex instanceof DegradeException) {
            return CommonResult.failed(ResultCode.BACKGROUD_DEGRADE_ERROR);
        }else{
            return CommonResult.failed(ResultCode.TOMANY_REQUEST_ERROR);
        }
    }


}
