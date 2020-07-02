package component;

import java.util.Vector;

public class TableInfo {

	// Attribute
	private String name;
	
	// Component
	private Vector<AttributeInfo> attributeInfos;
	
	// Constructor
	public TableInfo(String name) {
		// Set Attribute
		this.name=name;
		
		// Create Component
		this.attributeInfos = new Vector<AttributeInfo>();
	}

	// Any Time Use
	public void addAttributeInfo(AttributeInfo info) {this.attributeInfos.add(info);}
	public void addAttributeInfo(AttributeInfo...infos) {for(AttributeInfo info : infos) {this.addAttributeInfo(info);}}
	public String getCreateInfo() {
		String result = "create table "+this.name+" (";
		for(AttributeInfo info : this.attributeInfos) {result+=(info.getCreateInfo()+", ");}
		result = result.substring(0, result.length() - 2); // 마지막 ", "를 제거
		return result+");";
	}
	
	// Getter & Setter
	public String getName() {return this.name;}
}
