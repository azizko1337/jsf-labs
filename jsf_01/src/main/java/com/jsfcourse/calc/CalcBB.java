package com.jsfcourse.calc;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
//import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class CalcBB {
	private String amount;
	private String time;
	private String percentage;
	
	private Double result;

	@Inject
	FacesContext ctx;

	public boolean doTheMath() {
		try {
			double amount = Double.parseDouble(this.amount);
			double time = Double.parseDouble(this.time);
			double percentage = Double.parseDouble(this.percentage);
			
			if(time <= 0 || amount < 0 || percentage <0) {
				throw new Exception("Parameters must be positive.");
			}

			this.result = (double) Math.round((amount+amount*percentage/100)*100/time/12)/100;

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Miesięczna rata: " + result + " zł", null));
			
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return false;
		}
	}

	public String calc() {
		doTheMath();
		return null;
	}
	public String calc_AJAX() {
		doTheMath();
		return null;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	
	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}
}
