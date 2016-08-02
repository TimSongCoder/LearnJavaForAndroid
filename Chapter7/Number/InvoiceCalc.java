import java.text.NumberFormat;

public class InvoiceCalc{
	final static double DISCOUNT_PERCENT = 0.1;
	final static double TAX_PERCENT = 0.05;
	
	public static void main(String[] args){
		double invoiceSubtotal = 285.36;
		double discount = invoiceSubtotal * DISCOUNT_PERCENT;
		double subtotalBeforeTax = invoiceSubtotal - discount;
		double salesTax = subtotalBeforeTax * TAX_PERCENT;
		double invoiceTotal = subtotalBeforeTax + salesTax;
		
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
		
		System.out.println("Subtotal : " + currencyFormat.format(invoiceSubtotal) + " -- with number value : " + invoiceSubtotal);
		System.out.println("Discount : " + currencyFormat.format(discount) + " -- with number value : " + discount);
		System.out.println("Subtotal after discount : " + currencyFormat.format(subtotalBeforeTax) + " -- with number value : " + subtotalBeforeTax);
		System.out.println("Sales Tax : " + currencyFormat.format(salesTax) + " -- with number value : " + salesTax);
		System.out.println("Total : " + currencyFormat.format(invoiceTotal) + " -- with number value : " + invoiceTotal);
	}
}