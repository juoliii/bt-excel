package io.github.juoliii.excel.mapping;

import com.aspose.cells.Cell;
import com.aspose.cells.CellValueType;
import com.aspose.cells.Cells;
import com.bitian.common.exception.CustomException;
import com.bitian.common.util.NumberUtil;
import io.github.juoliii.excel.annotation.ImportExcel;
import io.github.juoliii.excel.dto.ReadObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author admin
 */
public class ReadClassMapping implements ReadColumnMapping {

    private Class clazz;

    private List<Field> list=new ArrayList<>();
    private List<String> titles=new ArrayList<>();

    public ReadClassMapping(Class tmp,Cells cells){
        this.clazz=tmp;
        Field fs[]=clazz.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            ImportExcel ie=fs[i].getDeclaredAnnotation(ImportExcel.class);
            if (ie != null) {
                fs[i].setAccessible(true);
                list.add(fs[i]);
            }
        }
        for (int i = 0; i <=cells.getMaxColumn(); i++) {
            titles.add(cells.get(0,i).getStringValue().trim());
        }
    }

    @Override
    public ReadObject mappingObject(int line, Cells cells) throws Exception {
        Object item=this.clazz.newInstance();
        StringBuffer sb=new StringBuffer();
        sb.append("第"+(line+1)+"行：");
        for (int j = 0; j < list.size(); j++) {
            Field f = list.get(j);
            ImportExcel ie = f.getDeclaredAnnotation(ImportExcel.class);
            int index = ie.index();
            if(index<0 && StringUtils.isNotBlank(ie.title())){
                index=titles.indexOf(ie.title().trim());
            }else if(index<0 && StringUtils.isBlank(ie.title())){
                //如果此字段找不到对应的excel所在的index，则忽略
                continue;
            }
            if(index==-1){
                continue;
            }
            Cell cell = cells.get(line, index);
            try {
                Object value = this.getValue(cell, f);
                f.set(item, value);
            } catch (CustomException iex){
                item=null;
                sb.append("单元格"+cell.getName()+"："+iex.getMessage()+"；");
                iex.printStackTrace();
            } catch (Exception ex){
                item=null;
                sb.append("单元格"+cell.getName()+"：数据解析异常；");
                ex.printStackTrace();
            }
        }
        return new ReadObject(item,sb.toString());
    }

    public static Object getValue(Cell cell,Field f) throws Exception{
        ImportExcel ie=f.getDeclaredAnnotation(ImportExcel.class);
        if (ie.notNull() && (cell.getType() == CellValueType.IS_NULL || StringUtils.isBlank(cell.getStringValue()))) {
            throw new CustomException(cell.getName()+"字段不能为空");
        }
        if (f.getType() == String.class) {
            return StringUtils.trim(cell.getStringValue());
        } else if (f.getType() == Integer.class) {
            if(cell.getType() == CellValueType.IS_NUMERIC){
                return cell.getIntValue();
            }else if(cell.getType() == CellValueType.IS_STRING){
                String val=StringUtils.defaultIfBlank(cell.getStringValue(),"");
                if(StringUtils.isBlank(val)){
                    return NumberUtil.isNumber(ie.defaultValueIfNull())?NumberUtil.parseInt(ie.defaultValueIfNull()):null;
                }else{
                    return NumberUtil.isNumber(val)?NumberUtil.parseInt(val):null;
                }
            }else{
                return null;
            }
        }else if (f.getType() == Short.class) {
            if(cell.getType() == CellValueType.IS_NUMERIC){
                return cell.getIntValue();
            }else if(cell.getType() == CellValueType.IS_STRING){
                String val=StringUtils.defaultIfBlank(cell.getStringValue(),"");
                if(StringUtils.isBlank(val)){
                    return NumberUtil.isNumber(ie.defaultValueIfNull())?NumberUtil.parseShort(ie.defaultValueIfNull()):null;
                }else{
                    return NumberUtil.isNumber(val)?NumberUtil.parseShort(val):null;
                }
            }else{
                return null;
            }
        }else if (f.getType() == Long.class) {
            if(cell.getType() == CellValueType.IS_NUMERIC || cell.getType() == CellValueType.IS_STRING){
                String val=StringUtils.defaultIfBlank(cell.getStringValue(),"");
                if(StringUtils.isBlank(val)){
                    return NumberUtil.isNumber(ie.defaultValueIfNull())?NumberUtil.parseLong(ie.defaultValueIfNull()):null;
                }else{
                    return NumberUtil.isNumber(val)?NumberUtil.parseLong(val):null;
                }
            }else{
                return null;
            }
        }else if (f.getType() == Float.class) {
            if(cell.getType() == CellValueType.IS_NUMERIC){
                return cell.getFloatValue();
            }else if(cell.getType() == CellValueType.IS_STRING){
                String val=StringUtils.defaultIfBlank(cell.getStringValue(),"");
                if(StringUtils.isBlank(val)){
                    return NumberUtil.isNumber(ie.defaultValueIfNull())?NumberUtil.parseFloat(ie.defaultValueIfNull()):null;
                }else{
                    return NumberUtil.isNumber(val)?NumberUtil.parseFloat(val):null;
                }
            }else{
                return null;
            }
        } else if (f.getType() == Double.class) {
            if(cell.getType() == CellValueType.IS_NUMERIC){
                return cell.getDoubleValue();
            }else if(cell.getType() == CellValueType.IS_STRING){
                String val=StringUtils.defaultIfBlank(cell.getStringValue(),"");
                if(StringUtils.isBlank(val)){
                    return NumberUtil.isNumber(ie.defaultValueIfNull())?NumberUtil.parseDouble(ie.defaultValueIfNull()):null;
                }else{
                    return NumberUtil.isNumber(val)?NumberUtil.parseDouble(val):null;
                }
            }else{
                return null;
            }
        }else if (f.getType() == Date.class) {
            if(cell.getType() == CellValueType.IS_DATE_TIME){
                return cell.getDateTimeValue().toLocalTime().toDate();
            }else if(cell.getType() == CellValueType.IS_STRING){
                return DateUtils.parseDate(cell.getStringValue(),ie.dateFormat());
            }else{
                throw new CustomException("解析格式错误");
            }
        } else if (f.getType() == Boolean.class) {
            return cell.getBoolValue();
        }else{
            throw new CustomException("无法解析单元格类型");
        }
    }

}
