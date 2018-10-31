package com.plk.sbdemo.admin.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author franplk 2016-01-11
 */
public class ExcelUtil {

	/**
	 * Export Excel
	 */
	public static <Data> Workbook exportExcel(Class<Data> clazz, List<Data> dataList, List<String> excludeFields)
			throws Exception {
		// 声明一个工作薄并生成一个表格
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();

		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth(15);
		sheet.setDefaultRowHeightInPoints(15);

		// 生成表头样式、内容样式
		HSSFCellStyle style_head = getcellStyle(workbook, 0);
		HSSFCellStyle style_row = getcellStyle(workbook, 1);

		// 获取类的属性
		Field[] fields = clazz.getDeclaredFields();
		int fieldSize = fields.length;

		// 产生表格标题行
		HSSFRow row_head = sheet.createRow(0);
		for (int i = 0; i < fieldSize; i++) {
			String fieldName = fields[i].getName();
			if (excludeFields.contains(fieldName)) {
				continue;
			}
			HSSFCell cell = row_head.createCell(i);
			setCellValue(cell, fieldName, style_head);
		}

		// 遍历集合数据，产生数据行
		int rowIndex = 1;
		for (Data data : dataList) {
			HSSFRow row_normal = sheet.createRow(rowIndex);
			for (int j = 0; j < fieldSize; j++) {
				String fieldName = fields[j].getName();
				if (excludeFields.contains(fieldName)) {
					continue;
				}
				HSSFCell cell = row_normal.createCell(j);
				String getter = "get" + StringUtils.toFirstUpperCase(fieldName);
				Method method = clazz.getMethod(getter);
				Object value = method.invoke(data);
				setCellValue(cell, value, style_row);
			}
			rowIndex++;
		}
		return workbook;
	}
	
	public static <Data> List<Data> readFromExcel(Class<Data> clazz) {
		return null;
	}

	/**
	 * Get Excel Style
	 */
	private static HSSFCellStyle getcellStyle(HSSFWorkbook workbook, int type) {
		HSSFCellStyle style = workbook.createCellStyle();
		if (type == 0) { // head
			HSSFFont font_head = workbook.createFont();
			font_head.setFontHeightInPoints((short) 12);
			style.setFont(font_head);
		} else if (type == 1) {// normal
			HSSFFont font_row = workbook.createFont();
			font_row.setFontHeightInPoints((short) 10);

			HSSFDataFormat format = workbook.createDataFormat();
			style.setDataFormat(format.getFormat("0.00"));

			// 把字体应用到当前的样式
			style.setFont(font_row);
		}
		return style;
	}

	/**
	 * Set Cell Value
	 */
	private static void setCellValue(HSSFCell cell, Object value, HSSFCellStyle style) {
		cell.setCellStyle(style);
		if (value == null) {
			value = "";
		}
		HSSFRichTextString text = new HSSFRichTextString(String.valueOf(value));
		cell.setCellValue(text);
	}
}