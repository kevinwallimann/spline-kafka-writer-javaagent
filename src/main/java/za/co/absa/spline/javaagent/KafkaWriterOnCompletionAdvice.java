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

import net.bytebuddy.asm.Advice;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaWriterOnCompletionAdvice {

    @Advice.OnMethodEnter
    public static void enter(@Advice.Origin String origin, @Advice.AllArguments() Object[] args) {
        if (origin.contains("org.apache.spark.sql.kafka010.KafkaRowWriter")) {
            RecordMetadata recordMetadata = (RecordMetadata) args[0];
            System.out.println("RecordMetadata:" + recordMetadata);
        }
    }
}
