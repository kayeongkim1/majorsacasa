package es.uji.ei1027.majorsacasa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.uji.ei1027.majorsacasa.model.Pay;

@Repository
public class PayDAO {
	private JdbcTemplate jdbcTemplate;

	@Autowired 
	public void setDataSource(DataSource dataSource){
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void addPay(Pay pay){
		jdbcTemplate.update("INSERT INTO PAY VALUES(?,?)",
								pay.getRequest_number(), 
								pay.getInvoice_number()
								);
	}
	
	public void deletePay(Pay pay){
		jdbcTemplate.update("DELETE FROM PAY WHERE REQUEST_NUMBER = ?, INVOICE_NUMBER = ?",
								pay.getRequest_number(),
								pay.getInvoice_number()
								);
	}
	
//	public void updatePay(Pay pay){
//		jdbcTemplate.update("UPDATE PAY SET REQUEST_NUMBER = ?, INVOICE_NUMBER = ? WHERE REQUEST_NUMBER = ?",
//							pay.getRequest_number(),
//							pay.getInvoice_number()
//							);
//	}
	
	public Pay getPay(int request_number){
		try{
			return jdbcTemplate.queryForObject("SELECT * FROM PAY WHERE REQUEST_NUMBER = ?", new PayRowMapper(), request_number);
		}catch (EmptyResultDataAccessException e){
			return null;
		}
	}
	
	public List<Pay> getpays(){
		try{
			return jdbcTemplate.query("SELECT * FROM PAY", new PayRowMapper());
		}catch (EmptyResultDataAccessException e){
			return new ArrayList<Pay>();
		}
	}
	
}
