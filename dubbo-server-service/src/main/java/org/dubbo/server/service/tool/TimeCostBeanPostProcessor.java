package org.dubbo.server.service.tool;

import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *
 * 统计spring的bean的耗时
 *
 * @author LvSheng
 * @date 2020/2/20
 **/
@Component
public class TimeCostBeanPostProcessor implements BeanPostProcessor {
	
	Map<String, Long> costMap = Maps.newConcurrentMap();
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		costMap.put(beanName, System.currentTimeMillis());
		return bean;
	}
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		long start = costMap.get(beanName);
		long cost  = System.currentTimeMillis() - start;
		if (cost > 0) {
			costMap.put(beanName, cost);
			System.out.println("class: " + bean.getClass().getName()
									   + "\tbean: " + beanName
									   + "\ttime" + cost);
		}
		return bean;
	}
}
