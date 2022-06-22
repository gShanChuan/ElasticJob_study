package com.shanchuan.JobBootstrap;

import com.shanchuan.job.classjob.MyDataflowJob;
import com.shanchuan.job.classjob.MySimpleJob;
import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;

public class MyScheduleJobBootstrap {

    public static void main(String[] args) {
        // 调度基于 class 类型的作业
//        new ScheduleJobBootstrap(createRegistryCenter(), new MySimpleJob(), createJobConfiguration()).schedule();
        new ScheduleJobBootstrap(createRegistryCenter(), new MyDataflowJob(), createJobConfiguration()).schedule();
        // 调度基于 type 类型的作业
//        new ScheduleJobBootstrap(createRegistryCenter(), "MY_TYPE", createJobConfiguration()).schedule();
    }

    private static CoordinatorRegistryCenter createRegistryCenter() {
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration("localhost:2181", "elastic-job-demo"));
        regCenter.init();
        return regCenter;
    }

    private static JobConfiguration createJobConfiguration() {
        // 创建作业配置
        JobConfiguration jobConfig = JobConfiguration.newBuilder("myJob-1", 3).cron("0/5 * * * * ?").shardingItemParameters("0=Beijing,1=Shanghai,2=Guangzhou").build();
        return jobConfig;
    }
}
