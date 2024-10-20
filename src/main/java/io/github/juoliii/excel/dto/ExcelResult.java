package io.github.juoliii.excel.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author admin
 */
@Data
public class ExcelResult<T> {

    private int all;

    private int success;
    private int error;

    private List<String> columns;
    private List<String> errors= Collections.emptyList();

    private List<T> datas=Collections.emptyList();

    public ExcelResult(List<String> errors){
        this.errors=errors;
    }
    public ExcelResult(List<T> datas, List<String> errors){
        this.datas=datas;
        this.errors=errors;
    }


    public static ExcelResult error(String error){
        List<String> errors=new ArrayList<>();
        errors.add(error);
        return new ExcelResult(errors);
    }

}
