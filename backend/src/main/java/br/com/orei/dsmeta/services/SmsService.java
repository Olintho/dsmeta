package br.com.orei.dsmeta.services;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import br.com.orei.dsmeta.entities.Sale;
import br.com.orei.dsmeta.repository.SaleRepository;

@Service
public class SmsService {
	
	@Value("${twilio.sid}")
	private String twilioSid;

	@Value("${twilio.key}")
	private String twilioKey;

	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;

	@Value("${twilio.phone.to}")
	private String twilioPhoneTo;
	
	@Autowired
	SaleRepository saleRepository;

	public void sendSms(Long saleId) {

		Sale sale = saleRepository.findById(saleId).get();
		String date = sale.getDate().getMonthValue() + "/" + sale.getDate().getYear();
		
		String msgSms = "O vendedor " + sale.getSellerName() 
						+ ", no período de " + date + " vendeu um total de R$ " 
						+ String.format("%.2f", sale.getAmount()) + ".";   
		
		 String msg = "O vendedor " + sale.getSellerName() + " foi destaque em " + date
			    + " com um total de R$ " + String.format("%.0f", sale.getAmount());
		
		String msg2 = "O vendedor " + sale.getSellerName() + " foi destaque em " + date
			    + " com um total de R$ " + new DecimalFormat("#,##0.00").format(sale.getAmount());
		
		String msg3 = String.format("O vendedor %s foi destaque em %s com um total de R$ %.2f", sale.getSellerName(), date, sale.getAmount());
		
		System.out.println(msgSms);
		
		Twilio.init(twilioSid, twilioKey);

		PhoneNumber to = new PhoneNumber(twilioPhoneTo);
		PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

		Message message = Message.creator(to, from, msgSms).create();

		System.out.println(message.getSid());
	}
}