package org.wcobq.AnkiServ.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class User {
    private Long id;
    private String username;
}
