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

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;

import java.lang.instrument.Instrumentation;

public class Agent {
    public static void load(String args, Instrumentation inst) {
        System.out.println("Spline Kafka Writer Java Agent loaded");
        new AgentBuilder.Default()
                .type(KafkaWriterOnCompletionMatcher.getTypeMatcher())
                .transform((builder, typeDescription, classLoader, javaModule) ->
                        builder.visit(Advice.to(KafkaWriterOnCompletionAdvice.class)
                                .on(KafkaWriterOnCompletionMatcher.getMethodMatcher())))
                .installOn(inst);
    }
}
