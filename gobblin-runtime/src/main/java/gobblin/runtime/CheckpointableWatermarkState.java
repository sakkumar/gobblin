/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package gobblin.runtime;

import com.google.gson.Gson;

import gobblin.configuration.State;
import gobblin.source.extractor.CheckpointableWatermark;


/**
 * Making {@link CheckpointableWatermark} look like {@link State} so it can be
 * stored in a {@link gobblin.metastore.StateStore}.
 */
public class CheckpointableWatermarkState extends State {

  /**
   * Create a CheckpointableWatermarkState object from a CheckpointableWatermark
   * @param watermark: the checkpointable watermark
   * @param gson: the instance of {@link Gson} to use for serializing the {@param watermark}.
   */
  public CheckpointableWatermarkState(CheckpointableWatermark watermark, Gson gson) {
    super.setProp(watermark.getSource(), gson.toJsonTree(watermark));
    super.setId(watermark.getSource());
  }

  /**
   * Needed for reflection based construction.
   */
  public CheckpointableWatermarkState() {
  }

}
