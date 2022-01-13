package cn.blue.phoenix.pojo.order;

import java.io.Serializable;
import java.util.List;

/**
 * @author : BlueVincent
 * @version V1.0
 * @Project: BlueMonster
 * @Package cn.blue.phoenix.pojo.order
 * @date Date : 2022年01月13日 22:14
 */
public class OrderItemDetails implements Serializable {
    private Order order;
    private List<OrderItem> orderItemList;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
