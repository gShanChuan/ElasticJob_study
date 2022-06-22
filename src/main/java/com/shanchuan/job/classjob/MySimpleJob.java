package com.shanchuan.job.classjob;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;

public class MySimpleJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        switch (shardingContext.getShardingItem()) {
            case 0:
                // do something by sharding item 0
                System.out.println("执行case0");
                break;
            case 1:
                // do something by sharding item 1
                System.out.println("执行case1");
                break;
            case 2:
                // do something by sharding item 2
                System.out.println("执行case2");
                break;
            // case n: ...
            default:
                System.out.println("default");
        }
    }
}
