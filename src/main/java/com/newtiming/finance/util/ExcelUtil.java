package com.newtiming.finance.util;

import java.io.File;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtil {
    private final static String excel2003L =".xls";    //2003- 版本的excel  
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel  
    

	/**
	 * 描述：根据文件路径获取项目中的文件
	 * 
	 * @param fileDir文件路径
	 * @return
	 * @throws Exception
	 */
	public File getExcelDemoFile(String filePath) throws Exception {
		String fileBaseDir = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
		File file = new File(fileBaseDir + filePath);
		if (!file.exists()) {
			throw new Exception("模板文件不存在！");
		}
		return file;
	}

	public XSSFWorkbook writeNewExcel(String templatePath, String[] columns, List<Map<String,Object>> list)
			throws Exception {
		InputStream in = ExcelUtil.class.getClassLoader().getResourceAsStream(templatePath);
		if (in == null) {
			throw new Exception("模板文件不存在！");
		}
		
		XSSFWorkbook wb = new XSSFWorkbook(in);
		Sheet sheet = wb.getSheetAt(0);

		// 循环插入数据
		int lastRow = sheet.getLastRowNum() + 1; // 插入数据的数据ROW
		CellStyle cs = setSimpleCellStyle(wb); // Excel单元格样式
		Row row = null;
		Cell cell = null;
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(lastRow + i); //创建新ROW
			for(int j=0; j<columns.length; j++){
				cell = row.createCell(j);
				cell.setCellValue(list.get(i).get(columns[j]).toString());
				cell.setCellStyle(cs);
			}
		}
		return wb;
	}

	/**
	 * 描述：设置简单的Cell样式
	 * @return
	 */
	public CellStyle setSimpleCellStyle(Workbook wb) {
		CellStyle cs = wb.createCellStyle();
		cs.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
		cs.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
		cs.setBorderTop(CellStyle.BORDER_THIN);// 上边框
		cs.setBorderRight(CellStyle.BORDER_THIN);// 右边框
		cs.setAlignment(CellStyle.ALIGN_CENTER); // 居中
		return cs;
	}

	/**
	 * 描述：根据文件后缀，自适应上传文件的版本
	 * @param inStr
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
		Workbook wb = null;
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		if (excel2003L.equals(fileType)) {
			wb = new HSSFWorkbook(inStr); // 2003-
		} else if (excel2007U.equals(fileType)) {
			wb = new XSSFWorkbook(inStr); // 2007+
		} else {
			throw new Exception("解析的文件格式有误！");
		}
		return wb;
	}
    
	public List<List<String>> getSheetData(Sheet sheet, int rowStart){
		Row row = null;
		Cell cell = null;
		int firstRow = sheet.getFirstRowNum()+rowStart-1;
		int lastRow = sheet.getLastRowNum()+rowStart-1;
		List<List<String>> list = new ArrayList<List<String>>();
		// 遍历当前sheet中行
		for (int i = firstRow; i < lastRow; i++) {
			row = sheet.getRow(i);
			if (row == null || row.getFirstCellNum() == i) {
				continue;
			}

			// 遍历所有的列
			List<String> li = new ArrayList<String>();
			for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				li.add(cell.getStringCellValue());
			}
			list.add(li);
		}
		return list;
	}
	
	/**
	 * 
	 * @param sheet 页码
	 * @param rowStart 第几行开始
	 * @param titles 字段名的数组
	 * @param columnStart 第几列开始
	 * @return
	 */
	public List<Map<String,String>> getSheetData(Sheet sheet, int rowStart, String[] titles, int columnStart){
		Row row = null;
		Cell cell = null;
		int firstRow = sheet.getFirstRowNum()+rowStart-1;
		int lastRow = sheet.getLastRowNum()+rowStart-1;
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		// 遍历当前sheet中行
		for (int i = firstRow; i < lastRow; i++) {
			row = sheet.getRow(i);
			if (row == null || row.getFirstCellNum() == i) {
				continue;
			}

			Map<String,String> data = new HashMap<String,String>();
			for(int j=0; j<titles.length; j++){
				cell = row.getCell(j+columnStart-1);
				data.put(titles[j], cell.getStringCellValue());
			}
			list.add(data);
		}
		return list;
	}
	
	/**
	 * 描述：对表格中数值进行格式化
	 * 
	 * @param cell
	 * @return
	 */
	public Object getCellValue(Cell cell) {
		Object value = null;
		DecimalFormat df = new DecimalFormat("0"); // 格式化number String字符
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // 日期格式化
		DecimalFormat df2 = new DecimalFormat("0.00"); // 格式化数字

		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			value = cell.getRichStringCellValue().getString();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if ("General".equals(cell.getCellStyle().getDataFormatString())) {
				value = df.format(cell.getNumericCellValue());
			} else if ("m/d/yy".equals(cell.getCellStyle()
					.getDataFormatString())) {
				value = sdf.format(cell.getDateCellValue());
			} else {
				value = df2.format(cell.getNumericCellValue());
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_BLANK:
			value = "";
			break;
		default:
			break;
		}
		return value;
	}
}
