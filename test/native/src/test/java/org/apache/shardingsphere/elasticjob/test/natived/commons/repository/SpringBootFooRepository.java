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

package org.apache.shardingsphere.elasticjob.test.natived.commons.repository;

import org.apache.shardingsphere.elasticjob.test.natived.commons.entity.Foo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.LongStream;

@Repository
public class SpringBootFooRepository {
    
    private final Map<Long, Foo> data = new ConcurrentHashMap<>(300, 1);
    
    public SpringBootFooRepository() {
        addData(0L, 100L, "Norddorf");
        addData(100L, 200L, "Bordeaux");
        addData(200L, 300L, "Somerset");
    }
    
    private void addData(final long idFrom, final long idTo, final String location) {
        LongStream.range(idFrom, idTo)
                .forEachOrdered(i -> data.put(i, new Foo(i, location, Foo.Status.UNFINISHED)));
    }
    
    /**
     * Find Unfinished Data.
     * @param location location
     * @param limit limit
     * @return An ordered collection, where the user has precise control over where in the list each element is inserted.
     */
    public List<Foo> findUnfinishedData(final String location, final int limit) {
        List<Foo> result = new ArrayList<>(limit);
        int count = 0;
        for (Map.Entry<Long, Foo> each : data.entrySet()) {
            Foo foo = each.getValue();
            if (foo.getLocation().equals(location) && foo.getStatus() == Foo.Status.UNFINISHED) {
                result.add(foo);
                count++;
                if (count == limit) {
                    break;
                }
            }
        }
        return result;
    }
    
    /**
     * Set completed.
     * @param id id
     */
    public void setCompleted(final long id) {
        data.get(id).setStatus(Foo.Status.COMPLETED);
    }
}
