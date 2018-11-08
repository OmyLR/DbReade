package Modelos;

import java.util.Date;

public class Tabla {
	private String table_Catalog;
	private String table_Schema;
	private String table_name;
	private String table_type;
	private String engine;
	private int version;
	private String row_format;
	private int table_rows;
	private int avg_row_length;
	private int data_length;
	private int max_length;
	private int index_length;
	private int data_free;
	private int auto_increment;
	private String create_time;
	private String update_time;
	private String check_time;
	private String table_collation;
	private int checksum;
	private String create_options;
	private String table_comment;
	
	public Tabla() {}

	public String getTable_Catalog() {
		return table_Catalog;
	}

	public void setTable_Catalog(String table_Catalog) {
		this.table_Catalog = table_Catalog;
	}

	public String getTable_Schema() {
		return table_Schema;
	}

	public void setTable_Schema(String table_Schema) {
		this.table_Schema = table_Schema;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getTable_type() {
		return table_type;
	}

	public void setTable_type(String table_type) {
		this.table_type = table_type;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getRow_format() {
		return row_format;
	}

	public void setRow_format(String row_format) {
		this.row_format = row_format;
	}

	public int getTable_rows() {
		return table_rows;
	}

	public void setTable_rows(int table_rows) {
		this.table_rows = table_rows;
	}

	public int getAvg_row_length() {
		return avg_row_length;
	}

	public void setAvg_row_length(int avg_row_length) {
		this.avg_row_length = avg_row_length;
	}

	public int getData_length() {
		return data_length;
	}

	public void setData_length(int data_length) {
		this.data_length = data_length;
	}

	public int getMax_length() {
		return max_length;
	}

	public void setMax_length(int max_length) {
		this.max_length = max_length;
	}

	public int getIndex_length() {
		return index_length;
	}

	public void setIndex_length(int index_length) {
		this.index_length = index_length;
	}

	public int getData_free() {
		return data_free;
	}

	public void setData_free(int data_free) {
		this.data_free = data_free;
	}

	public int getAuto_increment() {
		return auto_increment;
	}

	public void setAuto_increment(int auto_increment) {
		this.auto_increment = auto_increment;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = convertDate(create_time);
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = convertDate(update_time);
	}

	public String getCheck_time() {
		return check_time;
	}

	public void setCheck_time(Date check_time) {
		this.check_time = convertDate(check_time);
	}

	public String getTable_collation() {
		return table_collation;
	}

	public void setTable_collation(String table_collation) {
		this.table_collation = table_collation;
	}

	public int getChecksum() {
		return checksum;
	}

	public void setChecksum(int checksum) {
		this.checksum = checksum;
	}

	public String getCreate_options() {
		return create_options;
	}

	public void setCreate_options(String create_options) {
		this.create_options = create_options;
	}

	public String getTable_comment() {
		return table_comment;
	}

	public void setTable_comment(String table_comment) {
		this.table_comment = table_comment;
	}
	
	private String convertDate(Date date) {
		return ""+date.getDate()+"/"+(date.getMonth()+1)+"/"+date.getYear();
	}
}
