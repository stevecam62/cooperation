package au.org.cooperation.modules.base.dom.impl.location;

public enum State {
	ACT, NSW, NT, QLD, SA, TAS, VIC, WA;
	
	public String title(){
		return this.name();
	}
}
