/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gobblin.records;

import java.util.function.Function;

import gobblin.source.extractor.RecordEnvelope;

import io.reactivex.Flowable;
import lombok.Data;


/**
 * A stream of records along with metadata (e.g. Schema).
 * @param <D> type of record.
 * @param <S> type of schema.
 */
@Data
public class RecordStreamWithMetadata<D, S> {
  private final Flowable<RecordEnvelope<D>> recordStream;
  private final S schema;

  /**
   * @return a new {@link RecordStreamWithMetadata} with a different {@link #recordStream} but same schema.
   */
  public <DO> RecordStreamWithMetadata<DO, S> withRecordStream(Flowable<RecordEnvelope<DO>> newRecordStream) {
    return withRecordStream(newRecordStream, this.schema);
  }

  /**
   * @return a new {@link RecordStreamWithMetadata} with a different {@link #recordStream} and {@link #schema}.
   */
  public <DO, SO> RecordStreamWithMetadata<DO, SO> withRecordStream(Flowable<RecordEnvelope<DO>> newRecordStream, SO newSchema) {
    return new RecordStreamWithMetadata<>(newRecordStream, newSchema);
  }

  /**
   * @return a new {@link RecordStreamWithMetadata} with a different {@link #recordStream} but same schema using a
   * lambda expression on the stream.
   */
  public <DO> RecordStreamWithMetadata<DO, S>
      mapStream(Function<? super Flowable<RecordEnvelope<D>>, ? extends Flowable<RecordEnvelope<DO>>> transform) {
    return new RecordStreamWithMetadata<>(transform.apply(this.recordStream), this.schema);
  }
}
