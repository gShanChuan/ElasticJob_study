package com.shanchuan.errorhand;

import com.shanchuan.quickstart.MyJob;
import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.OneOffJobBootstrap;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;

public class Throw {

    public static void main(String[] args) {
        //  定时调度作业
        new ScheduleJobBootstrap(createRegistryCenter(), new ErrorJob(), createScheduleJobConfiguration()).schedule();
        // 一次性调度作业
//        new OneOffJobBootstrap(createRegistryCenter(), new ErrorJob(), createOneOffJobConfiguration()).execute();
    }

    private static JobConfiguration createScheduleJobConfiguration() {
        // 创建定时作业配置， 并且使用抛出异常策略
        return JobConfiguration.newBuilder("myScheduleJob", 3).jobErrorHandlerType("THROW").cron("0/5 * * * * ?").build();
    }

    private static JobConfiguration createOneOffJobConfiguration() {
        // 创建一次性作业配置， 并且使用抛出异常策略
        return JobConfiguration.newBuilder("myOneOffJob", 3).jobErrorHandlerType("THROW").build();
    }

    private static CoordinatorRegistryCenter createRegistryCenter() {
        // 配置注册中心
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration("localhost:2181", "elastic-job-demo-1"));
        regCenter.init();
        return regCenter;
    }
}
