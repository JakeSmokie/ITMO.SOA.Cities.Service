package ru.jakesmokie.cities;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.niws.loadbalancer.DiscoveryEnabledNIWSServerList;
import com.netflix.niws.loadbalancer.NIWSDiscoveryPing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class SampleRibbonConfiguration {

  @Autowired
  IClientConfig ribbonClientConfig;

  @Bean
  public IPing ribbonPing(IClientConfig config) {
    return new NIWSDiscoveryPing();
  }

  @Bean
  public IRule ribbonRule(IClientConfig config) {
    return new RandomRule();
  }

  @Bean
  public DiscoveryEnabledNIWSServerList ribbonServerList(IClientConfig config) {
    return new DiscoveryEnabledNIWSServerList();
  }
}