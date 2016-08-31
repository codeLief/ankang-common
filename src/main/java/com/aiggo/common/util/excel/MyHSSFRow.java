package com.aiggo.common.util.excel;

import org.apache.commons.lang.time.FastDateFormat;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;

import java.util.Date;

public class MyHSSFRow {

    private static final FastDateFormat dateFormat = FastDateFormat.getInstance("yyyy-MM-dd");

    private int currentCol = 0;
    private HSSFRow row;
    private MyHSSFWorkbook myWb;

    public MyHSSFRow(HSSFRow row, MyHSSFWorkbook myWb) {
        this.row = row;
        this.myWb = myWb;
    }

    public void createCell(String cellValue) {
        HSSFCell cell = row.createCell(currentCol);
        if (cellValue != null) {
            cell.setCellValue(cellValue);
        }
        currentCol++;
    }

    public void createCell(Double cellValue) {
        HSSFCell cell = row.createCell(currentCol);
        if (cellValue != null) {
            cell.setCellStyle(myWb.getMoneyCellStyle());
            cell.setCellValue(cellValue);
        }
        currentCol++;
    }

    public void createCell(Integer cellValue) {
        HSSFCell cell = row.createCell(currentCol);
        if (cellValue != null) {
            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
            cell.setCellValue(cellValue);
        }
        currentCol++;
    }

    public void createCell(Date cellValue) {
        HSSFCell cell = row.createCell(currentCol);
        String cellValueStr = null;
        if (cellValue != null) {
            cellValueStr = dateFormat.format(cellValue);
            cell.setCellValue(cellValueStr);
        }
        currentCol++;
    }


    public void createTitleCell(String cellValue) {

        HSSFCell cell = row.createCell(currentCol);
        cell.setCellStyle(myWb.getTitleStyle());
        cell.setCellValue(cellValue);
        currentCol++;
    }

}