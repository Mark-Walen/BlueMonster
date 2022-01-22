package cn.blue.phoenix.controller.order;

import cn.blue.phoenix.service.order.OrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>Date : 2022年01月22日 16:37</p>
 * <p>Project: BlueMonster</p>
 * <p>Package cn.blue.phoenix.controller.order</p>
 *
 * @author BlueVincent
 * @version V1.0
 */
@Component
public class OrderTask {

    @DubboReference
    private OrderService orderService;

    @Scheduled(cron = "0 0/2 * * * ?")
    public void orderTimeOutLogic() {
        System.out.println("每隔两分钟间隔执行一次任务" + new Date());
        orderService.orderTimeoutLogic();
    }
}
