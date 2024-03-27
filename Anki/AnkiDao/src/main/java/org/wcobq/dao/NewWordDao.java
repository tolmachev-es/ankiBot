package org.wcobq.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class NewWordDao {
    private String userId;
    private String word;
}
