package com.example;

import java.util.Collection;

import javax.annotation.PostConstruct;

import com.example.domain.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.Account;
import com.example.repository.AccountRepository;
import com.example.repository.TransferRepository;
import com.example.repository.service.TransferService;

@Controller
@SpringBootApplication
public class TransferApplication {

	@Autowired
	TransferService transferService;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
    TransferRepository transferRepository;
	
	@PostConstruct
	void init() {
		Account c1 = new Account();
		c1.setNumero("001");
		c1.setSaldo(1000d);
		Account c2 = new Account();
		c2.setNumero("002");
		c2.setSaldo(100d);
        Account c3 = new Account();
        c3.setNumero("003");
        c3.setSaldo(50d);
		accountRepository.save(c1);
		accountRepository.save(c2);
        accountRepository.save(c3);
	}

	@RequestMapping("/transferir")
	public String transferir(@RequestParam String origen, @RequestParam String destino,
			@RequestParam Double monto) throws Exception {
		transferService.transfer(origen, destino, monto);
		return "Transferencia completa!";
	}

	@RequestMapping("/cuentas")
	@ResponseBody
	Collection<Account> listarCuentas() {
		return accountRepository.findAll();
	}

	@RequestMapping("/transferencias")
	@ResponseBody
	Collection<Transfer> listartransferencias() {
		return transferRepository.findAll();
	}

	public static void main(String[] args) {
		SpringApplication.run(TransferApplication.class, args);
	}
}
