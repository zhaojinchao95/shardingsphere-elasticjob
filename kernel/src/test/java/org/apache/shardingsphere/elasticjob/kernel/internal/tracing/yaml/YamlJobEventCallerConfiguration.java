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

package org.apache.shardingsphere.elasticjob.kernel.internal.tracing.yaml;

import lombok.Getter;
import lombok.Setter;
import org.apache.shardingsphere.elasticjob.kernel.internal.tracing.api.TracingStorageConfiguration;
import org.apache.shardingsphere.elasticjob.kernel.internal.tracing.fixture.JobEventCaller;
import org.apache.shardingsphere.elasticjob.kernel.internal.tracing.fixture.JobEventCallerConfiguration;

/**
 * YAML JobEventCaller configuration.
 */
@Getter
@Setter
public final class YamlJobEventCallerConfiguration implements YamlTracingStorageConfiguration<JobEventCaller> {
    
    private static final long serialVersionUID = -3152825887223378472L;
    
    private JobEventCaller jobEventCaller;
    
    @Override
    public TracingStorageConfiguration<JobEventCaller> toConfiguration() {
        return new JobEventCallerConfiguration(jobEventCaller);
    }
}