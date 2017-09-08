package domainapp.modules.simple.dom.impl.location;

public enum State {
	ACT, NSW, NT, QLD, SA, TAS, VIC, WA;
	
	public String title(){
		return this.name();
	}
}
