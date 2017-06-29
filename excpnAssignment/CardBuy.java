package excpnAssignment;

//submitted by krishna kanth.


import java.time.YearMonth;
import java.util.Date;
import java.util.Scanner;

public class CardBuy {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		System.out.println("Enter the 16 Digit Card number");
		String card = s.nextLine();
		try {
			validate(card);
		} catch (CardInvalidException e) {
			System.out.println(e);
		}
		System.out.println("Enter the type of card: (American Express, Master card, Visa)");
		String cardType = s.nextLine();
		int CVV=0;
		if(cardType.equalsIgnoreCase("American express")){
			System.out.println("Enter 4 digit CVV");
			CVV=s.nextInt();
			
		}else{
			System.out.println("Enter 3 digit CVV");
			CVV=s.nextInt();
		}
		try{
			validatecvv(CVV, cardType);
		}catch(CardInvalidException e){System.out.println(e);}
		
		System.out.println("Enter the expiry date on your card in mm/yy format:");
		String expDate = s.next();
		int month = Integer.parseInt(expDate.substring(0, 2));
		int Year = 2000+Integer.parseInt(expDate.substring(3));
		YearMonth d = YearMonth.of(Year, month);
		//System.out.println(d);
		YearMonth ym = YearMonth.now();
		if(ym.compareTo(d)>0){
			try {
				throw new CardInvalidException(" The card is expired: ");
			} catch (CardInvalidException e) {
				System.out.println(e);
			}
		} else
			try {
				if((validatecvv(CVV,cardType))&(validate(card))){
					System.out.println("Hooray!!!!... We have finished processing your order.");
					System.out.println("You will receive your package in next 10 days.");
				}
			} catch (CardInvalidException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
		

	}

	private static boolean validatecvv(int CVV, String cardType)throws CardInvalidException {
		String cvv = Integer.toString(CVV);
		if(cardType.equalsIgnoreCase("American express")){
			if(cvv.length()==3)
				throw new CardInvalidException("For American express you need 4 digit CVV");
		}else if(cvv.length()!=3){
			throw new CardInvalidException("Your CVV should be 3 digits.");
		}else if(cardType.equalsIgnoreCase("discover")){
			throw new CardInvalidException("Sorry...We don't accept Discover cards");
		} return true;
		
	}

	public static boolean validate(String card) throws CardInvalidException {
		
		if(card.length()<16)
		{
			throw new CardInvalidException("Please enter 16 digits exactly");
		}
		String[] s=card.split("");
		for(int i=0;i<s.length;i++){
			try{
			Integer.parseInt(s[i]);}
			catch (NumberFormatException e){
				throw new CardInvalidException("You entered a character. Please Enter only numbers in your 16 Digit card number");
			}
		}

		return true;
	}
	
	

}
