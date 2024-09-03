package io.github.juoliii.excel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author admin
 */
@Data
@AllArgsConstructor
public class ReadObject {
    private Object value;
    private String error;
}
