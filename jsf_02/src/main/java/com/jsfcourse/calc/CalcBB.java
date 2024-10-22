package com.jsfcourse.calc;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
public class CalcBB {
	private Double amount = Double.valueOf(0);
	private Double time = Double.valueOf(0);
	private Double percentage = Double.valueOf(0);
	
	private Double result;

	@Inject
	FacesContext ctx;

	public boolean doTheMath() {
		try {			
			if(time < 0 || amount < 0 || percentage <0) {
				throw new Exception("Parametry muszą być dodatnie.");
			}
			if(time == 0) {
				throw new Exception("Czas nie może być zerowy.");
			}

			this.result = (double) Math.round((amount+amount*percentage/100)*100/time/12)/100;

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie.", ""));
			
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów.", e.getMessage()));
			return false;
		}
	}

	public String calc() {
		doTheMath();
		return null;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getTime() {
		return time;
	}

	public void setTime(Double time) {
		this.time = time;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	
	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}
}
