package com.mitake.component;

import javax.validation.constraints.NotNull;

public class Bok508rq {
	//註解驗證
	@NotNull
	private CommonHeader rqheader;
	@NotNull
	private Bok508CRQBody bok508crqbody;
	
	public CommonHeader getRqheader() {
		return rqheader;
	}
	public void setRqheader(CommonHeader rqheader) {
		this.rqheader = rqheader;
	}
	public Bok508CRQBody getBok508crqbody() {
		return bok508crqbody;
	}
	public void setBok508crqbody(Bok508CRQBody bok508crqbody) {
		this.bok508crqbody = bok508crqbody;
	}
}
