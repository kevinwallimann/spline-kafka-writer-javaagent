/*
 * Copyright 2022 ABSA Group Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package za.co.absa.spline.javaagent;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaWriterOnCompletionMatcher {
    private final static ElementMatcher<MethodDescription> onCompletionMatcher = ElementMatchers
            .named("onCompletion")
            .and(ElementMatchers.takesArguments(2))
            .and(ElementMatchers.takesArgument(0, RecordMetadata.class))
            .and(ElementMatchers.takesArgument(1, Exception.class));
//            .and(ElementMatchers.returns(void.class))
//            .and(ElementMatchers.isPublic())
//            .and(ElementMatchers.isOverriddenFrom(Callback.class));

    public static ElementMatcher<MethodDescription> getOnCompletionMatcher() {
        return onCompletionMatcher;
    }
}
