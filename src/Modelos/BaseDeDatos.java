package Modelos;

public class BaseDeDatos {
	private String catalog_name;
	private String schema_name;
	private String default_character;
	private String default_collation;
	private String sql_path;
	
	public BaseDeDatos() {}

	public String getCatalog_name() {
		return catalog_name;
	}

	public void setCatalog_name(String catalog_name) {
		this.catalog_name = catalog_name;
	}

	public String getSchema_name() {
		return schema_name;
	}

	public void setSchema_name(String schema_name) {
		this.schema_name = schema_name;
	}

	public String getDefault_character() {
		return default_character;
	}

	public void setDefault_character(String default_character) {
		this.default_character = default_character;
	}

	public String getDefault_collation() {
		return default_collation;
	}

	public void setDefault_collation(String default_collation) {
		this.default_collation = default_collation;
	}

	public String getSql_path() {
		return sql_path;
	}

	public void setSql_path(String sql_path) {
		this.sql_path = sql_path;
	}
	
	
}
