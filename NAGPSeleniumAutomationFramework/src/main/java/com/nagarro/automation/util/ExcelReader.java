package com.nagarro.automation.util;

public class ExcelReader {
	
	String Sheetname;
	XlsxUtil excel;
	public ExcelReader(String Sheet)
	{
		this.Sheetname=Sheet;
		
	}
	
	public int getRowCount()
	{
		excel = new XlsxUtil("TestData",Sheetname);
		return excel.getRowCount(Sheetname);
	}
	
	public String getCellData(int col, int row)
	{
		excel = new XlsxUtil("TestData",Sheetname);
		String cellvalue=excel.getCellData(Sheetname, col, row);
		return cellvalue;
		
	}
	
	public int getColCount()
	{
		excel = new XlsxUtil("TestData",Sheetname);
		return excel.getColumnCount(Sheetname);
	}
	

}
