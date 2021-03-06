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

package com.netflix.iceberg;

import java.util.List;

/**
 * A snapshot of the data in a table at a point in time.
 * <p>
 * A snapshot consist of one or more file manifests, and the complete table contents is the union
 * of all the data files in those manifests.
 * <p>
 * Snapshots are created by table operations, like {@link AppendFiles} and {@link RewriteFiles}.
 */
public interface Snapshot extends Filterable<FilteredSnapshot> {
  /**
   * Return this snapshot's ID.
   *
   * @return a long ID
   */
  long snapshotId();

  /**
   * Return this snapshot's parent ID or null.
   *
   * @return a long ID for this snapshot's parent, or null if it has no parent
   */
  Long parentId();

  /**
   * Return this snapshot's timestamp.
   * <p>
   * This timestamp is the same as those produced by {@link System#currentTimeMillis()}.
   *
   * @return a long timestamp in milliseconds
   */
  long timestampMillis();

  /**
   * Return the location of all manifests in this snapshot.
   * <p>
   * The current table is made of the union of the data files in these manifests.
   *
   * @return a list of fully-qualified manifest locations
   */
  List<String> manifests();

  /**
   * Return all files added to the table in this snapshot.
   * <p>
   * The files returned include the following columns: file_path, file_format, partition,
   * record_count, and file_size_in_bytes. Other columns will be null.
   *
   * @return all files added to the table in this snapshot.
   */
  Iterable<DataFile> addedFiles();

  /**
   * Return all files deleted from the table in this snapshot.
   * <p>
   * The files returned include the following columns: file_path, file_format, partition,
   * record_count, and file_size_in_bytes. Other columns will be null.
   *
   * @return all files deleted from the table in this snapshot.
   */
  Iterable<DataFile> deletedFiles();
}
