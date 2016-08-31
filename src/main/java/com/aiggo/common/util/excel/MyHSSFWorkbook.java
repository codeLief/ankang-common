package com.aiggo.common.util.excel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

public class MyHSSFWorkbook {

    private HSSFWorkbook wb;
    private HSSFCellStyle moneyCellStyle;
    private CellStyle titleStyle;

    public MyHSSFWorkbook(HSSFWorkbook wb) {
        this.wb = wb;
        HSSFDataFormat format = wb.createDataFormat();
        moneyCellStyle = wb.createCellStyle();
        moneyCellStyle.setDataFormat(format.getFormat("0.00"));
        HSSFCellStyle ztStyle = wb.createCellStyle();
        Font ztFont = wb.createFont();
        ztFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        // ztFont.setItalic(true); // 设置字体为斜体字
        // ztFont.setColor(Font.COLOR_RED); // 将字体设置为“红色”
        ztFont.setFontHeightInPoints((short) 10); // 将字体大小设置为18px
        ztFont.setFontName("华文行楷"); // 将“华文行楷”字体应用到当前单元格上
        // ztFont.setUnderline(Font.U_DOUBLE);
        ztStyle.setFont(ztFont);
        titleStyle = ExcelUtils.createBorderCellStyle(wb, HSSFColor.YELLOW.index, CellStyle.ALIGN_CENTER,
                ztFont);
    }

    public HSSFWorkbook getWb() {
        return wb;
    }

    public void setWb(HSSFWorkbook wb) {
        this.wb = wb;
    }

    public HSSFCellStyle getMoneyCellStyle() {
        return moneyCellStyle;
    }

    public void setMoneyCellStyle(HSSFCellStyle moneyCellStyle) {
        this.moneyCellStyle = moneyCellStyle;
    }

    public CellStyle getTitleStyle() {
        return titleStyle;
    }

    public void setTitleStyle(CellStyle titleStyle) {
        this.titleStyle = titleStyle;
    }
}
