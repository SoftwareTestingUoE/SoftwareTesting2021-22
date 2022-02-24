package st;

class Option {
	private String name;
	private String value;
	Type type;

	
	public Option(String name, Type type) {
		super();
		this.name = name;
		this.value = "";
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object obj) {
		
		Option other = (Option) obj;
		boolean notEqual = obj == null || 
				getClass() != obj.getClass() ||
				(this.type != other.type) ||
				(name == null && other.name != null) || !name.equals(other.name);
		
		return this == obj || !notEqual;
	}
	
	@Override
	public String toString() {
		return "Option[name:" + this.name + ", value:" + this.value  + ", type:" + this.type + "]";
	}
      
}