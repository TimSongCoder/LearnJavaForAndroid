import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class InvoiceCalcAdvanced{
	public static void main(String[] args){
		BigDecimal invoiceSubtotal = new BigDecimal("285.36");
		BigDecimal discountPercent = new BigDecimal("0.10");
		BigDecimal discount = invoiceSubtotal.multiply(discountPercent);
		discount = discount.setScale(2, RoundingMode.HALF_UP); // This step is vital.
		
		BigDecimal subtotalBeforeTax = invoiceSubtotal.subtract(discount);
		subtotalBeforeTax = subtotalBeforeTax.setScale(2, RoundingMode.HALF_UP);
		
		BigDecimal salesTaxPercent = new BigDecimal("0.05");
		BigDecimal salesTax = subtotalBeforeTax.multiply(salesTaxPercent);
		salesTax = salesTax.setScale(2, RoundingMode.HALF_UP);
		
		BigDecimal invoiceTotal = subtotalBeforeTax.add(salesTax);
		invoiceTotal = invoiceTotal.setScale(2, RoundingMode.HALF_UP);
		
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
		System.out.println("Subtotal : " + currencyFormat.format(invoiceSubtotal) + " -- with number value : " + invoiceSubtotal);
		System.out.println("Discount : " + currencyFormat.format(discount) + " -- with number value : " + discount);
		System.out.println("Subtotal after discount : " + currencyFormat.format(subtotalBeforeTax) + " -- with number value : " + subtotalBeforeTax);
		System.out.println("Sales Tax : " + currencyFormat.format(salesTax) + " -- with number value : " + salesTax);
		System.out.println("Total : " + currencyFormat.format(invoiceTotal) + " -- with number value : " + invoiceTotal);
	}
}