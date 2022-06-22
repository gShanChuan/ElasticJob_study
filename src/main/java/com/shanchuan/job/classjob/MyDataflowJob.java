package com.shanchuan.job.classjob;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.dataflow.job.DataflowJob;

import java.util.ArrayList;
import java.util.List;

public class MyDataflowJob implements DataflowJob<Foo> {
    @Override
    public List<Foo> fetchData(ShardingContext shardingContext) {
        switch (shardingContext.getShardingItem()) {
            case 0:
                Foo foo = new Foo("0");
                List<Foo> data = new ArrayList<>();// get data from database by sharding item 0
                data.add(foo);
                return data;
            case 1:
                foo = new Foo("1");
                data = new ArrayList<>();// get data from database by sharding item 1
                data.add(foo);
                return data;
            case 2:
                foo = new Foo("2");
                data = new ArrayList<>();// get data from database by sharding item 2
                data.add(foo);
                return data;
            // case n: ...
            default:
                return null;
        }
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Foo> list) {
        for (Foo foo : list) {
            System.out.println(foo.getName());
        }
    }
}
