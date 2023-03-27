package com.idf.sipmlerabbit.domain;

import java.io.Serializable;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Credit implements Serializable {
  private final String id = UUID.randomUUID().toString();
}
