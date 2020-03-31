package com.muggle.poseidon.utils;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.muggle.poseidon.base.exception.SimplePoseidonException;
import com.muggle.poseidon.constant.DingSendEnum;
import com.taobao.api.ApiException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Description:
 * @Author: muggle
 * @Date: 2020/3/31$
 **/
public class DingUtil {
    private DingUtil() {
        throw new SimplePoseidonException("禁止实例化");
    }

    public static OapiRobotSendResponse sendMessage(String url, DingSendEnum dingSendEnum, String secret) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, ApiException {
        long timestamp = System.currentTimeMillis();
        String key = getKey(timestamp, secret);
        DingTalkClient client = new DefaultDingTalkClient(String.format(url, timestamp, key));
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent("测试文本消息");
        request.setText(text);
        OapiRobotSendResponse response = client.execute(request);
        return response;
    }

    private static String getKey(Long timestamp,String secret) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
        return sign;
    }
}
