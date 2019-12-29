package org.dubbo.server.service;

import org.apache.dubbo.common.utils.NetUtils;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.dubbo.server.api.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("exportService")
public class ExportService {
	
	@Autowired
	private RegistryConfig    registry;
	@Autowired
	private ProtocolConfig    protocol;
	@Autowired
	private ApplicationConfig application;
	@Autowired
	private DemoService       demoService;
	
	private ServiceConfig<DemoService> newServiceConfig(String group) {
		ServiceConfig<DemoService> service = new ServiceConfig<DemoService>();
		service.setApplication(application);
		service.setRegistry(registry);
		service.setProtocol(protocol);
		service.setInterface(DemoService.class);
		service.setRef(demoService);
		service.setGroup(group);
		service.setRetries(3);
		service.setTimeout(5000);
		service.setAsync(false);
		return service;
	}
	
	public void export() {
		ServiceConfig<DemoService> service = newServiceConfig("IM-CELL-ACCESS-" + NetUtils.getLocalAddress().getHostAddress() + "-"
																	  + protocol.getPort());
		service.export();
	}
}
