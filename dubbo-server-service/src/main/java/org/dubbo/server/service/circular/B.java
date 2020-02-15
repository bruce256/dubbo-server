package org.dubbo.server.service.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @author LvSheng
 * @date 2020/2/9
 **/
@DependsOn("a")
public class B {
	
	@Autowired
	private A a;
}
