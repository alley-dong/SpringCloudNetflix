package com.example.userconsumer2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private UserApi userApi;

    @GetMapping("/alive")
    public String alive(@RequestParam("id") String id){
        return userApi.alive(id);
    }


    /**
     *
     * Consumer是接受用户的请求，用户请求打到了Consumer上
     *
     *
     *
     *
     * 降级：正常情况下 一个请求打到服务器 你应该给我一个结果， 不正常就是直接抛异常  一般就是避免返回友好的错误信息 或者写到MQ 方案有很多。
     *
     * try{
     * 1.向服务方发起请求
     *     1。1 判断是否连接超时
     *              将超时的请求记录到服务里，以供下次别人调用的时候 已知当前服务不可用 直接去调用其他服务
     *     1.2  尝试向其他服务发起请求，
     * 2.向其他服务发起请求 依然没有成功
     *   }catch(Exception e){
     *
     *   降级方案1：避免返回不友好的错误信息
     *              ->  返回一个好看的页面  重试按钮 联系邮箱等
     *   降级方案2：return 另外一个东西； 本来干一个事没有成功  然后我将这事写到MQ里 也算成功了  但是没有那么成功 就是不太成功。
     *   return “客官 稍后再来”;
     *
     *   }
     *
     *
     * 熔断：
     * 针对当前服务请求失败进行计数，当你的失败次数达到阈（yu）值的时候，立即切断针对当前服务的所有请求，throw Excrption。
     *
     * 分为三种情况：
     * 正常的情况请求
     * 不正常的情况不请求
     * 半请求（偶尔试一试） 可以按时间过了几秒发一个请求 试试成不成  不成接着熔断  成了则重置阈值。
     *
     *
     * Hystrix 干的就是这个事！
     */
}
