package io.github.juoliii.excel.util;

import com.aspose.cells.Cells;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.bitian.common.dto.BaseForm;
import com.bitian.common.dto.ExportParams;
import com.bitian.common.exception.CustomException;
import com.bitian.common.util.PrimaryKeyUtil;
import io.github.juoliii.excel.dto.ImportResult;
import io.github.juoliii.excel.dto.ReadResult;
import io.github.juoliii.excel.mapping.ReadClassMapping;
import io.github.juoliii.excel.mapping.ReadColumnMapping;
import io.github.juoliii.excel.mapping.WriteDefaultWriteFieldMapping;
import io.github.juoliii.excel.mapping.WriteFieldMapping;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author admin
 */
public class ExcelUtil {

    public static <T> ImportResult<T> readExcel(InputStream inputStream, Class<T> cls) throws Exception {
        return readExcel(inputStream,cls,1);
    }

    public static <T> ImportResult<T> readExcel(InputStream inputStream, Class<T> cls, int line) throws Exception {
        Workbook workbook=new Workbook(inputStream);
        Worksheet worksheet=workbook.getWorksheets().get(0);
        Cells cells=worksheet.getCells();
        return readExcel(cells,new ReadClassMapping(cls,cells),line);
    }

    public static <T> ImportResult<T> readExcel(Cells cells, ReadColumnMapping mapping, int line ){
        //检查行数够不够
        List<String> errors=new ArrayList<>();
        List<T> datas=new ArrayList<>();
        ImportResult<T> result=new ImportResult<>(datas,errors);
        if(cells.getMaxRow()+1<line){
            return ImportResult.error("没有可导入数据");
        }
        try {
            List<String> columns=new ArrayList<>();
            for (int i = 0; i <=cells.getMaxColumn(); i++) {
                columns.add(cells.get(0,i).getStringValue().trim());
            }
            int allNum=0;
            for (int i = line; i <=cells.getMaxDataRow(); i++) {
                allNum++;
                ReadResult readResult=mapping.mappingObject(i,cells);
                if(readResult.getValue()!=null){
                    datas.add((T)readResult.getValue());
                }else{
                    errors.add(readResult.getError());
                }
            }
            result.setAll(allNum);
            result.setError(errors.size());
            result.setSuccess(allNum-errors.size());
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException("解析excel错误！");
        }
        return result;
    }

    public static <T> File writeExcel(BaseForm form, Iterable<T> list, String name, WriteFieldMapping<T> fieldMapping) throws Exception {
        ExportParams params=form.getExportParams();
        List<ExportParams.Column> columns=params.getFullColumns();
        Workbook workbook = new Workbook();
        Worksheet worksheet = workbook.getWorksheets().get(0);
        Cells cells = worksheet.getCells();
        fieldMapping.handleHeader(cells,columns);
        AtomicInteger index= new AtomicInteger();
        list.forEach(item->{
            fieldMapping.mapping(cells,columns, index.getAndIncrement()+1,item);
        });
        for (int i = 0; i < worksheet.getCells().getMaxDataColumn() + 1; i++) {
            try{
                worksheet.autoFitColumn(i);
            }catch (Exception e){}
        }
        int format=SaveFormat.XLSX;
        if("csv".equals(params.getFormat())){
            format=SaveFormat.CSV;
        }
        File f=File.createTempFile(PrimaryKeyUtil.getUUID(),"."+format);
        workbook.save(f.getAbsolutePath(),format);
        return f;
    }

    public static <T> File writeExcel(BaseForm form, Iterable<T> list,String name) throws Exception {
        return writeExcel(form,list,name,new WriteDefaultWriteFieldMapping<>());
    }

    public static <T> File writeExcel(BaseForm form, Iterable<T> list) throws Exception {
        return writeExcel(form,list,"export.xlsx",new WriteDefaultWriteFieldMapping<>());
    }

}
