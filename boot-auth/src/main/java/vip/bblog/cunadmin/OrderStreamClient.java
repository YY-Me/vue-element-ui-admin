package vip.bblog.cunadmin;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author <a href="zyc199777@gmail.com">Zhu Yichen</a>
 * @version 1.0
 * @date 2019年12月03日 15:45
 * @desc MySource
 */
public interface OrderStreamClient {
    /**
     * 订单监听
     * @return SubscribableChannel
     */
    @Input("orderInput")
    SubscribableChannel orderInput();

    /**
     * 订单发送
     * @return MessageChannel
     */
    @Output("orderOutput")
    MessageChannel orderOutput();

    /**
     * 订单物流
     * @return SubscribableChannel
     */
    @Input("orderLogInput")
    SubscribableChannel orderLogInput();

    /**
     * 订单物流
     * @return MessageChannel
     */
    @Output("orderLogOutput")
    MessageChannel orderLogOutput();

    /**
     * 订单操作日志
     * @return SubscribableChannel
     */
    @Input("orderOperateLogInput")
    SubscribableChannel orderOperateLogInput();

    /**
     * 订单操作日志
     * @return MessageChannel
     */
    @Output("orderOperateLogOutput")
    MessageChannel orderOperateLogOutput();

    /**
     * 物流企业web-socket推送
     * @return MessageChannel
     */
    @Output("socketServiceOutput")
    MessageChannel socketServiceOutput();


    /**
     * 超时订单消费
     * @return R
     */
    @Input("orderTimeOutCancelInput")
    SubscribableChannel orderTimeOutCancelInput();

    /**
     * 超时订单推送到队列
     * @return R
     */
    @Output("orderTimeOutCancelOutput")
    MessageChannel orderTimeOutCancelOutput();

}
