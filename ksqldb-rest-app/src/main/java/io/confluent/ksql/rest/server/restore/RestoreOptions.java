/*
 * Copyright 2020 Confluent Inc.
 *
 * Licensed under the Confluent Community License (the "License"); you may not use
 * this file except in compliance with the License.  You may obtain a copy of the
 * License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.ksql.rest.server.restore;

import com.github.rvesse.airline.HelpOption;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import com.github.rvesse.airline.annotations.restrictions.Required;
import io.confluent.ksql.rest.util.OptionsParser;
import java.io.File;
import java.io.IOException;
import javax.inject.Inject;

@Command(name = "ksql-restore-metadata", description = "KSQL Restore Metadata")
public class RestoreOptions {
  // Only here so that the help message generated by Help.help() is accurate
  @Inject
  public HelpOption<?> help;

  @SuppressWarnings("unused") // Accessed via reflection
  @Required
  @Option(
      name = "--config-file",
      description = "A file specifying configs for the KSQL Server, KSQL, "
          + "and its underlying Kafka Streams instance(s). Refer to KSQL "
          + "documentation for a list of available configs.")
  private String configFile;

  @SuppressWarnings("unused") // Accessed via reflection
  @Option(
      name = {"--yes", "-y"},
      description = "Automatic \"yes\" as answer to prompt and run non-interactively.")
  private boolean automaticYes = false;

  @SuppressWarnings("unused") // Accessed via reflection
  @Required
  @Arguments(
      title = "backup-file",
      description = "A file specifying the file that contains the metadata backup.")
  private String backupFile;


  public File getConfigFile() {
    return new File(configFile);
  }

  public File getBackupFile() {
    return new File(backupFile);
  }

  public boolean isAutomaticYes() {
    return automaticYes;
  }

  public static RestoreOptions parse(final String...args) throws IOException {
    return OptionsParser.parse(args, RestoreOptions.class);
  }
}
