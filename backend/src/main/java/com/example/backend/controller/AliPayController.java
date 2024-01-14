package com.example.backend.controller;

import com.alipay.easysdk.factory.Factory;
import com.example.backend.common.Result;
import com.example.backend.config.AliPayConfig;
import com.example.backend.service.AliPayService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;

import javax.annotation.Resource;
import javax.print.attribute.standard.JobOriginatingUserName;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
public class AliPayController {

    @Autowired
    private AliPayService aliPayService;

    //调用支付宝支付
    @GetMapping("/alipay/pay")
    public String pay(String subject, String TradeNo, String totalAmount) {
        AlipayTradePagePayResponse response = null;
        try {
            response = Factory.Payment.Page().pay(subject, TradeNo, totalAmount, "");
        } catch (Exception e) {
            log.error("支付宝付款调用失败，原因：" + e.getMessage());
            
        }
        return response.getBody();
    }
//http://localhost:8081/alipay/pay?subject=测试商品&TradeNo=1&totalAmount=666

    //支付宝回调
    @PostMapping("/alipay/notify")
    public Result notify(HttpServletRequest request) {
        log.info("支付宝回调成功");
        //校验是否成功
        if ("TRADE_SUCCESS".equals(request.getParameter("trade_status"))) {
            //#解析回调数据
            Map<String, String> params = Maps.newHashMap();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
            }
            //订单号
            String tradeNo = params.get("out_trade_no");
            //支付金额
            String gmtPayment = params.get("gmt_payment");
            //支付宝交易凭证号
            String alipayTradeNo = params.get("trade_no");
            //#支付宝验签，防止不法分子伪造请求
            try {
                Boolean flag = Factory.Payment.Common().verifyNotify(params);
                if (Boolean.TRUE.equals(flag)) {
                    log.info("交易名称: " + params.get("subject"));
                    log.info("交易状态: " + params.get("trade_status"));
                    log.info("支付宝交易凭证号: " + params.get("trade_no"));
                    log.info("商户订单号: " + params.get("out_trade_no"));
                    log.info("交易金额: " + params.get("total_amount"));
                    log.info("买家在支付宝唯一id: " + params.get("buyer_id"));
                    log.info("买家付款时间: " + params.get("gmt_payment"));
                    log.info("买家付款金额: " + params.get("buyer_pay_amount"));

                    aliPayService.pay(params.get("out_trade_no"), Double.parseDouble(params.get("total_amount")));

                } else {
                    log.error("异步通知验签失败");
                    return Result.error("fail");
                }

            } catch (Exception e) {
                log.error("异步发生异常{}", e.getMessage());
                return Result.error("fail");
            }
            //#进行一些业务逻辑,如更新订单等操作
        }
        return Result.success();
    }

    //传入订单id和用户名
    @GetMapping("/getPayFeedback")
    public Result getPayFeedback(String id, String username){
        return aliPayService.getPayFeedback(id, username);
    }

    @GetMapping("/getBalance")
    public Result getBalance(String username){
        return Result.success(aliPayService.getBalance(username));
    }


}
