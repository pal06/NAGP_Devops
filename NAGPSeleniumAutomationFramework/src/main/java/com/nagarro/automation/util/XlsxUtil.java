package com.nagarro.automation.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nagarro.automation.reporting.LoggerLoad;

public class XlsxUtil {

	static Logger logger = LoggerLoad.config("XlsxUtil");
	private String filepath = "\\src\\test\\resources\\TestData\\";
	private String workBook;
	private String sheetname;
	private XSSFSheet sheet;
	private XSSFRow row = null;
	private XSSFCell cell = null;
	private String path;

	public XlsxUtil(String workBook, String sheetname) {
		this.workBook = workBook;
		this.sheetname = sheetname;
		path = System.getProperty("user.dir") + filepath + workBook + ".xlsx";

		try {

			FileInputStream fis = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetname);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	// returns the data from a cell
	public String getCellData(String sheetName, int colNum, int rowNum) {
		try {

			FileInputStream fis = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			if (rowNum <= 0)
				return "";

			int index = workbook.getSheetIndex(sheetName);

			if (index == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum);
			if (row == null)
				return "";
			cell = row.getCell(colNum);
			if (cell == null)
				return "";

			if (cell.getCellType() == CellType.STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
				return String.valueOf(cell.getNumericCellValue());
			} else if (cell.getCellType() == CellType.BLANK) {
				return "";
			} else {
				return String.valueOf(cell.getBooleanCellValue());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
		}
	}

	// find whether sheets exists
	public boolean isSheetExist(String sheetName) {
		
		try {
			FileInputStream fis = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1) {
				index = workbook.getSheetIndex(sheetName.toUpperCase());
				return index != -1;
			} else {
				return true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	// returns number of columns in a sheet
	public int getColumnCount(String sheetName) {
		// check if sheet exists
		
		try {
			FileInputStream fis = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			if (!isSheetExist(sheetName))
				return -1;

			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);

			if (row == null)
				return -1;

			return row.getLastCellNum();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return -1;
		}
	}

	public int getRowCount(String sheetName) {
		// check if sheet exists
		try {
			FileInputStream fis = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			if (!isSheetExist(sheetName))
				return -1;

			sheet = workbook.getSheet(sheetName);
			int rowcount = sheet.getLastRowNum() - sheet.getFirstRowNum();

			if (rowcount == -1)
				return -1;

			return rowcount;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return -1;
		}

	}

	public void setCellData(String sheetName, int rowNum, int colNum, String data) {
		try {
			FileInputStream fis = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			FileOutputStream fileOut = new FileOutputStream(path);

			if (rowNum <= 0)
				return;

			int index = workbook.getSheetIndex(sheetName);

			if (index == -1)
				return;

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				row = sheet.createRow(rowNum - 1);
			cell = row.getCell(colNum);
			if (cell == null)
				cell = row.createCell(colNum);
			// cell style
			CellStyle cs = workbook.createCellStyle();
			cs.setWrapText(true);
			cell.setCellStyle(cs);
			cell.setCellValue(data);

			workbook.write(fileOut);
			fileOut.flush();
		} catch (Exception e) {
			logger.error(e.getMessage());

		}

	}

}
