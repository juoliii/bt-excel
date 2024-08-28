package io.github.juoliii.excel.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author admin
 */
@Data
public class ImportResult<T> {

    private int all;

    private int success;
    private int error;

    private List<String> errors= Collections.emptyList();

    private List<T> datas=Collections.emptyList();

    public ImportResult(List<String> errors){
        this.errors=errors;
    }
    public ImportResult(List<T> datas, List<String> errors){
        this.datas=datas;
        this.errors=errors;
    }


    public static ImportResult error(String error){
        List<String> errors=new ArrayList<>();
        errors.add(error);
        return new ImportResult(errors);
    }

}
