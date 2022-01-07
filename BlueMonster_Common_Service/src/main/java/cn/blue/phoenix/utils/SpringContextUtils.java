package cn.blue.phoenix.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Classname SpringContextUtils
 * @Description TODO
 * @Date 2022/1/6 22:25
 * @author by BlueVincent
 */
public class SpringContextUtils  implements BeanFactoryAware {

    private static BeanFactory beanFactory;

    public static <T> T getBeanByGeneric(Class<T> type) {
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        assert wac != null;
        return wac.getBean(type);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        SpringContextUtils.beanFactory = beanFactory;
    }

    /**
     * 根据bean的名称获取相应类型的对象
	 *
     * @param beanName bean的名称
	 * @return Object类型的对象
	 */
    public static Object getBean(String beanName) {
        return beanFactory.getBean(beanName);
    }

    /**
     * 根据bean的Class获取相应类型的对象
     *
     * @param type bean的类型
     * @return Object类型的对象
     */
    public static <T> T getBean(Class<T> type) {
        return beanFactory.getBean(type);
    }
}
