package org.example;

import lombok.Data;

@Data
public class IdKeys {
  public   String gateDbId;
  public String monitorId;


  @Override
  public String toString() {
    return "IdKeys{" +
            "gateDbId='" + gateDbId + '\'' +
            ", monitorId='" + monitorId + '\'' +
            '}';
  }
}
