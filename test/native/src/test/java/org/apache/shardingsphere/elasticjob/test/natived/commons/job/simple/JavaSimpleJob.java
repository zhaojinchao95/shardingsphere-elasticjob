/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.elasticjob.test.natived.commons.job.simple;

import org.apache.shardingsphere.elasticjob.spi.executor.item.param.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.apache.shardingsphere.elasticjob.test.natived.commons.entity.Foo;
import org.apache.shardingsphere.elasticjob.test.natived.commons.repository.FooRepository;
import org.apache.shardingsphere.elasticjob.test.natived.commons.repository.FooRepositoryFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class JavaSimpleJob implements SimpleJob {
    
    private final FooRepository fooRepository = FooRepositoryFactory.getFOO_REPOSITORY();
    
    @Override
    public void execute(final ShardingContext shardingContext) {
        System.out.printf(
                "Item: %s | Time: %s | Thread: %s | %s%n",
                shardingContext.getShardingItem(),
                new SimpleDateFormat("HH:mm:ss").format(new Date()),
                Thread.currentThread().getId(),
                "SIMPLE");
        List<Foo> data = fooRepository.findUnfinishedData(shardingContext.getShardingParameter(), 10);
        data.stream().mapToLong(Foo::getId).forEach(fooRepository::setCompleted);
    }
}
