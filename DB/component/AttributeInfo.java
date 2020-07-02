package component;

import java.util.Arrays;

public class AttributeInfo {

	// Attribute
	private String name, createInfo;
	
	// Constructor
	public AttributeInfo(String name, String typeName) {this.name=name; this.createInfo = name+" "+typeName;}
	public AttributeInfo(String name, String typeName, String...additionals) {this.name=name; this.createInfo = name+" "+typeName+" "+String.join(" ", additionals);}
	public AttributeInfo(String name, Class<? extends Enum<?>> e) {this.name=name; this.createInfo = name+" "+this.createEnumType(e);}// For Enumeration Type

	// Create Time Use
	private String createEnumType(Class<? extends Enum<?>> e) {
		String result = "enum(";
		for(String name : this.getEnumNames(e)) {result+=("'"+name+"', ");}
		result = result.substring(0, result.length() - 2); // 마지막 ", "를 제거
		return result+")";
	}
	private String[] getEnumNames(Class<? extends Enum<?>> e) {return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);}
	
	// Getter & Setter
	public String getCreateInfo() {return this.createInfo;}
	public String getName() {return this.name;}
	
	// Inner Class
	public static class Type { // 진짜 static class랑 method쓰기 싫은데 어쩔 수 없다.
		public static String Int() {return "int";}
		public static String Date() {return "date";}
		public static String Varchar(int length) {return "varchar("+length+")";}
		public static String Boolean() {return "boolean default false";}
		public static String Decimal(int m, int d) {return "DECIMAL("+m+", "+d+") NOT NULL";} // m = 소수점 앞 길이, d = 소수점 뒤 길이
	}
	public static class PKAdditional { // 진짜 static class랑 method쓰기 싫은데 어쩔 수 없다.
		public static String PrimaryKey() {return "auto_increment primary key";}
	}
}