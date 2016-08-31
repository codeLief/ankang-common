package com.aiggo.common.util.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.aiggo.common.util.DateUtil;

/**
 * 
 * @Description: 文件导出
 * @author: qd-ankang
 * @date: 2016-2-29 上午11:08:15 excep
 */
public abstract class ExportExcel<T> {

	protected HSSFWorkbook workbook;

	/**
	 * 
	 * @param excelName
	 *            输出文件名称
	 * @param titles
	 *            excel标题
	 * @param rowNum
	 *            输出数据的总行数
	 */
	public ExportExcel(String excelName, String[] titles, List<T> data) {

		HSSFWorkbook workbook = new HSSFWorkbook();

		HSSFSheet sheet = workbook.createSheet(excelName);
		HSSFRow titleRow = sheet.createRow(0);
		MyHSSFWorkbook myWorkBook = new MyHSSFWorkbook(workbook);
		MyHSSFRow myTitleRow = new MyHSSFRow(titleRow, myWorkBook);

		for (String title : titles) {
			myTitleRow.createTitleCell(title);
		}

		if (data != null && data.size() > 0) {

			int row = 1;
			
			for (T t : data) {

				HSSFRow detailRow = sheet.createRow(row);
				MyHSSFRow myRow = new MyHSSFRow(detailRow, myWorkBook);

				buildExcelDocument(myRow, t);

				row++;
			}
		}
		for (int i = 0; i < titles.length; i++) {
			sheet.autoSizeColumn(i);
		}
		this.workbook = workbook;
	}

	/**
	 * 
	 * @param response
	 *            输出流
	 * @param excelName
	 *            输出文件名称
	 * @param titles
	 *            excel标题
	 * @param rowNum
	 *            输出数据的总行数
	 */
	public ExportExcel(HttpServletResponse response, String excelName,
			String[] titles, List<T> data) {

		this(excelName, titles, data);

		writeExcel(response, workbook, buildFileName(excelName));

	}

	public abstract void buildExcelDocument(MyHSSFRow myRow, T t);

	public HSSFWorkbook getWorkbook() {
		return workbook;
	}

	private String buildFileName(String excelName) {

		
		try {
			return new String((excelName + "_" + DateUtil.formatDatetime(
					new Date(), DateUtil.DATE_MINUTE_FORMAT + "_")).getBytes(),
					"iso8859-1" );
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return excelName;
	}

	private void writeExcel(HttpServletResponse response,
			HSSFWorkbook workbook, String filename) {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ filename + ".xls");
		OutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			workbook.write(outputStream);
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public File writeToFile(HSSFWorkbook workbook, String excelName) {
		
		FileOutputStream outputStream = null;
		File tempFile = null;
		try {
			tempFile = File.createTempFile(buildFileName(excelName), ".xls");
			tempFile.deleteOnExit();
			outputStream = new FileOutputStream(tempFile);
			workbook.write(outputStream);
			outputStream.flush();
			return tempFile;
		} catch (IOException e) {
			e.printStackTrace();
			return tempFile;
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
